
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {
    int yvelocity;
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void setYDirection(int yDirection) {
        yvelocity = yDirection;
    }

    public void move() {
        y += yvelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}