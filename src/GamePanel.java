import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    public final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    public final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public final int maxWorldRow = 70;
    public final int maxWorldCol = 70;
    public final int FPS = 60;
    public KeyHandler keyHandler;
    public TileManager tileManager;
    public CollisionChecker collisionChecker;
    public JRPG JRPG;
    public Screens screens;
    public boolean inGame;
    public boolean characterCreationScreen;
    public boolean titleScreen;
    public boolean menuScreen;
    public boolean gameOver;
    public boolean settingScreen;
    public boolean battleScreen;
    public boolean characterSelectionScreen;
    Thread gameThread;

    public GamePanel(JRPG JRPG) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.JRPG = JRPG;
        keyHandler = new KeyHandler(this);
        tileManager = new TileManager(this);
        collisionChecker = new CollisionChecker(this);
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
        double drawInterval = 1000000000 / FPS;
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
        } else if (characterSelectionScreen) {
            screens.drawCharacterSelection(g2);
        } else {
            tileManager.draw(g2);
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