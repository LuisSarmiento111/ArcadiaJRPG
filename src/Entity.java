import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    public String name;
    public int speed;
    public int strength;
    public int health;
    public int maxHealth;
    public int magicPower;
    public int defense;
    public int exp;
    public int worldX;
    public int worldY;
    public int screenX;
    public int screenY;
    public String direction;
    public Rectangle hitBox;
    public int hitBoxDefaultX;
    public int hitBoxDefaultY;
    public boolean collisionOn;
    public int walkSpeed;
    public GamePanel gamePanel;
    public BufferedImage[] sprites;
    public BufferedImage[] battleSprites;
    public int spriteInterval;
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public int actionLockCounter = 0;
    public int waitCounter = 0;
    public boolean turn;

    public Entity(String name, GamePanel panel) { // maybe remove the parameters for the different attributes since they're set to default values
        this.name = name;
        gamePanel = panel;
        hitBox = new Rectangle();
        waitCounter = 0;
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        if (gamePanel.inGame) {
            screenX = worldX - gamePanel.JRPG.player.worldX + gamePanel.JRPG.player.screenX;
            screenY = worldY - gamePanel.JRPG.player.worldY + gamePanel.JRPG.player.screenY;
        }
        if (gamePanel.battleScreen || worldX + gamePanel.tileSize > gamePanel.JRPG.player.worldX - gamePanel.JRPG.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.JRPG.player.worldX + gamePanel.JRPG.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.JRPG.player.worldY - gamePanel.JRPG.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.JRPG.player.worldY + gamePanel.JRPG.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum > spriteInterval - 1 || spriteNum < 0) {
                        spriteNum = 0;
                    }
                    image = sprites[spriteNum];
                    break;
                case "down":
                    if (spriteNum > spriteInterval * 3 - 1 || spriteNum < spriteInterval * 2) {
                        spriteNum = spriteInterval * 2;
                    }
                    image = sprites[spriteNum];
                    break;
                case "right":
                    if (spriteNum > spriteInterval * 4 - 1 || spriteNum < spriteInterval * 3) {
                        spriteNum = spriteInterval * 3;
                    }
                    image = sprites[spriteNum];
                    break;
                case "left":
                    if (spriteNum > spriteInterval * 2 - 1 || spriteNum < spriteInterval) {
                        spriteNum = spriteInterval;
                    }
                    image = sprites[spriteNum];
                    break;
            }


            int size = 80;
            if (gamePanel.battleScreen) {
                double oneScale = (double) hitBox.width / maxHealth;
                double hpBarValue = oneScale * health;
                size = 140;
                g.setColor(new Color(35, 35, 35));
                g.fillRect(screenX + hitBox.x - 1, screenY + hitBox.y - 16, hitBox.width + 2, 12);
                g.setColor(new Color(255, 0, 30));
                g.fillRect(screenX + hitBox.x, screenY + hitBox.y - 15, (int) hpBarValue, 10);
            }
            g.drawImage(image, screenX, screenY, size, size, null);
            g.setColor(Color.RED);
            g.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
        }
    }


    public void update() {
        if (!gamePanel.menuScreen && !gamePanel.characterStatsScreen) {
            setAction();
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.JRPG.NPC);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.JRPG.monsters);
            gamePanel.collisionChecker.checkPlayer(this);

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

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            int num = (int) (Math.random() * 100) + 1;

            if (num <= 25) {
                direction = "up";
            } else if (num <= 50) {
                direction = "down";
            } else if (num <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void battleTurn(ArrayList<Entity> entities) {
        waitCounter++;
        if (waitCounter >= 120) {
            waitCounter = 0;
            attack(entities.get(0));
        }
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
        }
        target.health -= damage;
        gamePanel.screens.addMessage(name + " did " + damage + " damage to " + target.name);
        gamePanel.JRPG.turn++;
        turn = false;
    }


}
