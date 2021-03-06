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
import java.io.InputStreamReader;

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
        //Instance Variables
        buff = new BufferedReader(new FileReader("input.txt"));
        ListArrayBasedPlus noKids = new ListArrayBasedPlus();		//No Kids Section
        ListArrayBasedPlus kids = new ListArrayBasedPlus();			//Kids Section
        CDLSReferenceBased customers = new CDLSReferenceBased();	//List of Customers
        CDLSReferenceBased served = new CDLSReferenceBased();       //List of served Customers                                      //counts amount of people served in the restaraunt

        //Start Program
        setTables(noKids, kids);
        while(true)
        {
            welcome(noKids, kids, customers, served);
        }
    }//end main

    /**
     * setTables- sets the tables for the kids and no kids section
     * 
     * @param ListArrayBasedPlus noKids - the no kids section
     * @param ListArrayBasedPlus kids - the kids section
     */
    public static void setTables(ListArrayBasedPlus noKids, ListArrayBasedPlus kids) throws IOException
    {
        System.out.print("Welcome! How many tables are there in No Kids Section?: ");
        String sNumTables = buff.readLine().trim();
        System.out.println(sNumTables);
        int numTables = Integer.parseInt(sNumTables);
        for(int i = 0; i < numTables; i++)
        {
            System.out.print("What is the Table Number?: ");
            String tableNum = buff.readLine().trim();
            System.out.println(tableNum);
            if(i > 0)
            {
                boolean free = checkNumber(noKids, tableNum);
                while(!free)
                {
                    System.out.println("Table Number already in use!");
                    System.out.print("Please Select a new number\n");
                    tableNum = buff.readLine().trim();
                    System.out.println(tableNum);
                    free = checkNumber(noKids, tableNum);
                }
            }
            System.out.print("How many Seats?: ");
            String numSeats = buff.readLine().trim();
            System.out.println(numSeats);
            Table table = new Table(tableNum, numSeats);
            noKids.add(i, table);
        }
        System.out.print("How many tables are there in Kids Section?: ");
        sNumTables = buff.readLine().trim();
        System.out.println(sNumTables);
        numTables = Integer.parseInt(sNumTables);
        for(int i = 0; i < numTables; i++)
        {
            System.out.print("What is the Table Number?: ");
            String tableNum = buff.readLine().trim();
            System.out.println(tableNum);
            if(i > 0)
            {
                boolean free = checkNumber(kids, tableNum);
                while(!free)
                {
                    System.out.println("Table Number already in use!");
                    System.out.print("Please Select a new number: ");
                    tableNum = buff.readLine().trim();
                    System.out.println(tableNum);
                    free = checkNumber(kids, tableNum);
                }
            }
            System.out.print("How many Seats?: ");
            String numSeats = buff.readLine().trim();
            System.out.println(numSeats);
            Table table = new Table(tableNum, numSeats);
            kids.add(i, table);
        }
    }//end setTables

    /**
     * The welcome menu for the driver, displays information about the options
     * 
     * @param ListArrayBasedPlus noKids - the no kids section
     * @param ListArrayBasedPlus kids - the kids section
     * @param CDLSReferenceBased customers - the line of customers
     */
    public static void welcome(ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased customers, CDLSReferenceBased served) throws IOException
    {
        System.out.print("Welcome to the Restaurant Menu\n"
            + "\t1. Customer party enters the restaurant\n"
            + "\t2. Customer party is seated and served\n"
            + "\t3. Customer party leaves the restaurant\n"
            + "\t4. Add a table\n"
            + "\t5. Remove a table\n"
            + "\t6. Display available tables\n"
            + "\t7. Display info about waiting customer parties\n"
            + "\t8. Display info about customer parties being served\n"
            + "\t9. Exit\n"
            + "Please Select a menu option: ");
        String response = buff.readLine().trim();
        System.out.println(response);
        switch(response)
        {
            case "9":
            System.out.println("Restaurant Closed. Thank you!");
            System.exit(0);
            case "1":
            walkIn(customers, noKids, kids, served);
            break;
            case "2":
            seated(noKids, kids, customers, served);
            break;
            case "3":
            leaves(noKids, kids, served);
            break;
            case "4":
            addTable(noKids, kids);
            break;
            case "5":
            removeTable(noKids, kids);
            break;
            case "6":
            displayFreeTables(noKids, kids);
            break;
            case "7":
            waitingCustomers(customers);
            break;
            case "8":
            servedInfo(noKids, kids, served);
            break;
            default:
            System.out.println("Invalid Entry, try again.\n");
            break;
        }
    }//end Welcome

    /**
     * checkNumber - checks if the number given is already in use
     * @param ListArrayBasedPlus list - either of the two table sections, kids or no Kids
     * @param String value - the table number you are trying to insert
     */
    public static boolean checkNumber(ListArrayBasedPlus list, String value)
    {
        boolean free = true;
        boolean found = false;
        while(!found)
        {
            for(int i = 0; i < list.size(); i++)
            {
                Table table = (Table) list.get(i);
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

    public static boolean checkNameCDLS(CDLSReferenceBased list, String name)
    {
        boolean found = false;
        Customer customer = null;
        int i = 0;
        while(!found && i < list.size())
        {
            customer = (Customer) list.get(i);
            if(customer.getName().equals(name))
            {
                found = true;
            }
            i++;
        }
        return found;
    }

    public static boolean checkNameLRB(ListArrayBasedPlus list, String name)
    {
        boolean found = false;
        Customer customer = null;
        Table table = null;
        int i = 0;
        while(!found && i < list.size())
        {
            table = (Table) list.get(i);
            customer = table.getCustomer();
            if(table.getOccupied())
            {
                if(customer.getName().equals(name))
                {
                    found = true;
                }
            }
            i++;
        }
        return found;
    }

    /**
     * walkIn - Customer first enters the restaurant to be seated
     * @param customers - List of customers waiting to be seated
     * @throws IOException
     */
    public static void walkIn(CDLSReferenceBased customers, ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased served) throws IOException
    {
        System.out.print("Enter Customer Party Name: ");
        String name = buff.readLine().trim();
        System.out.println(name);
        boolean good = false;
        boolean found = false;
        while(!good)
        {
            found = checkNameCDLS(customers, name);
            if(!found && served.size() > 0)
            {
                found = checkNameLRB(noKids, name);
            }
            if(!found && served.size() > 0)
            {
                found = checkNameLRB(kids, name);
            }
            if(!found)
            {
                good = true;
            }
            else
            {
                System.out.print("There already exists a customer with this name in the restaurant.\n" +
                    "Please select another Name: ");
                name = buff.readLine().trim();
                System.out.println(name);
            }
        }
        System.out.print("Enter Party Size: ");
        String size = buff.readLine();
        System.out.println(size);
        System.out.print("No kids Allowed?(Y/N): ");
        String hasKids = buff.readLine().trim();
        System.out.println(hasKids);
        char cKids = hasKids.charAt(0);
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
    public static void seated(ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased customers, CDLSReferenceBased served)
    {
        if(customers.size() == 0)
        {
            System.out.println("No one is currently in line!");
        }
        else
        {
            boolean seated = false;
            int index = 0;
            Customer current = null;
            Table table = null;
            Table sit = null;
            int tableSeats = 0;
            int partySize = 0;
            while(!seated && index < customers.size())
            {
                current = (Customer) customers.get(index);
                if(current.getKids())
                {
                    for(int i = 0; i < kids.size(); i++)
                    {
                        table = (Table) kids.get(i);
                        if(table.getOccupied() == false)
                        {
                            tableSeats = Integer.parseInt(table.getSeats().trim());
                            partySize = Integer.parseInt(current.getSize().trim());
                            if(tableSeats == partySize)
                            {
                                table.setCustomer(current);
                                table.setOccupied(true);
                                seated = true;
                                i = kids.size();
                                served.add(0, current);
                                customers.remove(index);
                                sit = table;
                            }
                            else if(tableSeats > partySize)
                            {
                                int sitSeats = 0;
                                if(sit != null)
                                {
                                    sitSeats = Integer.parseInt(sit.getSeats());
                                    if(tableSeats <= sitSeats)
                                    {
                                        sit = table;
                                    }
                                }
                                else
                                {
                                    sit = table;
                                }
                            }
                        }
                    }
                    if(!seated && sit != null)
                    {
                        sit.setCustomer(current);
                        sit.setOccupied(true);
                        seated = true;
                        served.add(0, current);
                        customers.remove(index);
                    }
                }
                else
                {
                    for(int i = 0; i < noKids.size(); i++)
                    {
                        table = (Table) noKids.get(i);
                        if(table.getOccupied() == false)
                        {
                            tableSeats = Integer.parseInt(table.getSeats().trim());
                            partySize = Integer.parseInt(current.getSize().trim());
                            if(tableSeats == partySize)
                            {
                                table.setCustomer(current);
                                table.setOccupied(true);
                                seated = true;
                                i = kids.size();
                                served.add(0, current);
                                customers.remove(index);
                                sit = table;
                            }
                            else if(tableSeats > partySize)
                            {
                                int sitSeats = 0;
                                if(sit != null)
                                {
                                    sitSeats = Integer.parseInt(sit.getSeats());
                                    if(tableSeats <= sitSeats)
                                    {
                                        sit = table;
                                    }
                                }
                                else
                                {
                                    sit = table;
                                }
                            }
                        }
                    }
                    if(!seated && sit != null)
                    {
                        sit.setCustomer(current);
                        sit.setOccupied(true);
                        seated = true;
                        served.add(0, current);
                        customers.remove(index);
                    }
                }
                if(!seated)
                {
                    System.out.println("Could not seat " + current.getName() + " no tables with a size of " + current.getSize() + " or greater.");
                }
                index++;
            }
            if(index == customers.size() && !seated)
            {
                System.out.println("The restraunt is currently full. Please wait till a customer leaves!");
            }
            else
            {
                System.out.println("Customer " + current.getName() + "'s party of " + current.getSize() + " was seated at a table " + sit.getNumber() + "( " + table.getSeats() + " seats).");
            }
        }
    }

    /**
     * leaves - removes a customer from a table and frees the table up for future use.
     * @param noKids - Section of restaurant without kids
     * @param kids - Section of restaurant with kids
     * @throws IOException
     */
    public static void leaves(ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased served) throws IOException
    {
        if(served.size() == 0)
        {
            System.out.println("There are no customers being served!");
        }
        else
        {
            System.out.print("Please enter the name of the Customer Party: ");
            String custName = buff.readLine().trim();
            System.out.println(custName);

            int i = 0;
            boolean found = false;
            Customer customer = null;
            Table table = null;

            while(!found && i < noKids.size())
            {
                table = (Table) noKids.get(i);
                if(table.getOccupied())
                {
                    if(table.getCustomer().getName().equals(custName))
                    {
                        customer = table.getCustomer();
                        table.setCustomer(null);
                        table.setOccupied(false);
                        found = true;
                        served.remove(0);
                    }
                }
                i++;
            }

            if(!found)
            {
                i = 0;
                while(!found && i < kids.size())
                {
                    table = (Table) kids.get(i);
                    if(table.getOccupied())
                    {
                        if(table.getCustomer().getName().equals(custName))
                        {
                            customer = table.getCustomer();
                            table.setCustomer(null);
                            table.setOccupied(false);
                            found = true;
                            served.remove(0);
                        }
                    }
                    i++;
                }
            }

            if(found)
            {
                System.out.println(custName + " has left the Restaurant\n"
                    + "Table " + table.getNumber() + " has been freed");
            }
            else
            {
                System.out.println("Customer does not exist or waiting to be seated.");
            }
        }
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
        System.out.print("Kids Section?(K/N): ");
        String section = buff.readLine();
        System.out.println(section);
        boolean free = false;
        if(section.charAt(0) == 'K')
        {
            System.out.print("What number will be applied to the table?: ");
            String num = buff.readLine().trim();
            System.out.println(num);
            free = checkNumber(kids, num);
            while(!free)
            {
                System.out.print("Number already in use. Please Select a new number: ");
                num = buff.readLine().trim();
                System.out.println(num);
                free = checkNumber(kids, num);
            }
            System.out.print("How many seats will there be?: ");
            String seats = buff.readLine().trim();
            System.out.println(seats);
            Table table = new Table(num, seats);
            kids.add(kids.size(), table);
            System.out.println("Table " + num + " added into " + section + " section");
        }
        else
        {
            System.out.print("What number will be applied to the table?: ");
            String num = buff.readLine().trim();
            System.out.println(num);
            free = checkNumber(noKids, num);
            while(!free)
            {
                System.out.print("Number already in use. Please Select a new number: ");
                num = buff.readLine().trim();
                System.out.println(num);
                free = checkNumber(noKids, num);
            }
            System.out.print("How many seats will there be?: ");
            String seats = buff.readLine().trim();
            System.out.println(seats);
            Table table = new Table(num, seats);
            noKids.add(noKids.size(), table);
            System.out.println("Table " + num + " added into " + section + " section");
        }
    }

    /**
     * removeTable - removes a table by section table number input from the user
     * @param noKids - section of restaurant with no kids
     * @param kids - section of restaurant with kids
     * @throws IOException
     */
    public static void removeTable(ListArrayBased noKids, ListArrayBased kids) throws IOException
    {
        System.out.print("You are now removing a table\n"
            + "From which section would you like to remove this table?(K/N): ");
        String response = buff.readLine().trim();
        System.out.println(response);
        System.out.print("Enter table number: ");
        String tableNumber = buff.readLine().trim();
        System.out.println(tableNumber);

        Table table = null;
        if(response.equalsIgnoreCase("K"))
        {
            int i = 0;
            boolean flag = false;
            while(!flag && i < kids.size())
            {
                table = (Table) kids.get(i);
                if(table.getNumber().equals(tableNumber))
                {
                    if(!table.getOccupied())
                    {
                        System.out.println("Table " + table.getNumber() + " with " + table.getSeats() + " has been removed");
                        kids.remove(i);
                        flag = true;
                    }
                    else
                    {
                        System.out.println("Can't remove a table that is currently in use!");
                        flag = true;
                    }		
                }
                i++;
            }
            if(!flag)
            {
                System.out.println("Table was not found.");
            }
        }
        if(response.equalsIgnoreCase("N"))
        {
            int i = 0;
            boolean flag = false;
            while(!flag && i < noKids.size())
            {
                table = (Table) noKids.get(i);
                if(table.getNumber().equals(tableNumber))
                {
                    if(!table.getOccupied())
                    {
                        System.out.println("Table " + table.getNumber() + " with " + table.getSeats() + " has been removed");
                        noKids.remove(i);
                        flag = true;
                    }
                    else
                    {
                        System.out.println("Can't remove a table that is currently in use!");
                        flag = true;
                    }
                }
                i++;
            }
            if(!flag)
            {
                System.out.println("Table was not found.");
            }
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
                System.out.println("\t" + table.toString());
            }
        }
        System.out.println("No Kids Section: ");
        for(int i = 0; i < noKids.size(); i++)
        {
            Table table = (Table) noKids.get(i);
            if(!table.getOccupied())
            {
                System.out.println("\t" + table.toString());
            }
        }
    }

    /**
     * waitingCustomers - Displays the info about the waiting customers
     * @param customers - List of Customers waiting to be seated
     */
    public static void waitingCustomers(CDLSReferenceBased list)
    {
        if(list.isEmpty())
        {
            System.out.println("There is no one waiting in line!");
        }
        else
        {
            Customer customer = null;
            String kids = null;
            System.out.println("The following " + list.size() + " customer parties are waiting for the tables: ");
            for(int i = 0; i < list.size(); i++)
            {
                customer = (Customer) list.get(i);
                if(customer.getKids())
                {
                    kids = "kids section";
                }
                else
                {
                    kids = "no kids section";
                }
                System.out.println("\t" + customer.getName() + "'s party with " + customer.getSize() + " is waiting for a table in the " + kids);
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
    public static void servedInfo(ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased served)
    {
        if(served.size() == 0)
        {
            System.out.println("No one is currently being served!");
        }
        else
        {
            System.out.println("The people being served in the Kids section are: ");
            for(int i = 0; i < kids.size(); i++)
            {
                Table table = (Table) kids.get(i);
                if(table.getOccupied())
                {
                    Customer cust = table.getCustomer();
                    System.out.println("\t" + cust.getName()+ "'s occupies table " + table.getNumber());
                }
            }
            System.out.println("The people being served in the No Kids section are: ");
            for(int i = 0; i < noKids.size(); i++)
            {
                Table table = (Table) noKids.get(i);
                if(table.getOccupied())
                {
                    Customer cust = table.getCustomer();
                    System.out.println("\t" + cust.getName() + "'s occupies table " + table.getNumber());
                }
            }
        }
    }
}
