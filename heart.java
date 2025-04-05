
import javax.swing.*;
import java.awt.*;


public class heart {
    Image heart;
    int n;

    public heart(int n){
        heart = new ImageIcon("src/Resources/Heart_resized.png").getImage();
        this.n = n;
    }

    public void draw(Graphics g){
        if(this.n <= 0){
            g.drawImage(heart, 380,580,null);
            g.drawImage(heart, 511,580,null);
            g.drawImage(heart, 640,580,null);
        }
        if(this.n == 1){
            g.drawImage(heart, 380,580,null);
            g.drawImage(heart, 511,580,null);
        }

        if(this.n <= 2){
            g.drawImage(heart, 380,580,null);
        }
    }


}