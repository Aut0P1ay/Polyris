package trs

import kaiju.math.Matrix2d
import processing.core.PApplet

fun main(args: Array<String>) {
    PApplet.main("trs.Polyris")
}

class Polyris : PApplet() {
    var lastDrawTime = System.currentTimeMillis() / 1000
    var nextPos = 0f
    var horizontalLocation = 4f
    val boxSize = 50f
    val xSize = 9
    val ySize = 14
    val stuckPieces = Matrix2d<Boolean>(xSize, ySize, { x, y -> y > 6 })

    override fun settings() {
        size(xSize * boxSize.toInt(), ySize * boxSize.toInt()) //Should be a multiple of variable boxSize
    }

    override fun setup() {
        fill(229f, 27f, 212f)
        background(0f, 0f, 0f)
    }

    override fun draw() //IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA
    {
        playerInput()
        canBoxMove()
        drawStuckPieces()

    }//IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA

    fun addBlocksToArray() {
        stuckPieces[horizontalLocation.toInt(), nextPos.toInt() - 1] = true
        nextPos = 0f
        horizontalLocation = 4f
    }

    fun drawStuckPieces() {
        for (x in 0 until xSize) {
            for (y in 0 until ySize) {
                if (stuckPieces[x, y]) {
                    fill(26f, 228f, 43f)
                    drawBox(x.toFloat(), y.toFloat())
                    fill(229f, 27f, 212f)
                }
            }
        }
    }

    fun canBoxMove() {
        var tsLong = System.currentTimeMillis() / 1000
        if (tsLong - lastDrawTime > 0.5) {
            if (stuckPieces[horizontalLocation.toInt(), nextPos.toInt()]) {
                addBlocksToArray()
            } else {
                background(0f, 0f, 0f)
                drawBox(horizontalLocation, nextPos) //horizontalLocation must be 0 - 9, nextPos must be 0 - 14
                if (nextPos >= height / 50 - 1) {
                    addBlocksToArray()
                } else {
                    nextPos += 1f
                }
                lastDrawTime = System.currentTimeMillis() / 1000
            }
        }
    }

    fun drawBox(x: Float, y: Float) {
        var xInternal1 = x * boxSize
        var xInternal2 = x * boxSize + boxSize
        var yInternal1 = y * boxSize
        var yInternal2 = y * boxSize + boxSize
        beginShape()
        vertex(xInternal1, yInternal1)
        vertex(xInternal2, yInternal1)
        vertex(xInternal2, yInternal2)
        vertex(xInternal1, yInternal2)
        endShape(CLOSE)
    }

    fun playerInput() {
        if (keyPressed) {
            if (key == 'a') {
                if (stuckPieces[horizontalLocation.toInt() - 1, nextPos.toInt() - 1]) {
                    //Blocked
                } else {
                    keyPressed = false
                    background(0f, 0f, 0f)
                    horizontalLocation -= 1f
                    if (horizontalLocation < 0) {
                        horizontalLocation = 0f
                    }
                    drawBox(horizontalLocation, nextPos - 1)
                }
            }
            if (key == 'd') {
                if (stuckPieces[horizontalLocation.toInt() - 1, nextPos.toInt() - 1]) {
                    //Blocked
                } else {
                    keyPressed = false
                    background(0f, 0f, 0f)
                    horizontalLocation += 1f
                    drawBox(horizontalLocation, nextPos - 1)
                }
            }
        }
    }
}