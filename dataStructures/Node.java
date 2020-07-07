package dataStructures;

public class Node {
	private Integer label;
	private Integer cost;
	
	public Node(){}
	
	public Node(int label)
	{
		this.label = label;
	}
	
	public Node(int label, int cost) {
		this.label = label;
		this.cost = cost;
	}
	
	public int getLabel()
	{
		return this.label;
	}
	
	public void setLabel(int label)
	{
		this.label = label;
	}
	public int getCost() {
		return this.cost;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Node) || other == null) return false;
		if(other == this) return true;
		Node node = (Node) other;
		return this.label == node.label;
	}
	@Override
	public int hashCode() {
		return label.hashCode();
	}
}
