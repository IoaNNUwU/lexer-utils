package github.ioannuwu.lexerutils

val INCLUDE_STRINGS: Pattern = Range('"', '"', IncludeStatus.INCLUDE)

val EXCLUDE_WHITESPACES: Pattern = Token(' ', IncludeStatus.EXCLUDE)