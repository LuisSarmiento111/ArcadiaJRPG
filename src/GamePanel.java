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
    private Screens screens;

    Thread gameThread;

    public Player player;
    final int FPS = 60;

    public boolean titleScreen;
    public boolean menuScreen;
    public boolean gameOver;
    public boolean battleScreen;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        screens = new Screens(this);

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

            // System.out.println("X-Coordiante: " + player.getxCoordinate() + " Y-Coordinate: " + player.getyCoordinate());

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
        player.update();
    }

    public void paintComponent (Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        if (titleScreen) {
            screens.drawTitleScreen(g2);
        }
        g2.clearRect(0,0,screenWidth, screenHeight);
        player.draw(g2);
        g2.dispose();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

}