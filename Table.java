/**
 * Purpose: DSA Final
 * Status: Complete and thoroughly tested
 * Last update: 4/26/2016
 * Submitted:  4/28/2016
 * Comment: Table class which holds the table number, number of seats, occupation status, and a customer
 * @author: Nathan Aydelotte
 * @version: 2016.04.02
 */

public class Table
{
	private String tableNumber;
	private String numSeats;
	private boolean occupied;
	private Customer customer = null;

	/**
	 * Constructor for objects of class Table
	 * @param tNum
	 * String Table Number
	 * @param sNum
	 * String Number of seats per table
	 */
	public Table(String tNum, String sNum)
	{
		tableNumber = tNum;
		numSeats = sNum;
		occupied = false;
	}

	/**
	 * Mutator method for table number
	 * @param tNum
	 * String Table Number
	 */
	public void setNumber(String tNum)
	{
		tableNumber = tNum;
	}

	/**
	 * Mutator method for number of seats
	 * @param sNum
	 * String Number of Seats
	 */
	public void setSeats(String sNum)
	{
		numSeats = sNum;
	}

	/**
	 * Mutator for occupation of table
	 * @param bIn
	 * boolean flag if table is occupied or not
	 */
	public void setOccupied(boolean bIn)
	{
		occupied = bIn;
	}

	/**
	 * Mutator for setting a customer to a specific table
	 * @param cIn
	 * Customer Customer to be set at the table
	 */
	public void setCustomer(Customer cIn)
	{
		customer = cIn;
	}

	/**
	 * Accessor for Table number
	 * @return
	 * String Table Number
	 */
	public String getNumber()
	{
		return tableNumber;
	}

	/**
	 * Accessor for number of seats
	 * @return
	 * String Number of Seats
	 */
	public String getSeats()
	{
		return numSeats;
	}

	/**
	 * Accessor for occupation state
	 * @return
	 * boolean true or false depending on whether table is occupied
	 */
	public boolean getOccupied()
	{
		return occupied;
	}

	/**
	 * Accessor for Customer
	 * @return
	 * Customer Customer at table
	 */
	public Customer getCustomer()
	{
		return customer;
	}

	/**
	 * toString method with table number and number of seats
	 */
	public String toString()
	{
		return("Table Number: " + tableNumber + " has " + numSeats + " seats.");
	}
}
