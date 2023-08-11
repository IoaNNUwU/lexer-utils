package github.ioannuwu.lexerutils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RangeSplitterTest {

    @Test
    fun `splitter should be able to identify strings`() {

        val splitter = Splitter.withPattern(
            Range('"', '"', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
        )

        val program = """ "Hello World" test """

        val result = splitter.split(program)

        Assertions.assertEquals(
            listOf("\"Hello World\"", "test"),
            result
        )
    }

    @Test
    fun `splitter should be able to identify different types of strings`() {

        val splitter = Splitter.withPattern(
            Range('"', '"', IncludeStatus.INCLUDE),
            Range('\'', '\'', IncludeStatus.INCLUDE),
            Range('`', '`', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
        )

        val program = """ test0 "Hell`o W`orld" test1 'Hello World' test2 `Hello World` test3"""

        val result = splitter.split(program)

        Assertions.assertEquals(
            listOf("test0", "\"Hell`o W`orld\"", "test1", "'Hello World'", "test2", "`Hello World`", "test3"),
            result
        )
    }

    @Test
    fun `splitter should be able to identify many strings`() {

        val splitter = Splitter.withPattern(
            Range('"', '"', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
        )

        val program = """ "Hello World" test "Test2" test3 "Test4" """

        val result = splitter.split(program)

        Assertions.assertEquals(
            listOf("\"Hello World\"", "test", "\"Test2\"", "test3", "\"Test4\""),
            result
        )
    }

    @Test
    fun `splitter should be able to identify lists`() {

        val splitter = Splitter.withPattern(
            Range('(', ')', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
        )

        val program = """ (Hello World) test """

        val result = splitter.split(program)

        Assertions.assertEquals(
            listOf("(Hello World)", "test"),
            result
        )
    }

    @Test
    fun `splitter should be able to identify complex lists`() {

        val splitter = Splitter.withPattern(
            Range('(', ')', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
        )

        val program = """ ((Hello World) test1) (test2 test3) (test4)"""

        val result = splitter.split(program)

        Assertions.assertEquals(
            listOf("((Hello World) test1)", "(test2 test3)", "(test4)"),
            result
        )
    }

    @Test
    fun `extension functions with range test`() {

        val splitter = Splitter.withPattern(
            Range('(', ')', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
        )

        val program = """ ((Hello World) test1) (test2 test3) (test4)"""

        val result = program.splitWithSplitter(splitter)

        Assertions.assertEquals(
            listOf("((Hello World) test1)", "(test2 test3)", "(test4)"),
            result
        )
    }

    @Test
    fun `extension functions with pattern test`() {

        val string = "  hello wor(ld) tes(;t)  "

        val pattern = arrayOf(
            Range('(', ')', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
            Token(';', IncludeStatus.INCLUDE),
        )

        val expectedResult = listOf("hello", "wor", "(ld)", "tes", "(;t)")

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

        val string = "  hello wor(ld) tes(;t)  "

        val splitter = Splitter.withPattern(
            Range('(', ')', IncludeStatus.INCLUDE),
            Token(' ', IncludeStatus.EXCLUDE),
            Token(';', IncludeStatus.INCLUDE),
        )


        val expectedResult = listOf("hello", "wor", "(ld)", "tes", "(;t)")

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