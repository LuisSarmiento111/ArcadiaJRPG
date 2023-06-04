import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TileManager {

    private GamePanel gp;
    private Tile[] tiles;
    public Tile[][] worldMap;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[4900];
        worldMap = new Tile[70][70];
        // getTitleImage();
        loadMap();
    }

    public void getTitleImage() {
        try {
            for (int i = 0; i < 4900; i++) {
                String tileNum = i + "";
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(new File("tiles/tile" + tileNum + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < gp.maxWorldCol &&  worldRow < gp.maxWorldRow) {
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.JRPG.player.worldX + gp.JRPG.player.screenX;
            int screenY = worldY - gp.JRPG.player.worldY + gp.JRPG.player.screenY;
            if (worldX + gp.tileSize > gp.JRPG.player.worldX - gp.JRPG.player.screenX &&
                    worldX - gp.tileSize < gp.JRPG.player.worldX + gp.JRPG.player.screenX &&
                    worldY + gp.tileSize > gp.JRPG.player.worldY - gp.JRPG.player.screenY &&
                    worldY - gp.tileSize < gp.JRPG.player.worldY + gp.JRPG.player.screenY) {
                g2.drawImage(worldMap[worldRow][worldCol].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public void loadMap() {
        int num = 0;
        try {
            for (int x = 0; x < 70; x++) {
                for (int y = 0; y < 70; y++) {
                    String tileNum = num + "";
                    while (tileNum.length() < 2) {
                        tileNum = "0" + tileNum;
                    }
                    System.out.println("" + tileNum + "  " + x + "  " + y);
                    worldMap[x][y] = new Tile();
                    worldMap[x][y].image = ImageIO.read(new File("maps/map/" + tileNum + "_map.png"));
                    num++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
