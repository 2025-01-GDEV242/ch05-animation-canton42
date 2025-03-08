import java.awt.Color;
import java.util.ArrayList;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * Added a new method called boxBounce that simulates a user-defined amount
 * of balls bouncing at random positions and speeds within a drawn box on the
 * canvas. All balls drawn have a random color.
 * 
 * @author Nolan Canto
 * @version 2025.03.07
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    /**
     * Simulates a user-defined amount of balls bouncing 
     * within a drawn box at random positions and speeds.
     * 
     * NOTE: The minimum number of balls that can be on screen is 5, 
     * the maximum amount is 30.
     * 
     * @param numBalls The number of balls to be drawn
     */
    public void boxBounce(int numBalls) {
        myCanvas.setVisible(true);
        
        int top = 50;
        int bottom = 450;
        int left = 50;
        int right = 550;
        boolean finished = false;
        
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(left, top, right, top);   
        myCanvas.drawLine(left, bottom, right, bottom); 
        myCanvas.drawLine(left, top, left, bottom);  
        myCanvas.drawLine(right, top, right, bottom); 
        
        //This hard limits the minimum and maximum amount of balls on screen
        //to be 5 and 30 respectively, no matter if a higher/lower amount is
        //entered for numBalls. I had too much fun spawning thousands of balls,
        //so there's a seperate branch to the project with this line removed.
        numBalls = Math.max(5, Math.min(numBalls, 30));
        
        
        ArrayList<BoxBall> balls = new ArrayList<>();
        for (int i = 0; i < numBalls; i++) {
            BoxBall ball = new BoxBall(top, bottom, right, left, myCanvas);
            ball.draw();
            balls.add(ball);
        }
        
        
        while (!finished) {
            myCanvas.wait(50);
            
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.drawLine(left, top, right, top);   
            myCanvas.drawLine(left, bottom, right, bottom); 
            myCanvas.drawLine(left, top, left, bottom);  
            myCanvas.drawLine(right, top, right, bottom);
            
            for (BoxBall ball : balls) {
                ball.move();
            }
        }
        
    }
}
