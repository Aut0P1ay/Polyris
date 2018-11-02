package trs

import processing.core.PApplet

fun main(args: Array<String>)
{
    PApplet.main("trs.Polyris")
}
class Polyris:PApplet()
{
    var lastDrawTime = System.currentTimeMillis() / 1000
    var nextPos = 0f
    var horizontalLocation = 0f
    var blockReachedBottom = false
    var xInternal1 = 0f
    var xInternal2 = 50f
    var yInternal1 = 0f
    var yInternal2 = 50f
    var boxSize = 50f
    override fun settings()
    {
        size(450, 650)
    }
    override fun setup()
    {
        fill(229f, 27f, 212f)
        background(0f,0f,0f)
    }
    override fun draw() //IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA
    {
        playerInput()
        canBoxMove()
        doLoop(false)
    }//IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA, IMPORTANT AREA
    fun doLoop(doLoop: Boolean)
    {
        if(doLoop == true)
        {
            if(blockReachedBottom == true)
            {
                blockReachedBottom = false
                nextPos = 0f
            }
        }
    }
    fun canBoxMove()
    {
        var tsLong = System.currentTimeMillis() / 1000
        if(tsLong - lastDrawTime > 0.5)
        {
            background(0f,0f,0f)
            drawBox(horizontalLocation, nextPos) //horizontalLocation must be 0 - 9, nextPos must be 0 - 14
            if(nextPos >= height / 50 - 1)
            {
                blockReachedBottom = true;
            }
            else
            {
                nextPos += 1f
            }
            lastDrawTime = System.currentTimeMillis() / 1000
        }
    }
    fun drawBox(x:Float, y:Float)
    {
        xInternal1 = x * 50
        xInternal2 = x * 50 + 50
        yInternal1 = y * 50
        yInternal2 = y * 50 + 50
        beginShape()
        vertex(xInternal1, yInternal1)
        vertex(xInternal2, yInternal1)
        vertex(xInternal2, yInternal2)
        vertex(xInternal1, yInternal2)
        endShape(CLOSE)
    }
    fun playerInput()
    {
        if(keyPressed)
        {
            if(key == 'a')
            {
                keyPressed = false
                background(0f,0f,0f)
                if(blockReachedBottom == false)
                {
                    horizontalLocation -= 1f
                }
                if(horizontalLocation < 0)
                {
                    horizontalLocation = 0f
                }
                drawBox(horizontalLocation, nextPos)
            }
            if(key == 'd')
            {
                keyPressed = false
                background(0f,0f,0f)
                if(blockReachedBottom == false)
                {
                    horizontalLocation += 1f
                }
                if(horizontalLocation > width / 50 - 1)
                {
                    horizontalLocation = width / 50f - 1
                }
                drawBox(horizontalLocation, nextPos)
            }
            if(key == 's')
            {
                keyPressed = false
                background(0f,0f,0f)
                if(blockReachedBottom == false)
                {
                    nextPos = height / 50f - 1
                    blockReachedBottom = true
                }
                drawBox(horizontalLocation, nextPos)
            }
        }
    }
}