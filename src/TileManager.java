import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class TileManager {

    public Tile[][] tutorialWorldMap;
    private GamePanel gp;
    private Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[8500];
        tutorialWorldMap = new Tile[85][100];
        loadMap();
    }


    public void draw(Graphics2D g2) {
        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.JRPG.player.worldX + gp.JRPG.player.screenX;
            int screenY = worldY - gp.JRPG.player.worldY + gp.JRPG.player.screenY;
            if (worldX + gp.tileSize > gp.JRPG.player.worldX - gp.JRPG.player.screenX &&
                    worldX - gp.tileSize * 2 < gp.JRPG.player.worldX + gp.JRPG.player.screenX &&
                    worldY + gp.tileSize > gp.JRPG.player.worldY - gp.JRPG.player.screenY &&
                    worldY - gp.tileSize * 2 < gp.JRPG.player.worldY + gp.JRPG.player.screenY) {
                g2.drawImage(tutorialWorldMap[worldRow][worldCol].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
            File file = new File("maps/Tutorial/OverWorldData");
            Scanner reader = new Scanner(file);
            for (int x = 0; x < 85; x++) {
                String[] row = reader.nextLine().split(" ");
                for (int y = 0; y < 100; y++) {
                    String tileNum = num + "";
                    while (tileNum.length() < 2) {
                        tileNum = "0" + tileNum;
                    }
                    tutorialWorldMap[x][y] = new Tile(row[y]);
                    tutorialWorldMap[x][y].image = ImageIO.read(new File("maps/Tutorial/OverWorld/" + tileNum + "_TutorialOverWorld.png"));
                    num++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
