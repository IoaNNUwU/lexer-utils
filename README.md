# LexerUtils
Kotlin library that provides easy way to split strings and char sequences to tokens

## Examples:

```kotlin
val splitter = SplitterImpl(
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
val result = "  hello ( world test11  ) ".splitWithSplitter(
        Token(' ', IncludeStatus.EXCLUDE),
        Token('(', IncludeStatus.INCLUDE),
        Token(')', IncludeStatus.INCLUDE),
)

Assertions.assertEquals(     
    listOf("hello", "(", "world", "test11", ")"),
    result
)
```