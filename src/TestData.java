import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*
 * This Class will test data that will be generated in a random way then it will will create a code of matlab
 * code of input-size versus time
 */

public class TestData {
	 static String getAlphaNumericString(int n) 
	    { 
	  
	        // choose a Character random from this String 
	        String AlphaNumericString = " ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ " abcdefghijklmnopqrstuvxyz";
	        // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder(n); 
	  
	        for (int i = 0; i < n; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(AlphaNumericString.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(AlphaNumericString 
	                          .charAt(index)); 
	        } 
	  
	        return sb.toString(); 
	    } 
	   public static void main(String[] args) 
	    { 
		   
		 int NumberOfInputs=10; //number of inputs to be tested
		
		 //Generate random number and put it in n variable
	   Random random = new Random();

      int n = random.nextInt(100); 
      double startTime = System.nanoTime(); // calculate running time

		try {
			//here we construct an output file to make code for plot to use it in Matlab
			FileWriter fw = new FileWriter("files/MatlabPlotCode.txt");
			FileWriter file = new FileWriter("files/Tests.txt"); //this is to reset the tests file

			fw.write("clear all;clc;figure\n");
			StringBuilder inputSize = new StringBuilder();
			StringBuilder time = new StringBuilder();
	for(int i=0; i<NumberOfInputs; i++) {
	        // Get the size n 
		
	        n = n+random.nextInt(50); 
	        if(n<0)n=-n;

	        String Testdata = TestData 
                    .getAlphaNumericString(n);
	        while(Testdata.isEmpty()) {
	        	if(n == 0)
	    	        n = n+random.nextInt(100); 
	        	Testdata = TestData.getAlphaNumericString(n);	
	        }
			// Get and display the alphanumeric string that will be used
	        System.out.println("Data: " + Testdata + " \nSize of data: " + Testdata.length() ); 
	        
	        System.out.println("Testing Started");
	        System.out.println("Generating Test Data");
	        //Use the Huffman algorithm 
	        String dotFile = "files/test.dot";
	       
			
			  HuffManDisplay h = new HuffManDisplay(Testdata, dotFile);
			  boolean isThisTestData = true;
			  h.DisplayHuffman(true,isThisTestData);
			  double endTime = System.nanoTime();

			  //The equation to find the running time:
			    double duration = (endTime - startTime)/ 1_000_000_000.0;  //Total execution time in seconds

			  System.out.println("Running time in s: " + duration);

			  //Write Matlab plot code
			  inputSize.append(Testdata.length() + " ");
				time.append(duration + " ");
			

				}
			  System.out.println("\n Test: DONE");
			  	fw.write("input = [ " + inputSize + " ];");
			  	fw.write("time = [ " + time + " ];");

				fw.write("\n plot(input,time,'-*');grid on;title('Huffman time vs input size plot');xlabel('input size, n');ylabel('Time (s)');");
				fw.close();
			
		}
		 catch (IOException e1) {

			e1.printStackTrace();
		 }

	    }}
