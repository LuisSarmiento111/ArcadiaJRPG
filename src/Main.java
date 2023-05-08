import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        String[][] monsters = {{"Slime", "3", "3", "3", "0"}};

        try {
            File f = new File("src/test.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (String[] monster : monsters) {
                for (String data: monster) {
                    fw.write(data + " ");
                }
                fw.write("\n");
            }
            fw.close();
        }
        catch (IOException ioe) {
            System.out.println("Writing file failed");
            System.out.println(ioe);
        }
    }
}
