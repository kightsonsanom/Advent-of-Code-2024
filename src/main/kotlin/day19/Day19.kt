package day19

import java.io.File

fun main() {
    var data = File("src/main/kotlin/day19/Day19Input.txt").readText().split("\n")
    val day19 = Day19(data)

    day19.part1()
    day19.part2()
}

class Day19(input: List<String>) {
    private val towelsAvailable = input[0].split(", ")
    private val towelsRequired = input.drop(2)

    fun part1() {
        val result = towelsRequired.count { isPieceAvailable(it) > 1 }
        println("part 1 result = $result")
    }

    fun part2() {
        val result = towelsRequired.sumOf { isPieceAvailable(it) }
        println("part 2 result = $result")
    }

    fun isPieceAvailable(
        requiredTowelPart: String,
        cache: MutableMap<String, Long> = mutableMapOf<String, Long>(),
    ): Long {
        return if (requiredTowelPart.isEmpty()) 1
        else cache.getOrPut(requiredTowelPart) {
            val potentialTowels = towelsAvailable.filter { requiredTowelPart.startsWith(it) }
            potentialTowels.sumOf { isPieceAvailable(requiredTowelPart.removePrefix(it), cache) }
        }
    }
}