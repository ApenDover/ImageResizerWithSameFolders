import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ThreadDelimiter {

    static private ArrayList<Thread> allThread = new ArrayList<>();

    public static void startThreadPool(File[] files, int newWidth, String pathResize, String originalPath) {
        allThread.clear();
        System.out.println("всего файлов: " + files.length);
        ArrayList<File> photoList = new ArrayList<>(Arrays.asList(files));

        while (photoList.size() > 0) {
            File[] f = null;
            if (photoList.size() >= 8) {
                f = new File[8];
                f[0] = photoList.get(0);
                f[1] = photoList.get(1);
                f[2] = photoList.get(2);
                f[3] = photoList.get(3);
                f[4] = photoList.get(4);
                f[5] = photoList.get(5);
                f[6] = photoList.get(6);
                f[7] = photoList.get(7);
                for (int k = 0; k < 8; k++) {
                    photoList.remove(0);
                }
            } else {
                f = new File[photoList.size()];
                for (int k = 0; k < photoList.size(); k++) {
                    f[k] = photoList.get(k);
                }
                int r = photoList.size();
                for (int k = 0; k < r; k++) {
                    photoList.remove(0);
                }
            }
            Imgsclr imgsclr = new Imgsclr(f, newWidth, pathResize, originalPath);
            Thread thread = new Thread(imgsclr);
            allThread.add(thread);
        }
    }

    public static ArrayList<Thread> getAllThread() {
        return allThread;
    }
}
