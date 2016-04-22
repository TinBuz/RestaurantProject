
public class Table
{
    private String tableNumber;
    private String numSeats;
    private boolean occupied;
    
    /**
     * Constructor for objects of class Table
     */
    public Table(String tNum, String sNum)
    {
        tableNumber = tNum;
        numSeats = sNum;
        occupied = false;
    }
    
    public void setNumber(String tNum)
    {
        tableNumber = tNum;
    }
    
    public void setSeats(String sNum)
    {
        numSeats = sNum;
    }

    public void setOccupied(boolean bIn)
    {
        occupied = bIn;
    }
    
    public String getNumber()
    {
        return tableNumber;
    }
    
    public String getSeats()
    {
        return numSeats;
    }
    
    public boolean getOccupied()
    {
        return occupied;
    }
    
    public String toString()
    {
        return("Table Number: " + tableNumber + " has " + numSeats + " seats.");
    }
}
