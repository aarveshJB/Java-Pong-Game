import java.awt.*;


public class AiPaddle extends Rectangle{
    int yvelocity;
    int speed;

    AiPaddle(int x, int y, int AiWidth, int AiHeight, int init_speed){
        super(x,y,AiWidth, AiHeight);
        this.speed = init_speed;
    }



    public void setYDirection(int YDirection){
        yvelocity = YDirection;
    }

    public void move(int b_y){
        if(b_y > y+(height/2) ){
            y += speed;
        }
        if(b_y < y+(height/2)){
            y -= speed;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }

}