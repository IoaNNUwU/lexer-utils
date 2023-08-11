package github.ioannuwu.lexerutils

interface Pattern {
    val leftToken: String
    val rightToken: String?
    val includeStatus: IncludeStatus

    fun isRange(): Boolean = rightToken != null
}