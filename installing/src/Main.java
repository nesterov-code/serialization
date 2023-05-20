import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<File> folders = new ArrayList<>();
        folders.add(new File("/Users/nesterov/Games", "src"));
        folders.add(new File("/Users/nesterov/Games", "rec"));
        folders.add(new File("/Users/nesterov/Games", "savegames"));
        folders.add(new File("/Users/nesterov/Games", "temp"));
        folders.add(new File("/Users/nesterov/Games/rec", "drawables"));
        folders.add(new File("/Users/nesterov/Games/rec", "vectors"));
        folders.add(new File("/Users/nesterov/Games/rec", "icons"));
        folders.add(new File("/Users/nesterov/Games/src", "main"));
        folders.add(new File("/Users/nesterov/Games/src", "test"));

        for (File folder : folders) {
            createFolder(folder, stringBuilder);
        }

        ArrayList<File> files = new ArrayList<>();
        files.add(new File("/Users/nesterov/Games/src/main", "Main.java"));
        files.add(new File("/Users/nesterov/Games/src/main", "Utils.java"));

        for (File file : files) {
            createFile(file, stringBuilder);
        }

        writeLog(new File("/Users/nesterov/Games/temp", "temp.txt"), stringBuilder);
    }

    public static void createFolder(File folder, StringBuilder stringBuilder) {
        GregorianCalendar date = new GregorianCalendar();
        if (folder.mkdir()) {
            stringBuilder.append(date.getTime())
                    .append(" | Folder ")
                    .append(folder.getName())
                    .append("\s")
                    .append("was successfully created. Path: ")
                    .append(folder.getPath())
                    .append("\n");
        }
    }

    public static void createFile(File file, StringBuilder stringBuilder) throws IOException {
        GregorianCalendar date = new GregorianCalendar();
        if (file.createNewFile()) {
            stringBuilder.append(date.getTime())
                    .append(" | File ")
                    .append(file.getName())
                    .append("\s")
                    .append("was successfully created. Path: ")
                    .append(file.getPath())
                    .append("\n");
        }
    }

    public static void writeLog(File log, StringBuilder stringBuilder) {
        try(FileWriter fileWriter = new FileWriter(log)) {
            fileWriter.write(String.valueOf(stringBuilder));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}