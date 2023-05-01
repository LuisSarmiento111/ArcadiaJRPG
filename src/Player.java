import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {
    private ArrayList<String> inventory;
    private String playerClass;
    private int playerLevel;
    private int experience;
    private int xCoordinate;
    private int yCoordinate;
    private KeyHandler keyHandler;
    private GamePanel gamePanel;

    public Player(JRPG game, GamePanel gamePanel, KeyHandler keyHandler, String name, String playerClass) {
        super(name, 100, 100, 100, 100, game);
        this.xCoordinate = 100;
        this.yCoordinate = 100;
        playerLevel = 1;
        experience = 0;
        this.keyHandler = keyHandler;
        this.gamePanel = gamePanel;
    }

    public void update() {
        if(keyHandler.isUpPressed()) {
            yCoordinate -= 4;
        }
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
