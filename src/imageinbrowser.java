import java.awt.Desktop;
import javax.imageio.ImageIO;
import java.net.URI;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class imageinbrowser {
    public static void main(String[] args) throws Exception {
        Desktop d = Desktop.getDesktop();
        Runtime.getRuntime().exec("dot -Tpng ../files/test.dot -o ../files/k.png");
        String filePath = "../files/k.png";
      File file = new File(filePath);
      //Getting the URI object
      URI uri = file.toURI();
      System.out.println(uri.toString());
        d.browse(new URI(uri.toString()));
 //       ImageIO.read(new File("../files/k.png"));
//         BufferedImage img = null;
// try {
//     img = ImageIO.read(new File("../files/k.png"));
// } catch (IOException e) {
// }
     }
}
