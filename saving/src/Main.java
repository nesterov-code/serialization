import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        ArrayList<GameProgress> progressesList = new ArrayList<>();
        progressesList.add(new GameProgress(34, 638, 999, 1));
        progressesList.add(new GameProgress(95, 472, 1434, 3));
        progressesList.add(new GameProgress(77, 123, 2874, 7));

        ArrayList<String> savesList = new ArrayList<>();

        for (int i = 0; i < progressesList.size(); i++) {
            saveGame("/Users/nesterov/Games/savegames/save" + (i + 1) + ".dat", progressesList.get(i));
            savesList.add("/Users/nesterov/Games/savegames/save" + (i + 1) + ".dat");
        }

        zipFiles("/Users/nesterov/Games/savegames/archive.zip", savesList);
        deleteFiles(savesList);
    }

    public static void saveGame(String filePath, GameProgress progress) {
        try(FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void zipFiles(String pathToZip, ArrayList<String> savesList) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(pathToZip))) {

            for (String save : savesList) {
                try (FileInputStream fileIn = new FileInputStream(save)) {

                    ZipEntry entry = new ZipEntry(save);
                    zipOut.putNextEntry(entry);
                    byte[] buffer = new byte[fileIn.available()];
                    fileIn.read(buffer);
                    zipOut.write(buffer);
                    zipOut.closeEntry();

                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void deleteFiles(ArrayList<String> saves) {
        for (String save : saves) {
            File file = new File(save);
            file.delete();
        }

    }
}