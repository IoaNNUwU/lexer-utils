package github.ioannuwu.lexerutils

interface Splitter {

    fun split(string: String): List<String>

    fun split(charSequence: CharSequence): List<String>

    fun split(charSequence: Sequence<Char>): List<String>
}