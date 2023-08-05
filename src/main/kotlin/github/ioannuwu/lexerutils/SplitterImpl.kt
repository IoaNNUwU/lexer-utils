package github.ioannuwu.lexerutils

class SplitterImpl(
    private vararg val patterns: Token
) : Splitter {

    override fun split(string: String): List<String> {

        var currentList: List<String> = listOf(string)

        for (pattern in patterns) {

            val nextList: MutableList<String> = mutableListOf()

            for (currentString in currentList) {

                val splitResultList = currentString.split(pattern.token)

                if (splitResultList.size == 1) {
                    nextList.add(currentString)
                } else {
                    if (pattern.includeStatus == IncludeStatus.EXCLUDE) {
                        nextList.addAll(splitResultList)
                    } else {
                        splitResultList.forEachIndexed { index, it ->
                            nextList.add(it)
                            if (index == splitResultList.lastIndex) return@forEachIndexed
                            nextList.add(pattern.token)
                        }
                    }
                }
            }
            currentList = nextList
        }

        return currentList.filter { it.isNotEmpty() }
    }

    override fun split(charSequence: CharSequence): List<String> = split(charSequence.toString())

    override fun split(charSequence: Sequence<Char>): List<String> = split(charSequence.joinToString(""))
}