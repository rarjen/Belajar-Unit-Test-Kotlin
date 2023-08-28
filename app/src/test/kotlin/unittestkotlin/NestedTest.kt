package unittestkotlin

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo

class NestedTest {

    @Test
    fun test1() {
    }

    @Nested
    inner class MyNestedTest {

        @Test
        fun testNested() {

        }
    }
}