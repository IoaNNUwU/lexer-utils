# Lexer Utils

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
val string = "  hello (world test11) "

// Split string using extension function
// using excluding pattern to exclude whitespaces
// and including pattern to include '(', ')'
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

```kotlin
val string = """ "Hello World" test "Test2" test3 "Test4" """

// Identify strings by using Range
// and remove whitespaces using excluding pattern
val splitter = Splitter.withPattern(
    Range('"', '"', IncludeStatus.INCLUDE),
    Token(' ', IncludeStatus.EXCLUDE),
)

val result = splitter.split(string)

Assertions.assertEquals(
    listOf("\"Hello World\"", "test", "\"Test2\"", "test3", "\"Test4\""),
    result
)
```

## You can add git dependency on this repo:

By copy-pasting this to your `settings.gradle.kts` which will convert repo to local module with
specified identifier

```kotlin
sourceControl {
    gitRepository(URI.create("https://github.com/IoaNNUwU/lexer-utils.git/")) {
        producesModule("github.ioannuwu:lexer-utils")
    }
}
```

And by adding dependency on produced module in your `build.gradle.kts`
with specific version (as now `1.1.0`)

versions can be found in GitHub Releases
```kotlin
dependencies {
    implementation("github.ioannuwu:lexer-utils:1.1.0")
}
```
To import this library
```kotlin
import github.ioannuwu.lexerutils.*
```

Try to compile code by executing `./gradlew build` and restart IDE if something doesn't work
