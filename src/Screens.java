import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Screens {
    private GamePanel gamePanel;
    public Font immortal;
    public int optionNum;

    public Screens (GamePanel gp) {
        gamePanel = gp;
        InitializeFont();
    }

    public void InitializeFont(){
        try{
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
        g.setFont(immortal.deriveFont(Font.BOLD,77F));
        String text = "TEST";
        int x = gamePanel.screenWidth / 2 - 144;
        int y = gamePanel.tileSize * 3;

        g.setColor(Color.gray);
        g.drawString(text, x + 2, y+5);

        g.setColor(Color.white);
        g.drawString(text, x, y);


        //x = gamePanel.screenWidth / 2 - (gamePanel.tileSize * 4) / 2 - 175;
        // y += gamePanel.tileSize * 2;
        // g.drawImage(gamePanel.player.down1, x , y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);

        g.setFont(immortal.deriveFont(Font.BOLD,77F));

        text = "NEW GAME";
        y += gamePanel.tileSize * 6;
        g.drawString(text, x, y);
        if(optionNum == 0)
        {
            g.drawString(">", x - gamePanel.tileSize, y - 8);
        }

        text = "LOAD GAME";
        y += 100;
        g.drawString(text, x, y);
        if(optionNum == 1)
        {
            g.drawString(">", x - gamePanel.tileSize, y - 8);
        }

        text = "QUIT";
        y += 100;
        g.drawString(text, x, y);
        if(optionNum == 2)
        {
            g.drawString(">", x - gamePanel.tileSize, y - 8);
        }
    }

    public void drawInGameMenu(Graphics2D g) {

    }

    public void drawBattle (Graphics2D g) {

    }

    public void drawDeathScreen(Graphics2D g) {

    }
}
