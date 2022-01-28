import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class gDescriptionWriter {

	String givenString;
	String graphDescriptionFileName;
	Huffman treeInfo;
	int prevwe;
	int flag = 0;

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
				if (prevwe == n.weight && flag > 2) {
					System.out.println("Graph Generation will not be correct");
				} else {
					o.println(
							" \"" + "\\n" + n.weight + "\" -> \"" + "\\n" + n.left.weight + tempVariable2
									+ "\" [color=red, label=0]");
					writerMethod(n.right, o);

				}
				prevwe = n.weight;
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
					System.out.println("Graph Generation will not be correct");
				} else {
					o.println(
							" \"" + "\\n" + n.weight + "\" -> \"" + "\\n" + n.left.weight + tempVariable2
									+ "\" [color=red, label=0]");
					writerMethod(n.right, o);

				}
				prevwe = n.weight;
			}
		}
	}
}