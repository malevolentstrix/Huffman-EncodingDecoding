import java.util.HashMap;

public class Coding {
	String OrginalString;
	public String decodedStr;
	HashMap<Character, String> CharCode;
	HashMap<String, Character> CodeChar;

	public Coding(String org, HashMap<String, Character> CodeCh, HashMap<Character, String> ChCode) {
		OrginalString = org;
		CharCode = ChCode;
		CodeChar = CodeCh;

	}

	public String encode() {
		StringBuilder sb = new StringBuilder();
		Character ch;
		for (int i = 0; i < OrginalString.length(); i++) {
			ch = OrginalString.charAt(i);
			sb.append(CharCode.get(ch));
		}
		String encodedStr = sb.toString();
		return encodedStr;
	}

	public String decode(String encodedStr) {
		StringBuilder sb = new StringBuilder();
		String t = "";

		for (int i = 0; i < encodedStr.length(); i++) {
			t += encodedStr.charAt(i);
			if (CodeChar.get(t) != null) {
				sb.append(CodeChar.get(t));
				t = "";
			}
		}
		decodedStr = sb.toString();
		return decodedStr;
	}
}
