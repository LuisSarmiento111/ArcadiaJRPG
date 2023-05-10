import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JRPG {
    public GamePanel gamePanel;
    private Player player;
    private ArrayList<Monster> onFieldMonsters;
    private final ArrayList<String> monsters = new ArrayList<String>(Arrays.asList("Slime", "Wolf", "Skeleton"));
    private final ArrayList<String> biomes = new ArrayList<String>(Arrays.asList("Forest", "Desert", "Ocean", "Plains", "Jungle", "Mushroom", "Ice"));

    public JRPG(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);


        player = new Player(this, gamePanel, "Test", "Mage");
        gamePanel.setPlayer(player);
        onFieldMonsters = new ArrayList<Monster>();

        gamePanel.startGameThread();
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

    public void deathScreen() {

    }

    public Player getPlayer() {
        return player;
    }
}