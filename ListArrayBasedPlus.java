/*
 * Purpose: Data Structure and Algorithms Lab 2 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 02/02/16
 * Submitted:  02/04/16
 * Comment: test suite and sample run attached
 * @author: Nathan Aydelotte
 * @version: 2016.02.02
 */
public class ListArrayBasedPlus extends ListArrayBased
{
    /**
     * Constructor for objects of class ListArrayBasedPlus
     */
    public ListArrayBasedPlus()
    {
        super();
    }

    public void add(int index, Object item)
    {
        if(numItems == items.length)
        {
            resize();
        }
        super.add(index, item);
    }

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
