package day22

import java.io.File

fun main() {
    val data: List<String> = File("src/main/kotlin/day22/Day22Input.txt").readText()
        .split("\n")

    val day22 = Day22(data)

    println("part 1 result = ${day22.solvePart1()}")
    println("part 2 result = ${day22.solvePart2()}")
}

class Day22(input: List<String>) {

    private var secretNumbers: List<Long> = input.map { it.toLong() }

    fun solvePart1(): Long {
        return secretNumbers.take(2000).reduce { first, sec -> first + sec }
    }


    fun solvePart2(): Int {
        return buildMap {
            secretNumbers
                .map { it.secretNumbers().take(2001).map { i -> (i % 10).toInt() }.toList() }
                .forEach { sequence ->
                    sequence
                        .windowed(5, 1)
                        .map { it.zipWithNext { first, second -> second - first } to it.last() }
                        .distinctBy { it.first }
                        .forEach { (key, value) ->
                            this[key] = (this[key] ?: 0) + value
                        }
                }
        }.maxOf { it.value }
    }

    private fun Long.secretNumbers(): Sequence<Long> =
        generateSequence(this) { secret ->
            secret
                .mixAndPrune { it shl 6 }
                .mixAndPrune { it shr 5 }
                .mixAndPrune { it shl 11 }
        }

    private fun Long.mixAndPrune(function: (Long) -> Long): Long =
        (this xor function(this)) % 16777216L


}