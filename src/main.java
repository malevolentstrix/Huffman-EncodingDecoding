import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;
import javax.imageio.ImageIO;
import java.net.URI;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class main {

	public static void main(String[] args) throws Exception {

		String orgFile = "../files/original.txt";
		String dotFile = "../files/test.dot";
		String orgString = readFile(orgFile);
		HuffManDisplay h = new HuffManDisplay(orgString, dotFile);
		boolean ShowInConsole = true;
		boolean isThisTestData = false;
		h.DisplayHuffman(ShowInConsole, isThisTestData);
		h.WriteToDictionary();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString, h.EncodedCost,
							h.OrgCost, h.Percent, ShowInConsole);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Desktop d = Desktop.getDesktop();
		Runtime.getRuntime().exec("dot -Tpng ../files/test.dot -o ../files/k.png");
		String filePath = "../files/k.png";
		File file = new File(filePath);

		URI uri = file.toURI();
		System.out.println(uri.toString());
		d.browse(new URI(uri.toString()));
	}

	public static String readFile(String fname) {
		StringBuilder sb = new StringBuilder();
		File filename = new File(fname);
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line = in.readLine();
			while (line != null) {
				sb.append(line + "");
				line = in.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return sb.toString();
	}

}
