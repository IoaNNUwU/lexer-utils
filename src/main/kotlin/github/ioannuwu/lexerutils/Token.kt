package github.ioannuwu.lexerutils

/**
 * Class used to describe tokens in Splitter
 *
 * @param token text fragment or character that will be divided
 * from remaining string (e.g. " ", ")", "(")
 *
 * @param includeStatus will token be excluded in or included in final list
 *
 * @see Splitter
 * @see Range
 */
data class Token(
    private val token: String,
    override val includeStatus: IncludeStatus = IncludeStatus.INCLUDE,
) : Pattern {

    constructor(char: Char, includeStatus: IncludeStatus = IncludeStatus.INCLUDE)
            : this(char.toString(), includeStatus)

    override val leftToken: String
        get() = token

    override val rightToken: String?
        get() = null
}