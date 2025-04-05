import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game_Instructions extends JPanel {
    Image GI;
    Dimension screen = new Dimension(1110, 760);
    GameFrame parentFrame;

    public Game_Instructions(GameFrame parentFrame) {
        this.parentFrame = parentFrame;
        GI = new ImageIcon("src/Resources/5.png").getImage();
        this.setFocusable(true);
        this.setPreferredSize(screen);
        setKeyBindings();
    }

    private void setKeyBindings() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "startGame");
        getActionMap().put("startGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showPanel("Level_1");
                parentFrame.gamePanel.gameThread.start();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(GI, 0, -10, getWidth(), getHeight(), this);
    }
}