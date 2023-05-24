import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int screenWidth = 1920;
    final int screenHeight = 1080;
    final int FPS = 60;
    public KeyHandler keyHandler;
    public JRPG JRPG;
    public Screens screens;
    public Player player;
    public boolean inGame;
    public boolean characterCreationScreen;
    public boolean titleScreen;
    public boolean menuScreen;
    public boolean gameOver;
    public boolean settingScreen;
    public boolean battleScreen;
    Thread gameThread;

    public GamePanel(JRPG JRPG) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.JRPG = JRPG;
        keyHandler = new KeyHandler(this);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        screens = new Screens(this);

        inGame = false;
        titleScreen = true;
        settingScreen = false;
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
        if (inGame) {
            JRPG.player.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0, screenWidth, screenHeight);
        if (titleScreen) {
            screens.drawTitleScreen(g2);
        } else if (characterCreationScreen) {
            screens.drawCharacterCreation(g2);
        } else {
            JRPG.player.draw(g2);
            if (menuScreen) {
                screens.drawInGameMenu(g2);
            }
        }
        g2.dispose();
    }

    public void endGame() {
        System.exit(0);
    }


}