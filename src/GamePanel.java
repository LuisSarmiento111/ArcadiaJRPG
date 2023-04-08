import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    private KeyHandler keyHandler;

    Thread gameThread;

    private int x;
    private int y;
    private int speed;
    private Player player;
    final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        x = 100;
        y = 100;
        speed = 4;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if(keyHandler.isUpPressed()) {
            y -= speed;
        }
        if(keyHandler.isDownPressed()) {
            y += speed;
        }
        if(keyHandler.isRightPressed()) {
            x += speed;
        }
        if(keyHandler.isLeftPressed()) {
            x -= speed;
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponents(g);
        Graphics g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.clearRect(0,0,screenWidth, screenHeight);
        g2.fillRect(x, y, tileSize, tileSize);
        g2.dispose();
    }

}