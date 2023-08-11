package github.ioannuwu.lexerutils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SplitterTest {

    @Test
    fun `empty result test`() {

        val splitter = Splitter.withPattern(
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
    fun `splitter with including whitespaces test`() {

        val splitter = Splitter.withPattern(
            Token(' ', IncludeStatus.INCLUDE),
            Token(')', IncludeStatus.INCLUDE),
            Token('(', IncludeStatus.INCLUDE),
        )

        val string = "  (hello world ) "

        val result = splitter.split(string)

        Assertions.assertEquals(
            listOf(" ", " ", "(", "hello", " ", "world", " ", ")", " "),
            result,
        )
    }

    @Test
    fun `splitter with excluding pattern test`() {

        val splitter = Splitter.withPattern(
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

        val splitter = Splitter.withPattern(
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

        val splitter = Splitter.withPattern(
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
    fun `splitter methods should produce same result`() {

        val splitter = Splitter.withPattern(
            Token(' ', IncludeStatus.EXCLUDE),
            Token(',', IncludeStatus.EXCLUDE),

            Token('(', IncludeStatus.INCLUDE),
            Token(')', IncludeStatus.INCLUDE),
            Token(';', IncludeStatus.INCLUDE),
        )

        val string = "  (hel,lo wor(ld) tes;t )  "
        val expectedResult = listOf("(", "hel", "lo", "wor", "(", "ld", ")", "tes", ";", "t", ")")

        val resultString = splitter.split(string)
        val resultCharSequence = splitter.split(string as CharSequence)
        val resultSequenceOfChars = splitter.split(string.asSequence())
        val resultCharArray = splitter.split(string.toCharArray())
        val resultArrayOfChars = splitter.split(string.toCharArray().toTypedArray())

        Assertions.assertEquals(expectedResult, resultString)
        Assertions.assertEquals(expectedResult, resultCharSequence)
        Assertions.assertEquals(expectedResult, resultSequenceOfChars)
        Assertions.assertEquals(expectedResult, resultCharArray)
        Assertions.assertEquals(expectedResult, resultArrayOfChars)
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

        val result1 = string.asSequence().splitWithPattern(*pattern)
        val result2 = string.splitWithPattern(*pattern)
        val result3 = (string as CharSequence).splitWithPattern(*pattern)
        val result4 = string.toCharArray().splitWithPattern(*pattern)
        val result5 = string.toCharArray().toTypedArray().splitWithPattern(*pattern)

        Assertions.assertEquals(expectedResult, result1)
        Assertions.assertEquals(expectedResult, result2)
        Assertions.assertEquals(expectedResult, result3)
        Assertions.assertEquals(expectedResult, result4)
        Assertions.assertEquals(expectedResult, result5)
    }

    @Test
    fun `extension functions with splitter test`() {

        val string = "  (hel,lo wor(ld) tes;t )  "

        val splitter = Splitter.withPattern(
            Token(' ', IncludeStatus.EXCLUDE),
            Token(',', IncludeStatus.EXCLUDE),

            Token('(', IncludeStatus.INCLUDE),
            Token(')', IncludeStatus.INCLUDE),
            Token(';', IncludeStatus.INCLUDE),
        )

        val expectedResult = listOf("(", "hel", "lo", "wor", "(", "ld", ")", "tes", ";", "t", ")")

        val result1 = string.asSequence().splitWithSplitter(splitter)
        val result2 = string.splitWithSplitter(splitter)
        val result3 = (string as CharSequence).splitWithSplitter(splitter)
        val result4 = string.toCharArray().splitWithSplitter(splitter)
        val result5 = string.toCharArray().toTypedArray().splitWithSplitter(splitter)

        Assertions.assertEquals(expectedResult, result1)
        Assertions.assertEquals(expectedResult, result2)
        Assertions.assertEquals(expectedResult, result3)
        Assertions.assertEquals(expectedResult, result4)
        Assertions.assertEquals(expectedResult, result5)
    }
}