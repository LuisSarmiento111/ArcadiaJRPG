import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JRPG {
    private final ArrayList<String> monsters = new ArrayList<String>(Arrays.asList("Slime", "Wolf", "Skeleton"));
    private final ArrayList<String> biomes = new ArrayList<String>(Arrays.asList("Forest", "Desert", "Ocean", "Plains", "Jungle", "Mushroom", "Ice"));
    public GamePanel gamePanel;
    public Player player;
    private ArrayList<Monster> onFieldMonsters;

    public JRPG() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        gamePanel = new GamePanel(this);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        onFieldMonsters = new ArrayList<Monster>();

        gamePanel.startGameThread();
        titleScreen();
    }

    public void titleScreen() {
        // gamePanel.titleScreen = true;
    }

    public void inGameMenu() {

    }

    public void battle() {
        gamePanel.battleScreen = true;
        for (int i = 0; i < (int) (Math.random() * 4); i++) {
            onFieldMonsters.add(new Monster(monsters.get((int) (Math.random() * monsters.size())), this, gamePanel));
        }
    }

    public Player[] loadPlayerData() {
        return new Player[2]; // test
    }

    public void deathScreen() {

    }
}