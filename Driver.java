package mainPackage;

/**
 * Purpose: DSA Final
 * Status: Complete and thoroughly tested
 * Last update: 04/26/16
 * Submitted:  04/28/16
 * Comment: Driver class of the Restaurant Application
 * @author: Nathan Aydelotte & Tin Buzancic
 * @version: 2016.04.26
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver
{
    private static BufferedReader buff; //= new BufferedReader(new InputStreamReader(System.in));

    /**
     * The main function of the driver
     * Calls setTables to set the number of tables for the no kids section and the kids section
     * then calls the Welcome menu
     */
    public static int main() throws IOException
    {
        buff = new BufferedReader(new FileReader("input.txt"));
        ListArrayBasedPlus noKids = new ListArrayBasedPlus();
        ListArrayBasedPlus kids = new ListArrayBasedPlus();
        CDLSReferenceBased customers = new CDLSReferenceBased();
        for(int i = 0; i < 9;)
        {
            setTables(noKids, kids);
            i = welcome(noKids, kids, customers);
        }
        return 0;
    }

    /**
     * setTables- sets the tables for the kids and no kids section
     * 
     * @param ListArrayBasedPlus noKids - the no kids section
     * @param ListArrayBasedPlus kids - the kids section
     */
    public static void setTables(ListArrayBasedPlus noKids, ListArrayBasedPlus kids) throws IOException
    {
        System.out.println("Welcome! How many tables are there in No Kids Section?");
        String sNumTables = buff.readLine();
        sNumTables.trim();
        int numTables = Integer.parseInt(sNumTables);
        for(int i = 0; i < numTables; i++)
        {
            System.out.println("What is the Table Number?");
            String tableNum = buff.readLine();
            if(i > 0)
            {
                boolean free = checkNumber(noKids, tableNum);
                if(!free)
                {
                    System.out.println("Table Number already in use!");
                    System.out.println("Please Select a new number");
                    tableNum = buff.readLine();
                }
            }
            System.out.println("How many Seats?");
            String numSeats = buff.readLine();
            Table table = new Table(tableNum, numSeats);
            noKids.add(i, table);
        }
        System.out.println("How many tables are there in Kids Section?");
        sNumTables = buff.readLine();
        sNumTables.trim();
        numTables = Integer.parseInt(sNumTables);
        for(int i = 0; i < numTables; i++)
        {
            System.out.println("What is the Table Number?");
            String tableNum = buff.readLine();
            if(i > 0)
            {
                boolean free = checkNumber(kids, tableNum);
                if(!free)
                {
                    System.out.println("Table Number already in use!");
                    System.out.println("Please Select a new number");
                    tableNum = buff.readLine();
                }
            }
            System.out.println("How many Seats?");
            String numSeats = buff.readLine();
            Table table = new Table(tableNum, numSeats);
            kids.add(i, table);
        }
    }

    /**
     * The welcome menu for the driver, displays information about the options
     * 
     * @param ListArrayBasedPlus noKids - the no kids section
     * @param ListArrayBasedPlus kids - the kids section
     * @param CDLSReferenceBased customers - the line of customers
     */
    public static int welcome(ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased customers) throws IOException
    {
        System.out.println("Welcome, select one:");
        System.out.println("\n1. Customer party enters the restaurant");
        System.out.println("2. Customer party is seated and served");
        System.out.println("3. Customer party leaves the restaurant");
        System.out.println("4. Add a table");
        System.out.println("5. Remove a table");
        System.out.println("6. Display available tables");
        System.out.println("7. Display info about waiting customer parties");
        System.out.println("8. Display info about customer parties being served");
        System.out.println("9. Exit");
        String sChoice = buff.readLine();
        sChoice.trim();
        int iChoice = Integer.parseInt(sChoice);
        switchBoard(iChoice, noKids, kids, customers);
        return iChoice;
    }

    /**
     * checkNumber - checks if the number given is already in use
     * 
     * @param ListArrayBasedPlus lab - either of the two table sections, kids or no Kids
     * @param String value - the table number you are trying to insert
     */
    public static boolean checkNumber(ListArrayBasedPlus lab, String value)
    {
        boolean free = true;
        boolean found = false;
        while(!found)
        {
            for(int i = 0; i < lab.size(); i++)
            {
                Table table = (Table) lab.get(i);
                if(value.equals(table.getNumber()))
                {
                    found = true;
                    free = false;
                }
            }
            found = true;
        }
        return free;
    }

    /**
     * switchBoard - Uses a switch statement to do what you ask to do
     * 
     * @param int i - the selection of menu option you wish to perform
     * @param ListArrayBasedPlus noKids - the no kids section of tables
     * @param ListArrayBasedPlus kids - the kids section of tables
     * @param CDLSRefereneBased customers - the line of customers
     */
    public static void switchBoard(int i, ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased customers) throws IOException
    {
        switch(i)
        {
        	case 9:
        		System.out.println("Restaurant Closed. Thank you!");
            	System.exit(0);
            case 1:
            	walkIn(customers);
            	break;
            case 2:
            	seated(noKids, kids, customers);
            	break;
            case 3:
            	break;
            case 4:
            	addTable(noKids, kids);
            	break;
            case 5:
            	break;
            case 6:
            	displayFreeTables(noKids, kids);
            	break;
            case 7:
            	break;
            case 8:
            	servedInfo(noKids, kids);
            	break;
            default:
            	System.out.println("Invalid Entry, try again.\n");
            	break;
        }
        welcome(noKids, kids, customers);
    }

    public static void walkIn(CDLSReferenceBased customers) throws IOException
    {
    	System.out.print("Enter Customer Party Name: ");
        String name = buff.readLine();
        System.out.println(name);
        System.out.print("Enter Party Size: ");
        String size = buff.readLine();
        System.out.println(size);
        System.out.print("Does the party have kids?(Y/N): ");
        String kids = buff.readLine();
        System.out.print(kids);
        char cKids = kids.charAt(0);
        Customer newCust = new Customer(name, size);
        newCust.setKids(cKids);
        customers.add(customers.size(), newCust);
    }

    /**
     * Steps through the list of customers to find a suitable table
     * if the table size is greater than the party size,
     * the table is assigned to the customer and set to filled
     * 
     * @param ListArrayBasedPlus noKids - the no kids section of tables
     * @param ListArrayBasedPlus kids - the kids section of tables
     * @param CDLSRefereneBased customers - the line of customers
     */
    public static void seated(ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased customers)
    {
        boolean seated = false;
        boolean full = false;
        int index = 0;
        Customer current = null;
        Table table = null;
        int tableSeats = 0;
        int partySize = 0;
        while(!seated && !full)
        {
            current = (Customer) customers.get(index);
            if(current.getKids())
            {
                for(int i = 0; i < kids.size(); i++)
                {
                    table = (Table) kids.get(i);
                    if(table.getOccupied() == false)
                    {
                        tableSeats = Integer.parseInt(table.getSeats());
                        partySize = Integer.parseInt(current.getSize());
                        if(tableSeats >= partySize)
                        {
                            table.setCustomer(current);
                            table.setOccupied(true);
                            seated = true;
                            i = kids.size();
                            customers.remove(index);
                        }
                    }
                }
            }
            else
            {
                for(int i = 0; i < noKids.size(); i++)
                {
                    table = (Table) noKids.get(i);
                    if(table.getOccupied() == false)
                    {
                        tableSeats = Integer.parseInt(table.getSeats());
                        partySize = Integer.parseInt(current.getSize());
                        if(tableSeats >= partySize)
                        {
                            table.setCustomer(current);
                            table.setOccupied(true);
                            seated = true;
                            i = noKids.size();
                            customers.remove(index);
                        }
                    }
                }
            }
            index++;
        }
        System.out.println("Customer " + current.toString() + " was seated at a table " + table.getNumber());
    }

    /**
     * Adds a table to a section, kids or no kids
     * The table has a number and an amount of seats
     * 
     * @param ListArrayBasedPlus noKids - the no kids section of tables
     * @param ListArrayBasedPlus kids - the kids section of tables
     */
    public static void addTable(ListArrayBasedPlus noKids, ListArrayBasedPlus kids) throws IOException
    {
        System.out.println("Kids Section? (K/N)");
        String section = buff.readLine();
        if(section.charAt(0) == 'K')
        {
            System.out.println("What number will be applied to the table?");
            String num = buff.readLine();
            checkNumber(kids, num);
            System.out.println("How many seats will there be?");
            String seats = buff.readLine();
            Table table = new Table(num, seats);
            kids.add(kids.size(), table);
            System.out.println("Table " + num + " added into " + section + " section");
        }
        else
        {
            System.out.println("What number will be applied to the table?");
            String num = buff.readLine();
            checkNumber(noKids, num);
            System.out.println("How many seats will there be?");
            String seats = buff.readLine();
            Table table = new Table(num, seats);
            noKids.add(noKids.size(), table);
            System.out.println("Table " + num + " added into " + section + " section");
        }
    }
    
    /**
     * Steps through the list of tables and checks if they are free
     * if they are free prints them out to the screen
     * done for both sections
     * 
     * @param ListArrayBasedPlus noKids - the no kids section of tables
     * @param ListArrayBasedPlus kids - the kids section of tables
     */
    public static void displayFreeTables(ListArrayBasedPlus noKids, ListArrayBasedPlus kids)
    {
        System.out.println("Kids Section: ");
        for(int i = 0; i < kids.size(); i++)
        {
            Table table = (Table) kids.get(i);
            if(!table.getOccupied())
            {
                System.out.println(table.toString());
            }
        }
        System.out.println("No Kids Section: ");
        for(int i = 0; i < noKids.size(); i++)
        {
            Table table = (Table) noKids.get(i);
            if(!table.getOccupied())
            {
                System.out.println(table.toString());
            }
        }
    }
    
    /**
     * Steps through the list of tables
     * Checks if they are taken
     * If they are prints out customer and table information
     * does this for both sections
     * 
     * @param ListArrayBasedPlus noKids - the no kids section of tables
     * @param ListArrayBasedPlus kids - the kids section of tables
     */
    public static void servedInfo(ListArrayBasedPlus noKids, ListArrayBasedPlus kids)
    {
        System.out.println("The people being served in the Kids section are: ");
        for(int i = 0; i < kids.size(); i++)
        {
            Table table = (Table) kids.get(i);
            if(table.getOccupied())
            {
                Customer cust = table.getCustomer();
                System.out.println(cust.toString() + " occupies table " + table.getNumber());
            }
        }
        System.out.println("The people being served in the No Kids section are: ");
        for(int i = 0; i < noKids.size(); i++)
        {
            Table table = (Table) noKids.get(i);
            if(table.getOccupied())
            {
                Customer cust = table.getCustomer();
                System.out.println(cust.toString() + " occupies table " + table.getNumber());
            }
        }
    }
}
