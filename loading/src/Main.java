import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> saves = openZip("/Users/nesterov/Games/savegames/archive.zip");
        for (String save : saves) {
            System.out.println(openProgress(save));
        }
    }

    public static ArrayList<String> openZip(String zip) {
        ArrayList<String> saves = new ArrayList<>();

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zip))) {
            ZipEntry entry;
            String name;

            while ((entry = zipIn.getNextEntry()) != null) {
                name = entry.getName();
                try (FileOutputStream fileOut = new FileOutputStream(name)) {
                    for (int aChar = zipIn.read(); aChar != -1; aChar = zipIn.read()) {
                        fileOut.write(aChar);
                    }
                    fileOut.flush();
                    zipIn.closeEntry();
                    saves.add(name);
                }
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return saves;
    }

    public static GameProgress openProgress(String path) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return gameProgress;
    }

}