import java.awt.*;

public class Entity {
    public String name;
    public int speed;
    public int strength;
    public int health;
    public int magicPower;
    private Image image;
    public JRPG game;
    public GamePanel panel;

    public Entity(String name, int health, int speed, int strength, int magicPower, JRPG game, GamePanel panel) { // maybe remove the parameters for the different attributes since they're set to default values
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.strength = strength;
        this.magicPower = magicPower;
        this.game = game;
        this.panel = panel;
        // image = Toolkit.getDefaultToolkit().getImage(imageURL);
    }

    public Entity(String name, JRPG JRPG, GamePanel panel) {
        this.name = name;
        this.game = JRPG;
        this.panel = panel;
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
