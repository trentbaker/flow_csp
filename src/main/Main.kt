import java.io.File
import kotlin.system.measureTimeMillis

//PUZZLE_PATH can be either a file or a directory
var PUZZLE_PATH = "src/res/puzzles"
var OUTPUT_PATH = "src/res/images/"


fun main(args: Array<String>) {
    var backtrack : BacktrackAlg = SmartBacktrack()
    if (args.count() > 0){
        PUZZLE_PATH = args[0]
    }
    if(args.count() > 1){
        OUTPUT_PATH = args[1]
    }
    if (args.count() > 2){
        if (args[2].toLowerCase().equals("true")){
            backtrack = BasicBacktrack()
        }
    }

    val startTime = System.currentTimeMillis()
    File(PUZZLE_PATH).walk().forEach { file ->
        //        only try to import text files
        if (file.path.endsWith(".txt")) {
            backtrack.assignments = 0
            var currentMaze = Maze(file.path).nodes
//            render input state
            renderMaze(currentMaze,
                    "$OUTPUT_PATH${file.name.removeSuffix("maze.txt")}_input.png")
//            find solution
            val time = measureTimeMillis {
                backtrack.search(currentMaze)
            }
            println("solved in ${time}ms")

            println("... using ${backtrack.assignments} assignments")
//            render solution
            renderMaze(currentMaze,
                    "$OUTPUT_PATH${file.name.removeSuffix("maze.txt")}_output.png")
        }
    }
    println("Solved $PUZZLE_PATH in ${System.currentTimeMillis() - startTime}ms\n")
}



