package day2

import java.io.File


fun main() {
    // Read data from file
    val data = File("src/main/kotlin/day2/Day2Input.txt").readLines().map { line ->
        line.split(" ").map { it.toInt() }
    }

    // Part 1: Check if a report is safe
    fun safe(report: List<Int>): Boolean {
        val gaps = report.zipWithNext { a, b -> a - b }
        val decreasingRange = (gaps.maxOrNull() ?: 0) <= 3 && (gaps.minOrNull() ?: 0) >= 1
        val increasingRange = (gaps.maxOrNull() ?: 0) <= -1 && (gaps.minOrNull() ?: 0) >= -3

        return decreasingRange || increasingRange
    }

    // Part 1: Count safe reports
    val part1SafeCount = data.count { safe(it) }
    println("Part 1 - number safe: $part1SafeCount")

    // Part 2: Check if a report is safe when dampened
    fun safeDampened(report: List<Int>): Boolean {
        return report.indices.any { i ->
            val dampened = report.filterIndexed { index, _ -> index != i }
            safe(dampened)
        }
    }

    // Part 2: Count safe dampened reports
    val part2SafeCount = data.count { safeDampened(it) }
    println("Part 2 - number safe (dampened): $part2SafeCount")
}