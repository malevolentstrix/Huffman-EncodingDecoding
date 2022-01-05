
import java.util.Map;

import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HuffManDisplay {
	public static Huffman h;
	public static Graphvz Graph;
	public static Coding Coding;
	public static String dot;
	public static String orginalString;
	static String encodedString;
	static String DecodedString;
	static String[][] DataArray;
	static double OrgCost;
	static double EncodedCost;
	static double Percent;

	public HuffManDisplay(String orgStr, String dotfilename) {
		dot = dotfilename;
		orginalString = orgStr;
		h = new Huffman(orgStr, dotfilename);
		Graph = new Graphvz(orgStr, dotfilename, h);
		Coding = new Coding(orgStr, h.getCodeChar(), h.getCharCode());

	}

	public static void WriteToDictionaryTestData() {
		BufferedWriter out = null;

		try {
			FileWriter fstream = new FileWriter("files/Tests.txt", true);
			out = new BufferedWriter(fstream);

			out.write("Data: " + orginalString + "\n");
			out.write("Data size: " + orginalString.length() + "\n");
			for (int i = 0; i < DataArray.length; i++) {
				if (DataArray[i][0] == null)
					break;
				out.write("Letter: " + DataArray[i][0] + " frequency: " + DataArray[i][1] + " Code: " + DataArray[i][2]
						+ "\n");

			}
			out.write("---------------------------------------------------------\n");

			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void WriteToDictionary() {
		FileWriter fw;
		try {
			fw = new FileWriter("../files/Dictionary.txt");
			fw.write("Letter, " + "Code: " + "\n");

			for (Map.Entry<Character, String> entry : h.CharCode.entrySet()) {
				String key = entry.getKey().toString();
				String val = entry.getValue();
				if (key.equals("\n"))
					key = "\\n";
				fw.write(key + ": " + val + "\n");
			}
			fw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void DisplayHuffman(boolean ShowInConsole, boolean isThisTestData) {
		DataArray = null;
		DataArray = new String[orginalString.length()][3];

		encodedString = Coding.encode();

		DecodedString = Coding.decode(encodedString);
		myassert(orginalString.equals(DecodedString));

		Graph.WriteGraphVizFile(dot);

		System.out.println("\n Letter: Frequency: Code:");
		int i = 0;
		for (Map.Entry<Character, Integer> entry : h.CharFreq.entrySet()) {
			String key = entry.getKey().toString();
			int val = entry.getValue();
			if (key.equals("\n"))
				key = "\\n";
			DataArray[i][0] = key;
			DataArray[i][1] = Integer.toString(val);
			i++;
			if (ShowInConsole)
				System.out.println(key + " occurs " + val + " times");
		}

		System.out.println("\nLetter: Code");

		int j = 0;

		for (Map.Entry<Character, String> entry : h.CharCode.entrySet()) {
			String key = entry.getKey().toString();
			String val = entry.getValue();
			if (key.equals("\n"))
				key = "\\n";
			DataArray[j][2] = val;
			j++;
			if (ShowInConsole)
				System.out.println(key + ": " + val);
		}

		if (isThisTestData)
			WriteToDictionaryTestData();

		OrgCost = orginalString.length() * 7;
		EncodedCost = encodedString.length();
		System.out.println("\nCOST: ");
		System.out.println("Original string cost = " + (int) OrgCost + " bits");
		System.out.println("Encoded  string cost = " + (int) EncodedCost + " bits");
		Percent = -1 * ((EncodedCost - OrgCost) / OrgCost) * 100;
		System.out.println("% of reduction = " + (Percent));
	}

	public static void myassert(boolean x) {
		if (!x) {
			throw new IllegalArgumentException("Assert fail");
		}
	}

}