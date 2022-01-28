import java.util.HashMap;

public class Coding {
	String givenString;
	public String decodedString;
	HashMap<Character, String> characterToCode;
	HashMap<String, Character> codeToCharacter;

	public Coding(String org, HashMap<String, Character> codeCharacter, HashMap<Character, String> characterCode) {
		givenString = org;
		characterToCode = characterCode;
		codeToCharacter = codeCharacter;
	}

	public String encode() {
		StringBuilder sb = new StringBuilder();
		Character character;
		for (int i = 0; i < givenString.length(); i++) {
			character = givenString.charAt(i);
			sb.append(characterToCode.get(character));
		}
		String encodedString = sb.toString();
		return encodedString;
	}

	public String decode(String encodedString) {
		StringBuilder sb = new StringBuilder();
		String t = "";

		for (int i = 0; i < encodedString.length(); i++) {
			t += encodedString.charAt(i);
			if (codeToCharacter.get(t) != null) {
				sb.append(codeToCharacter.get(t));
				t = "";
			}
		}
		decodedString = sb.toString();
		return decodedString;
	}
}
