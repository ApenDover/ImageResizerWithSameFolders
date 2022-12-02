import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

public class FolderScanner {

    static private ArrayList<File> images = new ArrayList<>();

    static public void startScan(String path) {
        File srcDir = new File(path);
        File[] files = srcDir.listFiles();
        Arrays.sort(files);
        for (File f : files) {
            String fileFormat = f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase(Locale.ROOT);
            if (f.isDirectory()) {
                Settings.getFoldersPath().add(f.getPath());
                startScan(f.getPath());
            } else if (Arrays.asList(Settings.getImgFormat()).contains(fileFormat)) {
                Settings.getImageFiles().add(f);
            }
            else {
                if (!f.getName().startsWith(".")) {
                    String pathOther = f.getAbsolutePath();
                    String newPathOther = Settings.getResizePath() + pathOther.substring(Settings.getOldPath().length());
                    try {
                        Files.move(Paths.get(f.getAbsolutePath()), Paths.get(newPathOther));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static ArrayList<File> getImages() {
        return images;
    }

}
