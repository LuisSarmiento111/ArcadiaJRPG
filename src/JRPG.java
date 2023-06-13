import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class JRPG {
    private final ArrayList<String> biomes = new ArrayList<String>(Arrays.asList("Forest", "Desert", "Ocean", "Plains", "Jungle", "Mushroom", "Ice"));
    public GamePanel gamePanel;
    public Player player;
    public NPC[] NPC = new NPC[10];
    public int turn;
    public Entity[] monsters = new Entity[10];
    public ArrayList<Entity> entities;

    public JRPG() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("2D Adventure");
        gamePanel = new GamePanel(this);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        turn = -1;

        gamePanel.startGameThread();
        gamePanel.titleScreen = true;
    }

    public void setEntities() {
        entities = new ArrayList<Entity>();
        setNPC();
        setMonster();
    }

    public void setNPC() {
        NPC[0] = new NPC(gamePanel);
        NPC[0].worldX = 47 * gamePanel.tileSize;
        NPC[0].worldY = 72 * gamePanel.tileSize;
    }

    public void setMonster() {
        for (int i = 0; i < 4; i++) {
            monsters[i] = new Slime(gamePanel);
            monsters[i].worldX = ((int) (Math.random() * 21) + 22) * gamePanel.tileSize;
            monsters[i].worldY = ((int) (Math.random() * 6) + 42) * gamePanel.tileSize;
        }
    }

    public void battleSetUp() {
        turn = 0;
        entities.clear();
        entities.add(player);
        for (int i = 0; i < (int) (Math.random() * 3) + 1; i++) {
            entities.add(new Slime(gamePanel));
        }
    }

    public void battle() {
        player.hitBox.x = 176;
        player.hitBox.y = 168;
        player.hitBox.width = 48;
        player.hitBox.height = 96;
        player.screenX = 300;
        player.screenY = gamePanel.screenHeight / 2 - (player.hitBox.height + player.hitBox.y) + 80;
        int x = gamePanel.screenWidth / 2 + 100;
        for (int i = 1; i < entities.size(); i++) {
            entities.get(i).screenX = x;
            entities.get(i).screenY = gamePanel.screenHeight / 2 - entities.get(i).hitBox.height / 2;
            entities.get(i).direction = "left";
            entities.get(i).spriteNum = entities.get(i).spriteInterval;
            x += 100;
        }
        Entity entity = entities.get(turn % entities.size());
        entity.turn = true;
        if (!(entity instanceof Player)) {
            entity.battleTurn(entities);
        }
        if (entities.size() < 2 || player.health <= 0) {
            if (player.health <= 0) {
                player.health = (int) (player.maxHealth * .25);
                player.worldX = 24 * gamePanel.tileSize;
                player.worldY = 57 * gamePanel.tileSize;
            }
            player.screenX = (gamePanel.screenWidth - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
            player.screenY = (gamePanel.screenHeight - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
            player.hitBox.x = player.hitBoxDefaultX;
            player.hitBox.y = player.hitBoxDefaultY;
            player.spriteNum = 0;
            player.hitBox.width = 36;
            player.hitBox.height = 48;
            gamePanel.battleScreen = false;
            gamePanel.inGame = true;
            turn = -1;
        }
    }


    public Player[] loadPlayerData() {
        return new Player[2]; // test
    }

    public void deathScreen() {

    }
}