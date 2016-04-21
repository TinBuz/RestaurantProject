/*
 * Purpose: Data Structure and Algorithms Lab 5 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 03/09/16
 * Submitted:  02/25/16
 * Comment: test suite and sample run attached
 * @author: Nathan Aydelotte
 * @version: 2016.02.25
 */
public class QueueADT<T> implements QueueInterface<T>
{

    protected T []items;  // an array of list items
    protected int top;  // where the top of the Queue is
    protected int end; //where the end of the quue
    protected int numItems; // number of items 

    public QueueADT()
    {
        items =(T[]) new Object[3];
        top = 0;
        end = 0;
        numItems = 0;
    }  // end default constructor

    public boolean isEmpty()
    {
        return (items[end] == null);
    } // end isEmpty

    public void dequeueAll()
    {
        // Creates a new array; marks old array for
        // garbage collection.
        items =(T[]) new Object[5];
        top = 0;
        end = 0;
        numItems = 0;
    } // end removeAll

    public void enqueue(T newItem)
    throws  QueueException
    {
        if (numItems == items.length)
        {
            resize();
        }
        items[top] = newItem;
        top = (top + 1) % items.length;
        numItems++;
    } //end add

    protected void resize()
    {
        Object []temp =(T[]) new Object[items.length * 2];
        int j = 0;
        for(int i = end; j < items.length;)
        {
            temp[j] = items[i];
            i = (i + 1) % items.length;
            j++;
        }
        end = 0;
        items =(T[]) temp;
        top = numItems;
    }

    public T peek()
    throws QueueException
    {
        if (items[end] != null)
        {
            int n = top - 1;
            if(n == -1 && items[items.length - 1] != null)
            {
                n = items.length - 1;
            }
            else if(n == -1 && items[items.length - 1] == null)
            {
                n = 0;
            }
            T curr = items[n];
            return curr;
        }
        else
        {
            // index out of range
            throw new QueueException(
                "QueueException on get");
        }  // end if
    } // end get

    public T dequeue()
    throws QueueException
    {
        if (items[end] != null)
        {
            T item = items[end];
            items[end] = null;
            end = (end + 1) % items.length;
            numItems--;
            return item;
        }
        else
        {
            // index out of range
            throw new QueueException(
                "QueueException on remove");
        }  // end if
    } //end remove

    public String toString()
    {
        String stuff = null;
        int count = 0;
        for(int i = end; count < numItems;)
        {
            if(stuff == null && items[i] != null)
            {
                stuff = items[i] + " ";
                count++;
            }
            else if(items[i] != null)
            {
                stuff = stuff + items[i] + " ";
                count++;
            }
            i = (i + 1) % items.length;
        }
        return "This Queue of size " + Integer.toString(count) + " has the following items: " + stuff;
    }
}
