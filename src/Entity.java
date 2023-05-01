import java.awt.*;

public class Entity {
    private String name;
    private int speed;
    private int strength;
    private int health;
    private int magicPower;
    private Image image;
    private JRPG game;

    public Entity(String name, int health, int speed, int strength, int magicPower, JRPG game) { // maybe remove the parameters for the different attributes since they're set to default values
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.strength = strength;
        this.magicPower = magicPower;
        this.game = game;
        // image = Toolkit.getDefaultToolkit().getImage(imageURL);
    }

    public Entity(String species, JRPG game) {
        name = species;

    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
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

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void update() {
    }

}
