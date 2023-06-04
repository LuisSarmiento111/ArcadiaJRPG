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
    private boolean collisionOn;
    public int screenX;
    public int screenY;
    private String spriteUID;
    private final int  walkSpeed = 1;
    public final BufferedImage[] sprites = new BufferedImage[36];
    public int saveSlot;

    private final String[][] classes = {{"Knight", "8"}, {"Mage", "5"}};
    private final GamePanel gamePanel;

    public Player(JRPG game, GamePanel gamePanel, String name, String playerClass, int saveSlot) {
        super(name, game, gamePanel);
        worldX = 35 * gamePanel.tileSize;
        screenX = (gamePanel.screenWidth - gamePanel.tileSize)/ 2-  gamePanel.tileSize / 2;
        worldY = 55 * gamePanel.tileSize;
        screenY = (gamePanel.screenHeight - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBox.width = 24;
        hitBox.height = 24;

        this.playerClass = playerClass;
        direction = "up";
        spriteUID = "022612111220";
        setStats();
        playerLevel = 1;
        experience = 0;
        this.saveSlot = saveSlot;

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
                    sprites[index] = ImageIO.read(new File(("sprites/" + spriteUID + ".png"))).getSubimage(x, y, 64, 64);
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
        if (gamePanel.inGame && !gamePanel.menuScreen) {
            if (gamePanel.keyHandler.isUpPressed() || gamePanel.keyHandler.isDownPressed() ||
                    gamePanel.keyHandler.isLeftPressed() || gamePanel.keyHandler.isRightPressed() || gamePanel.keyHandler.isShiftPressed()){
                if (gamePanel.keyHandler.isUpPressed()) {
                    direction = "up";
                    worldY -= walkSpeed;
                }
                if (gamePanel.keyHandler.isDownPressed()) {
                    direction = "down";
                    worldY += walkSpeed;
                } else
                if (gamePanel.keyHandler.isRightPressed()) {
                    direction = "right";
                    worldX += walkSpeed;
                } else
                if (gamePanel.keyHandler.isLeftPressed()) {
                    direction = "left";
                    worldX -= walkSpeed;
                }
                if(gamePanel.keyHandler.isMenuPressed()) {
                    gamePanel.titleScreen = true;
                    gamePanel.inGame = false;
                }

                collisionOn = false;
                // gamePanel.collisionChecker.checkTile(this);
                spriteCounter++;
                if (spriteCounter > 7) {
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
        // g2.drawString(name, screenX + 35 - name.length() / 2, screenY);
        g2.drawImage(image, screenX , screenY, 64, 64, null);
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
