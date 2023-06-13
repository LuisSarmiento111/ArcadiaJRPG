import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Slime extends Entity {

    public final double scale = 1 + (gamePanel.JRPG.player.playerLevel / 5.0);

    public Slime(GamePanel gp) {
        super("Slime", gp);
        walkSpeed = 1;
        health = (int) (5 * scale);
        maxHealth = health;
        strength = (int) (2 * scale);
        magicPower = (int) (2 * scale);
        speed = (int) (3 * scale);
        defense = (int) (2 * scale);
        exp = (int) (2 * scale);
        int directionNum = (int) (Math.random() * 4);
        if (directionNum == 0) {
            direction = "up";
        } else if (directionNum == 1) {
            direction = "down";
        } else if (directionNum == 2) {
            direction = "left";
        } else {
            direction = "right";
        }

        if (gamePanel.battleScreen) {
            hitBox.x = 32;
            hitBox.y = 48;
            hitBox.width = 72;
            hitBox.height = 54;
        } else {
            hitBox.x = 16;
            hitBox.y = 24;
            hitBox.width = 48;
            hitBox.height = 36;
        }
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;


        spriteInterval = 4;
        sprites = new BufferedImage[16];
        getImage();
    }

    public void getImage() {
        int index = 0;
        int y = 0;
        try {
            for (int i = 0; i < 4; i++) {
                int x = 0;
                for (int j = 0; j < 4; j++) {
                    sprites[index] = ImageIO.read(new File(("sprites/Monsters/Slime.png"))).getSubimage(x, y, 64, 64);
                    x += 64;
                    index++;
                }
                y += 64;
            }
            for (int i = 4; i < 8; i++) {
                BufferedImage holder = sprites[i];
                sprites[i] = sprites[i + 8];
                sprites[i + 8] = holder;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
