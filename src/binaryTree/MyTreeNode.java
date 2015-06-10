package binaryTree;

public class MyTreeNode<E> implements TreeNodeI<E> {

	protected TreeNodeI<E> $left;
	protected TreeNodeI<E> $right;
	protected E $value;
	protected TreeNodeI<E> $parent;
	protected boolean $isLeaf;

	public MyTreeNode(E newValue) {
		$isLeaf = true;
		$value = newValue;
	}

	public MyTreeNode() {
	}

	@Override
	public TreeNodeI<E> getLeftChild() {
		return $left;
	}

	@Override
	public TreeNodeI<E> getRightChild() {
		return $right;
	}

	@Override
	public void setLeftChild(TreeNodeI<E> node) {

		boolean leftNull = node == null;
		$left = node;

		if (!leftNull) {
			node.setParent(this);
		}
		if (leftNull && $right == null) {
			$isLeaf = true;
		}
		else {
			$isLeaf = false;
		}
	}

	@Override
	public void setRightChild(TreeNodeI<E> node) {

		boolean rightNull = node == null;
		$right = node;

		if (!rightNull) {
			node.setParent(this);
		}
		if (rightNull && $left == null) {
			$isLeaf = true;
		}
		else {
			$isLeaf = false;
		}
	}

	@Override
	public boolean isLeaf() {
		return $isLeaf;
	}

	@Override
	public E getValue() {
		return $value;
	}

	@Override
	public void setValue(E value) {
		$value = value;
	}

	@Override
	public TreeNodeI<E> getParent() {
		return $parent;
	}

	@Override
	public void setParent(TreeNodeI<E> parent) {
		$parent = parent;
	}
}
