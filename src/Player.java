import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {
    private ArrayList<String> inventory;
    private String playerClass;
    private int playerLevel;
    private int experience;
    public int xCoordinate;
    public int yCoordinate;

    private final String[][] classes = {{"Knight", "8"}, {"Mage", "5"}};
    private KeyHandler keyHandler;
    private GamePanel gamePanel;

    public Player(JRPG game, GamePanel gamePanel, KeyHandler keyHandler, String name, String playerClass) {
        super(name, 10, 10, 10, 10, game, gamePanel);
        this.xCoordinate = 100;
        this.yCoordinate = 100;
        playerLevel = 1;
        experience = 0;
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
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
        g2.fillRect(xCoordinate, yCoordinate, gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setStats() { // base stats for different monsters that are based on the player's level.
        for (int r = 0; r < classes.length; r++) {
            if (classes[r][0].equals(playerClass)) {
                health = Integer.parseInt(classes[r][1]);
                speed = Integer.parseInt(classes[r][2]);
                strength = Integer.parseInt(classes[r][3]);
                magicPower = Integer.parseInt(classes[r][4]);
            }
        }
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

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
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
