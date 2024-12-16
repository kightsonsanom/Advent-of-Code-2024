package utils

enum class Direction(val x: Int, val y: Int) {
    North(0, -1),
    NorthWest(-1, -1),
    NorthEast(1, -1),
    West(-1, 0),
    East(1, 0),
    South(0, 1),
    SouthWest(-1, 1),
    SouthEast(1, 1),
}

data class Point(
    val x: Int,
    val y: Int,
    val value: Char,
)

class Grid(
    matrix: List<List<Char>>,
) {
    private var points: List<Point> = emptyList()

    init {
        points = matrix.flatMapIndexed { rowIndex, rowValue ->
            rowValue.mapIndexed { index, value ->
                Point(index, rowIndex, value)
            }
        }
    }

    fun forEach(call: (Point) -> Unit) {
        return points.forEach(call)
    }

    fun exploreDirection(
        startingPosition: Point,
        direction: Direction,
    ): Point? {
        return points.firstOrNull { point ->
            point.x == startingPosition.x + direction.x &&
                    point.y == startingPosition.y + direction.y
        }
    }
}
