package github.ioannuwu.lexerutils

data class Token(
    val token: String,
    val includeStatus: IncludeStatus = IncludeStatus.INCLUDE,
) {
    constructor(char: Char, includeStatus: IncludeStatus = IncludeStatus.INCLUDE) : this(char.toString(), includeStatus)
}