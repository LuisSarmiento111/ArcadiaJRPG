import javax.swing.*;

public class JRPG {
    public GamePanel gamePanel;
    private Player player;

    public JRPG(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);


        player = new Player(this);
        gamePanel.startGameThread();
    }

    public Player getPlayer() {
        return player;
    }
}