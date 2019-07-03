import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun renderMaze(maze: MutableList<MutableList<Node>>, outputFilename: String = "res/output/output.png") {
    if (maze.size == 0) {
        error("Passed an empty maze")
    }
    val startTime = System.currentTimeMillis()
//    assuming rectangular with top row widest
    var image = BufferedImage(maze.size, maze[0].size, BufferedImage.TYPE_INT_RGB)
    for (i in 0 until maze.size) {
        for (j in 0 until maze[i].size) {
            when (maze[i][j].type) {
//                set a pixel to the correct color
                '_' -> image.setRGB(j, i, ColorPalette.BLACK.rgb)
                'B' -> image.setRGB(j, i, ColorPalette.BLUE.rgb)
                'R' -> image.setRGB(j, i, ColorPalette.RED.rgb)
                'O' -> image.setRGB(j, i, ColorPalette.ORANGE.rgb)
                'Y' -> image.setRGB(j, i, ColorPalette.YELLOW.rgb)
                'G' -> image.setRGB(j, i, ColorPalette.GREEN.rgb)
                'P' -> image.setRGB(j, i, ColorPalette.PURPLE.rgb)
                'Q' -> image.setRGB(j, i, ColorPalette.QUARTZ.rgb)
                'K' -> image.setRGB(j, i, ColorPalette.KHAKI.rgb)
                'D' -> image.setRGB(j, i, ColorPalette.DIRT.rgb)
                'W' -> image.setRGB(j, i, ColorPalette.WHITE.rgb)
                'A' -> image.setRGB(j, i, ColorPalette.APRICOT.rgb)
                '?' -> error("Maze contained an uninitialized node")
                else -> error("Maze contained unsupported color: '${maze[i][j].type}'")
            }
        }
    }
    var out = File(outputFilename)
    ImageIO.write(image, "png", out)
    println("render finished: ${System.currentTimeMillis() - startTime}ms")
}

fun BufferedImage.scaleTo(width: Int, height: Int) {
    var temp = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
//    width/this.width
//    temp.se
}