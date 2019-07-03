import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun renderMaze(maze: MutableList<MutableList<Node>>): BufferedImage {
    if (maze.size == 0) {
        error("Passed an empty maze")
    }
    val startTime = System.currentTimeMillis()
//    assuming rectangular with top row widest
    return BufferedImage(maze.size, maze[0].size, BufferedImage.TYPE_INT_RGB).also {
        for (i in 0 until maze.size) {
            for (j in 0 until maze[i].size) {
                when (maze[i][j].type) {
//                set a pixel to the correct color
                    '_' -> it.setRGB(j, i, ColorPalette.BLACK.rgb)
                    'B' -> it.setRGB(j, i, ColorPalette.BLUE.rgb)
                    'R' -> it.setRGB(j, i, ColorPalette.RED.rgb)
                    'O' -> it.setRGB(j, i, ColorPalette.ORANGE.rgb)
                    'Y' -> it.setRGB(j, i, ColorPalette.YELLOW.rgb)
                    'G' -> it.setRGB(j, i, ColorPalette.GREEN.rgb)
                    'P' -> it.setRGB(j, i, ColorPalette.PURPLE.rgb)
                    'Q' -> it.setRGB(j, i, ColorPalette.QUARTZ.rgb)
                    'K' -> it.setRGB(j, i, ColorPalette.KHAKI.rgb)
                    'D' -> it.setRGB(j, i, ColorPalette.DIRT.rgb)
                    'W' -> it.setRGB(j, i, ColorPalette.WHITE.rgb)
                    'A' -> it.setRGB(j, i, ColorPalette.APRICOT.rgb)
                    '?' -> error("Maze contained an uninitialized node")
                    else -> error("Maze contained unsupported color: '${maze[i][j].type}'")
                }
            }
        }
        println("render finished: ${System.currentTimeMillis() - startTime}ms")
    }
}

fun BufferedImage.save(fileName: String = "src/res/images/test.png") = ImageIO.write(this, "png", File(fileName))

fun BufferedImage.scale(imageWidth: Int, imageHeight: Int) = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB).also {
    it.graphics.drawImage(this.getScaledInstance(imageWidth, imageHeight, Image.SCALE_FAST), 0, 0, null)
}