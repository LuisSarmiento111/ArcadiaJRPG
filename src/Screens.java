import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Screens {
    public Font immortal;
    public int optionNum;
    public int pgNum;
    public String textBox;
    private GamePanel gamePanel;

    public Screens(GamePanel gp) {
        gamePanel = gp;
        textBox = "";
        pgNum = -1;
        InitializeFont();
    }

    public void InitializeFont() {
        try {
            InputStream is = new FileInputStream("fonts/IMMORTAL.ttf");
            immortal = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void drawTitleScreen(Graphics2D g) {
        g.setFont(immortal.deriveFont(Font.BOLD, 77F));
        String text = "TEST";
        int x = gamePanel.screenWidth / 2;
        int y = (int) (gamePanel.screenHeight * .1);

        g.setColor(Color.black);
        g.drawString(text, x - getCenterOfText(text, g)+ 2, y + 5);

        g.setColor(Color.black);
        g.drawString(text, x- getCenterOfText(text, g), y);


        g.setFont(immortal.deriveFont(Font.BOLD, 77F));

        text = "NEW GAME";
        y += gamePanel.tileSize * 6;
        g.drawString(text, x- getCenterOfText(text, g), y);
        if (optionNum == 0) {
            g.drawString(">", x- getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }

        text = "LOAD GAME";
        y += 100;
        g.drawString(text, x- getCenterOfText(text, g), y);
        if (optionNum == 1) {
            g.drawString(">", x- getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
        }

        text = "QUIT";
        y += 100;
        g.drawString(text, x- getCenterOfText(text, g), y);
        if (optionNum == 2) {
            g.drawString(">", x- getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
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

            text = "Paladin";
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
            text = "Fighter";
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
         g.drawString(text, x- getCenterOfText(text, g), y);
         if (optionNum == 2) {
             g.drawString(">", x - getCenterOfText(text, g) - gamePanel.tileSize, y - 8);
         }

         text = "MAIN MENU";
         y += 100;
         g.drawString(text, x- getCenterOfText(text, g), y);
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
            text = "LOAD " +(i + 1) + ": ";
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
            g.drawString(text,x, y);
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

    }

    public void drawDeathScreen(Graphics2D g) {

    }

    public int getCenterOfText(String text, Graphics2D g) {
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        return length / 2;
    }
}
