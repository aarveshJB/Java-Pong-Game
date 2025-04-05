
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JPanel {
    Image startImage;
    Dimension screen = new Dimension(1110, 760);
    ImageIcon playButtonIcon;
    JButton playButton;
    ImageIcon instructionsButtonIcon;
    JButton instructionsButton;
    GameFrame parentFrame;

    public Start(GameFrame parentFrame) {
        this.parentFrame = parentFrame;
        startImage = new ImageIcon("src/Resources/4.png").getImage();
        this.setFocusable(true);
        this.setPreferredSize(screen);
        this.setLayout(null); // Disable layout manager for absolute positioning

        playButtonIcon = new ImageIcon("src/Resources/PG_button.png");
        playButton = new JButton(playButtonIcon);
        playButton.setBounds(11, 580, 543, 158);
        playButton.setBackground(Color.BLACK);
        playButton.setBorderPainted(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showPanel("Level_1");
                parentFrame.gamePanel.gameThread.start();
            }
        });
        this.add(playButton);

        instructionsButtonIcon = new ImageIcon("src/Resources/GI_button.png");
        instructionsButton = new JButton(instructionsButtonIcon);
        instructionsButton.setBounds(555, 590, 494, 137);
        instructionsButton.setBackground(Color.BLACK);
        instructionsButton.setBorderPainted(false);
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showPanel("Instructions");
            }
        });
        this.add(instructionsButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(startImage, 0, 0, getWidth(), getHeight(), this);
    }
}