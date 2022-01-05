
public class node {
	int weight;
	char ch;
	node left, right;

	public node(Character ch, Integer weight) {
		this.weight = weight;
		this.ch = ch;
		this.left = null;
		this.right = null;
	}

	public node(Character ch, Integer weight, node left, node right) {
		this.weight = weight;
		this.ch = ch;
		this.left = left;
		this.right = right;
	}
}