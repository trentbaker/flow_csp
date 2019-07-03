class Node(val x: Int = -1, val y: Int = -1, character: Char = '?') {
    lateinit var north: Node
    lateinit var east: Node
    lateinit var south: Node
    lateinit var west: Node
    val neighbors get() = mutableListOf(north, east, south, west)
    var type = character
    var constrainValue = -1
    var domain = mutableListOf(type)
    val source = type != '_' && type != '?'

    @Deprecated(message = "Functionality moved into Import.kt")
    fun assignNeighbors(northNode: Node, eastNode: Node, southNode: Node, westNode: Node) {
        this.north = northNode
        this.east = eastNode
        this.south = southNode
        this.west = westNode
    }

//    constrainValue is 0-4 representing how many of north, east, south, west are blocked
//    use (4 - constrainValue) for how many of north, east, south, west are open
    fun calculateConstrain() {
        if (this.type == '?') return
        this.constrainValue = 0
        this.neighbors.forEach { node: Node -> if (node.type != '_') this.constrainValue++ }
    }

    /*
        Returns true if constraints are held, false otherwise
     */
    fun validateConstraints() : Boolean
    {
        // Ignore unassigned constraints
        if (this.type == '_')
        {
            return true
        }
        val unassigned = this.neighbors.count { node -> node.type == '_' }
        val matchingAssigned = this.neighbors.count{node -> node.type == this.type}

        // Validate there aren't more of a color neighboring current node than valid
        if(this.source && matchingAssigned > 1 || matchingAssigned > 2)
        {
            return false
        }
        // Validate that there can be enough neighbors of the current node to be valid
        if(this.source && (matchingAssigned + unassigned) < 1 || !this.source && (matchingAssigned + unassigned) < 2)
        {
            return false
        }
        return true
    }

}
