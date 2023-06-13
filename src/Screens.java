import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Screens {
    public Font immortal;
    public Font pixel;
    public int optionNum;
    public int pgNum;
    public String textBox;
    public boolean messageOn;
    public ArrayList<String> message = new ArrayList<String>();
    ArrayList<Integer> messageCounter = new ArrayList<Integer>();
    private GamePanel gamePanel;

    public Screens(GamePanel gp) {
        gamePanel = gp;
        textBox = "";
        pgNum = -1;
        messageOn = false;
        InitializeFont();
    }

    public void InitializeFont() {
        try {
            InputStream is = new FileInputStream("fonts/IMMORTAL.ttf");
            immortal = Font.createFont(Font.TRUETYPE_FONT, is);
            is = new FileInputStream("fonts/VCR_OSD_MONO_1.001.ttf");
            pixel = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void drawTitleScreen(Graphics2D g) {
        g.setFont(immortal.deriveFont(Font.BOLD, 77F));
        String text = "LEGEND OF ARCADIA";
        int x = gamePanel.screenWidth / 2;
        int y = (int) (gamePanel.screenHeight * .1);

        g.setColor(Color.black);
        g.drawString(text, x - getCenterOfText(text, g) + 2, y + 5);

        g.setColor(Color.black);
        g.drawString(text, x - getCenterOfText(text, g), y);


        g.setFont(immortal.deriveFont(Font.BOLD, 77F));

        text = "NEW GAME";
        y = gamePanel.screenHeight / 3;
        g.drawString(text, x - getCenterOfText(text, g), y);
        if (optionNum == 0) {
            g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }

        text = "LOAD GAME";
        y += 100;
        g.drawString(text, x - getCenterOfText(text, g), y);
        if (optionNum == 1) {
            g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }

        text = "QUIT";
        y += 100;
        g.drawString(text, x - getCenterOfText(text, g), y);
        if (optionNum == 2) {
            g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }
    }

    public void drawCharacterCreation(Graphics2D g) {
        gamePanel.setOpaque(true);
        gamePanel.setBackground(Color.BLACK);
        if (pgNum == 0) {
            g.setFont(immortal.deriveFont(Font.PLAIN, 77F));
            String text = "What is your name?";
            int x = gamePanel.screenWidth / 2;
            int y = (int) (gamePanel.screenHeight * .1);

            g.setColor(Color.black);
            g.drawString(text, x - getCenterOfText(text, g), y + 5);

            x = (int) (gamePanel.screenWidth * .1);
            y += gamePanel.tileSize * 6;
            g.setColor(Color.black);
            g.drawRect(x, y, (int) (gamePanel.screenWidth * .8), 80);


            x = (int) (gamePanel.screenWidth * .1);
            g.setColor(Color.BLACK);
            g.drawString(textBox, x + 25, y + 60);

        } else if (pgNum == 1) {
            g.setFont(immortal.deriveFont(Font.PLAIN, 77F));
            String text = "Select your class!";
            int x = gamePanel.screenWidth / 2;
            int y = (int) (gamePanel.screenHeight * .1);

            g.setColor(Color.black);
            g.drawString(text, x - getCenterOfText(text, g), y + 5);

            g.setFont(immortal.deriveFont(Font.BOLD, 77F));

            text = "Knight";
            y = gamePanel.screenHeight / 2;
            x = (int) (gamePanel.screenWidth * .1);
            g.drawString(text, x, y);
            if (optionNum == 0) {
                g.drawString(">", x - gamePanel.tileSize, y - 8);
            }
            text = "Mage";
            x += 430;
            g.drawString(text, x, y);
            if (optionNum == 1) {
                g.drawString(">", x - gamePanel.tileSize, y - 8);
            }
            text = "Rouge";
            x += 430;
            g.drawString(text, x, y);
            if (optionNum == 2) {
                g.drawString(">", x - gamePanel.tileSize, y - 8);
            }
            text = "Tank";
            x += 430;
            g.drawString(text, x, y);
            if (optionNum == 3) {
                g.drawString(">", x - gamePanel.tileSize, y - 8);
            }
        }
    }


    public void drawInGameMenu(Graphics2D g) {
        g.setColor(Color.BLACK);
        String text = "PAUSED";
        g.setFont(immortal.deriveFont(Font.BOLD, 80F));
        int x = gamePanel.screenWidth / 2;
        int y = 250;
        g.drawString(text, x - getCenterOfText(text, g), y);

        g.setFont(immortal.deriveFont(Font.BOLD, 77F));

        text = "RESUME";
        y += gamePanel.tileSize * 6;
        g.drawString(text, x - getCenterOfText(text, g), y);
        if (optionNum == 0) {
            g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }

        text = "SAVE GAME";
        y += 100;
        g.drawString(text, x - getCenterOfText(text, g), y);
        if (optionNum == 1) {
            g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }

        text = "SETTINGS";
        y += 100;
        g.drawString(text, x - getCenterOfText(text, g), y);
        if (optionNum == 2) {
            g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }

        text = "MAIN MENU";
        y += 100;
        g.drawString(text, x - getCenterOfText(text, g), y);
        if (optionNum == 3) {
            g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }
    }

    public void drawCharacterSelection(Graphics2D g) {
        g.setColor(Color.BLACK);
        String text = "Select a load slot:";
        g.setFont(immortal.deriveFont(Font.BOLD, 80F));
        int x = gamePanel.screenWidth / 2;
        int y = 250;
        g.drawString(text, x - getCenterOfText(text, g), y);

        y += gamePanel.tileSize * 6;
        ArrayList<String> playerSlotData = PlayerDataStorage.getPlayerData();
        for (int i = 0; i < playerSlotData.size(); i++) {
            g.setFont(immortal.deriveFont(Font.BOLD, 77F));
            x = 600;
            text = "LOAD " + (i + 1) + ": ";
            String slot = playerSlotData.get(i);
            if (!slot.contains("|")) {
                text += "EMPTY";
            } else {
                slot = slot.substring(slot.indexOf(":") + 2);
                String[] data = slot.split("\\|");
                text += data[0];
            }
            g.drawString(text, x, y);
            if (optionNum == i) {
                g.drawString(">", x - gamePanel.tileSize, y - 8);
            }
            y += 100;
        }
        if (pgNum == 1) {
            y = 325;
            g.setFont(immortal.deriveFont(Font.BOLD, 40));
            text = "NO CHARACTER DATA FOR SELECTED SLOT";
            x = (gamePanel.screenWidth / 2) - getCenterOfText(text, g);
            g.drawString(text, x, y);
            y += 50;
            text = "WOULD YOU LIKE TO CREATE A NEW CHARACTER FOR?";
            x = (gamePanel.screenWidth / 2) - getCenterOfText(text, g);
            g.drawString(text, x, y);
            y += 50;
            text = "YES";
            x = gamePanel.screenWidth / 2 - getCenterOfText(text, g) * 4;
            g.drawString(text, x, y);
            if (optionNum == 5) {
                g.drawString(">", x - gamePanel.tileSize, y - 8);
            }
            x = gamePanel.screenWidth / 2 + getCenterOfText(text, g) * 2;
            text = "NO";
            g.drawString(text, x, y);
            if (optionNum == 6) {
                g.drawString(">", x - gamePanel.tileSize, y - 8);
            }
        }
        g.setFont(immortal.deriveFont(Font.BOLD, 40));
        g.drawString("BACK TO MENU", 25, gamePanel.tileSize);
        if (optionNum == 4) {
            g.drawString(">", 5, gamePanel.tileSize);
        }

    }


    public void drawBattle(Graphics2D g) {
        try {
            BufferedImage background = ImageIO.read(new File(("maps/Tutorial/forest_background.jpg")));
            g.drawImage(background, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight - 300, null);
            g.setColor(new Color(0, 0, 0, 75));
            int y = gamePanel.screenHeight - 300;
            int x = 0;
            g.fillRect(x, y, gamePanel.screenWidth, 300);
            g.fillRect(x, y, 640, 300);

            g.setColor(Color.WHITE);
            g.setFont(pixel.deriveFont(Font.BOLD, 50));
            g.drawString("Turn:" + gamePanel.JRPG.turn, 25, 200);
            g.drawString(gamePanel.JRPG.entities.get((gamePanel.JRPG.turn % (gamePanel.JRPG.entities.size()))).name, 25, 250);
            g.setFont(pixel.deriveFont(Font.BOLD, 40));
            String text = "Attack";
            x += 320;
            y += 50;
            g.drawString(text, x - getCenterOfText(text, g), y);
            if (pgNum == 1 && optionNum == 0) {
                g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
            }

            text = "Skill";
            y += 50;
            g.drawString(text, x - getCenterOfText(text, g), y);
            if (pgNum == 1 && optionNum == 1) {
                g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
            }

            text = "Item";
            y += 50;
            g.drawString(text, x - getCenterOfText(text, g), y);
            if (pgNum == 1 && optionNum == 2) {
                g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
            }
            x = 750;
            y = gamePanel.screenHeight - 250;
            g.drawImage(gamePanel.JRPG.player.sprites[16].getSubimage(gamePanel.JRPG.player.hitBoxDefaultX - 2,
                    15, 24, 22), x, y, 100, 100, null);
            g.drawString(gamePanel.JRPG.player.name, x + 50 - getCenterOfText(gamePanel.JRPG.player.name, g), y);
            if (pgNum == 0) {
                g.setColor(Color.RED);
                g.drawRect((optionNum + 2) * 320, gamePanel.screenHeight - 300, 320, 300);
            }
            if (pgNum == 2 && gamePanel.JRPG.entities.size() > 1) {
                Entity entity = gamePanel.JRPG.entities.get(optionNum);
                text = "v";
                g.drawString(text, entity.screenX + entity.hitBox.x + entity.hitBox.width / 2 - getCenterOfText(text, g),
                        entity.screenY + entity.hitBox.height / 2 - 8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawStatsScreen(Graphics2D g) {
        int x = gamePanel.screenWidth / 2 - 300;
        int y = 100;
        String text = "";
        g.setColor(Color.WHITE);
        g.fillRect(x - 2, y - 2, 604, 304);
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 600, 300);
        x += 15;
        y += 30;
        g.setColor(Color.WHITE);
        g.setFont(pixel.deriveFont(Font.BOLD, 25));
        text = "Level:" + gamePanel.JRPG.player.playerLevel;
        g.drawString(text, x, y);
        y += 27;
        text = "Class:" + gamePanel.JRPG.player.playerClass;
        g.drawString(text, x, y);
        y += 27;
        text = "Max Health:" + gamePanel.JRPG.player.maxHealth;
        g.drawString(text, x, y);
        if (gamePanel.JRPG.player.statPoints > 0) {
            g.drawString("[+]", x + 2 * getCenterOfText(text, g) + 15, y);
        }
        text = "Strength:" + gamePanel.JRPG.player.strength;
        y += 27;
        g.drawString(text, x, y);
        if (gamePanel.JRPG.player.statPoints > 0) {
            g.drawString("[+]", x + 2 * getCenterOfText(text, g) + 15, y);
        }
        text = "Speed:" + gamePanel.JRPG.player.speed;
        y += 27;
        g.drawString(text, x, y);
        if (gamePanel.JRPG.player.statPoints > 0) {
            g.drawString("[+]", x + 2 * getCenterOfText(text, g) + 15, y);
        }
        text = "Magic Power:" + gamePanel.JRPG.player.magicPower;
        y += 27;
        g.drawString(text, x, y);
        if (gamePanel.JRPG.player.statPoints > 0) {
            g.drawString("[+]", x + 2 * getCenterOfText(text, g) + 15, y);
        }
        text = "Defense:" + gamePanel.JRPG.player.defense;
        y += 27;
        g.drawString(text, x, y);
        if (gamePanel.JRPG.player.statPoints > 0) {
            g.drawString("[+]", x + 2 * getCenterOfText(text, g) + 15, y);
        }
        y += 27;
        g.drawString("Next Level:" + gamePanel.JRPG.player.nextLvlExp, x, y);
        y += 50;
        g.drawString("Stat Points:" + gamePanel.JRPG.player.statPoints, x, y);
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void drawMessage(Graphics2D g) {
        int messageX = gamePanel.tileSize;
        int messageY = gamePanel.tileSize * 8;
        g.setFont(pixel.deriveFont(Font.BOLD, 25));
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g.setColor(Color.BLACK);
                g.drawString(message.get(i), messageX + 2, messageY);
                g.setColor(Color.WHITE);
                g.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 25;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawDeathScreen(Graphics2D g) {

    }

    public int getCenterOfText(String text, Graphics2D g) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        return length / 2;
    }
}
