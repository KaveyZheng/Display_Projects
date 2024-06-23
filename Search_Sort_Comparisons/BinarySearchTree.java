public class BinarySearchTree implements BinarySearchTreeFunctions
{
	private Node root;
	
	public BinarySearchTree() { root = null; }
	
	public Node getRoot() { return root; }
	
	public void setRoot(Node root) { this.root = root; }
		
	public void insertNode(Node z)
	{
		Node child = root;
		Node parent = null;
		
		while(child != null)
		{
			parent = child;
			if (z.getKey() < parent.getKey()) { child = child.getLeft(); }
			else { child = child.getRight(); }
		}
		z.setParent(parent);
		if (parent == null) { setRoot(z); }
		else 
		{
			if (z.getKey() < parent.getKey()) { parent.setLeft(z); }
			else { parent.setRight(z); }
		}
	}
	
	public void updateNode(Node z)
	{
		Node n = getNode(root, z.getKey());

		if (n != null) n.setData(z.getData());
	}
	
	public void preOrderWalk(Node x)
	{
		if(x != null)
		{
			System.out.println(x.toString());
			preOrderWalk(x.getLeft());
			preOrderWalk(x.getRight());
		}
	}
	
	public void preOrderWalk(Node x, java.util.ArrayList<String> list)
	{
		if(x != null)
		{
			list.add(x.toString());
			preOrderWalk(x.getLeft(), list);
			preOrderWalk(x.getRight(), list);
		}
	}
	
	public void preOrderWalk(Node x, String id, java.util.ArrayList<String> result)
	{
		if(x != null)
		{
			System.out.println(x.toString() + " " + id);
			result.add(x.getKey() + " " + id);
			preOrderWalk(x.getLeft(), "0"+id, result);
			preOrderWalk(x.getRight(), "1"+id, result);
		}
	}
	
	public void inOrderWalk(Node x)
	{
		if(x != null)
		{
			preOrderWalk(x.getLeft());
			System.out.println(x.toString());
			preOrderWalk(x.getRight());
		}
	}
	
	public void inOrderWalk(Node x, java.util.ArrayList<String> list)
	{
		if(x != null)
		{
			inOrderWalk(x.getLeft(), list);
			list.add(x.toString());
			inOrderWalk(x.getRight(), list);

		}
	}
	
	public void postOrderWalk(Node x)
	{
		if(x != null)
		{
			preOrderWalk(x.getLeft());
			preOrderWalk(x.getRight());
			System.out.println(x.toString());
		}
	}
	
	public void postOrderWalk(Node x, java.util.ArrayList<String> list)
	{
		if(x != null)
		{
			inOrderWalk(x.getLeft(), list);
			inOrderWalk(x.getRight(), list);
			list.add(x.toString());

		}
	}
		
	public Node getMax(Node x)
	{
		Node max = x;
		while (x != null) {
			max = x;
			x = x.getRight();
		}
		return max;
	}
	
	public Node getMin(Node x)
	{
		Node min = x;
		while (x != null) {
			min = x;
			x = x.getLeft();
		}
		return min;	
	}
	
	public Node getSuccessor(Node x)
	{
		if(x.getRight() != null) { return getMin(x.getRight()); }
		
		Node y = x.getParent();
		while((y != null) && x == y.getRight())
		{
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	public Node getPredecessor(Node x)
	{
		if(x.getLeft() != null) { return getMax(x.getLeft()); }
		Node y = x.getParent();
		while((y != null) && x == y.getLeft())
		{
			x = y;
			y = y.getParent();
		}
		return y;	
	}
	
	public Node getNode(Node x, int key)
	{
		while ((x != null) && (key != x.getKey())) {
			if (key < x.getKey()) { x = x.getLeft(); }
			else { x = x.getRight(); }
		}
		return x; 
	}
	
	public int getHeight(Node x)
	{
		if(x == null) { return -1; }
		
		int leftHeight = getHeight(x.getLeft());
		int rightHeight = getHeight(x.getRight());
		
		if(leftHeight > rightHeight) { return leftHeight + 1; }
		return rightHeight + 1;
	}
	
	public void shiftNode(Node u, Node v)
	{
		if (u.getParent() == null) { root = v; }
		else {
			if (u == u.getParent().getLeft()) { u.getParent().setLeft(v); }
			else { u.getParent().setRight(v); }
		}
		if (v != null) v.setParent(u.getParent());
	}
	
	public void deleteNode(Node z)
	{
		if (z.getLeft() == null) { shiftNode(z, z.getRight()); }
		else if (z.getRight() == null) { shiftNode(z, z.getLeft()); }
		else {
			Node successor = getSuccessor(z);

			if (successor.getParent() != z) 
			{
				shiftNode(successor, successor.getRight());
				successor.setRight(z.getRight());
				successor.getRight().setParent(successor);
			}
			shiftNode(z, successor);
			successor.setLeft(z.getLeft());
			successor.getLeft().setParent(successor);
		}
	}
}
