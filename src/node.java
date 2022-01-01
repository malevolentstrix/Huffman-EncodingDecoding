
 public class node{
		int  weight; // the weight of the node
		char ch; // character of the node
		node left, right; // left node and right node
		
		// Constructor for class node
		public node(Character ch, Integer weight){
			this.weight = weight;
			this.ch = ch;
			this.left = null;
			this.right = null;
		}
		// Constructor for class node
		public node(Character ch, Integer weight, node left, node right){
			this.weight = weight;
			this.ch = ch;
			this.left = left;
			this.right = right;
		}	
	}