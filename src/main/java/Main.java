import java.io.File;
import java.util.*;

public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сколько px по ширине?");
        int newWidth = Integer.parseInt(scanner.nextLine());
        System.out.println("Укажите путь к папке с оригиналами:");
        String pathOriginal = scanner.nextLine();
        System.out.println("Укажите путь к папке, куда класть resize:");
        String pathResize = scanner.nextLine();

        long start = System.currentTimeMillis();
//        String pathOriginal = "/Users/andrey/Downloads/отчет";
//        String pathResize = "/Users/andrey/Downloads/Resize";

        Settings.setOldPath(pathOriginal);
        Settings.setResizePath(pathResize);

        File originalDir = new File(pathOriginal);
        if (!originalDir.exists()) {
            System.out.println("такой папки нет");
            System.exit(0);
        }
        File resizeDir = new File(pathResize);
        resizeDir.mkdir();
        FolderScanner.startScan(pathOriginal);
        ThreadDelimiter.startThreadPool(Settings.getImageFiles().toArray(new File[Settings.getImageFiles().size()]), newWidth, pathResize, pathOriginal);
        int i = 0;
        int j = 0;
        for (Thread thread : ThreadDelimiter.getAllThread()) {
            thread.start();
            i++;
            j++;
            if (i == 6 | j == ThreadDelimiter.getAllThread().size()) {
                ThreadDelimiter.getAllThread().forEach(thread1 -> {
                    try {
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                i = 0;
            }
        }
        ThreadDelimiter.getAllThread().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Готово! ## Duration: " + (System.currentTimeMillis() - start + " ms"));
    }
}
