/**
 * Purpose: DSA Final
 * Status: Complete and thoroughly tested
 * Last update: 4/26/2016
 * Submitted:  4/28/2016
 * Comment: Interface for List ADT
 * @author: Tin Buzancic
 * @version: 2016.28.04
 */

public class Customer
{
    private String name;
    private String size;
    private boolean hasKids;
    
    /**
     * Constructor for objects of class Customer
     * @param name
     * String Name of the Customer
     * @param size
     * String Size of the Customer
     */
    public Customer(String name, String size)
    {
        this.name = name;
        this.size = size;
    }
    
    /**
     * Determinant whether customer has a child in the party
     * @param kids
     * char 'Y' for Yes, 'N' for No
     */
    public void setKids(char kids)
    {
        switch(kids)
        {
            case 'Y':
            this.hasKids = true;
            break;
            case 'N':
            this.hasKids = false;
            break;
        }
    }
    
    /**
     * Mutator for Customer name
     * @param name
     * String Name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Mutator for Customer size
     * @param size
     * String Size
     */
    public void setSize(String size)
    {
        this.size = size;
    }
    
    /**
     * Accessor for Customer Name
     * @return
     * String Name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Accessor for Customer size
     * @return
     * String Size
     */
    public String getSize()
    {
        return this.size;
    }
    
    /**
     * Accessor for Customer kids
     * @return
     * boolean True if they have kids, False if they do not
     */
    public boolean getKids()
    {
        return this.hasKids;
    }
    
    /**
     * toString method for Customer class
     */
    public String toString()
    {
        String kids = null;
        if(hasKids)
        {
            kids = " has kids, ";
        }
        else
        {
            kids = " does not have kids, ";
        }
        return name + " party " + kids + "and has " + size + " people";
    }
}

