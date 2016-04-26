public class Customer
{
    private String name;
    private String size;
    private boolean kids;
    
    /**
     * Constructor for objects of class Customer
     */
    public Customer(String nameIn, String sizeIn)
    {
        name = nameIn;
        size = sizeIn;
    }
    
    public void setKids(char kidsIn)
    {
        switch(kidsIn)
        {
            case 'Y':
            kids = true;
            break;
            case 'N':
            kids = false;
        }
    }
    
    public void setName(String nameIn)
    {
        name = nameIn;
    }
    
    public void setSize(String sizeIn)
    {
        size = sizeIn;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getSize()
    {
        return size;
    }
    
    public boolean getKids()
    {
        return kids;
    }
    
    public String toString()
    {
        String hasKids = null;
        if(kids)
        {
            hasKids = " has kids, ";
        }
        else
        {
            hasKids = " does not have kids, ";
        }
        return name + " party " + hasKids + "and has " + size + " people";
    }
}
