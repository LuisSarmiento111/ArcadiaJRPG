import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision;
    public Tile (String collision) {
        if (collision.equals("1")) {
            this.collision = true;
        } else {
            this.collision = false;
        }
    }


}
