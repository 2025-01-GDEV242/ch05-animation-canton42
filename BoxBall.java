import java.awt.Color;
import java.util.Random;
/**
 * BoxBall models a ball that moves inside a box drawn within the canvas.
 * The ball will move at a random speed and will be placed at a random position
 * within the box. The ball bounces off the inner walls of the box. Each ball
 * is assigned a random color that doesn't go too close to white. 
 *
 * @author Nolan Canto
 * @version 2025.03.07
 */
public class BoxBall
{
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 7;
    private int xPos, yPos;
    private int xSpeed, ySpeed;
    private int diameter;
    private int topWall, bottomWall, rightWall, leftWall;
    private Color color;
    private Canvas canvas;
    

    /**
     * Constructor for objects of class BoxBall
     * 
     * @param top y-coord of top wall
     * @param bottom y-coord of bottom wall  
     * @param left x-coord of left wall
     * @param right x-coord of right wall
     * @param canvas The canvas being drawn on
     */
    public BoxBall(int top, int bottom, int right, int left, Canvas ballCanvas)
    {
        Random rand = new Random();
        int red = rand.nextInt(200);
        int green = rand.nextInt(200);
        int blue = rand.nextInt(200);
        this.color = new Color(red, green, blue);
        
        this.topWall = top;
        this.bottomWall = bottom;
        this.rightWall = right;
        this.leftWall = left;
        this.canvas = ballCanvas;
        
        
        this.diameter = 16;
        
        this.xPos = left + rand.nextInt(right - left - diameter);
        this.yPos = top + rand.nextInt(bottom - top - diameter);
        
        do {
            this.xSpeed = MIN_SPEED + rand.nextInt(MAX_SPEED - MIN_SPEED);
            this.ySpeed = MIN_SPEED + rand.nextInt(MAX_SPEED - MIN_SPEED);
        } while (xSpeed == 0 || ySpeed == 0);
        
        if (rand.nextBoolean()) {
            xSpeed = -xSpeed;
        }
        if (rand.nextBoolean()) {
            ySpeed = -ySpeed;
        }
        
    }
    
    /**
     * Draws ball at its current position
     */
    public void draw() {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPos, yPos, diameter);
    }
    
    /**
     * Erases ball at its current position
     */
    public void erase() {
        canvas.eraseCircle(xPos, yPos, diameter);
    }
    
    /**
     * Move this ball according to its position and speed and redraw.
     */
    public void move() {
        erase();
        xPos += xSpeed;
        yPos += ySpeed;
        
        if (xPos <= leftWall || xPos >= rightWall - diameter) {
            xSpeed = -xSpeed;
        }
        if (yPos <= topWall || yPos >= bottomWall - diameter) {
            ySpeed = -ySpeed;
        }
        draw();
    }
}
