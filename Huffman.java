import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class Huffman {
	public String givenString;
	public String stringAfterCoding;
	public String stringBeforeCoding;
	public HashMap<Character, Integer> characterToFrequency;
	public HashMap<Character, String> characterToCode;
	public HashMap<String, Character> codeToCharacter;
	private priorityQueue<node> priorityQueue;
	public int sizeOfHuffManTree;
	public node root;

	public Huffman(String givenString, String dotfilename) {
		this.sizeOfHuffManTree = 0;
		this.givenString = givenString;
		characterToFrequency = new HashMap<Character, Integer>();
		characterToCode = new HashMap<Character, String>();
		codeToCharacter = new HashMap<String, Character>();
		priorityQueue = new priorityQueue<node>(givenString.length(), new Comparator<node>() {
			@Override
			public int compare(node node1, node node2) {
				if (node1.weight < node2.weight)
					return -1;
				else if (node1.weight > node2.weight)
					return 1;
				return 0;
			}
		});

		numberofWords();
		buildTree();
		buildCodeTable();
	}

	public HashMap<String, Character> rCodeToCharacter() {
		return codeToCharacter;
	}

	public HashMap<Character, String> rCharacterToCode() {
		return characterToCode;
	}

	private void numberofWords() {
		Character character;
		Integer weight;

		for (int i = 0; i < givenString.length(); i++) {
			character = givenString.charAt(i);

			if (characterToFrequency.get(character) == null)
				weight = 1;
			else
				weight = characterToFrequency.get(character) + 1;
			characterToFrequency.put(character, weight);
		}
	}

	private void buildCodeTable() {
		String code = "";
		node node = root;
		buildCodeRecursion(node, code);
	}
// building the table for storng th codes
	private void buildCodeRecursion(node node, String code) {
		if (node != null) {
			if (!checkIfLeafOrNot(node)) {
				buildCodeRecursion(node.left, code + '0');
				buildCodeRecursion(node.right, code + '1');
			} else {
				characterToCode.put(node.character, code);
				codeToCharacter.put(code, node.character);
			}
		}
	}
// building the 
	private void buildTree() {
		buildHeap();

		node left, right;
		while (priorityQueue != null) {
			left = priorityQueue.serve();
			sizeOfHuffManTree++;
			if (priorityQueue.retrieve() != null) {
				right = priorityQueue.serve();
				sizeOfHuffManTree++;
				root = new node('\0', left.weight + right.weight, left, right);
			} else {
				root = new node('\0', left.weight, left, null);
			}

			if (priorityQueue.retrieve() != null) {
				priorityQueue.insert(root);
			} else {

				sizeOfHuffManTree++;
				break;
			}
		}
	}

	private void buildHeap() {

		for (Map.Entry<Character, Integer> entry : characterToFrequency.entrySet()) {
			Character character = entry.getKey();
			Integer weight = entry.getValue();
			node node = new node(character, weight);
			priorityQueue.insert(node);
		}
	}

	public boolean checkIfLeafOrNot(node node) {

		return (node.left == null) && (node.right == null);
	}

}