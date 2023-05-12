import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean menuPressed;
    private GamePanel gp;

    public KeyHandler(GamePanel gamePanel) {
        gp = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gp.titleScreen)
        {
            onTitleScreen(e);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w') {
            upPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 's') {
            downPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
            leftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
            rightPressed = true;
        }
        if (e.getKeyChar() == 'p') {
            menuPressed = true;
        }
    }

    public void onTitleScreen(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            gp.screens.optionNum--;
            if(gp.screens.optionNum < 0)
            {
                gp.screens.optionNum = 2;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            gp.screens.optionNum++;
            if(gp.screens.optionNum > 2)
            {
                gp.screens.optionNum = 0;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            gp.titleScreen = false;
            gp.inGame = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w') {
            upPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 's') {
            downPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {
            leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {
            rightPressed = false;
        }
        if (e.getKeyChar() == 'p') {
            menuPressed = false;
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isMenuPressed() {
        return menuPressed;
    }
}
