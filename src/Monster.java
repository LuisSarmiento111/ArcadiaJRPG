import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Monster extends Entity{
    private final String[][] monsters = {{"Slime", "3", "3", "3", "0"}};

    public Monster(String species, JRPG JRPG, GamePanel panel) { // maybe coordinate parameters since monsters can't be on the same place when in battle
        super(species, JRPG, panel);
        setStats();
    }

    public void setStats() { // base stats for different monsters that are based on the player's level.
        for (int r = 0; r < monsters.length; r++) {
            if (monsters[r][0].equals(name)) {
                health = Integer.parseInt(monsters[r][1]);
                speed = Integer.parseInt(monsters[r][2]);
                strength = Integer.parseInt(monsters[r][3]);
                magicPower = Integer.parseInt(monsters[r][4]);
            }
        }
    }
}
