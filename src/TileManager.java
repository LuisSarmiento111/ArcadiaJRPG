import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TileManager {

    private GamePanel gp;
    private Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[3];
        getTitleImage();
    }

    public void getTitleImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(new File("tiles/tile000.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(new File("tiles/tile001.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(new File("tiles/tile002.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(tiles[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
    }

}
