package com.example.earthquakereport

import org.junit.Test

import org.junit.Assert.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun isDateCorrect() {
        val mydate = Date()
        val date = SimpleDateFormat("yyyy-MM-dd").format(mydate)
        assertEquals("2020-05-09", date)
    }
}
