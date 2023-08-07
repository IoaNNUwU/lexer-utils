package github.ioannuwu.lexerutils

import github.ioannuwu.lexerutils.internal.SplitterImpl

/**
 * Main interface used to split text by pattern represented by tokens.
 *
 * @see Splitter.withPattern
 * @see Token
 */
interface Splitter {

    fun split(string: String): List<String>

    fun split(charSequence: CharSequence): List<String>

    fun split(charSequence: Sequence<Char>): List<String>

    fun split(charArray: CharArray): List<String>

    fun split(charArray: Array<Char>): List<String>

    companion object {
        fun withPattern(vararg patterns: Token) = SplitterImpl(*patterns) as Splitter
    }
}