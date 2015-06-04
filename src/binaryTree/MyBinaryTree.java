package binaryTree;

import java.util.*;

public class MyBinaryTree<E extends Comparable<E>> implements BinarySearchTreeStoreI<E> {
	protected TreeNodeI<E> $root;
	protected int $size;

	public MyBinaryTree() {

	}

	@Override
	public TreeNodeI<E> getRoot() {
		return $root;
	}

	@Override
	public List<E> getValuesInAscOrder() {
		return getSub($root, true);
	}

	@Override
	public List<E> getValuesInDesOrder() {
		return getSub($root, false);
	}

	protected List<E> getSub(TreeNodeI node, Boolean ascending) {
		List list = new LinkedList();
		if (node.isLeaf()) {
			list.add(node.getValue());
		}
		else {
			TreeNodeI left = node.getLeftChild();
			TreeNodeI right = node.getRightChild();

			if (ascending) {
				if (left != null) {
					list.addAll(getSub(left, ascending));
				}
				list.add(node.getValue());
				if (right != null) {
					list.addAll(getSub(right, ascending));
				}
			}
			else {
				if (right != null) {
					list.addAll(getSub(right, ascending));
				}
				list.add(node.getValue());
				if (left != null) {
					list.addAll(getSub(left, ascending));
				}
			}
		}
		return list;
	}

	@Override
	public E getMaxValue() {
		TreeNodeI<E> node = $root;
		while (!node.isLeaf() && (node.getValue() != null)) {
			node = node.getRightChild();
		}
		return node.getValue();
	}

	@Override
	public E getMinValue() {
		TreeNodeI<E> node = $root;
		while (!node.isLeaf() && (node.getValue() != null)) {
			node = node.getLeftChild();
		}
		return node.getValue();
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public TreeNodeI<E> getPredecessor(TreeNodeI<E> examine) {
		return null;
	}

	@Override
	public TreeNodeI<E> getSuccessor(TreeNodeI<E> examine) {
		return null;
	}

	@Override
	public void setRoot(TreeNodeI<E> newRoot) {
		$root = newRoot;
	}

	@Override
	public boolean add(E value) {
		if ($root == null) {
			$root = new MyTreeNode<E>(value);
			return true;
		}
		boolean added = addToNode($root, value);
		if (added) {
			$size++;
		}
		return added;
	}

	protected boolean addToNode(TreeNodeI node, Comparable value) {
		Object nodeval = node.getValue();
		int compare = value.compareTo(nodeval);

		if (node.isLeaf()) {
			if (compare < 0) {
				node.setLeftChild(new MyTreeNode(value));
			}
			else if (compare > 0) {
				node.setRightChild(new MyTreeNode(value));
			}
			return compare != 0;
		}
		else {
			if (compare < 0) {
				TreeNodeI left = node.getLeftChild();

				if (left != null) {
					return addToNode(left, value);
				}
				node.setLeftChild(new MyTreeNode(value));
				return true;
			}
			else if (compare > 0) {
				TreeNodeI right = node.getRightChild();

				if (right != null) {
					return addToNode(right, value);
				}
				node.setRightChild(new MyTreeNode(value));
				return true;
			}
			return false;
		}
	}

	@Override
	public boolean contains(E val) {
		return false;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public boolean isEmpty() {
		return $root == null;
	}

	@Override
	public int size() {
		return $size;
	}

	@Override
	public boolean remove(E val) {
		return false;
	}

	@Override
	public void clear() {
		$root = null;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}
}
