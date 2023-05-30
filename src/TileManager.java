import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TileManager {

    private GamePanel gp;
    private Tile[] tiles;
    private Tile[][] worldMap;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[1000];
        worldMap = new Tile[50][25];
        getTitleImage();
        loadMap();
    }

    public void getTitleImage() {
        try {
            for (int i = 0; i < 1000; i++) {
                String tileNum = ("00" + i);
                tileNum = tileNum.substring(tileNum.length() - 3);
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(new File("tiles/tile" + tileNum + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;
        while (row < 50 &&  col < 25) {
            g2.drawImage(worldMap[row][col].image, x, y, gp.tileSize, gp.tileSize, null);
            y += 48;
            if (y >= gp.screenHeight) {
                y = 0;
                row++;
                x += 48;
            }
        }
    }

    public void loadMap() {
        try {
            for (int x = 0; x < 50; x++) {
                for (int y = 0; y < 25; y++) {
                    String tileNum = ("00" + ((x+1 * y+1) -1 ));
                    tileNum = tileNum.substring(tileNum.length() - 3);
                    worldMap[x][y] = new Tile();
                    worldMap[x][y].image = ImageIO.read(new File("tiles/tile" +  tileNum + ".png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
