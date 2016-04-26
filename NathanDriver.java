import java.io.*;

/*
 * Purpose: Data Structure and Algorithms Lab 11 problem 3
 * Status: Complete and thoroughly tested
 * Last update: 04/20/16
 * Submitted:  04/21/16
 * Comment: test suite and sample run attached
 * 
 * I edited BinarySearchTree to include the toStringinorder
 * I did not see anyway to cast a BinarySearchTree into
 * A MyBinarySearchTreePlus
 * @author: Nathan Aydelotte
 * @version: 2016.04.20
 */
public class Driver
{
    private static BufferedReader buff; //= new BufferedReader(new InputStreamReader(System.in));

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

    public static void switchBoard(int i, ListArrayBasedPlus noKids, ListArrayBasedPlus kids, CDLSReferenceBased customers) throws IOException
    {
        if(i < 1 || i > 9)
        {
            System.out.println("Error Selection is not in the list.");
            welcome(noKids, kids, customers);
        }
        switch(i)
        {
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
            case 9:
            System.out.println("Goodbye!");
            System.exit(0);
        }
        welcome(noKids, kids, customers);
    }

    public static void walkIn(CDLSReferenceBased customers) throws IOException
    {
        String name = buff.readLine();
        String size = buff.readLine();
        String kids = buff.readLine();
        char cKids = kids.charAt(0);
        Customer newCust = new Customer(name, size);
        newCust.setKids(cKids);
        customers.add(customers.size(), newCust);
    }

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

