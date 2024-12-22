package day9

import java.io.File


fun main() {
    val data: List<Int> = File("src/main/kotlin/day9/Day11Input.txt").readText().map { it.toString().toInt() }

    val newData: MutableList<String> = data.flatMapIndexed { index, value ->
        if (index % 2 == 0) {
            List(value, { index / 2 })
        } else {
            List(value, { "." })
        }
    }.map { it.toString() }.toMutableList()

    for (i in newData.size - 1 downTo 0) {
        if (newData[i] == ".") {
            continue
        } else {
            val firstDotIndex = newData.indexOfFirst { it == "." }
            newData[firstDotIndex] = newData[i].toString()
            newData[i] = "."
        }
    }

    val sum: Long =
        newData.filter { it != "." }.mapIndexed { index, value -> index * value.toInt() }.sumOf { it.toLong() }

    print("dupa $sum")


}