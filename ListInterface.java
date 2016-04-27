/**
 * Purpose: DSA Final
 * Status: Complete and thoroughly tested
 * Last update: 4/26/2016
 * Submitted:  4/28/2016
 * Comment: Interface for List ADT
 * @author: Tin Buzancic
 * @version: 2016.04.02
 */

public interface ListInterface 
{
	/**
	 * Determines whether a List is empty.
	 * @return
	 * boolean
	 */
	boolean isEmpty();

	/**
	 * Give us the size number of the List
	 * @return
	 * Integer Size
	 */
	int size();

	/**
	 * Adds an item to the list at a designated index
	 * @param index
	 * Location in List
	 * @param item
	 * object you wish to add
	 * @throws ListIndexOutOfBoundsException
	 */
	void add(int index, Object item) 
			throws ListIndexOutOfBoundsException;

	/**
	 * Retrieves an item at a designated index
	 * @param index
	 * Index of item you wish to get
	 * @return
	 * @throws ListIndexOutOfBoundsException
	 */
	Object get(int index) 
			throws ListIndexOutOfBoundsException;

	/**
	 * Removes and item at a designated index
	 * @param index
	 * Index of item you wish to delete
	 * @throws ListIndexOutOfBoundsException
	 */
	void remove(int index) 
			throws ListIndexOutOfBoundsException;

	/**
	 * Removes all items from the List.
	 */
	void removeAll();
}  // end ListInterface
