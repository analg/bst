package binaryTree;

public interface TreeNodeI<V> {

	public TreeNodeI<V> getLeftChild();

	public TreeNodeI<V> getRightChild();

	public void setLeftChild(TreeNodeI<V> in);

	public void setRightChild(TreeNodeI<V> in);

	public boolean isLeaf();

	public V getValue();

	public void setValue(V newData);

	public TreeNodeI<V> getParent();

	public void setParent(TreeNodeI<V> newp);

	//in your class, write toString(), have default, value only and value/left/right constructors
}