package github.ioannuwu.lexerutils

data class Range(
    override val leftToken: String,
    override val rightToken: String,
    override val includeStatus: IncludeStatus = IncludeStatus.INCLUDE,
) : Pattern {

    constructor(leftToken: Char, rightToken: Char, includeStatus: IncludeStatus = IncludeStatus.INCLUDE)
            : this(leftToken.toString(), rightToken.toString(), includeStatus)
}