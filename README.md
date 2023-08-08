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

### You can add git dependency on this repo:

By copy-pasting this to your `settings.gradle.kts` which will convert repo to local module with
specified identifier

```kotlin
sourceControl {
    gitRepository(URI.create("https://github.com/IoaNNUwU/lexer-utils.git/")) {
        producesModule("com.github.ioannuwu:lexer-utils")
    }
}
```

And by adding dependency on produced module in your `build.gradle.kts`
with specific version (as now `1.0.3`)

versions can be found in GitHub Releases
```kotlin
dependencies {
    implementation("com.github.ioannuwu:lexer-utils:1.0.3")
}
```

Try to compile code by executing `./gradlew build` and restart IDE if something doesn't work