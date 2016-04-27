package mainPackage;

/**
 * Purpose: DSA Final
 * Status: Complete and thoroughly tested
 * Last update: 4/26/2016
 * Submitted:  4/28/2016
 * Comment: Subclass of ListArrayBased which offers a resize and a reverse method
 * @author: Nathan Aydelotte
 * @version: 2016.02.02
 */

public class ListArrayBasedPlus extends ListArrayBased
{
	/**
	 * Constructor for objects of class ListArrayBasedPlus which calls method form super class
	 */
	public ListArrayBasedPlus()
	{
		super();
	}

	/**
	 * Adds an item to the list at a designated index
	 * @param index
	 * Location in List
	 * @param item
	 * object you wish to add
	 * @throws ListIndexOutOfBoundsException
	 */
	public void add(int index, Object item)
	{
		if(numItems == items.length)
		{
			resize();
		}
		super.add(index, item);
	}

	/**
	 * Resizes the list when the array cap is met
	 */
	private void resize()
	{
		Object []temp = new Object[items.length * 2];
		for(int i = 0; i < items.length; i++)
		{
			temp[i] = items[i];
		}
		items = new Object[temp.length];
		items = temp;
	}

	/**
	 * Reverses the list
	 */
	public void reverse()
	{
		Object []temp = new Object[items.length];
		int j = 0;
		for(int i = super.size() - 1; i >= 0; i--)
		{
			temp[j] = items[i];
			j++;
		}
		int size = super.size();
		super.removeAll();
		for(int q = 0; q < size; q++)
		{
			if(temp[q] != null)
			{
				add(q, temp[q]);
			}
		}
	}

	/**
	 * toString method for List ADT Class
	 */
	public String toString()
	{
		String stuff = null;
		for(int i = 0; i < items.length; i++)
		{
			if(stuff == null && items[i] != null)
			{
				stuff = items[i] + "\n";
			}
			else if(items[i] != null)
			{
				stuff = stuff + items[i] + "\n";
			}
		}
		return "List of size " + super.size() + " has the following: " + stuff;
	}
}
