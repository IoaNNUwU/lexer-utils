package github.ioannuwu.lexerutils

fun String.splitWith(
    vararg patterns: Token
): List<String> {
    val string = this

    val splitter = SplitterImpl(*patterns)

    return splitter.split(string)
}

fun CharSequence.splitWith(
    vararg patterns: Token
) : List<String> {
    val charSequence = this

    val splitter = SplitterImpl(*patterns)

    return splitter.split(charSequence)
}

fun Sequence<Char>.splitWith(
    vararg patterns: Token
): List<String> {
    val charSequence = this

    val splitter = SplitterImpl(*patterns)

    return splitter.split(charSequence)
}

fun String.splitWith(
    splitter: Splitter
): List<String> {
    val string = this

    return splitter.split(string)
}

fun CharSequence.splitWith(
    splitter: Splitter
) : List<String> {
    val charSequence = this

    return splitter.split(charSequence)
}

fun Sequence<Char>.splitWith(
    splitter: Splitter
): List<String> {
    val charSequence = this

    return splitter.split(charSequence)
}