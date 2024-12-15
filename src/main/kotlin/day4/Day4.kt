package day4

import java.io.File


fun main() {

    val data: List<String> = File("src/main/kotlin/day4/Day4Input.txt").readText().split("\n")

    val vertical: List<String> = data.first().indices.map { index ->
        data.map { it[index] }.joinToString(separator = "")
    }

    val firstDiagonal = transformMatrixDiagonally(data)
    val secondDiagonal = transformMatrixDiagonally2(data)

    var count = 0

    val xmasRexes = "XMAS".toRegex()
    val samxRexes = "SAMX".toRegex()

    vertical.forEach { count += xmasRexes.findAll(it).count() }
    vertical.forEach { count += samxRexes.findAll(it).count() }

    data.forEach { count += xmasRexes.findAll(it).count() }
    data.forEach { count += samxRexes.findAll(it).count() }

    firstDiagonal.forEach { count += xmasRexes.findAll(it).count() }
    firstDiagonal.forEach { count += samxRexes.findAll(it).count() }

    secondDiagonal.forEach { count += xmasRexes.findAll(it).count() }
    secondDiagonal.forEach { count += samxRexes.findAll(it).count() }

    println("result = $count")

}

fun transformMatrixDiagonally(matrix: List<String>): List<String> {
    val result = mutableListOf<String>()

    // Iterate over diagonals starting from bottom-left corner
    for (i in matrix.size - 1 downTo 0) {
        var row = i
        var col = 0
        val diagonal = StringBuilder()
        while (row >= 0 && col < matrix[0].length) {
            diagonal.append(matrix[row][col])
            row--
            col++
        }
        result.add(diagonal.toString())
    }

    // Iterate over diagonals starting from second-to-last row, first column
    for (i in 1 until matrix[0].length) {
        var row = matrix.size - 1
        var col = i
        val diagonal = StringBuilder()
        while (row >= 0 && col < matrix[0].length) {
            diagonal.append(matrix[row][col])
            row--
            col++
        }
        result.add(diagonal.toString())
    }

    return result
}

fun transformMatrixDiagonally2(matrix: List<String>): List<String> {
    val result = mutableListOf<String>()

    // Iterate over diagonals starting from top-right corner
    for (i in 0 until matrix[0].length) {
        var row = 0
        var col = i
        val diagonal = StringBuilder()
        while (col < matrix.size) {
            diagonal.append(matrix[row][col])
            row++
            col++
        }
        result.add(diagonal.toString())
    }

    // Iterate over diagonals starting from second row, last column
    for (i in 1 until matrix.size) {
        var row = i
        var col = 0
        val diagonal = StringBuilder()
        while (row < matrix.size) {
            diagonal.append(matrix[row][col])
            row++
            col++
        }
        result.add(diagonal.toString())
    }

    return result
}