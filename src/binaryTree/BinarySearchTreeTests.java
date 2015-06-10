package binaryTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.*;

public class BinarySearchTreeTests {
	protected BinarySearchTreeI<Integer> sampleTree() {
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();

		tree.add(11);
		tree.add(2);
		tree.add(19);
		tree.add(13);
		tree.add(18);
		tree.add(15);
		tree.add(3);
		tree.add(4);
		tree.add(0);
		tree.add(1);
		tree.add(-1);

		return tree;
	}

	@Test
	public void treeIsInitiallyEmpty() {
		BinarySearchTreeI<String> tree = new MyBSTstore<String>();
		assertTrue(tree.isEmpty());
	}

	@Test
	public void treeCanAddRoot() {
		BinarySearchTreeI<String> tree = new MyBSTstore<String>();
		tree.add("TestString");
	}

	@Test
	public void treeCanGetRoot() {
		BinarySearchTreeI<String> tree = new MyBSTstore<String>();
		tree.add("TestString");
		assertEquals(tree.getRoot().getValue(), "TestString");
	}

	@Test
	public void treeCanAddMultiple() {
		BinarySearchTreeI<String> tree = new MyBSTstore<String>();
		tree.add("TestString");
		tree.add("ATestString");
		tree.add("ZTestString");
	}

	@Test
	public void treeCanRetrieveThreeAscending() {
		BinarySearchTreeI<String> tree = new MyBSTstore<String>();
		tree.add("TestString");
		tree.add("ATestString");
		tree.add("ZTestString");

		assertArrayEquals(tree.getValuesInAscOrder().toArray(), new String[] {
			"ATestString",
			"TestString",
			"ZTestString"
		});
	}

	@Test
	public void treeCanRetrieveThreeDescending() {
		BinarySearchTreeI<String> tree = new MyBSTstore<String>();
		tree.add("TestString");
		tree.add("ATestString");
		tree.add("ZTestString");

		assertArrayEquals(tree.getValuesInDesOrder().toArray(), new String[] {
			"ZTestString",
			"TestString",
			"ATestString"
		});
	}

	@Test
	public void treeCanRetrieveMultipleAscending() {
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();
		Random r = new Random();

		Integer[] array = new Integer[1000];

		{
			int random;
			for (int i = 0; i < 1000; i++) {
				random = r.nextInt();
				array[i] = random;
				tree.add(random);
			}
		}

		Arrays.sort(array);

		assertArrayEquals(tree.getValuesInAscOrder().toArray(), array);
	}

	@Test
	public void treeCanRetrieveMultipleDescending() {
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();
		Random r = new Random();

		Integer[] array = new Integer[1000];

		{
			int random;
			for (int i = 0; i < 1000; i++) {
				random = r.nextInt();
				array[i] = random;
				tree.add(random);
			}
		}

		Arrays.sort(array);
		Collections.reverse(Arrays.asList(array));

		assertArrayEquals(tree.getValuesInDesOrder().toArray(), array);
	}

	@Test
	public void treeContains() {
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();
		Random r = new Random();

		Integer[] array = new Integer[1000];

		{
			int random;
			for (int i = 0; i < 1000; i++) {
				random = r.nextInt();
				array[i] = random;
				tree.add(random);
			}
		}

		for (int i: array) {
			assertTrue(tree.contains(i));
		}
	}

	@Test
	public void treeSize() {
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();

		for (int i = 0; i < 1000; i++) {
			tree.add(i);
			tree.add(i);
		}
		for (int i = 0; i < 1000; i++) {
			tree.add(i);
			tree.add(i);
		}

		assertEquals(tree.size(), 4000);
	}

	@Test
	public void treeCanRemove() {
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();

		for (int i = 0; i < 1000; i++) {
			tree.add(i);
		}

		assertFalse(tree.remove(2000));
		assertTrue(tree.remove(500));
		assertFalse(tree.remove(500));

		assertEquals(tree.size(), 999);
	}

	@Test
	public void treeCanRemoveAndKeepDuplicate() {
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();

		tree.add(1);
		tree.add(1);

		assertTrue(tree.contains(1));
		tree.remove(1);
		assertTrue(tree.contains(1));
		tree.remove(1);
		assertFalse(tree.contains(1));
	}

	@Test
	public void treeCanGetDirectPredecessor() {
		Random r = new Random();
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>(1001);

		tree.add(500);
		list.add(500);

		{
			int random;
			for (int i = 0; i < 500; i++) {
				random = r.nextInt(500) + 501;
				list.add(random);
				tree.add(random);
				random = r.nextInt(500);
				list.add(random);
				tree.add(random);
			}
		}

		Collections.sort(list);
		TreeNodeI root = tree.getRoot();
		int index = list.indexOf(root.getValue());

		assertEquals(tree.getPredecessor(root).getValue(), list.get(index - 1));
	}

	@Test
	public void treeCanGetDirectSuccessor() {
		Random r = new Random();
		BinarySearchTreeI<Integer> tree = new MyBSTstore<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>(1001);

		tree.add(500);
		list.add(500);

		{
			int random;
			for (int i = 0; i < 500; i++) {
				random = r.nextInt(500) + 501;
				list.add(random);
				tree.add(random);
				random = r.nextInt(500);
				list.add(random);
				tree.add(random);
			}
		}

		Collections.sort(list);
		TreeNodeI root = tree.getRoot();
		int index = list.indexOf(root.getValue());

		assertEquals(tree.getSuccessor(root).getValue(), list.get(index + 1));
	}

	@Test
	public void treeCanGetSequential() {
		BinarySearchTreeI tree = sampleTree();
		TreeNodeI root = tree.getRoot();

		TreeNodeI twoNode = root.getLeftChild();
		assertEquals(twoNode.getValue(), 2);
		assertEquals(tree.getPredecessor(twoNode).getValue(), 1);

		TreeNodeI fifteenNode = root.getRightChild().getLeftChild().getRightChild().getLeftChild();
		assertEquals(fifteenNode.getValue(), 15);
		assertEquals(tree.getPredecessor(fifteenNode).getValue(), 13);

		TreeNodeI fourNode = twoNode.getRightChild().getRightChild();
		assertEquals(fourNode.getValue(), 4);
		assertEquals(tree.getSuccessor(fourNode).getValue(), 11);

		TreeNodeI oneNode = twoNode.getLeftChild().getRightChild();
		assertEquals(oneNode.getValue(), 1);
		assertEquals(tree.getSuccessor(oneNode).getValue(), 2);

	}

	@Test
	public void treeCanGetHeight() {
		BinarySearchTreeI tree = sampleTree();
		assertEquals(tree.getHeight(), 5);
	}

	@Test
	public void treeCanGetMin() {
		BinarySearchTreeI tree = sampleTree();
		assertEquals(tree.getMinValue(), -1);
	}

	@Test
	public void treeCanGetMax() {
		BinarySearchTreeI tree = sampleTree();
		assertEquals(tree.getMaxValue(), 19);
	}
}
