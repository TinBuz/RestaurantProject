/**
 * Purpose: DSA Final
 * Status: Complete and thoroughly tested
 * Last update: 4/26/2016
 * Submitted:  4/28/2016
 * Comment: List ADT class
 * @author: Nathan Aydelotte
 * @version: 2016.02.02
 */

public class ListArrayBased implements ListInterface
{

	private static final int MAX_LIST = 3;
	protected Object []items;  // an array of list items
	protected int numItems;  // number of items in list

	/**
	 * Constructor for List ADT
	 */
	public ListArrayBased()
	{
		items = new Object[MAX_LIST];
		numItems = 0;
	}  // end default constructor

	/**
	 * Determines whether a List is empty.
	 * @return
	 * boolean
	 */
	public boolean isEmpty()
	{
		return (numItems == 0);
	} // end isEmpty

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
	 * Removes all items from the List.
	 */
	public void removeAll()
	{
		// Creates a new array; marks old array for
		// garbage collection.
		items = new Object[MAX_LIST];
		numItems = 0;
	} // end removeAll

	/**
	 * Adds an item to the list at a designated index
	 * @param index
	 * Location in List
	 * @param item
	 * object you wish to add
	 * @throws ListIndexOutOfBoundsException
	 */
	public void add(int index, Object item)
			throws  ListIndexOutOfBoundsException
	{
		if (numItems >= items.length) //fixes implementation and programming style errors
		{
			throw new ListException("ListException on add");
		}  // end if
		if (index >= 0 && index <= numItems)
		{
			// make room for new element by shifting all items at
			// positions >= index toward the end of the
			// list (no shift if index == numItems+1)
			for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
			{
				items[pos+1] = items[pos];
			} // end for
			// insert new item
			items[index] = item;
			numItems++;
		}
		else
		{
			// index out of range
			throw new ListIndexOutOfBoundsException(
					"ListIndexOutOfBoundsException on add");
		}  // end if
	} //end add

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
		if (index >= 0 && index < numItems)
		{
			return items[index];
		}
		else
		{
			// index out of range
			throw new ListIndexOutOfBoundsException(
					"ListIndexOutOfBoundsException on get");
		}  // end if
	} // end get

	/**
	 * Removes and item at a designated index
	 * @param index
	 * Index of item you wish to delete
	 * @throws ListIndexOutOfBoundsException
	 */
	public void remove(int index)
			throws ListIndexOutOfBoundsException
	{
		if (index >= 0 && index < numItems)
		{
			// delete item by shifting all items at
			// positions > index toward the beginning of the list
			// (no shift if index == size)
			for (int pos = index+1; pos < items.length; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
			{
				items[pos-1] = items[pos];
				items[pos] = null; //added for memory leak
			}  // end for
			numItems--;
		}
		else
		{
			// index out of range
			throw new ListIndexOutOfBoundsException(
					"ListIndexOutOfBoundsException on remove");
		}  // end if
	} //end remove
}
