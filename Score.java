import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score  extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    static int player1;
    static int player2;



    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;

    }

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Consolas", Font.PLAIN, 60));
        g2d.drawString(String.valueOf(Score.player1/10)+String.valueOf(Score.player1%10),90,680);
        //g2d.drawString(String.valueOf(Score.player2/10)+String.valueOf(Score.player2%10),(Score.GAME_WIDTH/2)+300,650);
    }

}