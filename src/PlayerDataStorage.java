import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerDataStorage {

    public static void savePlayerData(Player player, int slot) {
        ArrayList<String> playerData = getPlayerData();
        try {
            File f = new File("src/playerData.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < playerData.size(); i++) {
                if (i + 1 == slot) {
                    fw.write("LoadFile" + slot + ": " + player.name);
                    fw.write("|" + player.getPlayerClass());
                    fw.write("|" + player.getxCoordinate() + "|" + player.getyCoordinate());
                } else {
                    fw.write(playerData.get(i));
                }
                fw.write("\n");
            }
            fw.close();
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static ArrayList<String> getPlayerData() {
        ArrayList<String> playerData = new ArrayList<String>();
        try {
            File file = new File("src/playerData.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                playerData.add(line);
            }

        }
        catch (FileNotFoundException noFile) {
            return null;
        }
        return playerData;
    }

    public static Player loadPlayerData(int slot, GamePanel gp) {
        String playerData = getPlayerData().get(slot - 1);
        if (!playerData.contains("|")) {
            return null;
        } else {
            playerData = playerData.substring(playerData.indexOf(":") + 2);
            String[] data = playerData.split("\\|");
            return new Player(gp.JRPG, gp, data[0], data[1]);
        }
    }

}


