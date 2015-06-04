package binaryTree;

import org.junit.Test;
import static org.junit.Assert.*;

public class TreeNodeTests {
	@Test
	public void nodeIsInitiallyLeaf() {
		TreeNodeI<String> node = new MyTreeNode<String>("TestString");
		assertTrue(node.isLeaf());
	}

	@Test
	public void nodeStoresValue() {
		TreeNodeI<String> node = new MyTreeNode<String>("TestString");
		assertEquals(node.getValue(), "TestString");
	}

	@Test
	public void nodeBecomesBranchAfterSet() {
		TreeNodeI<String> node = new MyTreeNode<String>("TestString");
		node.setLeftChild(new MyTreeNode<String>("ATestString"));
		assertFalse(node.isLeaf());
	}

	@Test
	public void nodeRetainsValueAfterSet() {
		TreeNodeI<String> node = new MyTreeNode<String>("TestString");
		node.setLeftChild(new MyTreeNode<String>("ATestString"));
		node.setRightChild(new MyTreeNode<String>("ZTestString"));

		assertEquals(node.getLeftChild().getValue(), "ATestString");
		assertEquals(node.getValue(), "TestString");
		assertEquals(node.getRightChild().getValue(), "ZTestString");
	}
}
