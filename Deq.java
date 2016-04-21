/* Purpose: Data Structure and Algorithms Final
 * Status: Ready for implementation
 * Last update: 3/10/2016
 * Submitted:  3/10/2016
 * Comment: test suite and sample run attached
 * @author: Tin Buzancic
 * @version: 2016.03.10
 */

public class Deq<T> extends Queue<T> implements ExtendedQueueInterface<T>
{

    public void enqueueFirst(T item) throws ExtendedQueueException
    {
        if(numItems == items.length)
        {
            super.resize();
        }
        front = (front + items.length - 1) % items.length;
        items[front] = item;
        numItems++;
    }


    public T dequeueLast() throws ExtendedQueueException
    {
        T temp = null;
        if(numItems != 0)
        {	
        	temp = items[end];
        	end = (end + items.length - 1) % items.length;
        	items[end] = null;
        	numItems--;
        }
        return temp;
    }

    public T peekLast() throws ExtendedQueueException
    {
    	if(numItems == 0)
    	{
    		throw new ExtendedQueueException("No items");
    	}
    	return items[(end + items.length - 1) % items.length];
    }
}


