package binaryTree;

import structures.CollectionI;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyBSTstore<E extends Comparable<E>> implements BinarySearchTreeI<E>, CollectionI<E> {
	protected RootParent $parent;
	protected int $size;

	public MyBSTstore() {
		$parent = new RootParent();
	}

	/**
	 * Returns root node of the tree.
	 * @return root
	 */
	@Override
	public TreeNodeI<E> getRoot() {
		return $parent.getChild();
	}

	/**
	 * Returns list of values in ascending order.
	 * @return list
	 */
	@Override
	public List<E> getValuesInAscOrder() {
		return getSub($parent.getChild(), true);
	}

	/**
	 * Returns list of values in descending order.
	 * @return list
	 */
	@Override
	public List<E> getValuesInDesOrder() {
		return getSub($parent.getChild(), false);
	}

	protected List getSub(TreeNodeI node, Boolean ascending) {
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

	/**
	 * Returns maximum value in the tree.
	 * @return max
	 */
	@Override
	public E getMaxValue() {
		return rightmost($parent).getValue();
	}

	/**
	 * Returns minimum value in the tree.
	 * @return min
	 */
	@Override
	public E getMinValue() {
		return leftmost($parent).getValue();
	}

	/**
	 * Returns height of the tree.
	 * @return height
	 */
	@Override
	public int getHeight() {
		return heightSub($parent) - 1;
	}

	protected int heightSub(TreeNodeI node) {
		TreeNodeI left = node.getLeftChild();
		TreeNodeI right = node.getRightChild();

		int leftHeight = left == null ? 0 : heightSub(left);
		int rightHeight = right == null ? 0 : heightSub(right);

		return 1 + Math.max(leftHeight, rightHeight);
	}

	/**
	 * Returns the previous consecutive node with respect to value.
	 * @param node of reference
	 * @return predecessor
	 */
	@Override
	public TreeNodeI<E> getPredecessor(TreeNodeI<E> node) {
		TreeNodeI child = node.getLeftChild();
		if (child != null) {
			return rightmost(child);
		}

		TreeNodeI<E> parent = node.getParent();
		while (parent != $parent && parent.getLeftChild() == node) {
			node = parent;
			parent = parent.getParent();
		}
		return parent;
	}

	/**
	 * Returns the next consecutive node with respect to value.
	 * @param node of reference
	 * @return successor
	 */
	@Override
	public TreeNodeI<E> getSuccessor(TreeNodeI<E> node) {
		TreeNodeI child = node.getRightChild();
		if (child != null) {
			return leftmost(child);
		}

		TreeNodeI<E> parent = node.getParent();
		while (parent != $parent && parent.getRightChild() == node) {
			node = parent;
			parent = parent.getParent();
		}
		return parent;
	}

	protected TreeNodeI<E> leftmost(TreeNodeI node) {
		TreeNodeI child = node.getLeftChild();
		TreeNodeI next;
		if (child != null) {
			while ((next = child.getLeftChild()) != null) {
				child = next;
			}
		}
		return child;
	}

	protected TreeNodeI<E> rightmost(TreeNodeI node) {
		TreeNodeI child = node.getRightChild();
		TreeNodeI next;
		if (child != null) {
			while ((next = child.getRightChild()) != null) {
				child = next;
			}
		}
		return child;
	}

	/**
	 * Inserts a value into the tree.
	 * @param value to add
	 */
	@Override
	public void add(E value) {
		if ($parent.getChild() == null) {
			$parent.setLeftChild(new MyTreeNode<E>(value));
			$size++;
			return;
		}
		if (addToNode($parent.getChild(), value)) {
			$size++;
		}
	}

	protected boolean addToNode(TreeNodeI node, Comparable value) {
		Object nodeval = node.getValue();
		int compare = value.compareTo(nodeval);

		if (node.isLeaf()) {
			if (compare < 0) {
				TreeNodeI child = new MyTreeNode(value);
				node.setLeftChild(child);
			}
			else if (compare >= 0) {
				TreeNodeI child = new MyTreeNode(value);
				node.setRightChild(child);
			}
			return true;
		}
		else {
			if (compare < 0) {
				TreeNodeI left = node.getLeftChild();

				if (left != null) {
					return addToNode(left, value);
				}

				TreeNodeI child = new MyTreeNode(value);
				node.setLeftChild(child);
				return true;
			}
			else if (compare >= 0) {
				TreeNodeI right = node.getRightChild();

				if (right != null) {
					return addToNode(right, value);
				}

				TreeNodeI child = new MyTreeNode(value);
				node.setRightChild(child);
				return true;
			}
			return false;
		}
	}

	/**
	 * Returns whether value is present in the tree.
	 * @param value to find
	 * @return whether value was found
	 */
	@Override
	public boolean contains(E value) {
		return containsSub($parent.getChild(), value);
	}

	protected boolean containsSub(TreeNodeI node, Comparable value) {
		if (node == null) {
			return false;
		}
		if (node.getValue().equals(value)) {
			return true;
		}
		if (containsSub(node.getLeftChild(), value)) {
			return true;
		}
		return containsSub(node.getRightChild(), value);
	}

	/**
	 * Returns an array of values in the tree.
	 * @return array
	 */
	@Override
	public Object[] toArray() {
		return getValuesInAscOrder().toArray();
	}

	/**
	 * Returns whether tree is empty.
	 * @return empty
	 */
	@Override
	public boolean isEmpty() {
		return $parent.getChild() == null;
	}

	/**
	 * Return the number of elements in the tree.
	 * @return size
	 */
	@Override
	public int size() {
		return $size;
	}

	/**
	 * Attempts to remove the first occurence of the value from the tree, and returns whether the value was indeed removed.
	 * @param value to remove
	 * @return whether value was removed
	 */
	@Override
	public boolean remove(E value) {
		if ($parent.getChild() == null) {
			return false;
		}

		boolean removed = removeSub($parent.getChild(), value);
		if (removed) {
			$size--;
		}
		return removed;
	}

	protected boolean removeSub(TreeNodeI node, Comparable value) {
		if (node.getValue().equals(value)) {
			deleteNode(node.getParent(), node);
			return true;
		}

		TreeNodeI left = node.getLeftChild();
		if (left != null) {
			if (removeSub(left, value)) {
				return true;
			}
		}

		TreeNodeI right = node.getRightChild();
		if (right != null) {
			if (removeSub(right, value)) {
				return true;
			}
		}

		return false;
	}

	protected void deleteNode(TreeNodeI<Comparable> parent, TreeNodeI<Comparable> node) {
		int compare = (parent == $parent)
			? 0
			: node.getValue().compareTo(parent.getValue());

		if (node.isLeaf()) {
			if (compare < 0) {
				parent.setLeftChild(null);
			}
			else {
				parent.setRightChild(null);
			}
		}
		else {
			TreeNodeI<Comparable> right = node.getRightChild();
			TreeNodeI<Comparable> left = node.getRightChild();
			boolean leftNull = left == null;
			boolean rightNull = right == null;
			if (leftNull && rightNull) {
				node.setValue(right.getValue());
				deleteNode(node, right);
			}
			else {
				TreeNodeI replace = leftNull ? right : left;
				if (compare < 0) {
					parent.setLeftChild(replace);
				}
				else {
					parent.setRightChild(replace);
				}
			}

		}

	}


	/**
	 * Clears the tree.
	 */
	@Override
	public void clear() {
		$parent.setChild(null);
		$size = 0;
	}

	/**
	 * Returns an Iterator for the tree.
	 * @return iterator
	 */
	@Override
	public Iterator<E> iterator() {
		throw new NotImplementedException();
	}

	protected class RootParent extends MyTreeNode {
		protected TreeNodeI $root;

		public TreeNodeI<E> getChild() {
			return $root;
		}

		public void setChild(TreeNodeI<E> node) {
			$root = node;
			node.setParent(this);
		}

		@Override
		public TreeNodeI getLeftChild() {
			return $root;
		}

		@Override
		public TreeNodeI getRightChild() {
			return $root;
		}

		@Override
		public void setLeftChild(TreeNodeI node) {
			$root = node;
			if (node != null) {
				node.setParent(this);
			}
		}

		@Override
		public void setRightChild(TreeNodeI node) {
			$root = node;
			if (node != null) {
				node.setParent(this);
			}
		}

		@Override
		public boolean isLeaf() {
			return false;
		}

		@Override
		public Object getValue() {
			return null;
		}

		@Override
		public void setValue(Object newData) {

		}

		@Override
		public TreeNodeI getParent() {
			return null;
		}

		@Override
		public void setParent(TreeNodeI newp) {

		}
	}
}
