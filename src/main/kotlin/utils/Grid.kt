package utils

enum class Direction(val x: Int, val y: Int) {
    North(0, -1), NorthWest(-1, -1), NorthEast(1, -1), West(-1, 0), East(1, 0), South(0, 1), SouthWest(
        -1,
        1
    ),
    SouthEast(1, 1),
}

data class Point(
    val x: Int,
    val y: Int,
    var value: Char,
)

class Grid(
    matrix: List<List<Char>>,
) {
    private var points: MutableList<Point> = mutableListOf()

    init {
        points = matrix.flatMapIndexed { rowIndex, rowValue ->
            rowValue.mapIndexed { index, value ->
                Point(index, rowIndex, value)
            }
        }.toMutableList()
    }

    operator fun set(at: Point, c: Char) {
        points.firstOrNull { it == at }?.value = c
    }

    operator fun get(at: Point): Point? {
        return points.firstOrNull { it.x == at.x && it.y == at.y }
    }

    fun forEach(call: (Point) -> Unit) {
        return points.forEach(call)
    }

    fun exploreDirection(
        startingPosition: Point,
        direction: Direction,
    ): Point? {
        return points.firstOrNull { point ->
            point.x == startingPosition.x + direction.x && point.y == startingPosition.y + direction.y
        }
    }

    fun contains(point: Point) = points.contains(point)

    fun findValue(char: Char): Point? {
        return points.firstOrNull { it.value == char }
    }

    fun turnRight90(currentDirection: Direction): Direction = when (currentDirection) {
        Direction.North,
        Direction.NorthEast,
        Direction.NorthWest,
            -> Direction.East

        Direction.West -> Direction.North
        Direction.East -> Direction.South
        Direction.South,
        Direction.SouthEast,
        Direction.SouthWest,
            -> Direction.West

    }

}
