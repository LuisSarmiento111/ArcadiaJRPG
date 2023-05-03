import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {
    private ArrayList<String> inventory;
    private String playerClass;
    private int playerLevel;
    private int experience;

    private final String[][] classes = {{"Knight", "8"}, {"Mage", "5"}};
    private KeyHandler keyHandler;
    private GamePanel gamePanel;

    public Player(JRPG game, GamePanel gamePanel, KeyHandler keyHandler, String name, String playerClass) {
        super(name, game, gamePanel);
        xCoordinate = (gamePanel.screenWidth - gamePanel.tileSize)/ 2-  gamePanel.tileSize / 2;
        yCoordinate = (gamePanel.screenHeight - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBox.width = gamePanel.tileSize;
        hitBox.height = gamePanel.tileSize;

        this.playerClass = playerClass;
        setStats();
        playerLevel = 1;
        experience = 0;


        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
    }

    private void setStats() {
        if (playerLevel != 1) {

        } else {

        }
    }

    public void update() {
            if(keyHandler.isUpPressed()) {
                yCoordinate -= 4;}
            if(keyHandler.isDownPressed()) {
                yCoordinate += 4;       }
            if(keyHandler.isRightPressed()) {
                xCoordinate += 4;      }
            if(keyHandler.isLeftPressed()) {
                xCoordinate -= 4;        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(xCoordinate, yCoordinate, hitBox.width, hitBox.height);
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }


    public int getyCoordinate() {
        return yCoordinate;
    }


    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
