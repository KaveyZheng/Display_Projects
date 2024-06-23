interface BinarySearchTreeFunctions
{
	public Node getRoot();
	public void setRoot(Node root);
	public void insertNode(Node z);
	public void updateNode(Node z);
	public void preOrderWalk(Node x);
	public void preOrderWalk(Node x, java.util.ArrayList<String> list);
	public void inOrderWalk(Node x);
	public void inOrderWalk(Node x, java.util.ArrayList<String> list);
	public void postOrderWalk(Node x);
	public void postOrderWalk(Node x, java.util.ArrayList<String> list);
	public Node getMax(Node x);
	public Node getMin(Node x);
	public Node getSuccessor(Node x);
	public Node getPredecessor(Node x);
	public Node getNode(Node x, int key);
	public int getHeight(Node x);
	public void shiftNode(Node u, Node v);
	public void deleteNode(Node z);
}