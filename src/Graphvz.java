import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Graphvz {

	String orgStr;
	String dotfilename;
	Huffman get;

	public Graphvz(String org, String dot, Huffman h) {
		orgStr = org;
		dotfilename = dot;
		get = h;
	}

	public void WriteGraphVizFile(String fname) {
		if (get.treeSize > 1) {
			node n = get.root;
			try (PrintWriter o = new PrintWriter(new BufferedWriter(new FileWriter(fname)))) {
				o.println("digraph g {");
				dotFileWrite(n, o);
				o.println("}");
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	public void dotFileWrite(node n, PrintWriter o) {
		if (!get.isLeaf(n)) {
			if (n.left != null) {
				String t = "";
				char c = n.left.ch;
				if (c != '\0' && c != ' ' && c != '"' && c != '\n')
					t = "\\n " + c;
				else if (c == ' ')
					t = "\\n blank";
				else if (c == '"')
					t = "\\n \\\"";
				else if (c == '\n')
					t = "\\n /n";
				o.println(
						" \"" + "\\n" + n.weight + "\" -> \"" + "\\n" + n.left.weight + t + "\" [color=red, label=0]");
				dotFileWrite(n.left, o);
			}
			if (n.right != null) {
				String t = "";
				char c = n.right.ch;
				if (c != '\0' && c != ' ' && c != '"' && c != '\n')
					t = "\\n " + c;
				else if (c == ' ')
					t = "\\n blank";
				else if (c == '"')
					t = "\\n \\\"";
				else if (c == '\n')
					t = "\\n /n";
				o.println(" \"" + "\\" + "n" + n.weight + "\" -> \"" + "\\n" + n.right.weight + t
						+ "\" [color=blue, label=1]");
				dotFileWrite(n.right, o);
			}
		}
	}
}
