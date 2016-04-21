
public class Table
{
    private String tableNumber;
    private String numSeats;
    
    /**
     * Constructor for objects of class Table
     */
    public Table(String tNum, String sNum)
    {
        tableNumber = tNum;
        numSeats = sNum;
    }
    
    public void setNumber(String tNum)
    {
        tableNumber = tNum;
    }
    
    public void setSeats(String sNum)
    {
        numSeats = sNum;
    }
    
    public String getNumber()
    {
        return tableNumber;
    }
    
    public String getSeats()
    {
        return numSeats;
    }
    
    public String toString()
    {
        return("Table Number: " + tableNumber + " has " + numSeats + " seats.");
    }
}
