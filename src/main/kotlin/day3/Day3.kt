package day3

import java.io.File

private val mulRexeg = """mul\(([0-9]{1,3}),([0-9]{1,3})\)""".toRegex()

fun main() {
    val data = File("src/main/kotlin/day3/Day3Input.txt").readText()

    val firstAnswer: Int = getSum(data)
    println("Part 1 answer: $firstAnswer")

    val secondAnswer = data.split("do()")
        .map { it.substringBefore("don't()") }
        .sumOf { getSum(it) }

    println("Part 2 answer: $secondAnswer")
}

private fun getSum(data: String): Int = mulRexeg
    .findAll(data).sumOf { match ->
        val (x, y) = match.destructured
        x.toInt() * y.toInt()
    }
