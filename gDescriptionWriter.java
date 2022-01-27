import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;  


public class gDescriptionWriter {

	String givenString;
	String graphDescriptionFileName;
	Huffman treeInfo;
	int prevwe;
	int flag = 0;
	Map<Integer, String> map = new HashMap<>();
	List<Integer> l = new ArrayList<>();



	public gDescriptionWriter(String org, String dot, Huffman h) {
		givenString = org;
		graphDescriptionFileName = dot;
		treeInfo = h;
	}

	public void generateDescriptionFile(String fname) {
		if (treeInfo.sizeOfHuffManTree > 1) {
			node n = treeInfo.root;
			try (PrintWriter o = new PrintWriter(new BufferedWriter(new FileWriter(fname)))) {
				o.println("digraph g {");
				writerMethod(n, o);
				o.println("}");
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	public void writerMethod(node n, PrintWriter o) {
		if (!treeInfo.checkIfLeafOrNot(n)) {
			if (n.left != null) {
				String tempVariable2 = "";
				char tempVariable = n.left.character;
				if (tempVariable != '\0' && tempVariable != ' ' && tempVariable != '"' && tempVariable != '\n')
					tempVariable2 = "\\n " + tempVariable;
				else if (tempVariable == ' ')
					tempVariable2 = "\\n blank";
				else if (tempVariable == '"')
					tempVariable2 = "\\n \\\"";
				else if (tempVariable == '\n')
					tempVariable2 = "\\n /n";
				if (prevwe == n.weight) {
					flag++;
				}
				map.put(n.weight, tempVariable2);
				if (prevwe == n.weight && flag > 2) {
					o.println(
							" \"" + "\\n" + n.weight + "\" -> \"" + "\\n" + n.left.weight + tempVariable2
									+ "\" [color=red, label=0]");
				} else {

					o.println(
							" \"" + "\\n" + n.weight + "\" -> \"" + "\\n" + n.left.weight + tempVariable2
									+ "\" [color=red, label=0]");
				}
				prevwe = n.weight;

				writerMethod(n.left, o);
			}
			if (n.right != null) {
				String tempVariable2 = "";
				char tempVariable = n.right.character;
				if (tempVariable != '\0' && tempVariable != ' ' && tempVariable != '"' && tempVariable != '\n')
					tempVariable2 = "\\n " + tempVariable;
				else if (tempVariable == ' ')
					tempVariable2 = "\\n blank";
				else if (tempVariable == '"')
					tempVariable2 = "\\n \\\"";
				else if (tempVariable == '\n')
					tempVariable2 = "\\n /n";
				if (prevwe == n.weight) {
					flag++;
				}
				if (prevwe == n.weight && flag > 2) {
					o.println(" \"" + "\\" + "n" + n.weight + " " + "\" -> \"" + "\\n" + n.right.weight + tempVariable2
							+ "\" [color=blue, label=1]");
				} else {
					o.println(" \"" + "\\" + "n" + n.weight + "\" -> \"" + "\\n" + n.right.weight + tempVariable2
							+ "\" [color=blue, label=1]");
				}
				prevwe = n.weight;
				writerMethod(n.right, o);
			}
		}
	}
}
