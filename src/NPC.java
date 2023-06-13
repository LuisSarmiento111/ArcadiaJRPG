import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity {


    public NPC(GamePanel gp) {
        super("Eric", gp);
        direction = "up";
        speed = 2;
        hitBox.x = 22;
        hitBox.y = 32;
        hitBox.width = 36;
        hitBox.height = 48;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        walkSpeed = 1;

        spriteInterval = 9;
        sprites = new BufferedImage[spriteInterval * 4];
        getSpriteImage();
    }

    public void getSpriteImage() {
        int index = 0;
        int y = 512;
        try {
            for (int i = 0; i < 4; i++) {
                int x = 0;
                for (int j = 0; j < 9; j++) {
                    sprites[index] = ImageIO.read(new File(("sprites/Guide/Guide.png"))).getSubimage(x, y, 64, 64);
                    x += 64;
                    index++;
                }
                y += 64;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
