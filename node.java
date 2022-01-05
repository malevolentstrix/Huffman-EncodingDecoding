
public class node {
	int weight;
	char character;
	node left, right;

	public node(Character character, Integer weight) {
		this.weight = weight;
		this.character = character;
		this.left = null;
		this.right = null;
	}

	public node(Character character, Integer weight, node left, node right) {
		this.weight = weight;
		this.character = character;
		this.left = left;
		this.right = right;
	}
}