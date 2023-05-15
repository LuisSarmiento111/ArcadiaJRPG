import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Screens {
    public Font immortal;
    public int optionNum;
    public int pgNum;
    public String textBox;
    private String confirmationBox;
    private GamePanel gamePanel;

    public Screens(GamePanel gp) {
        gamePanel = gp;
        textBox = "";
        pgNum = -1;
        InitializeFont();
    }

    public void InitializeFont() {
        try {
            InputStream is = new FileInputStream("Font/IMMORTAL.ttf");
            immortal = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void drawTitleScreen(Graphics2D g) {
        gamePanel.setBackground(Color.blue);
        g.setFont(immortal.deriveFont(Font.BOLD, 77F));
        String text = "TEST";
        int x = gamePanel.screenWidth / 2 - 144;
        int y = gamePanel.tileSize * 3;

        g.setColor(Color.black);
        g.drawString(text, x + 2, y + 5);

        g.setColor(Color.black);
        g.drawString(text, x, y);


        //x = gamePanel.screenWidth / 2 - (gamePanel.tileSize * 4) / 2 - 175;
        // y += gamePanel.tileSize * 2;
        // g.drawImage(gamePanel.player.down1, x , y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);

        g.setFont(immortal.deriveFont(Font.BOLD, 77F));

        text = "NEW GAME";
        y += gamePanel.tileSize * 6;
        g.drawString(text, x, y);
        if (optionNum == 0) {
            g.drawString(">", x - gamePanel.tileSize, y - 8);
        }

        text = "LOAD GAME";
        y += 100;
        g.drawString(text, x, y);
        if (optionNum == 1) {
            g.drawString(">", x - gamePanel.tileSize, y - 8);
        }

        text = "QUIT";
        y += 100;
        g.drawString(text, x, y);
        if (optionNum == 2) {
            g.drawString(">", x - gamePanel.tileSize, y - 8);
        }
    }

    public void drawCharacterCreation(Graphics2D g) {
        gamePanel.setBackground(Color.blue);
        if (pgNum == 0) {
            g.setFont(immortal.deriveFont(Font.PLAIN, 77F));
            String text = "What is your name?";
            int x = gamePanel.screenWidth / 3 - 144;
            int y = gamePanel.tileSize * 3;

            g.setColor(Color.black);
            g.drawString(text, x + 2, y + 5);

            y += gamePanel.tileSize * 6;
            g.drawString(textBox, x, y);
        } else if (pgNum == 1) {
            g.setFont(immortal.deriveFont(Font.PLAIN, 77F));
            String text = "Choose a class";
            int x = gamePanel.screenWidth / 3;
            int y = gamePanel.tileSize * 3;

            g.setColor(Color.black);
            g.drawString(text, x + 2, y + 5);

            g.setFont(immortal.deriveFont(Font.BOLD, 77F));

            text = "Paladin";
            y += gamePanel.tileSize * 6;
            x = 200;
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

            /*
        String text = "TEST";
        int x = gamePanel.screenWidth / 3 - 144;
        int y = gamePanel.tileSize * 3;

        g.setColor(Color.black);
        g.drawString(text, x + 2, y+5);

        y += gamePanel.tileSize * 6;
        g.drawString("BANANANAA", x, y);

             */

    }

    public void drawInGameMenu(Graphics2D g) {

    }

    public void drawBattle(Graphics2D g) {

    }

    public void drawDeathScreen(Graphics2D g) {

    }
}
