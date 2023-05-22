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
    public final BufferedImage[] sprites = new BufferedImage[36];

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
        direction = "up";
        UID = "022612111220";
        setStats();
        playerLevel = 1;
        experience = 0;

        getPlayerImage();


        this.gamePanel = gamePanel;
    }

    public void getPlayerImage() {
        int index = 0;
        int y = 512;
        try {
            for (int i = 0; i < 4; i++) {
                int x = 0;
                for (int j = 0; j < 9; j++) {
                    sprites[index] = ImageIO.read(new File(("Sprites/" + UID + ".png"))).getSubimage(x, y, 64, 64);
                    x += 64;
                    index++;
                }
                y += 64;
            }
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
            if (gamePanel.keyHandler.isUpPressed() || gamePanel.keyHandler.isDownPressed() ||
                    gamePanel.keyHandler.isLeftPressed() || gamePanel.keyHandler.isRightPressed()) {
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
                spriteCounter++;
                if (spriteCounter > 10) {
                    spriteNum++;
                    spriteCounter = 0;
                }
            }

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum > 8 || spriteNum < 0) {
                    spriteNum = 0;
                }
                image = sprites[spriteNum];
                break;
            case "down":
                if (spriteNum > 26 || spriteNum < 18) {
                    spriteNum = 18;
                }
                image = sprites[spriteNum];
                break;
            case "right":
                if (spriteNum > 35 || spriteNum < 28) {
                    spriteNum = 28;
                }
                image = sprites[spriteNum];
                break;
            case "left":
                if (spriteNum > 17 || spriteNum < 9) {
                    spriteNum = 9;
                }
                image = sprites[spriteNum];
                break;
        }
        g2.drawString(name.substring(1), xCoordinate + 35, yCoordinate);
        g2.drawImage(image, xCoordinate , yCoordinate, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);
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
