import java.awt.*;

public class Entity {
    private int xCoordinate;
    private int yCoordinate;
    private int speed;
    private Image image;
    private JRPG game;

    public Entity(int xCoordinate, int yCoordinate, int speed, String imageURL, JRPG game) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.speed = speed;
        this.game = game;
        // game.GUI.getMainPanel().add(new CustomPaintComponent());
        image = Toolkit.getDefaultToolkit().getImage(imageURL);
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
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

    public void update() {
    }

}
