interface NodeFunctions
{
	public int getKey();
	public Node getParent();
	public Node getLeft();
	public Node getRight();
	public void setLeft(Node n);
	public void setRight(Node n);
	public void setParent(Node n);
	public String toString();
	public boolean equals(Object o);
	public Object getData();
	public void setData(Object o);
}