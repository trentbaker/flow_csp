class BasicBacktrack : BacktrackAlg {

    // Tracks number of assignments made
    override var assignments: Int = 0

    // Sets this algorithm to not smart
    override val smart = false

    /*
    Selects based on location in the puzzle
    most northwesterly unassigned node will be selected first
     */
    override fun selectUnassignedVariable(maze: MutableList<MutableList<Node>>): Node {
        var temp = maze.flatten()
                .filter { node -> node.type == '_' }
        return temp.first()
    }
}

