import java.io.File;
import java.util.ArrayList;

public class Settings {
    static private String[] imgFormat = {"jpg", "jpeg", "JPG", "JPEG"};
    static private ArrayList<String> foldersPath = new ArrayList<>();
    static private ArrayList<File> imageFiles = new ArrayList<>();
    static String oldPath;
    static String resizePath;
    static private final int NEW_WIDTH = 1920;

    public static String[] getImgFormat() {
        return imgFormat;
    }

    public static ArrayList<String> getFoldersPath() {
        return foldersPath;
    }

    public static int getNewWidth() {
        return NEW_WIDTH;
    }
    public static ArrayList<File> getImageFiles() {
        return imageFiles;
    }

    public static String getOldPath() {
        return oldPath;
    }

    public static void setOldPath(String oldPath) {
        Settings.oldPath = oldPath;
    }

    public static String getResizePath() {
        return resizePath;
    }

    public static void setResizePath(String resizePath) {
        Settings.resizePath = resizePath;
    }
    //    static public
}
