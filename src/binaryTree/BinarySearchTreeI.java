package binaryTree;

import structures.CollectionI;

import java.util.List;

public interface BinarySearchTreeI<E> extends CollectionI<E>{
	
	
	//Set 1 (incl contains(), add(), size() and clear() in CollectionI, leave toArray())
	/**
	 * Helper method for testing
	 * @return
	 */
	public TreeNodeI<E> getRoot();
	
	
	//Set 2 (incl toArray() and equals())
	/**
	 * Describe
	 * @return what?
	 */
	public List<E> getValuesInAscOrder();
	/**
	 * Describe
	 * @return what?
	 */
	public List<E> getValuesInDesOrder();

	
	/**
	 * Describe
	 * @return what?
	 */
	public E getMaxValue();

	/**
	 * Describe
	 * @return what?
	 */
	public E getMinValue();


	/**
	 * Describe
	 * @return what?
	 */
	public int getHeight();
	
	//Set 3
	/**
	 * Describe
	 * @return what?
	 */
	public TreeNodeI<E> getPredecessor(TreeNodeI<E> examine);
	
	/**
	 * Describe
	 * @return what?
	 */
	public TreeNodeI<E> getSuccessor(TreeNodeI<E> examine);

	/**
	 * Describe
	 * @return what?
	 */
	public boolean remove(E val);
	
	//Set 5
	//public LinkedList<V> getValuesInBreadthFirstOrder();
	
}