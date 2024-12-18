package day5

import java.io.File

fun main() {
    val data: List<String> = File("src/main/kotlin/day5/Day5Input.txt").readText().split("\n\n")

    val rules = data[0].split("\n").toSet()
    val pages = data[1].split("\n").map { it.split(",") }

    val rulesComparator = Comparator<String> { a, b ->
        when {
            "$a|$b" in rules -> -1
            "$b|$a" in rules -> 1
            else -> 0
        }
    }

    val sortedPages = pages.map { it.sortedWith(rulesComparator) }

    val result1 = sortedPages
        .filterIndexed { index, sortedPage -> sortedPage == pages[index] }
        .sumOf { it[it.size / 2].toInt() }

    println("part 1 result: $result1")

    val result2 = sortedPages
        .filterIndexed { index, sortedPage -> sortedPage != pages[index] }
        .sumOf { it[it.size / 2].toInt() }

    println("part 2 result: $result2")
}
