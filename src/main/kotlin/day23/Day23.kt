package day23

import java.io.File

fun main() {
    val data: List<List<Char>> = File("src/main/kotlin/day22/Day23Input.txt").readText()
        .split("\n")
        .map { it.map { it.toChar() } }

    val day6 = Day22(data)

    println("part 1 result = ${day6.solvePart1()}")
    println("part 2 result = ${day6.solvePart2()}")
}

class Day22(input: List<List<Char>>) {
    fun solvePart1(): Int {
        return 0
    }

    fun solvePart2(): Int {
        return 0
    }

}