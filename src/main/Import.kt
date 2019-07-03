import java.io.File

class Maze(fileName: String) {

    val domain: MutableSet<Char> = mutableSetOf()
    val nodes = importMaze(fileName)

    /*
        Pass filename of puzzle. Returns parsed puzzle.
     */
    fun importMaze(fileName: String): MutableList<MutableList<Node>> {
        val startTime = System.currentTimeMillis()
        val input = File(fileName).readLines()
        var maze: MutableList<MutableList<Node>> = mutableListOf()

        for (i in 0 until input.size) {
            maze.add(mutableListOf())
            for (j in 0 until input[i].length) {
//            creates a node for each character in the input
                maze[i].add(Node(i, j, input[i][j]))
                domain.add(input[i][j])
            }
            domain.distinct()
        }
        domain.remove('_')

        for (i in 0 until maze.size) {
            for (j in 0 until maze[i].size) {
//            if a node is on the border that neighbor is set to to type '?'
                maze[i][j].west = maze.getOrElse(i) { mutableListOf() }.getOrElse(j - 1) { Node() }
                maze[i][j].east = maze.getOrElse(i) { mutableListOf() }.getOrElse(j + 1) { Node() }
                maze[i][j].south = maze.getOrElse(i + 1) { mutableListOf() }.getOrElse(j) { Node() }
                maze[i][j].north = maze.getOrElse(i - 1) { mutableListOf() }.getOrElse(j) { Node() }
            }
        }
        println("\nimported \"$fileName\": ${System.currentTimeMillis() - startTime}ms")
        return maze
    }

}


