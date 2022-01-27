import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HuffManDisplay {
	public static Huffman huffmanAccessor;
	public static gDescriptionWriter descriptionVariable;
	public static Coding Coding;
	public static String dot;
	public static String orginalString;
	static String encodedString;
	static String DecodedString;
	static String[][] DataArray;
	static double sizeForGivenString;
	static double sizeAfterCoding;
	static double reductionPercentage;

	public HuffManDisplay(String givenString, String dotfilename) {
		dot = dotfilename;
		orginalString = givenString;
		huffmanAccessor = new Huffman(givenString, dotfilename);
		descriptionVariable = new gDescriptionWriter(givenString, dotfilename, huffmanAccessor);
		Coding = new Coding(givenString, huffmanAccessor.rCodeToCharacter(), huffmanAccessor.rCharacterToCode());

	}


	public void WriteToDictionary() {
		FileWriter fw;
		try {
			fw = new FileWriter("HuffmanCodesAssigned.txt");
			fw.write("Letter, " + "Code: " + "\n");

			for (Map.Entry<Character, String> entry : huffmanAccessor.characterToCode.entrySet()) { 
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

	public static void DisplayHuffman(boolean isThisTestData) {
		DataArray = null;
		DataArray = new String[orginalString.length()][3];

		encodedString = Coding.encode();

		DecodedString = Coding.decode(encodedString);
		descriptionVariable.generateDescriptionFile(dot);

		int i = 0;
		for (Map.Entry<Character, Integer> entry : huffmanAccessor.characterToFrequency.entrySet()) {
			String key = entry.getKey().toString();
			int val = entry.getValue();
			if (key.equals("\n"))
				key = "\\n";
			DataArray[i][0] = key;
			DataArray[i][1] = Integer.toString(val);
			i++;
		}


		int j = 0;

		for (Map.Entry<Character, String> entry : huffmanAccessor.characterToCode.entrySet()) {
			String key = entry.getKey().toString();
			String val = entry.getValue();
			if (key.equals("\n"))
				key = "\\n";
			DataArray[j][2] = val;
			j++;
		}


		sizeForGivenString = orginalString.length() * 7;
		sizeAfterCoding = encodedString.length();
		reductionPercentage = -1 * ((sizeAfterCoding - sizeForGivenString) / sizeForGivenString) * 100;
	}

}