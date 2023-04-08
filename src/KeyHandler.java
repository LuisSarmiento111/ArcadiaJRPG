import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public KeyHandler() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
}
