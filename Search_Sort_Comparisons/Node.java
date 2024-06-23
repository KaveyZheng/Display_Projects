public class Node implements NodeFunctions
{
	private final int key;
	private Node parent;
	private Node left;
	private Node right;
	private Object data;
	
	public Node(int key, Object data)
	{
		this.key = key;
		parent = null;
		left = null;
		right = null;
		this.data = data;
	}
	
	public Node(int key) { this(key, null); }
	
	public int getKey() { return key; }
	
	public Node getParent() { return parent; }
	
	public Node getLeft() { return left; }
	
	public Node getRight() { return right; }
	
	public void setLeft(Node n) { left = n; }
	
	public void setRight(Node n){ right = n; }
	
	public void setParent(Node n) { parent = n; }
	
	public String toString() 
	{ 
		/*
		String s = "(" + key + ",";
		if (parent != null) s += parent.key;
		s += ",";
		if (left != null) s += left.key;
		s += ",";
		if (right != null) s += right.key;
		s += ")";
		return s; 
		*/
		StringBuilder str = new StringBuilder();
		str.append("(" + key + ",");
		if (parent != null) str.append(parent.key);
		str.append(",");
		if (left != null) str.append(left.key);
		str.append(",");
		if (right != null) str.append(right.key);
		str.append(")");
		return str.toString();
	}
	
	public boolean equals(Object o)  {  
		if (this == o) return true; 
		if (o == null) return false;  

		if (getClass() != o.getClass()) return false;

		Node n = (Node) o;
		return key == n.key;
	}
	
	public Object getData() { return data; }
	
	public void setData(Object o) { data = o; }
}
