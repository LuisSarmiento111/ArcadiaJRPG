import java.io.*;
import java.util.Scanner;

public class PlayerDataStorage {


    public static void savePlayerData(Player player, int slot) {
        try {
            File file = new File("src/playerData.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            String[] playerData = getPlayerData();
            for (int i = 0; i < playerData.length; i++) {
                if (i + 1 == slot) {
                    fw.write("LoadSlot" + slot + ": " + player.name + "\n");
                } else {
                    fw.write(playerData[i] + "\n");
                }
            }
        } catch(IOException ioe) {
            System.out.println("Writing file failed");
            System.out.println(ioe);
        }
    }

    public static String[] getPlayerData() {
        String[] playerData = new String[4];
        try {
            File file = new File("src/playerData.txt");
            Scanner reader = new Scanner(file);
            int i = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                playerData[i] = line;
                i++;
            }
        }
        catch (FileNotFoundException noFile) {
            return null;
        }
        return playerData;
    }

    public static void loadPlayerData(int loadNum) {

    }

}
