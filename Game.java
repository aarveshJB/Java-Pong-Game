
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Game extends JPanel implements Runnable {
    static final int GAME_HEIGHT = 760;
    static final int GAME_WIDTH = 1110;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 20;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    AiPaddle paddle2;
    Ball ball;
    Score score;
    int level1_score;
    int level2_score;
    int level3_score;
    Image level1_bg;
    Image level2_bg;
    Image level3_bg;
    heart lives;
    Boolean pause = false;
    JDialog pauseDialog;
    GameFrame parentFrame;

    Game(GameFrame parentFrame) {
        this.parentFrame = parentFrame;
        newPaddles();
        newBall();
        newScore();
        newlives();
        newbg();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        setKeyBindings();


        gameThread = new Thread(this);
    }

    public void newbg() {
        if (level1_score < 5) {
            level1_bg = new ImageIcon("src/Resources/1.png").getImage();
        } else if (level2_score < 5) {
            level2_bg = new ImageIcon("src/Resources/2.png").getImage();
        } else if (level3_score < 5) {
            level3_bg = new ImageIcon("src/Resources/3.png").getImage();
        }
    }

    public void newBall() {
        if (level1_score < 5) {
            ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER, 5);
        } else if (level2_score < 5) {
            ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER, 8);
        } else if (level3_score < 5) {
            ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER, 10);
        }
    }

    public void newPaddles() {
        paddle1 = new Paddle(55, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);

        if (level1_score < 5) {
            paddle2 = new AiPaddle(GAME_WIDTH - PADDLE_WIDTH - 55, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 4);
        } else if (level2_score < 5) {
            paddle2 = new AiPaddle(GAME_WIDTH - PADDLE_WIDTH - 55, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 7);
        } else if (level3_score < 5) {
            paddle2 = new AiPaddle(GAME_WIDTH - PADDLE_WIDTH - 55, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 8);
        }
    }

    public void newScore() {
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
    }

    public void newlives() {
        lives = new heart(Score.player2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        if (level1_score < 5) {
            g.drawImage(level1_bg, 0, 0, this);
        } else if (level2_score < 5) {
            g.drawImage(level2_bg, 0, 0, this);
        } else if (level3_score < 5) {
            g.drawImage(level3_bg, 0, 0, this);
        }

        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        lives.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move(ball.y);
        ball.move();
    }

    public void checkCollision() {
        //bounces ball off top and bottom
        if (ball.y <= 55) {
            ball.setYDirection(-ball.yVelocity);
            ball.xVelocity++;
        }

        if (ball.y >= 555 - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        //bounces ball off paddle
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //keeps paddle in container
        if (paddle1.y <= 55) paddle1.y = 55;
        if (paddle1.y + paddle1.height >= 555) paddle1.y = 555 - paddle1.height;

        if (paddle2.y <= 55) paddle2.y = 55;
        if (paddle2.y + paddle2.height >= 555) paddle2.y = 555 - paddle2.height;

        // point score
        if (level1_score < 5) {
            // player 2 score
            if (ball.x <= 55) {
                Score.player2++;
                newPaddles();
                newBall();
                newlives();
                System.out.println("Player 2: " + Score.player2);
            }
            //player 1 score
            if (ball.x >= 1055) {
                Score.player1++;
                level1_score++;
                newPaddles();
                newBall();
                newlives();
                System.out.println("Player 1: " + Score.player1);
            }

            // lose 3 lives, restart score
            if (Score.player2 >= 3) {
                Score.player2 = 0;
                Score.player1 = 0;
                level1_score = 0;
                newlives();
            }

            newbg();

        } else if (level2_score < 5) {
            // player 2 score
            if (ball.x <= 55) {
                Score.player2++;
                newPaddles();
                newBall();
                newlives();
                System.out.println("Player 2: " + Score.player2);
            }
            //player 1 score
            if (ball.x >= 1055) {
                Score.player1++;
                level2_score++;
                newPaddles();
                newBall();
                newlives();
                System.out.println("Player 1: " + Score.player1);
            }

            // lose 3 lives, restart score
            if (Score.player2 >= 3) {
                Score.player2 = 0;
                Score.player1 = 0;
                level2_score = 0;
                newlives();
            }

            newbg();

        } else if (level3_score < 5) {
            // player 2 score
            if (ball.x <= 55) {
                Score.player2++;
                newPaddles();
                newBall();
                newlives();
                System.out.println("Player 2: " + Score.player2);
            }
            //player 1 score
            if (ball.x >= 1055) {
                Score.player1++;
                level3_score++;
                newPaddles();
                newBall();
                newlives();
                System.out.println("Player 1: " + Score.player1);
            }

            // lose 3 lives, restart score
            if (Score.player2 >= 3) {
                Score.player2 = 0;
                Score.player1 = 0;
                level3_score = 0;
                newlives();
            }

            newbg();
        }

        // Check if all levels are completed
        if (level1_score >= 5 && level2_score >= 5 && level3_score >= 5) {
            parentFrame.showPanel("Congrats");
        }
    }

    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        boolean wasPaused = false;

        while (level3_score < 5) {
            if (!pause) {
                if (wasPaused) {
                    // Reset the last time to current time when resuming from pause
                    lastTime = System.nanoTime();
                    wasPaused = false;
                    pauseDialog.setVisible(false); // Hide the pause dialog
                }
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                if (delta >= 1) {
                    move();
                    checkCollision();
                    repaint();
                    delta--;
                }
            } else {
                // If the game is paused, mark it as paused and show the pause dialog
                wasPaused = true;
                pauseDialog.setVisible(true); // Show the pause dialog
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setKeyBindings() {
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "stopMovingUp");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "stopMovingDown");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "togglePause");

        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1.setYDirection(-paddle1.speed);
            }
        });

        actionMap.put("stopMovingUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1.setYDirection(0);
            }
        });

        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1.setYDirection(paddle1.speed);
            }
        });

        actionMap.put("stopMovingDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle1.setYDirection(0);
            }
        });

        actionMap.put("togglePause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause = !pause; // Toggle pause state
            }
        });
    }

    public void startGame() {
        gameThread.start();
    }
}