/**
 * Purpose: DSA Final
 * Status: Complete and thoroughly tested
 * Last update: 4/26/2016
 * Submitted:  4/28/2016
 * Comment: CDLS ADT
 * @author: Nathan Aydelotte
 * @version: 2016.28.04
 */

class CDLSReferenceBased implements ListInterface
{
	// reference to linked list of items
	private Node head;
	private int numItems;

	/**
	 * Constructor for CDLS ADT
	 */
	public CDLSReferenceBased()
	{
		head = null;
		numItems = 0;
	}  // end default constructor

	/**
	 * Give us the size number of the List
	 * @return
	 * Integer Size
	 */
	public boolean isEmpty()
	{
		boolean empty = true;
		if(numItems > 0)
		{
			empty = false;
		}
		return empty;
	}  // end isEmpty

	/**
	 * Give us the size number of the List
	 * @return
	 * Integer Size
	 */
	public int size()
	{
		return numItems;
	}  // end size

	/**
	 * Locates a specified node in a linked list.
	 * @param index
	 * Node at index
	 * @return
	 * Node 
	 */
	private Node find(int index)
	{
		// --------------------------------------------------
		// Locates a specified node in a linked list.
		// Precondition: index is the number of the desired
		// node. Assumes that 0 <= index <= numItems
		// Postcondition: Returns a reference to the desired
		// node.
		// --------------------------------------------------
		Node curr = head;
		if(index == 0)
		{
			return curr;
		}
		else if(index >= numItems/2)
		{
			curr = curr.getBack();
			for(int i = numItems - 1; i > index; i--)
			{
				curr = curr.getBack();
			}
			return curr;
		}
		else
		{
			for (int skip = 0; skip < index; skip++)
			{
				curr = curr.getNext();
			} // end for
			return curr;
		}
	} // end find

	/**
	 * Retrieves an item at a designated index
	 * @param index
	 * Index of item you wish to get
	 * @return
	 * @throws ListIndexOutOfBoundsException
	 */
	public Object get(int index)
			throws ListIndexOutOfBoundsException
	{
		if (index >=0 && index < size())
		{
			// get reference to node, then data in node
			Node curr = find(index);
			Object dataItem = curr.getItem();
			return dataItem;
		}
		else
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on get");
		} // end if
	} // end get

	/**
	 * Adds an item to the list at a designated index
	 * @param index
	 * Location in List
	 * @param item
	 * object you wish to add
	 * @throws ListIndexOutOfBoundsException
	 */
	public void add(int index, Object item)
			throws ListIndexOutOfBoundsException
	{
		if (index >= 0 && index < size() + 1)
		{
			if (index == 0 && isEmpty() == true)
			{
				// insert the new node containing item at
				// beginning of list
				Node newNode = new Node(item);
				newNode.setNext(newNode);
				newNode.setBack(newNode);
				head = newNode;
				numItems++;
			}
			else if(index == 0)
			{
				Node newNode = new Node(item);
				Node curr = head;
				Node prev = find(numItems - 1);
				newNode.setNext(curr);
				newNode.setBack(prev);
				prev.setNext(newNode);
				curr.setBack(newNode);
				head = newNode;
				numItems++;
			}
			else
			{
				Node prev = find(index-1);
				// insert the new node containing item after
				// the node that prev references
				Node next = prev.getNext();
				Node newNode = new Node(item, next, prev);
				prev.setNext(newNode);
				next.setBack(newNode);
				numItems++;
			} // end if
		}
		else
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on add");
		} // end if
	}  // end add

	/**
	 * Removes and item at a designated index
	 * @param index
	 * Index of item you wish to delete
	 * @throws ListIndexOutOfBoundsException
	 */
	public void remove(int index)
			throws ListIndexOutOfBoundsException
	{
		if (index >= 0 && index < size())
		{
			if(numItems == 1)
			{
				head = null;
				numItems--;
			}
			else if (index == 0)
			{
				// delete the first node from the list
				Node curr = head;
				head = head.getNext();
				Node prev = curr.getBack();
				head.setBack(prev);
				prev.setNext(head);
				numItems--;
			}
			else
			{
				// delete the node after the node that prev
				// references, save reference to node
				Node prev = find(index - 1);
				Node curr = prev.getNext();
				Node next = curr.getNext();
				next.setBack(prev);
				prev.setNext(next);
				numItems--;
			} // end if
		} // end if
		else
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on remove");
		} // end if
	}   // end remove

	/**
	 * Removes all items from the List.
	 */
	public void removeAll()
	{
		// setting head to null causes list to be
		// unreachable and thus marked for garbage
		// collection
		head = null;
		numItems = 0;
	} // end removeAll

	/**
	 * toSTring method for CDLS ADT
	 */
	public String toString()
	{
		String value = null;
		Node temp = head;
		for(int i = 0; i < size(); i++)
		{
			if(value == null)
			{
				value = temp.getItem().toString() + " ";
			}
			else
			{
				value = value + temp.getItem().toString() + " ";
			}
			temp = temp.getNext();
		}
		return "The size of this list is " + Integer.toString(size()) + ": "+ value;
	}
} // end ListReferenceBased
