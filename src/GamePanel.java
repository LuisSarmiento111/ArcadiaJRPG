import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int screenWidth = 1920;
    final int screenHeight = 1080;
    public KeyHandler keyHandler;
    public Screens screens;

    Thread gameThread;

    public Player player;
    final int FPS = 60;

    public boolean inGame;
    public boolean titleScreen;
    public boolean menuScreen;
    public boolean gameOver;
    public boolean battleScreen;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);

        keyHandler = new KeyHandler(this);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        screens = new Screens(this);

        inGame = false;
        titleScreen = true;
        menuScreen = false;
        gameOver = false;
        battleScreen = false;
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
        g2.clearRect(0,0,screenWidth, screenHeight);
        if (titleScreen) {
            screens.drawTitleScreen(g2); // fix arrows not updating correctly
        } else {
            player.draw(g2);
            g2.dispose();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

}