interface BacktrackAlg {

    // Tracks number of assignments made
    var assignments : Int

    // Tracks if algorithm is smart for conditionally executing code
    val smart : Boolean

    /*
    This function is implemented to select in different ways to vary algorithm behavior
    Could select by location, randomly or by constrainValue for example
     */
    fun selectUnassignedVariable(maze: MutableList<MutableList<Node>>): Node

    /*
        Returns true if the assignment is complete, false otherwise.
     */
    fun isComplete(maze: MutableList<MutableList<Node>>): Boolean {
        return maze.all { row -> row.all { node -> node.type != '_' } }
    }

    /*
        Search the puzzle for a solution
        Returns true if solved, false otherwise
     */
    fun search(maze: MutableList<MutableList<Node>>): Boolean {

        // if assignment is complete then return
        if (isComplete(maze)) {
            return true
        }
        var node = selectUnassignedVariable(maze)

        for (value in node.domain) {
            assignments++
            node.type = value

            if(smart){
                node.neighbors.forEach { node -> node.calculateConstrain() }
            }

//            consistent is true if there are no constraint violations among neighbors
            val consistent = node.neighbors.filter { neighbor -> neighbor.type != '?' }
                    .all { neighbor -> neighbor.validateConstraints() }

//            if the current assignment is consistent with the constraints, continue assigning
            if (consistent) {
//                result is true if the assignment is complete
                var result = search(maze)
                if (result) {
//                    solved the puzzle
                    return true
                }
            }
//            assignment is not consistent with the constraints
//            undo the last step and try another value
            node.type = '_'
        }
        // return failure
        return false
    }
}