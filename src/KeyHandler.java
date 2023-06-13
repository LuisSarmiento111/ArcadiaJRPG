import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Objects;

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
        } else if (gp.characterSelectionScreen) {
            onCharacterSelectionScreen(e);
        } else if (gp.battleScreen) {
            onBattleScreen(e);
        } else if (gp.characterStatsScreen) {
            onStatsScreen(e);
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
                gp.characterStatsScreen = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyChar() == 'm') {
                gp.menuScreen = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }
        }
    }

    private void onStatsScreen(KeyEvent e) {
        if (e.getKeyChar() == 'p') {
            gp.characterStatsScreen = false;
        }
    }

    private void onBattleScreen(KeyEvent e) {
        if (gp.JRPG.player.turn) {
            if (gp.screens.pgNum == 0) {
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
            }
            if (gp.screens.pgNum == 1) {
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
            }
            if (gp.screens.pgNum == 2) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    gp.screens.optionNum--;
                    if (gp.screens.optionNum < 1) {
                        gp.screens.optionNum = gp.JRPG.entities.size() - 1;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    gp.screens.optionNum++;
                    if (gp.screens.optionNum > gp.JRPG.entities.size() - 1) {
                        gp.screens.optionNum = 1;
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (gp.screens.pgNum == 0) {
                    gp.screens.optionNum = 0;
                    gp.screens.pgNum = 1;
                } else if (gp.screens.pgNum == 1) {
                    if (gp.screens.optionNum == 0) {
                        gp.screens.optionNum = 1;
                        gp.screens.pgNum = 2;
                    }
                } else if (gp.screens.pgNum == 2) {
                    Entity target = gp.JRPG.entities.get(gp.screens.optionNum);
                    gp.JRPG.player.attack(target);
                    if (target.health <= 0) {
                        gp.screens.addMessage("Killed a " + target.name + "!");
                        gp.screens.addMessage("Gained " + target.exp + " exp!");
                        gp.JRPG.player.exp += target.exp;
                        gp.JRPG.player.checkLvlUp();
                        gp.JRPG.entities.remove(target);
                    }
                    gp.screens.pgNum = 0;
                    gp.screens.optionNum = 0;
                }
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
            if (gp.screens.optionNum == 0 && PlayerDataStorage.getEmptySlot() != 5) {
                gp.screens.optionNum = 0;
                gp.screens.textBox = "";
                gp.screens.pgNum = 0;
                gp.characterCreationScreen = true;
            } else if (gp.screens.optionNum == 1) {
                gp.screens.optionNum = 0;
                gp.screens.pgNum = 0;
                gp.characterSelectionScreen = true;
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
                gp.screens.optionNum = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && gp.screens.textBox.length() > 0) {
                gp.screens.textBox = gp.screens.textBox.substring(0, gp.screens.textBox.length() - 1);
            } else {
                if (gp.screens.textBox.length() + 1 <= 14 && ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) ||
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
                int slot = PlayerDataStorage.getEmptySlot();
                if (gp.screens.optionNum == 0) {
                    gp.JRPG.player = new Player(gp, gp.screens.textBox, "Knight", slot);
                } else if (gp.screens.optionNum == 1) {
                    gp.JRPG.player = new Player(gp, gp.screens.textBox, "Mage", slot);
                } else if (gp.screens.optionNum == 2) {
                    gp.JRPG.player = new Player(gp, gp.screens.textBox, "Rouge", slot);
                } else {
                    gp.JRPG.player = new Player(gp, gp.screens.textBox, "Tank", slot);
                }
                PlayerDataStorage.savePlayerData(gp.JRPG.player, gp.JRPG.player.saveSlot);
                gp.characterCreationScreen = false;
                gp.inGame = true;
                gp.JRPG.setEntities();
            }
        }
    }

    public void onCharacterSelectionScreen(KeyEvent e) {
        if (gp.screens.pgNum == 0) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                gp.screens.optionNum--;
                if (gp.screens.optionNum < 0) {
                    gp.screens.optionNum = 3;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                gp.screens.optionNum++;
                if (gp.screens.optionNum > 3) {
                    gp.screens.optionNum = 0;
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (gp.screens.pgNum == 0) {
                if (gp.screens.optionNum > 3) {
                    gp.screens.optionNum = 0;
                } else {
                    gp.screens.optionNum = 4;
                }
            } else {
                if (gp.screens.optionNum == 5) {
                    gp.screens.optionNum = 6;
                } else {
                    gp.screens.optionNum = 5;
                }
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (gp.screens.pgNum == 0) {
                if (gp.screens.optionNum > 3) {
                    gp.screens.optionNum = 0;
                } else {
                    gp.screens.optionNum = 4;
                }
            } else {
                if (gp.screens.optionNum == 5) {
                    gp.screens.optionNum = 6;
                } else {
                    gp.screens.optionNum = 5;
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gp.titleScreen = true;
            gp.characterSelectionScreen = false;
            gp.screens.optionNum = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gp.screens.pgNum == 1) {
                if (gp.screens.optionNum == 5) {
                    gp.characterCreationScreen = true;
                    gp.characterSelectionScreen = false;
                }
                gp.screens.pgNum = 0;
                gp.screens.optionNum = 0;
            } else if (gp.screens.optionNum == 4) {
                gp.titleScreen = true;
                gp.characterSelectionScreen = false;
                gp.screens.optionNum = 0;
            } else {
                if (PlayerDataStorage.loadPlayerData(gp.screens.optionNum + 1, gp) != null) {
                    gp.JRPG.player = new Player(gp, Objects.requireNonNull(PlayerDataStorage.loadPlayerData(gp.screens.optionNum + 1, gp)), gp.screens.optionNum + 1);
                    gp.characterSelectionScreen = false;
                    gp.inGame = true;
                    gp.JRPG.setEntities();
                } else {
                    gp.screens.pgNum = 1;
                    gp.screens.optionNum = 5;
                }
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
                gp.screens.optionNum = 3;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gp.screens.optionNum++;
            if (gp.screens.optionNum > 3) {
                gp.screens.optionNum = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gp.screens.optionNum == 0) {
            } else if (gp.screens.optionNum == 1) {
                PlayerDataStorage.savePlayerData(gp.JRPG.player, gp.JRPG.player.saveSlot);
            } else if (gp.screens.optionNum == 2) {
                gp.settingScreen = true;
            } else {
                for (int i = 0; i < gp.JRPG.entities.size(); i++) {
                    gp.JRPG.entities.remove(i);
                    i--;
                }
                gp.inGame = false;
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
