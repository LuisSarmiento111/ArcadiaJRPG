import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    private final String[][] classes = {{"Knight", "8"}, {"Mage", "5"}};
    public int saveSlot;
    public int storyProgress;
    public ArrayList<String> inventory;
    public String playerClass;
    public int playerLevel;
    public boolean attacking;
    public int nextLvlExp;
    public int statPoints;

    public Player(GamePanel gamePanel, String name, String playerClass, int saveSlot) {
        super(name, gamePanel);
        worldX = 49 * gamePanel.tileSize;
        screenX = (gamePanel.screenWidth - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
        worldY = 72 * gamePanel.tileSize;
        screenY = (gamePanel.screenHeight - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
        hitBox.x = 22;
        hitBox.y = 32;
        hitBox.width = 36;
        hitBox.height = 48;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        this.playerClass = playerClass;
        collisionOn = false;
        direction = "up";
        setStats();
        walkSpeed = 2;
        playerLevel = 1;
        statPoints = 0;
        nextLvlExp = 5;
        exp = 0;

        setStats();
        storyProgress = 0;
        this.saveSlot = saveSlot;

        sprites = new BufferedImage[36];
        getPlayerImage();
        battleSprites = new BufferedImage[6];
        // getBattleImage();
    }

    public Player(GamePanel gp, String[] data, int saveSlot) {
        super(data[0], gp);
        playerClass = data[1];
        worldX = Integer.parseInt(data[2]);
        worldY = Integer.parseInt(data[3]);
        screenX = (gamePanel.screenWidth - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
        screenY = (gamePanel.screenHeight - gamePanel.tileSize) / 2 - gamePanel.tileSize / 2;
        hitBox.x = 22;
        hitBox.y = 32;
        hitBox.width = 36;
        hitBox.height = 48;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        collisionOn = false;
        direction = data[7];
        walkSpeed = 2;
        playerLevel = Integer.parseInt(data[4]);
        exp = Integer.parseInt(data[5]);
        health = Integer.parseInt(data[6]);
        maxHealth = Integer.parseInt(data[7]);
        speed = Integer.parseInt(data[8]);
        strength = Integer.parseInt(data[9]);
        magicPower = Integer.parseInt(data[10]);
        defense = Integer.parseInt(data[11]);
        statPoints = Integer.parseInt(data[12]);
        nextLvlExp = Integer.parseInt(data[13]);
        storyProgress = Integer.parseInt(data[14]);
        this.saveSlot = saveSlot;

        sprites = new BufferedImage[36];
        getPlayerImage();
        battleSprites = new BufferedImage[6];
        // getBattleImage();
    }

    public void getPlayerImage() {
        int index = 0;
        int y = 512;
        try {
            for (int i = 0; i < 4; i++) {
                int x = 0;
                for (int j = 0; j < 9; j++) {
                    sprites[index] = ImageIO.read(new File(("sprites/Player/Player_Normal.png"))).getSubimage(x, y, 64, 64);
                    x += 64;
                    index++;
                }
                y += 64;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBattleImage() {
        int index = 0;
        int y = 1920;
        try {
            int x = 0;
            for (int i = 0; i < 6; i++) {
                battleSprites[index] = ImageIO.read(new File(("sprites/Player/Player_" + playerClass + ".png"))).getSubimage(x, y, 192, 192);
                x += 192;
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStats() {
        if (playerClass.equals("Knight")) {
            health = 20;
            maxHealth = health;
            speed = 3;
            strength = 6;
            magicPower = 1;
            defense = 3;
        } else if (playerClass.equals("Mage")) {
            health = 14;
            maxHealth = health;
            speed = 3;
            strength = 3;
            magicPower = 6;
            defense = 2;
        } else if (playerClass.equals("Rouge")) {
            health = 16;
            maxHealth = health;
            speed = 6;
            strength = 3;
            magicPower = 2;
            defense = 3;
        } else {
            health = 22;
            maxHealth = health;
            speed = 2;
            strength = 4;
            magicPower = 0;
            defense = 6;
        }
    }

    public void update() {
        if (gamePanel.inGame && !gamePanel.menuScreen) {
            if (gamePanel.keyHandler.isUpPressed() || gamePanel.keyHandler.isDownPressed() ||
                    gamePanel.keyHandler.isLeftPressed() || gamePanel.keyHandler.isRightPressed() || gamePanel.keyHandler.isShiftPressed()) {
                if (gamePanel.keyHandler.isUpPressed()) {
                    direction = "up";
                }
                if (gamePanel.keyHandler.isDownPressed()) {
                    direction = "down";
                }
                if (gamePanel.keyHandler.isRightPressed()) {
                    direction = "right";

                }
                if (gamePanel.keyHandler.isLeftPressed()) {
                    direction = "left";

                }
                if (gamePanel.keyHandler.isMenuPressed()) {
                    gamePanel.titleScreen = true;
                    gamePanel.inGame = false;
                }

                collisionOn = false;
                gamePanel.collisionChecker.checkTile(this);

                int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.JRPG.NPC);
                interactNPC(npcIndex);

                int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.JRPG.monsters);
                contactMonster(monsterIndex);

                if (!collisionOn) {
                    switch (direction) {
                        case "up":
                            worldY -= walkSpeed;
                            break;
                        case "down":
                            worldY += walkSpeed;
                            break;
                        case "left":
                            worldX -= walkSpeed;
                            break;
                        case "right":
                            worldX += walkSpeed;
                            break;
                    }
                }
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
                if (spriteNum > 35 || spriteNum < 27) {
                    spriteNum = 27;
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
        int size = 80;
        if (gamePanel.battleScreen) {
            image = battleSprites[0];
            double oneScale = (double) 100 / maxHealth;
            double hpBarValue = oneScale * health;
            size = 400;
            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(750, gamePanel.screenHeight - 145, 100, 22);
            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(750, gamePanel.screenHeight - 146, (int) hpBarValue, 20);
            g2.setFont(gamePanel.screens.pixel.deriveFont(Font.PLAIN, 20));
            g2.setColor(Color.WHITE);
            g2.drawString(health + "/" + maxHealth, 750, gamePanel.screenHeight - 128);
        }
        g2.drawImage(image, screenX, screenY, size, size, null);
        //g2.setColor(Color.RED);
        // g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
    }


    public void attack(Entity target) {
        int damage = (strength - 4);
        if (damage < 0) {
            damage = 0;
        }
        damage = ((int) (Math.random() * 6) + 1) + damage;
        if (target.defense > strength) {
            damage = Math.abs(target.defense - damage) + 1;
        } else if (target.defense < strength) {
            damage += target.defense / 2;
        } else {
        }
        gamePanel.screens.addMessage(name + " did " + damage + " damage to " + target.name);
        target.health -= damage;
        gamePanel.JRPG.turn++;
        turn = false;
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 100) {
            spriteNum = 0;
        } else if (spriteCounter <= 200) {
            spriteNum = 1;
        } else if (spriteCounter <= 300) {
            spriteNum = 2;
        } else if (spriteCounter <= 400) {
            spriteNum = 3;
        } else if (spriteCounter <= 500) {
            spriteNum = 4;
        } else if (spriteCounter <= 600) {
            spriteNum = 5;
        } else {
            spriteNum = 0;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            System.out.println("Hitting Eric");
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (gamePanel.inGame) {
                gamePanel.inGame = false;
                gamePanel.battleScreen = true;
                gamePanel.JRPG.monsters[i] = null;
            }
        }
    }

    public void checkLvlUp() {
        if (exp >= nextLvlExp) {
            playerLevel++;
            maxHealth += 2;
            strength++;
            magicPower++;
            defense++;
            speed++;
            nextLvlExp = nextLvlExp * 2;
            statPoints += 3;
            gamePanel.screens.addMessage(name + " leveled up level " + playerLevel);
            gamePanel.screens.addMessage("Gained 3 stat points");
        }
    }
}
