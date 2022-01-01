import java.util.HashMap;

public class Coding {
	String OrginalString; // Our original string
	public String decodedStr; // The decoded string
	HashMap<Character, String> CharCode; //HashMap of Character and frequency 
	HashMap<String, Character> CodeChar; //HashMap of code and Character 

	//Constructor to set OrginalString  HashMap of Character and frequency  and HashMap of code and Character
	public Coding(String org, HashMap<String, Character> CodeCh, HashMap<Character, String> ChCode) {
		OrginalString=org;
		CharCode=ChCode;
		CodeChar = CodeCh;
		
	}
	
	//in this method we append each character code to get the final encoded string
	public String encode(){
		StringBuilder sb = new StringBuilder();
		Character ch;
		for(int i=0; i<OrginalString.length(); i++){
			ch = OrginalString.charAt(i);
			sb.append(CharCode.get(ch));
		}
		String encodedStr = sb.toString();
		return encodedStr;
	}
	
	//in this method we find the character of each code then append it to create decoded string
	public String decode(String encodedStr){
		StringBuilder sb = new StringBuilder();
		String t = "";
		
		for(int i=0; i<encodedStr.length(); i++){
			t += encodedStr.charAt(i);
			if (CodeChar.get(t) != null ){
				sb.append(CodeChar.get(t));
				t = "";
			}
		}
		decodedStr = sb.toString();
		return decodedStr;
	}
}
