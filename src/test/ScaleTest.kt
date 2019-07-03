 fun main() {
        renderMaze(Maze("src/res/puzzles/5x5maze.txt").nodes)
            .scale(100, 100)
            .save()
    }
