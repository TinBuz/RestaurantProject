package mainPackage;

/**
 * Purpose: DSA Final
 * Status: Thoroughly tested
 * Last update: 4/26/2016
 * Submitted: 4/28/2016
 * Comment: Node Class with new fields next and back
 * @author: Tin Buzancic
 * @version: 2016.28.4
 */

public class Node
{
	private Object item;
	private Node next;
	private Node back;

	/**
	 * Constructor for Node Class
	 * @param newItem
	 * First item of the Node linked structure
	 */
	public Node(Object newItem)
	{
		item = newItem;
		next = null;
		back = null;
	} // end constructor

	/**
	 * Three argument constructor for Node Class
	 * @param newItem
	 * Item link to the node
	 * @param nextNode
	 * Reference to the next node
	 * @param backNode
	 * Reference to the previous node
	 */
	public Node(Object newItem, Node nextNode, Node backNode)
	{
		item = newItem;
		next = nextNode;
		back = backNode;
	} // end constructor

	/**
	 * Mutator for a give Node's item reference
	 * @param newItem
	 */
	public void setItem(Object newItem)
	{
		item = newItem;
	} // end setItem

	/**
	 * Accessor for the Node's item
	 * @return
	 * Object at the Node
	 */
	public Object getItem()
	{
		return item;
	} // end getItem

	/**
	 * Mutator to set reference to the next node
	 * @param nextNode
	 * Node next in order from the current node
	 */
	public void setNext(Node nextNode)
	{
		next = nextNode;
	} // end setNext

	/**
	 * Accessor for the next node
	 * @return
	 * Node Next Reference
	 */
	public Node getNext()
	{
		return next;
	} // end getNext

	/**
	 * Mutator to set reference to the previous
	 * @param backNode
	 * Node previous to the current node
	 */
	public void setBack(Node backNode)
	{
		back = backNode;
	} // end setBack

	/**
	 * Accessor for the previous node
	 * @return
	 * Node Previous Reference
	 */
	public Node getBack()
	{
		return back;
	} // end getBack
} // end class Node
