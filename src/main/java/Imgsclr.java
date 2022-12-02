import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Imgsclr implements Runnable {

    static int k = 0;
    private File[] files;
    private int newWidth;
    private String dstFolder;
    private String srcFolder;
    static private int g = 0;

    public Imgsclr(File[] files, int newWidth, String dstFolder, String srcFolder) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.srcFolder = srcFolder;
    }

    @Override
    public void run() {
        try {
            ArrayList<File> f = new ArrayList<>(Arrays.asList(files));
            while (f.contains(null)) {
                f.remove(null);
            }
            for (File file : f) {
                System.out.println(++g);
                    BufferedImage image = null;
                    try {
//                        System.out.println(file.getName());
                        image = ImageIO.read(file);
                    } catch (Exception e) {
                        System.out.println(file.exists() + " - " + file.getName() + " ERROR " + e.getMessage() + " - - " + Arrays.toString(e.getStackTrace()));
                    }
                    if (image == null) {
                        continue;
                    }
                    int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
                    BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                    newImage = Scalr.resize(image, newWidth);

                    String folder = file.getPath().substring(srcFolder.length());
                    String[] fs = folder.split("/");

                    String p = "";
                    for (int i = 1; i < fs.length - 1; i++) {
                        p = p.concat("/" + fs[i]);
                        File fi = new File(dstFolder + "/" + p);
                        fi.mkdir();
                    }
                    File newFile = new File(dstFolder + folder.substring(0, folder.lastIndexOf("/")) + "/" + file.getName());
                    ImageIO.write(newImage, "jpg", newFile);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
