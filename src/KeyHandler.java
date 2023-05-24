import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean shiftPressed;
    private boolean menuPressed;
    private boolean enterPressed;
    private GamePanel gp;

    public KeyHandler(GamePanel gamePanel) {
        gp = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gp.titleScreen) {
            onTitleScreen(e);
        } else if (gp.characterCreationScreen) {
            onCharacterCreationScreen(e);
        } else if (gp.menuScreen) {
            onMenuScreen(e);
        } else {
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
                gp.titleScreen = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyChar() == 'm') {
               gp.menuScreen = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }
        }
    }

    public void onTitleScreen(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            gp.screens.optionNum--;
            if (gp.screens.optionNum < 0) {
                gp.screens.optionNum = 2;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gp.screens.optionNum++;
            if (gp.screens.optionNum > 2) {
                gp.screens.optionNum = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gp.screens.optionNum == 0) {
                gp.screens.textBox = "";
                gp.screens.pgNum = 0;
                gp.characterCreationScreen = true;
            } else if (gp.screens.optionNum == 1 && gp.JRPG.player != null) {
                gp.inGame = true;
            } else {
                gp.endGame();
            }
            gp.titleScreen = false;
        }
    }

    public void onCharacterCreationScreen(KeyEvent e) {
        if (gp.screens.pgNum == 0) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && gp.screens.textBox.length() != 0) {
                gp.screens.pgNum = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && gp.screens.textBox.length() > 0) {
                gp.screens.textBox = gp.screens.textBox.substring(0, gp.screens.textBox.length() - 1);
            } else {
                if (gp.screens.textBox.length() + 1 <= 14  && ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) ||
                        e.getKeyCode() >= 65 && e.getKeyCode() <= 90)) {
                    gp.screens.textBox += e.getKeyChar();
                }
            }
        } else if (gp.screens.pgNum == 1) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                gp.screens.optionNum--;
                if (gp.screens.optionNum < 0) {
                    gp.screens.optionNum = 3;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                gp.screens.optionNum++;
                if (gp.screens.optionNum > 3) {
                    gp.screens.optionNum = 0;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (gp.screens.optionNum == 0) {
                    gp.JRPG.player = new Player(gp.JRPG, gp, gp.screens.textBox, "Paladin");
                } else if (gp.screens.optionNum == 1) {
                    gp.JRPG.player = new Player(gp.JRPG, gp, gp.screens.textBox, "Mage");
                } else if (gp.screens.optionNum == 2) {
                    gp.JRPG.player = new Player(gp.JRPG, gp, gp.screens.textBox, "Rouge");
                } else {
                    gp.JRPG.player = new Player(gp.JRPG, gp, gp.screens.textBox, "Fighter");
                }
                gp.characterCreationScreen = false;
                gp.inGame = true;
            }
        }
    }

    public void onMenuScreen(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyChar() == 'm') {
            gp.menuScreen = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            gp.screens.optionNum--;
            if (gp.screens.optionNum < 0) {
                gp.screens.optionNum = 2;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gp.screens.optionNum++;
            if (gp.screens.optionNum > 2) {
                gp.screens.optionNum = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gp.screens.optionNum == 0) {
            } else if (gp.screens.optionNum == 1) {
                gp.settingScreen = true;
            } else {
                gp.titleScreen = true;
            }
            gp.menuScreen = false;
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
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
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

    public boolean isShiftPressed() {
        return shiftPressed;
    }
}
