package day6

import utils.Direction
import utils.Grid
import utils.Point
import java.io.File

fun main() {
    val data: List<List<Char>> = File("src/main/kotlin/day6/Day6Input.txt").readText()
        .split("\n")
        .map { it.map { it.toChar() } }

    val day6 = Day6(data)

    println("part 1 result = ${day6.solvePart1()}")
    println("part 2 result = ${day6.solvePart2()}")
}

class Day6(input: List<List<Char>>) {
    private val grid = Grid(input)
    private var start = grid.findValue('^')

    fun solvePart1(): Int {
        return traverse().first.size
    }

    fun solvePart2(): Int {
        return traverse()
            .first
            .filterNot { it == start }
            .count { candidate ->
                grid[candidate] = '#'
                traverse().also { grid[candidate] = '.' }.second
            }
    }

    private fun traverse(): Pair<Set<Point>, Boolean> {
        var currentPosition = start
        var currentDirection = Direction.North
        val exploredPoints: MutableSet<Pair<Point, Direction>> = mutableSetOf()

        while (currentPosition != null && (currentPosition to currentDirection) !in exploredPoints) {
            exploredPoints += currentPosition to currentDirection
            var nextPosition = grid.exploreDirection(currentPosition, currentDirection)

            if (nextPosition?.value == '#') {
                currentDirection = grid.turnRight90(currentDirection)
            } else {
                currentPosition = nextPosition
            }
        }
        return exploredPoints.map { it.first }.toSet() to (currentPosition != null)
    }
}