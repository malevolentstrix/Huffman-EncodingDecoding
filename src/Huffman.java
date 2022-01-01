import java.util.HashMap; // Note: We have used HashMap from java because the implementation is so long 
import java.util.Map; // Note: We have used Map from java because the implementation is so long
import java.util.Comparator;

public class Huffman {
	public String OriginalStr; 
	public String encodedStr;
	public String decodedStr;
	public HashMap<Character, Integer> CharFreq;  // for Frequency counting
	public HashMap<Character, String> CharCode; // for code(character/code)
	public HashMap<String, Character> CodeChar; // for code(code/character)
	private pq<node> pq;  // for MinHeap
	public int treeSize;  // # of total nodes in the tree
	public node root;

	// Constructor for class Huffman
	public Huffman(String OriginalStr, String dotfilename){
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
		
		countWord();  // STEP 1: Count frequency of word
		buildTree();  // STEP 2: Build Huffman Tree
		buildCodeTable();  // STEP34: Build Huffman Code Table
	}
	//Setters and getters of hashmaps
	public HashMap<String, Character> getCodeChar() {
		return CodeChar;
	}
	public HashMap<Character, String> getCharCode() {
		return CharCode;
	}
	private void countWord(){
		Character ch;
		Integer weight;
		//This method loops the string adds each character and add it to the HashMap 
		for (int i=0; i<OriginalStr.length(); i++){
			ch = OriginalStr.charAt(i);
			// here we check if char exists in the hashmap or not if yes then add +1 to the frequency
			if (CharFreq.get(ch) == null)
				weight = 1;
			else
				weight = CharFreq.get(ch) + 1;
			CharFreq.put(ch, weight);
		}
	}
		
	private void buildCodeTable(){
		String code = "";
		node n = root;
		buildCodeRecursion(n, code);  // Recursion
	}
	
	private void buildCodeRecursion(node n, String code){
		if (n != null){
			if (! isLeaf(n)){  // n = internal node
				buildCodeRecursion(n.left, code + '0');
				buildCodeRecursion(n.right, code + '1');
			}
			else{  // n = Leaf node
				CharCode.put(n.ch, code); // for {character:code}
				CodeChar.put(code, n.ch); // for {code:character}
			}
		}
	}
			
	private void buildTree(){
		minHeapBuild();  // Set all leaf nodes into MinHeap
		//Comment to be added later
		node left, right;
		while (pq != null ){
			left = pq.serve(); treeSize++;
			if (pq.retrieve() != null){
				right = pq.serve();  treeSize++;
				root = new node('\0', left.weight + right.weight, left, right);
			}
			else{  // only left child. right=null
				root = new node('\0', left.weight, left, null);
			}
			
			if (pq.retrieve() != null){
				pq.insert(root);
			}
			else{  
				// if we get to the root hence add +1 to treesize.
				treeSize++;
				break;
			}
		}
	}
	
	private void minHeapBuild(){
		//in this method we traverse to all elements of character, frequency map and add it to the priority queue 
		for (Map.Entry<Character, Integer> entry: CharFreq.entrySet()){
			Character ch = entry.getKey();
	        Integer weight = entry.getValue();
	        node n = new node(ch, weight);
	        pq.insert(n);
		}		
	}
	
	public boolean isLeaf(node n) {
		//in this method we check if the node is leaf
        return (n.left == null) && (n.right == null);
    }	
	
}