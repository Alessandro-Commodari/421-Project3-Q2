/**
 * Make sure the Postgresql JDBC driver is in your classpath.
 * You can download the JDBC 4 driver from here if required.
 * https://jdbc.postgresql.org/download.html
 *
 * take care of the variables usernamestring and passwordstring to use 
 * appropriate database credentials before you compile !
 *
**/
package proj_3;
import java.sql.* ;
import java.util.Scanner;



class simpleJDBC
{
    public static void main ( String [ ] args ) throws SQLException
    {
	// Unique table names.  Either the user supplies a unique identifier as a command line argument, or the program makes one up.
	String tableName = "";
        int sqlCode=0;      // Variable to hold SQLCODE
        String sqlState="00000";  // Variable to hold SQLSTATE
        Scanner reader = new Scanner(System.in);
        int mem_id = 10;
	
	// Register the driver.  You must register the driver before you can use it.    
    try {
    DriverManager.registerDriver ( new org.postgresql.Driver() ) ;
    } catch (Exception cnfe){
    System.out.println("Class not found");
    }

// This is the url you must use for Postgresql.
//Note: This url may not valid now !
String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
Connection con = DriverManager.getConnection (url,"cs421g43", "databas3partY") ;
Statement statement = con.createStatement ( ) ;

	System.out.println("Please select the function of your choice: ");
	System.out.println("1: Create a library profile \n"
						+ "2: Modify a library profile \n"
						+ "3: View your membership information \n"
						+ "4: Search the availability of a specific book \n"
						+ "5: Search books by author \n"
						+ "6: Quit \n");
	int userSelection = 0;
	System.out.print("Input your selection here: ");
	userSelection = reader.nextInt();
	switch(userSelection) {
	case 1:
		tableName = "member";
		String address;
		long number;
		String email;
		int choice;
		String type;
		int cost;
		System.out.println("You would like to create a library profile!");
		System.out.print("What is your address?: ");
		reader.nextLine();
		address = reader.nextLine();
		System.out.print("What is your phone number? (Please input it with no dashes like so 4501235493): ");
		number = reader.nextLong();
		System.out.print("What is your email address?: ");
		email = reader.next();
		System.out.print("Are you a student or a senior? (2 for student, 1 for senior, 0 for neither): ");
		choice = reader.nextInt();
		if(choice == 0)
		{	
			type = "Regular";
			cost = 10;
		}
		else if (choice == 1)
		{
			type = "Student";
			cost = 5;
		}
		else
		{
			type = "Senior";
			cost = 5;
		}
		try {
		    String insertSQL = "INSERT INTO " + tableName + " VALUES ( " +(mem_id+1)+ ", " +address+ ", " +number+ ", " +email+ ", 20 Dec 2017, " +cost+ ", " +type+") " ;
		    System.out.println ( insertSQL ) ;
		    statement.executeUpdate ( insertSQL ) ;
		    System.out.println ( "DONE" ) ;
		    System.out.println("Your account has been created with the following options: \n"
		    		+ "Address: "+address+"\n"
		    		+ "Phone number: "+number+"\n"
		    		+ "Email: "+email+"\n"
		    		+ "Expiry Date: 20 Dec 2017 \n"
		    		+ "Membership Cost: "+cost+"\n"
		    		+ "Membership Type: "+type);

		} catch (SQLException e)
	            {
	                sqlCode = e.getErrorCode(); // Get SQLCODE
	                sqlState = e.getSQLState(); // Get SQLSTATE
	    
	       
	                // Your code to handle errors comes here;
	                // something more meaningful than a print would be good
	                System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
	            }
		break;
	case 2:
		tableName = "member";
		String newAddress;
		long newNumber;
		String mail;
		String newEmail;
		int chx;
		int newChoice;
		System.out.println("\nWhat would you like to modify?");
		System.out.println("1: Modify address \n"
				+ "2: Modify email \n"
				+ "3: Modify phone number \n"
				+ "4: Modify your status (student/senior) \n"
				+ "5: Modify all three values \n");
		System.out.print("Input your selection here: ");
		int modifySelect = reader.nextInt();
		switch(modifySelect) {
			case 1:
				System.out.println("What is your current email address?: ");
				mail = reader.next();
				System.out.println("What is your new address?: ");
				reader.nextLine();
				newAddress = reader.nextLine();
				break;
			case 2:
				System.out.println("What is your current email address?: ");
				mail = reader.next();
				System.out.print("What is your new phone number? (Please input it with no dashes like so 4501235493): ");
				newNumber = reader.nextLong();
				break;
			case 3:
				System.out.println("What is your current email address?: ");
				mail = reader.next();
				System.out.println("What is your new email address?: ");
				newEmail = reader.next();
				break;
			case 4:
				System.out.println("What is your current email address?: ");
				mail = reader.next();
				System.out.print("Are you a student or a senior? (2 for student, 1 for senior, 0 for neither): ");
				newChoice = reader.nextInt();
				if(newChoice == 0)
				{	
					type = "Regular";
					cost = 10;
				}
				else if (newChoice == 1)
				{
					type = "Student";
					cost = 5;
				}
				else
				{
					type = "Senior";
					cost = 5;
				}
				break;
			case 5:
				System.out.println("What is your current email address?: ");
				mail = reader.next();
				System.out.print("What is your new address?: ");
				reader.nextLine();
				newAddress = reader.nextLine();
				System.out.print("What is your new phone number? (Please input it with no dashes like so 4501235493): ");
				newNumber = reader.nextLong();
				System.out.print("What is your new email address?: ");
				newEmail = reader.next();
				System.out.print("Are you a student or a senior? (2 for student, 1 for senior, 0 for neither): ");
				newChoice = reader.nextInt();
				if(newChoice == 0)
				{	
					type = "Regular";
					cost = 10;
				}
				else if (newChoice == 1)
				{
					type = "Student";
					cost = 5;
				}
				else
				{
					type = "Senior";
					cost = 5;
				}
				break;
			default:
				System.out.println("Invalid Selection");
			
		}
		break;
	case 3:
		break;
	case 4:
		break;
	case 5:
		break;
	case 6:
		System.out.println("Now Quitting...");
		statement.close ( ) ;
		con.close ( ) ;
		break;
	default:
		System.out.println("Invalid Selection");
	}

	// Inserting Data into the table
	/*
	try {
	    String insertSQL = "INSERT INTO " + tableName + " VALUES ( 400 , \'HSJUE3ID9XKJ3\' ) " ;
	    System.out.println ( insertSQL ) ;
	    statement.executeUpdate ( insertSQL ) ;
	    System.out.println ( "DONE" ) ;

	} catch (SQLException e)
            {
                sqlCode = e.getErrorCode(); // Get SQLCODE
                sqlState = e.getSQLState(); // Get SQLSTATE
                
                // Your code to handle errors comes here;
                // something more meaningful than a print would be good
                System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
            }

	// Querying a table
	try {
	    String querySQL = "SELECT user_id, password from " + tableName + " WHERE user_id = 400";
	    System.out.println (querySQL) ;
	    java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
	    while ( rs.next ( ) ) {
		int id = rs.getInt ( 1 ) ;
		String name = rs.getString (2);
		System.out.println ("id:  " + id);
		System.out.println ("name:  " + name);
	    }
	    System.out.println ("DONE");
	} catch (SQLException e)
	    {
		sqlCode = e.getErrorCode(); // Get SQLCODE
		sqlState = e.getSQLState(); // Get SQLSTATE
                
		// Your code to handle errors comes here;
		// something more meaningful than a print would be good
		System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
	    }

	//Updating a table
	/*
    	try {
	    String updateSQL = "UPDATE " + tableName + " SET NAME = \'Mimi\' WHERE id = 3";
	    System.out.println(updateSQL);
	    statement.executeUpdate(updateSQL);
	    System.out.println("DONE");

	    // Dropping a table
	    String dropSQL = "DROP TABLE " + tableName;
	    System.out.println ( dropSQL ) ;
	    statement.executeUpdate ( dropSQL ) ;
	    System.out.println ("DONE");
	} catch (SQLException e)
	    {
		sqlCode = e.getErrorCode(); // Get SQLCODE
		sqlState = e.getSQLState(); // Get SQLSTATE
                
		// Your code to handle errors comes here;
		// something more meaningful than a print would be good
		System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
	    }
	 */

	// Finally but importantly close the statement and connection
	
    }
}

