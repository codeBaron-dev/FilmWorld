package com.codebaron.filmworld

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val amount = 200
        val rangesMap = mapOf(
            "250.00" to 0.5,
            "1000.00" to 25.0,
            "5000.00" to 50.0
        ).toList()
        println(rangesMap[0].first.toDouble())
        println(rangesMap[1].first)

        when(amount in 0..rangesMap[0].first.toDouble().toInt()) {
            true -> {
                println("Your charge rate is ${rangesMap[0].second}")
            }
            false -> {
                println("failed")
            }
        }
    }
}