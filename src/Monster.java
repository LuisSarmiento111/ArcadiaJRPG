import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Monster extends Entity {
    private final String[][] monsters = getMonsterData("src/monster.data");

    public Monster(String species, GamePanel panel) { // maybe coordinate parameters since monsters can't be on the same place when in battle
        super(species, panel);
        setStats();
    }

    public static String[][] getMonsterData(String fileName) {
        ArrayList<String[]> monsters = new ArrayList<String[]>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(" ");
                monsters.add(data);
            }
        } catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        String[][] monsterData = new String[monsters.size()][monsters.get(0).length];
        for (int i = 0; i < monsterData.length; i++) {
            monsterData[i] = monsters.get(i);
        }
        return monsterData;
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
