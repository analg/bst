package binaryTree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class BinarySearchTreeTests {
	@Test
	public void treeIsInitiallyEmpty() {
		BinarySearchTreeStoreI<String> tree = new MyBinaryTree<String>();
		assertTrue(tree.isEmpty());
	}

	@Test
	public void treeCanAddRoot() {
		BinarySearchTreeStoreI<String> tree = new MyBinaryTree<String>();
		tree.add("TestString");
	}

	@Test
	public void treeCanRetrieveRoot() {
		BinarySearchTreeStoreI<String> tree = new MyBinaryTree<String>();
		tree.add("TestString");
		assertEquals(tree.getRoot().getValue(), "TestString");
	}

	@Test
	public void treeCanAddMultiple() {
		BinarySearchTreeStoreI<String> tree = new MyBinaryTree<String>();
		tree.add("TestString");
		tree.add("ATestString");
		tree.add("ZTestString");
	}

	@Test
	public void treeCanRetrieveThreeAscending() {
		BinarySearchTreeStoreI<String> tree = new MyBinaryTree<String>();
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
		BinarySearchTreeStoreI<String> tree = new MyBinaryTree<String>();
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
	public void treeCanRetrieveMultiple() {
		BinarySearchTreeStoreI<Integer> tree = new MyBinaryTree<Integer>();
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

}
