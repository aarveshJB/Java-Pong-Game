
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Congrats extends JPanel {
    Image CongratsImage;
    Dimension screen = new Dimension(1110, 760);
    ImageIcon MainMenu;
    JButton MainMenu_button;
    GameFrame parentFrame;

    public Congrats(GameFrame parentFrame) {

        // Load the background image
        CongratsImage = new ImageIcon("src/Resources/Victory page.png").getImage();
        if (CongratsImage == null) {
            System.out.println("Failed to load background image.");
        }

        this.setFocusable(true);
        this.setPreferredSize(screen);
        this.setLayout(null); // Disable layout manager for absolute positioning

        // Load the play button image
        MainMenu = new ImageIcon("src/Resources/Final button.png");
        if (MainMenu.getIconWidth() == -1) {
            System.out.println("Failed to load play button image.");
        }
        MainMenu_button = new JButton(MainMenu);
        MainMenu_button.setBounds(239, 514, 632, 207);
        MainMenu_button.setBackground(Color.BLACK); // background color
        MainMenu_button.setBorderPainted(false); //button border
        MainMenu_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showPanel("Start");
            }
        });

        this.add(MainMenu_button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(CongratsImage, 0, 0, getWidth(), getHeight(), this);
    }

}