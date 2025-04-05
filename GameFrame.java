
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public CardLayout cardLayout;
    public JPanel cardPanel;
    public Start startPanel;
    public Game gamePanel;
    public Game_Instructions instructionsPanel;
    public Congrats congratsPanel;

    public GameFrame() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        startPanel = new Start(this);
        gamePanel = new Game(this);
        instructionsPanel = new Game_Instructions(this);
        congratsPanel = new Congrats(this);

        cardPanel.add(startPanel, "Start");
        cardPanel.add(gamePanel, "Level_1");
        cardPanel.add(instructionsPanel, "Instructions");
        cardPanel.add(congratsPanel, "Congrats");

        this.add(cardPanel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/Resources/Icon.png");
        this.setIconImage(icon.getImage());
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

}