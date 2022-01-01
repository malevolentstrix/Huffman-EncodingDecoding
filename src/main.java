import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class main {

	  public static void main(String[] args) {

		  // read the file from the folder files
		  String orgFile = "../files/original.txt";
		  String dotFile = "../files/test.dot";
		  String orgString = readFile(orgFile);
		  HuffManDisplay h = new HuffManDisplay(orgString, dotFile);
		  boolean ShowInConsole = true; //if you only to show out output in gui put = false
		  boolean isThisTestData = false; 
		  h.DisplayHuffman(ShowInConsole,isThisTestData);
		  h.WriteToDictionary();
		  
		  // GUI
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString,h.EncodedCost,h.OrgCost,h.Percent,ShowInConsole);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			

	  }

	  // Method to read from file
		public static String readFile(String fname){
			StringBuilder sb = new StringBuilder();
			File filename = new File(fname);
			try (BufferedReader in = new BufferedReader(new FileReader(filename))){
				String line = in.readLine();
				while (line != null){
					sb.append(line + "\n");
					line = in.readLine();
				}
			}
			catch (IOException e){
				System.out.println(e);
			}
			return sb.toString();
		}
	
}
