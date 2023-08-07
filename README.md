# LexerUtils

Kotlin library that provides easy way to split `Strings` to `List of Tokens` by
specified pattern.

Methods defined for:
* `String`
* `CharSequence`
* `Sequence<Char>`
* `CharArray`
* `Array<Char>`

## Examples:

```kotlin
val splitter = Splitter.withPattern(
    Token(' ', IncludeStatus.EXCLUDE),
    Token('(', IncludeStatus.INCLUDE),
    Token(')', IncludeStatus.INCLUDE),
)

val string = "  hello ( world test11  ) "

val result = splitter.split(string)

Assertions.assertEquals(
    listOf("hello", "(", "world", "test11", ")"),
    result
)
```

```kotlin
val string = "  hello ( world test11  ) "

val result = string.asSequence()
    .splitWithPattern(
        Token(' ', IncludeStatus.EXCLUDE),
        Token('(', IncludeStatus.INCLUDE),
        Token(')', IncludeStatus.INCLUDE),
    )

Assertions.assertEquals(     
    listOf("hello", "(", "world", "test11", ")"),
    result
)
```