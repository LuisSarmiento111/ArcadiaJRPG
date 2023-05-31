import java.awt.*;

public class Entity {
    public String name;
    public int speed;
    public int strength;
    public int health;
    public int magicPower;
    public int worldX;
    public int worldY;
    public String direction;
    public Rectangle hitBox;
    private Image image;
    public JRPG game;
    public GamePanel panel;
    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Entity(String name, JRPG game, GamePanel panel) { // maybe remove the parameters for the different attributes since they're set to default values
        this.name = name;
        this.game = game;
        this.panel = panel;
        // image = Toolkit.getDefaultToolkit().getImage(imageURL);
    }

    public String displayStats() {
        return "Name: " + name + "\nHealth: " + health + "\nSpeed: " + speed + "" +
                "\nStrength: " + strength + "\nMagicPower: " + magicPower;
    }

    public Image getImage() {
        return image;
    }

    public GamePanel getGamePanel() {
        return game.gamePanel;
    }

    public JRPG getGame() {
        return game;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
