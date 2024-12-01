package day1

import firstList
import secondList
import kotlin.math.absoluteValue

fun main() {
    firstPart()
    secondPart()
}

fun firstPart() {
    var answer = 0
    val firstSorted = firstList.sorted()
    val secondSorted = secondList.sorted()
    for (i in firstSorted.indices) {
        answer += (firstSorted[i] - secondSorted[i]).absoluteValue
    }

    println("first part answer: $answer")
}

fun secondPart() {
    var answer = 0
    val secondAsMap: Map<Int, Int> = secondList.groupingBy { it }.eachCount()
    for (number in firstList) {
        if (secondAsMap.contains(number)) {
            answer += number * secondAsMap[number]!!
        }
    }

    println("second part answer: $answer")
}
