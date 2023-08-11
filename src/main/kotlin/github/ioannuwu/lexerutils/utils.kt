package github.ioannuwu.lexerutils

/**
 * fun *.splitWithSplitter() can be used for optimal performance
 */
fun String.splitWithPattern(
    vararg patterns: Pattern
): List<String> {
    val string = this

    val splitter = Splitter.withPattern(*patterns)

    return splitter.split(string)
}

/**
 * fun *.splitWithSplitter() can be used for optimal performance
 */
fun CharSequence.splitWithPattern(
    vararg patterns: Pattern
) : List<String> {
    val charSequence = this

    val splitter = Splitter.withPattern(*patterns)

    return splitter.split(charSequence)
}

/**
 * fun *.splitWithSplitter() can be used for optimal performance
 */
fun Sequence<Char>.splitWithPattern(
    vararg patterns: Pattern
): List<String> {
    val charSequence = this

    val splitter = Splitter.withPattern(*patterns)

    return splitter.split(charSequence)
}

/**
 * fun *.splitWithSplitter() can be used for optimal performance
 */
fun CharArray.splitWithPattern(
    vararg patterns: Pattern
): List<String> {
    val charArray = this

    val splitter = Splitter.withPattern(*patterns)

    return splitter.split(charArray)
}

/**
 * fun *.splitWithSplitter() can be used for optimal performance
 */
fun Array<Char>.splitWithPattern(
    vararg patterns: Pattern
): List<String> {
    val charArray = this

    val splitter = Splitter.withPattern(*patterns)

    return splitter.split(charArray)
}

fun String.splitWithSplitter(
    splitter: Splitter
): List<String> {
    val string = this

    return splitter.split(string)
}

fun CharSequence.splitWithSplitter(
    splitter: Splitter
) : List<String> {
    val charSequence = this

    return splitter.split(charSequence)
}

fun Sequence<Char>.splitWithSplitter(
    splitter: Splitter
): List<String> {
    val charSequence = this

    return splitter.split(charSequence)
}

fun CharArray.splitWithSplitter(
    splitter: Splitter
): List<String> {
    val charArray = this

    return splitter.split(charArray)
}

fun Array<Char>.splitWithSplitter(
    splitter: Splitter
): List<String> {
    val charArray = this

    return splitter.split(charArray)
}