import java.util.ArrayList;
import java.util.Arrays;

public class Monster extends Entity{
    private JRPG JRPG;

    public Monster(String species, JRPG JRPG) { // maybe coordinate parameters since monsters can't be on the same place when in battle
        super(species, 100, 100, 100, 100, JRPG);
        setStats();
    }

    public void setStats() { // base stats for different monsters that are based on the player's level.
        if (super.getName().equals("Slime")) {
            super.setHealth(JRPG.getPlayer().getPlayerLevel());
            super.setSpeed(JRPG.getPlayer().getPlayerLevel());
            super.setStrength(JRPG.getPlayer().getPlayerLevel());
            super.setMagicPower(JRPG.getPlayer().getPlayerLevel());
        }
        if (super.getName().equals("Wolf")) {
            super.setHealth(JRPG.getPlayer().getPlayerLevel());
            super.setSpeed(JRPG.getPlayer().getPlayerLevel());
            super.setStrength(JRPG.getPlayer().getPlayerLevel());
            super.setMagicPower(JRPG.getPlayer().getPlayerLevel());
        }
        if (super.getName().equals("Skeleton")) {
            super.setHealth(JRPG.getPlayer().getPlayerLevel());
            super.setSpeed(JRPG.getPlayer().getPlayerLevel());
            super.setStrength(JRPG.getPlayer().getPlayerLevel());
            super.setMagicPower(JRPG.getPlayer().getPlayerLevel());
        }
    }
}
