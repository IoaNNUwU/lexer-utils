package github.ioannuwu.lexerutils.internal

import github.ioannuwu.lexerutils.IncludeStatus
import github.ioannuwu.lexerutils.Pattern
import github.ioannuwu.lexerutils.Splitter

internal class SplitterImpl(
    private vararg val patterns: Pattern
) : Splitter {

    override fun split(string: String): List<String> {

        var currentList: List<SToken> = listOf(SToken(string, isFinished = false))

        for (pattern in patterns) {

            if (!pattern.isRange()) {

                val nextList: MutableList<SToken> = mutableListOf()

                for ((currentString, isFinished) in currentList) {

                    if (isFinished) {
                        nextList.add(SToken(currentString, isFinished = true))
                        continue
                    }

                    val splitResultList = currentString.split(pattern.leftToken)
                        .map { SToken(it, isFinished = false) }

                    if (splitResultList.size == 1) {
                        nextList.add(SToken(currentString, isFinished = false))
                    } else {
                        if (pattern.includeStatus == IncludeStatus.EXCLUDE) {
                            nextList.addAll(splitResultList)
                        } else {
                            splitResultList.forEachIndexed { index, it ->
                                nextList.add(it)
                                if (index == splitResultList.lastIndex) return@forEachIndexed
                                nextList.add(SToken(pattern.leftToken, isFinished = false))
                            }
                        }
                    }
                }
                currentList = nextList
            } else {

                val rightToken: String = pattern.rightToken!!
                val leftToken: String = pattern.leftToken

                val nextList: MutableList<SToken> = mutableListOf()

                for ((currentString, isFinished) in currentList) {

                    if (isFinished) {
                        nextList.add(SToken(currentString, isFinished = true))
                        continue
                    }

                    var startIndex = 0
                    var finishIndex = 0

                    val indexes = mutableListOf<Pair<Int, Int>>()

                    while (true) {
                        startIndex = currentString.indexOf(leftToken, finishIndex + 1)

                        if (startIndex == -1) break

                        finishIndex = currentString.indexOfClosingTokenStacked(leftToken, rightToken, startIndex + 1)

                        if (finishIndex == -1) throw IllegalArgumentException(
                            "Unable to split: $leftToken has no corresponding $rightToken"
                        )

                        indexes.add(Pair(startIndex, finishIndex))
                    }

                    if (indexes.isEmpty()) {
                        nextList.add(SToken(currentString, isFinished = false))
                        continue
                    }

                    when (indexes.size) {
                        1 -> {
                            val (first, last) = indexes[0]

                            val beforeString = currentString.substring(0 until first)
                            val middleString = currentString.substring(first..last)
                            val afterString = currentString.substring(last + 1..currentString.lastIndex)

                            nextList.add(SToken(beforeString, isFinished = false))
                            nextList.add(SToken(middleString, isFinished = true))
                            nextList.add(SToken(afterString, isFinished = false))
                        }

                        else -> {
                            val firstOccur = indexes[0].first

                            val beforeString = currentString.substring(0 until firstOccur)

                            nextList.add(SToken(beforeString, isFinished = false))

                            for (i in 0 until indexes.lastIndex) {

                                val (firstIndex, lastIndex) = indexes[i]
                                val nextStart =
                                    (indexes.getOrElse(i + 1) { Pair(indexes.lastIndex, indexes.lastIndex) }).first

                                val stringItself = currentString.substring(firstIndex..lastIndex)
                                val afterCurrentString = currentString.substring(lastIndex + 1 until nextStart)

                                nextList.add(SToken(stringItself, isFinished = true))
                                nextList.add(SToken(afterCurrentString, isFinished = false))
                            }

                            val stringItself = currentString.substring(indexes.last().first..indexes.last().second)
                            val afterString =
                                currentString.substring(indexes.last().second + 1..currentString.lastIndex)

                            nextList.add(SToken(stringItself, isFinished = true))
                            nextList.add(SToken(afterString, isFinished = false))
                        }
                    }
                }
                currentList = nextList
            }
        }

        return currentList.asSequence()
            .map { it.string }
            .filter { it.isNotEmpty() }
            .toList()
    }

    override fun split(charSequence: CharSequence): List<String> = split(charSequence.toString())

    override fun split(charSequence: Sequence<Char>): List<String> = split(charSequence.joinToString(""))

    override fun split(charArray: CharArray): List<String> =
        split(charArray.asSequence())

    override fun split(charArray: Array<Char>): List<String> =
        split(charArray.asSequence())
}

private data class SToken(
    val string: String,
    val isFinished: Boolean,
)

private fun String.indexOfClosingTokenStacked(openingToken: String, closingToken: String, startingIndex: Int): Int {
    val string = this

    if (openingToken == closingToken) {
        return string.indexOf(openingToken, startingIndex)
    }

    var counter = 1

    for (i in startingIndex..string.lastIndex) {

        // if there is opening token on that index
        if (string.substring(i, i + openingToken.length) == openingToken) {
            counter++
        }
        // if there is closing token on that index
        if (string.substring(i, i + closingToken.length) == closingToken) {
            counter--
        }

        if (counter == 0) return i
    }

    return -1
}