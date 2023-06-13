import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public final int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public final int maxWorldRow = 85;
    public final int maxWorldCol = 100;
    public final int FPS = 60;
    public KeyHandler keyHandler;
    public TileManager tileManager;
    public CollisionChecker collisionChecker;
    public JRPG JRPG;
    public int timer;
    public Screens screens;
    public boolean inGame;
    public boolean characterCreationScreen;
    public boolean titleScreen;
    public boolean menuScreen;
    public boolean gameOver;
    public boolean settingScreen;
    public boolean battleScreen;
    public boolean characterStatsScreen;
    public boolean characterSelectionScreen;
    public boolean dialogueScreen;
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
        timer = 0;

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
            timer++;
            if (timer > 1000) {
                JRPG.setMonster();
                timer = 0;
            }
            JRPG.player.update();
            for (int i = 0; i < JRPG.NPC.length; i++) {
                if (JRPG.NPC[i] != null) {
                    JRPG.NPC[i].update();
                }
            }
            for (int i = 0; i < JRPG.monsters.length; i++) {
                if (JRPG.monsters[i] != null) {
                    JRPG.monsters[i].update();
                }
            }
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
        } else if (battleScreen) {
            if (JRPG.turn == -1) {
                JRPG.battleSetUp();
            }
            screens.drawBattle(g2);
            for (int i = 0; i < JRPG.entities.size(); i++) {
                JRPG.entities.get(i).draw(g2);
            }
            JRPG.battle();
            screens.drawMessage(g2);
        } else {
            tileManager.draw(g2);
            JRPG.entities.add(JRPG.player);
            for (int i = 0; i < JRPG.NPC.length; i++) {
                if (JRPG.NPC[i] != null) {
                    JRPG.entities.add(JRPG.NPC[i]);
                }
            }
            if (JRPG.player.storyProgress > -1) {
                for (int i = 0; i < JRPG.monsters.length; i++) {
                    if (JRPG.monsters[i] != null) {
                        JRPG.entities.add(JRPG.monsters[i]);
                    }
                }
            }
            for (int i = 0; i < JRPG.entities.size(); i++) {
                JRPG.entities.get(i).draw(g2);
            }
            JRPG.entities.clear();
            screens.drawMessage(g2);
            if (menuScreen) {
                screens.drawInGameMenu(g2);
            }
            if (characterStatsScreen) {
                screens.drawStatsScreen(g2);
            }
        }
        g2.dispose();
    }

    public void endGame() {
        System.exit(0);
    }


}