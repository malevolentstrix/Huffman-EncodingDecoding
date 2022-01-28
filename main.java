import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;

public class main {

	public static void main(String[] args) throws Exception {

		long startTime = System.nanoTime();
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		String orgFile = "./inputStringFile.txt";
		String dotFile = "./outputDescriptionFile.dot";
		String givenString = readFile(orgFile);
		HuffManDisplay h = new HuffManDisplay(givenString, dotFile);
		h.DisplayHuffman();
		h.WriteToDictionary();
		long endTime = System.nanoTime();
		System.out.println("Time Taken " + (endTime - startTime) + " ns");
		long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long actualMemUsed=afterUsedMem-beforeUsedMem;
		int dataSize = 1024 * 1024;
		System.out.println("Memory Used " + (actualMemUsed)/dataSize + "MB");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString, h.sizeAfterCoding,
							h.sizeForGivenString, h.reductionPercentage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Desktop d = Desktop.getDesktop();
		Runtime.getRuntime().exec("dot -Tpng ./outputDescriptionFile.dot -o ./finalOutputHuffmanTree.png");
		String filePath = "./finalOutputHuffmanTree.png";
		File file = new File(filePath);

		URI uri = file.toURI();
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
