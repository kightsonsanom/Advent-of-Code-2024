package day11

import java.io.File

const val blinking = 75

fun main() {
    var data: MutableList<Long> =
        File("src/main/kotlin/day11/Day11Input.txt").readText().split(" ").map { it.toString().toLong() }
            .toMutableList()

    var newList = mutableListOf<Long>()
    repeat(blinking) {
        if (data.isEmpty()) {
            data = newList.map { function(it) }.flatten().toMutableList()
            newList.clear()
        } else {
            newList = data.map { function(it) }.flatten().toMutableList()
            data.clear()
        }
    }

    print("result data ${data.size}")
    print("result newList ${newList.size}")
}

private fun function(number: Long): MutableList<Long> {
    if (number == 0L) {
        return mutableListOf(1)
    } else if (number.toString().length % 2 == 0) {
        val stringNumber = number.toString()
        val firstNumber = stringNumber.substring(0..(stringNumber.length / 2) - 1)
        val secondNumber = stringNumber.substring(((stringNumber.length / 2))..stringNumber.length - 1)
        return mutableListOf(firstNumber, secondNumber)
            .map { if (it.all { it == '0' }) 0 else it.toLong() }
            .toMutableList()
    } else {
        return mutableListOf(number * 2024)
    }
}