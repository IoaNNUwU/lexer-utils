package github.ioannuwu.lexerutils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SplitterTest {

    @Test
    fun `empty result test`() {

        val splitter = SplitterImpl(
            Token(' ', IncludeStatus.EXCLUDE),
            Token(')', IncludeStatus.EXCLUDE),
            Token('(', IncludeStatus.EXCLUDE),
        )

        val string = "  (())) "

        val result = splitter.split(string)

        Assertions.assertEquals(
            emptyList<String>(),
            result
        )
    }

    @Test
    fun `splitter with excluding pattern test`() {

        val splitter = SplitterImpl(
            Token(' ', IncludeStatus.EXCLUDE),
        )

        val string = " hello  world  "

        val result = splitter.split(string)

        Assertions.assertEquals(
            listOf("hello", "world"),
            result
        )
    }

    @Test
    fun `splitter with including pattern test`() {

        val splitter = SplitterImpl(
            Token('(', IncludeStatus.INCLUDE),
            Token(')', IncludeStatus.INCLUDE),
        )

        val string = "(hello)world"

        val result = splitter.split(string)

        Assertions.assertEquals(
            listOf("(", "hello", ")", "world"),
            result
        )
    }

    @Test
    fun `splitter with mixed pattern test`() {

        val splitter = SplitterImpl(
            Token(' ', IncludeStatus.EXCLUDE),
            Token(',', IncludeStatus.EXCLUDE),

            Token('(', IncludeStatus.INCLUDE),
            Token(')', IncludeStatus.INCLUDE),
            Token(';', IncludeStatus.INCLUDE),
        )

        val string = "  (hel,lo wor(ld) tes;t )  "

        val result = splitter.split(string)

        Assertions.assertEquals(
            listOf("(", "hel", "lo", "wor", "(", "ld", ")", "tes", ";", "t", ")"),
            result
        )
    }

    @Test
    fun `extension functions with pattern test`() {

        val string = "  (hel,lo wor(ld) tes;t )  "

        val pattern = arrayOf(
            Token(' ', IncludeStatus.EXCLUDE),
            Token(',', IncludeStatus.EXCLUDE),

            Token('(', IncludeStatus.INCLUDE),
            Token(')', IncludeStatus.INCLUDE),
            Token(';', IncludeStatus.INCLUDE),
        )

        val expectedResult = listOf("(", "hel", "lo", "wor", "(", "ld", ")", "tes", ";", "t", ")")

        val result1 = string.asSequence().splitWith(*pattern)
        val result2 = string.splitWith(*pattern)
        val result3 = (string as CharSequence).splitWith(*pattern)

        Assertions.assertEquals(expectedResult, result1)
        Assertions.assertEquals(expectedResult, result2)
        Assertions.assertEquals(expectedResult, result3)
    }

    @Test
    fun `extension functions with splitter test`() {

        val string = "  (hel,lo wor(ld) tes;t )  "

        val splitter = SplitterImpl(
            Token(' ', IncludeStatus.EXCLUDE),
            Token(',', IncludeStatus.EXCLUDE),

            Token('(', IncludeStatus.INCLUDE),
            Token(')', IncludeStatus.INCLUDE),
            Token(';', IncludeStatus.INCLUDE),
        )

        val expectedResult = listOf("(", "hel", "lo", "wor", "(", "ld", ")", "tes", ";", "t", ")")

        val result1 = string.asSequence().splitWith(splitter)
        val result2 = string.splitWith(splitter)
        val result3 = (string as CharSequence).splitWith(splitter)

        Assertions.assertEquals(expectedResult, result1)
        Assertions.assertEquals(expectedResult, result2)
        Assertions.assertEquals(expectedResult, result3)
    }
}