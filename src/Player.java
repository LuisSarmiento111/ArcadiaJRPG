import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    private ArrayList<String> inventory;
    private String playerClass;
    private int playerLevel;
    private int experience;
    private String UID;
    private BufferedImage playerSprite;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    private final String[][] classes = {{"Knight", "8"}, {"Mage", "5"}};
    private KeyHandler keyHandler;
    private GamePanel gamePanel;

    public Player(JRPG game, GamePanel gamePanel, String name, String playerClass) {
        super(name, game, gamePanel);
        xCoordinate = (gamePanel.screenWidth - gamePanel.tileSize)/ 2-  gamePanel.tileSize / 2;
        yCoordinate = (gamePanel.screenHeight - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBox.width = gamePanel.tileSize;
        hitBox.height = gamePanel.tileSize;

        this.playerClass = playerClass;
        UID = "02612111220";
        setStats();
        playerLevel = 1;
        experience = 0;
        direction = "down";
        getPlayerImage();


        this.gamePanel = gamePanel;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
            up2 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
            down1 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
            down2 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
            left1 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
            left2 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
            right1 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
            right2 = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(0, 0, 50, 50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStats() {
        if (playerLevel != 1) {

        } else {

        }
    }

    public void update() {
        if (gamePanel.inGame) {
            if (gamePanel.keyHandler.isUpPressed()) {
                direction = "up";
                yCoordinate -= 4;
            }
            if (gamePanel.keyHandler.isDownPressed()) {
                direction = "down";
                yCoordinate += 4;
            }
            if (gamePanel.keyHandler.isRightPressed()) {
                direction = "right";
                xCoordinate += 4;
            }
            if (gamePanel.keyHandler.isLeftPressed()) {
                direction = "left";
                xCoordinate -= 4;
            }
            if(gamePanel.keyHandler.isMenuPressed()) {
                gamePanel.titleScreen = true;
                gamePanel.inGame = false;
            }
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.black);
        // g2.fillRect(xCoordinate, yCoordinate, hitBox.width, hitBox.height);
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "right":
                image = right1;
                break;
            case "left":
                image = left1;
                break;
        }
        g2.drawImage(image, xCoordinate, yCoordinate, gamePanel.tileSize, gamePanel.tileSize, null);
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
