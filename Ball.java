
import java.awt.*;
import java.util.*;


public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    static int yVelocity;
    int initialSpeed;




    Ball(int x, int y, int width, int height, int start_speed){
        super(x,y,width,height);
        this.initialSpeed = start_speed;
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);

    }

    public void setXDirection(int randomXDireciton){
        xVelocity=randomXDireciton;
    }

    public void setYDirection(int randomYDireciton){
        yVelocity=randomYDireciton;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y, width, height);
    }
}