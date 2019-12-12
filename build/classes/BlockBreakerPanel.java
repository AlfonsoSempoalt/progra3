
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks;
    Block ball;
    Block paddle;

    JFrame mainFrame, startScreen;

    Thread thread;

      public BlockBreakerPanel(JFrame frame, JFrame startScreen) {

        this.mainFrame = frame;
        this.startScreen = startScreen;

        medio();

        thread = new Thread(() -> {
            while (true) {
                update();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        });
        thread.start();

    }
    
    public void medio() {
        blocks = new ArrayList<Block>();
        ball = new Block(237, 435, 35, 25, "ball.png");
        paddle = new Block(175, 480, 150, 25, "paddle.png");
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 0, 55, 25, "blue.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 25, 55, 25, "green.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 50, 55, 25, "yellow.png"));
        }
        for (int i = 0; i < 8 ; i++) {
            blocks.add(new Block((i * 60 + 2), 75, 55, 25, "red.png"));
            
        }
       for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 100, 55, 25, "green.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Block((i * 60 + 2), 135, 5, 25, "yellow.png"));
        }   
        addKeyListener(this);
        setFocusable(true);
    } 

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        blocks.forEach(block -> {
            block.draw(g, this);
        });
        ball.draw(g, this);
        paddle.draw(g, this);
    }

    public void update() {
        ball.x += ball.movX;

        if (ball.x > (getWidth() - 25) || ball.x < 0) {
            ball.movX *= -1;
        }

        if (ball.y < 0 || ball.intersects(paddle)) {
            ball.movY *= -1;
        }

        ball.y += ball.movY;

        if (ball.y > getHeight()) {
            thread = null;
            mainFrame.setVisible(false);
            JOptionPane.showMessageDialog(this, "Presione para jugar");
            medio();
            startScreen.setVisible(true);

        }
 
        blocks.forEach(block -> {
            if (ball.intersects(block) && !block.destroyed) {
                ball.movY *= -1;
                block.destroyed = true;
            }
        });
        this.repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            paddle.x += 15;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 15;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
