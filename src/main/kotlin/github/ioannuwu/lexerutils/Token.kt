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
 */
data class Token(
    val token: String,
    val includeStatus: IncludeStatus = IncludeStatus.INCLUDE,
) {
    constructor(char: Char, includeStatus: IncludeStatus = IncludeStatus.INCLUDE)
            : this(char.toString(), includeStatus)
}