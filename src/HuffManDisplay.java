
import java.util.Map;

import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class HuffManDisplay{
	public static Huffman h; 
	public static Graphvz Graph; //this is for Graphviz 
	public static Coding Coding; //this for encoding/decoding process
	public static String dot; //this is for Graphviz 
	public static	String orginalString; // Original string
	static String encodedString;
	static String DecodedString;
	static String[][] DataArray;
	static double OrgCost; // Original string cost
	static	double EncodedCost; // Encoded  string cost
    static double Percent; //% of reduction
    //Constructor 
	public HuffManDisplay(String orgStr, String dotfilename){
		  dot = dotfilename;
		  orginalString = orgStr;
		  h = new Huffman(orgStr,dotfilename);
		    Graph = new Graphvz(orgStr,dotfilename, h);
		     Coding = new Coding(orgStr,h.getCodeChar(),h.getCharCode());

	}
	
// this method is going to write a text file contains the letter, frequency and code for random data test
public static void WriteToDictionaryTestData() {
	BufferedWriter out = null;

	
	try {
		FileWriter fstream = new FileWriter("files/Tests.txt", true); //true tells to append data.
	    out = new BufferedWriter(fstream);
	 
	    out.write("Data: "+ orginalString + "\n");
	    out.write("Data size: "+ orginalString.length() + "\n");
		for(int i=0;i<DataArray.length;i++) {
			if(DataArray[i][0] == null)
				break;
			out.write("Letter: " + DataArray[i][0] + " frequency: " + DataArray[i][1] + " Code: " + DataArray[i][2]+ "\n");

		}
	    out.write("---------------------------------------------------------\n");

		out.close();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
}
	
// method to write charater:frequancy in a text file, it will get the data from original.txt
	public void WriteToDictionary() {
		FileWriter fw;
		try {
			fw = new FileWriter("../files/Dictionary.txt");
			fw.write("Letter, "  +"Code: " +"\n");

		for (Map.Entry<Character, String> entry: h.CharCode.entrySet()){
			String key = entry.getKey().toString();
			String val = entry.getValue();
			if (key.equals("\n"))
				key = "\\n";
			fw.write(key + ": " + val +"\n");
			  }
		fw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void DisplayHuffman(boolean ShowInConsole, boolean isThisTestData){
		DataArray = null;
		DataArray = new String[orginalString.length()][3]; // Size 3 because we have letter,frequency,code

		//Encoding the text
		encodedString = Coding.encode();
	    
	    //Decoding the encoded text
		DecodedString = Coding.decode(encodedString);
	    myassert(orginalString.equals(DecodedString)) ;   // Check if original text and decoded text is exactly the same
	    
	  //Building Huffman Tree and Code Tables
		Graph.WriteGraphVizFile(dot);
		
	
			//Display all Letters, Frequencies also add it to array.
			System.out.println("\n Letter: Frequency: Code:");
			int i = 0;
			for (Map.Entry<Character, Integer> entry: h.CharFreq.entrySet()){
				String key = entry.getKey().toString();
				int val = entry.getValue();
				if (key.equals("\n"))
					key = "\\n";
			        DataArray[i][0] = key;
			        DataArray[i][1] = Integer.toString(val);
			        i++;
			        if(ShowInConsole) 
						System.out.println(key + " occurs " + val + " times");
			}
			
			System.out.println("\nLetter: Code");
		
			int j=0;
			//Display each Letter's code also add it to array.
			for (Map.Entry<Character, String> entry: h.CharCode.entrySet()){
				String key = entry.getKey().toString();
				String val = entry.getValue();
				if (key.equals("\n"))
					key = "\\n";
				DataArray[j][2] = val;
				j++;
		        if(ShowInConsole)
				System.out.println(key + ": " + val); 
			}
		
			//write test data Dictionary
		if(isThisTestData)
			WriteToDictionaryTestData();
			
	    // calculate the Original and encoded cost
	    OrgCost = orginalString.length() * 7 ;
	     EncodedCost = encodedString.length();
	    System.out.println("\nCOST: ");
	    System.out.println("Original string cost = " + (int)OrgCost + " bits") ;
	    System.out.println("Encoded  string cost = " + (int)EncodedCost + " bits") ;
	     Percent = -1*((EncodedCost - OrgCost)/OrgCost) * 100 ;
	    System.out.println("% of reduction = " + (Percent)) ;
	}
	
	

  public static void myassert(boolean  x) {
	    if (!x) {
	    	throw new IllegalArgumentException("Assert fail") ;
	    }
  }
  
  
}