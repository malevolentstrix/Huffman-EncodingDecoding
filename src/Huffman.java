import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class Huffman {
	public String OriginalStr;
	public String encodedStr;
	public String decodedStr;
	public HashMap<Character, Integer> CharFreq;
	public HashMap<Character, String> CharCode;
	public HashMap<String, Character> CodeChar;
	private pq<node> pq;
	public int treeSize;
	public node root;

	public Huffman(String OriginalStr, String dotfilename) {
		this.treeSize = 0;
		this.OriginalStr = OriginalStr;
		CharFreq = new HashMap<Character, Integer>();
		CharCode = new HashMap<Character, String>();
		CodeChar = new HashMap<String, Character>();
		pq = new pq<node>(OriginalStr.length(), new Comparator<node>() {
			@Override
			public int compare(node n1, node n2) {
				if (n1.weight < n2.weight)
					return -1;
				else if (n1.weight > n2.weight)
					return 1;
				return 0;
			}
		});

		countWord();
		buildTree();
		buildCodeTable();
	}

	public HashMap<String, Character> getCodeChar() {
		return CodeChar;
	}

	public HashMap<Character, String> getCharCode() {
		return CharCode;
	}

	private void countWord() {
		Character ch;
		Integer weight;

		for (int i = 0; i < OriginalStr.length(); i++) {
			ch = OriginalStr.charAt(i);

			if (CharFreq.get(ch) == null)
				weight = 1;
			else
				weight = CharFreq.get(ch) + 1;
			CharFreq.put(ch, weight);
		}
	}

	private void buildCodeTable() {
		String code = "";
		node n = root;
		buildCodeRecursion(n, code);
	}

	private void buildCodeRecursion(node n, String code) {
		if (n != null) {
			if (!isLeaf(n)) {
				buildCodeRecursion(n.left, code + '0');
				buildCodeRecursion(n.right, code + '1');
			} else {
				CharCode.put(n.ch, code);
				CodeChar.put(code, n.ch);
			}
		}
	}

	private void buildTree() {
		minHeapBuild();

		node left, right;
		while (pq != null) {
			left = pq.serve();
			treeSize++;
			if (pq.retrieve() != null) {
				right = pq.serve();
				treeSize++;
				root = new node('\0', left.weight + right.weight, left, right);
			} else {
				root = new node('\0', left.weight, left, null);
			}

			if (pq.retrieve() != null) {
				pq.insert(root);
			} else {

				treeSize++;
				break;
			}
		}
	}

	private void minHeapBuild() {

		for (Map.Entry<Character, Integer> entry : CharFreq.entrySet()) {
			Character ch = entry.getKey();
			Integer weight = entry.getValue();
			node n = new node(ch, weight);
			pq.insert(n);
		}
	}

	public boolean isLeaf(node n) {

		return (n.left == null) && (n.right == null);
	}

}