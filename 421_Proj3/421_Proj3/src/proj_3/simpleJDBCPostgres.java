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
import java.sql.*;
import java.util.Scanner;



class simpleJDBC
{
    public static void main ( String [ ] args ) throws SQLException {
		// Unique table names.  Either the user supplies a unique identifier as a command line argument, or the program makes one up.
		String tableName;
		int sqlCode;      // Variable to hold SQLCODE
		String sqlState;  // Variable to hold SQLSTATE
		Scanner reader = new Scanner(System.in);
		int mem_id = 10;

		// Register the driver.  You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

// This is the url you must use for Postgresql.
//Note: This url may not valid now !
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		Connection con = DriverManager.getConnection(url, "cs421g43", "databas3partY");
		Statement statement = con.createStatement();
		int userSelection = 0;
		while (userSelection != 6) {
			System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////\n"
					+"///////////////////////////////////////////////////////////////////////////////////////////////////");
			System.out.println("Please select the function of your choice: ");
			System.out.println("1: Create a library profile \n"
					+ "2: Modify a library profile \n"
					+ "3: View your membership information \n"
					+ "4: Search the availability of a specific book \n"
					+ "5: Search books by author \n"
					+ "6: Quit \n");

			System.out.print(">> Input your selection here: ");
			userSelection = reader.nextInt();

			switch (userSelection) {
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
					if (choice == 0) {
						type = "Regular";
						cost = 10;
					} else if (choice == 1) {
						type = "Student";
						cost = 5;
					} else {
						type = "Senior";
						cost = 5;
					}
					try {
						String insertSQL = "INSERT INTO " + tableName + " VALUES ( " + (mem_id + 1) + ", " + address + ", " + number + ", " + email + ", 20 Dec 2017, " + cost + ", " + type + ") ";
						System.out.println(insertSQL);
						statement.executeUpdate(insertSQL);
						System.out.println("DONE");
						System.out.println("Your account has been created with the following options: \n"
								+ "Address: " + address + "\n"
								+ "Phone number: " + number + "\n"
								+ "Email: " + email + "\n"
								+ "Expiry Date: 20 Dec 2017 \n"
								+ "Membership Cost: " + cost + "\n"
								+ "Membership Type: " + type);

					} catch (SQLException e) {
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
					int newChoice;
					String newType;
					int newCost;
					System.out.println("\nWhat would you like to modify?");
					System.out.println("1: Modify address \n"
							+ "2: Modify email \n"
							+ "3: Modify phone number \n"
							+ "4: Modify your status (student/senior) \n"
							+ "5: Modify all three values \n");
					System.out.print("Input your selection here: ");
					int modifySelect = reader.nextInt();
					switch (modifySelect) {
						case 1:
							System.out.println("What is your current email address?: ");
							mail = reader.next();
							System.out.println("What is your new address?: ");
							reader.nextLine();
							newAddress = reader.nextLine();
							try {
								String updateSQL = "UPDATE " + tableName + " SET address = \'" + newAddress + "\' WHERE email = \'" + mail + "\'";
								System.out.println(updateSQL);
								statement.executeUpdate(updateSQL);
								System.out.println("DONE");
								System.out.println("Your account has been modified with the following option: \n"
										+ "Address: " + newAddress + "\n");
							} catch (SQLException e) {
								sqlCode = e.getErrorCode(); // Get SQLCODE
								sqlState = e.getSQLState(); // Get SQLSTATE

								// Your code to handle errors comes here;
								// something more meaningful than a print would be good
								System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
							}
							break;
						case 2:
							System.out.println("What is your current email address?: ");
							mail = reader.next();
							System.out.print("What is your new phone number? (Please input it with no dashes like so 4501235493): ");
							newNumber = reader.nextLong();
							try {
								String updateSQL = "UPDATE " + tableName + " SET phone_number = " + newNumber + " WHERE email = \'" + mail + "\'";
								System.out.println(updateSQL);
								statement.executeUpdate(updateSQL);
								System.out.println("DONE");
								System.out.println("Your account has been modified with the following option: \n"
										+ "Phone number: " + newNumber + "\n");
							} catch (SQLException e) {
								sqlCode = e.getErrorCode(); // Get SQLCODE
								sqlState = e.getSQLState(); // Get SQLSTATE

								// Your code to handle errors comes here;
								// something more meaningful than a print would be good
								System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
							}
							break;
						case 3:
							System.out.println("What is your current email address?: ");
							mail = reader.next();
							System.out.println("What is your new email address?: ");
							newEmail = reader.next();
							try {
								String updateSQL = "UPDATE " + tableName + " SET email = \'" + newEmail + "\' WHERE email = \'" + mail + "\'";
								System.out.println(updateSQL);
								statement.executeUpdate(updateSQL);
								System.out.println("DONE");
								System.out.println("Your account has been modified with the following option: \n"
										+ "Email: " + newEmail + "\n");
							} catch (SQLException e) {
								sqlCode = e.getErrorCode(); // Get SQLCODE
								sqlState = e.getSQLState(); // Get SQLSTATE

								// Your code to handle errors comes here;
								// something more meaningful than a print would be good
								System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
							}
							break;
						case 4:
							System.out.println("What is your current email address?: ");
							mail = reader.next();
							System.out.print("Are you a student or a senior? (2 for student, 1 for senior, 0 for neither): ");
							newChoice = reader.nextInt();
							if (newChoice == 0) {
								newType = "Regular";
								newCost = 10;
							} else if (newChoice == 1) {
								newType = "Student";
								newCost = 5;
							} else {
								newType = "Senior";
								newCost = 5;
							}
							try {
								String updateSQL = "UPDATE " + tableName + " SET mem_type = \'" + newType + "\', mem_cost =" + newCost + " WHERE email = \'" + mail + "\'";
								System.out.println(updateSQL);
								statement.executeUpdate(updateSQL);
								System.out.println("DONE");
								System.out.println("Your account has been modified with the following options: \n"
										+ "Membership Cost: " + newCost + "\n"
										+ "Membership Type: " + newType);
							} catch (SQLException e) {
								sqlCode = e.getErrorCode(); // Get SQLCODE
								sqlState = e.getSQLState(); // Get SQLSTATE

								// Your code to handle errors comes here;
								// something more meaningful than a print would be good
								System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
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
							if (newChoice == 0) {
								newType = "Regular";
								newCost = 10;
							} else if (newChoice == 1) {
								newType = "Student";
								newCost = 5;
							} else {
								newType = "Senior";
								newCost = 5;
							}
							try {
								String updateSQL = "UPDATE " + tableName + " SET address = \'" + newAddress + "\', email = \'" + newEmail + "\', phone_number = " + newNumber + ", mem_type = \'" + newType + "\', mem_cost = " + newCost + " WHERE email = \'" + mail + "\'";
								System.out.println(updateSQL);
								statement.executeUpdate(updateSQL);
								System.out.println("DONE");
								System.out.println("Your account has been modified with the following options: \n"
										+ "Address: " + newAddress + "\n"
										+ "Phone number: " + newNumber + "\n"
										+ "Email: " + newEmail + "\n"
										+ "Expiry Date: 20 Dec 2017 \n"
										+ "Membership Cost: " + newCost + "\n"
										+ "Membership Type: " + newType);

							} catch (SQLException e) {
								sqlCode = e.getErrorCode(); // Get SQLCODE
								sqlState = e.getSQLState(); // Get SQLSTATE


								// Your code to handle errors comes here;
								// something more meaningful than a print would be good
								System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
							}
							break;
						default:
							System.out.println("Invalid Selection");

					}
					break;
				case 3:
					tableName = "member";
					String emailAddr;
					System.out.println("What is your current email address?: ");
					emailAddr = reader.next();
					try {
						String selectSQL = "SELECT * FROM " + tableName + " WHERE email LIKE \'" + emailAddr + "\'";
						System.out.println(selectSQL + "\n");
						java.sql.ResultSet rs = statement.executeQuery(selectSQL);
						while (rs.next()) {
							int selectId = rs.getInt(1);
							String selectAddr = rs.getString(2);
							long selectNum = rs.getLong(3);
							String selectEmail = rs.getString(4);
							Date selectExp = rs.getDate(5);
							int selectCost = rs.getInt(6);
							String selectType = rs.getString(7);

							System.out.println("Here is your account info: \n"
									+ "ID: " + selectId + "\n"
									+ "Address: " + selectAddr + "\n"
									+ "Phone number: " + selectNum + "\n"
									+ "Email: " + selectEmail + "\n"
									+ "Expiry Date: " + selectExp + "\n"
									+ "Membership Cost: " + selectCost + "\n"
									+ "Membership Type: " + selectType);
							System.out.println("DONE");
						}
					} catch (SQLException e) {
						sqlCode = e.getErrorCode(); // Get SQLCODE
						sqlState = e.getSQLState(); // Get SQLSTATE


						// Your code to handle errors comes here;
						// something more meaningful than a print would be good
						System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
					}
					break;
				case 4:
					String tableName1 = "books";
					String tableName2 = "loans";
					String bookCheck;
					System.out.println(">> What book would you like to check for?: ");
					reader.nextLine();
					bookCheck = reader.nextLine();
					try {
						String countSQL = "SELECT COUNT(book_id) FROM " + tableName1 + " WHERE title LIKE  \'" + bookCheck + "\'";
						System.out.println(countSQL + "\n");
						java.sql.ResultSet rs1 = statement.executeQuery(countSQL);
						int size = 0;
						while (rs1.next()) {
							size = rs1.getInt(1);
							System.out.println(">> There are " + size + " book(s) with the title " + bookCheck + ".\n");
						}
						String selectSQL = "SELECT book_id FROM " + tableName1 + " WHERE title LIKE  \'" + bookCheck + "\'";
						System.out.println(selectSQL + "\n");
						java.sql.ResultSet rs2 = statement.executeQuery(selectSQL);
						int[] bookIds = new int[size];
						int count = 0;
						while (rs2.next()) {
							bookIds[count] = rs2.getInt(1);

							System.out.println(">> " + bookCheck + " - book ID: " + bookIds[count]);
							count++;
						}
						for (int element : bookIds) {
							String newSelectSQL = "SELECT * FROM " + tableName2 + " WHERE book_id = " + element;
							System.out.println("\n" + newSelectSQL + "\n");
							java.sql.ResultSet rs3 = statement.executeQuery(newSelectSQL);
							if (rs3.next()) {
								int id = rs3.getInt(3);
								String dueDate = rs3.getString(5);
								System.out.println(">> " + bookCheck + " with book ID " + id + " is currently on loan and should be returned by " + dueDate + ".");
							} else {
								System.out.println(">> " + bookCheck + " with book ID " + element + "is available.\n");
							}
						}
						System.out.println("DONE\n");
					} catch (SQLException e) {
						sqlCode = e.getErrorCode(); // Get SQLCODE
						sqlState = e.getSQLState(); // Get SQLSTATE


						// Your code to handle errors comes here;
						// something more meaningful than a print would be good
						System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
					}
					break;
				case 5:
					System.out.println(">> Search by author name:");
					reader.nextLine();
					String auth = reader.nextLine();
					try {
						String authSelectSQL = "SELECT title FROM books INNER JOIN written_by ON books.book_id = written_by.book_id INNER JOIN authors ON authors.authors_id = written_by.authors_id WHERE name LIKE \'" + auth+ "\'";
						System.out.println("\n"+authSelectSQL+"\n");
						java.sql.ResultSet rs = statement.executeQuery(authSelectSQL);
						if (rs.next()) {
							System.out.println(">> These are all the books written by " +auth+" in this library: \n");
							java.sql.ResultSet rsCheck = statement.executeQuery(authSelectSQL);
							while (rsCheck.next()) {

								String title = rsCheck.getString(1);
								System.out.println("-- " + title);
							}
						}
						else {
							System.out.println("This library doesn't have any books from "+auth+".\n");
						}
						System.out.println("\nDONE\n");
					} catch (SQLException e) {
						sqlCode = e.getErrorCode(); // Get SQLCODE
						sqlState = e.getSQLState(); // Get SQLSTATE

						// Your code to handle errors comes here;
						// something more meaningful than a print would be good
						System.out.println("There was an error \nCode: " + sqlCode + "  sqlState: " + sqlState);
					}
					break;
				case 6:
					System.out.println("Now Quitting...");
					statement.close();
					con.close();
					break;
				default:
					System.out.println("Invalid Selection");
			}
		}
	}
}

