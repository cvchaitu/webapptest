/**
 * This class is just a utility class, you should not have to change anything here
 * @author rconklin
 */
public class DatabaseUtil {
	public static void initDB() {
		try {
			Connection cn = getConnection();
			Statement stmt = cn.createStatement();
			stmt.execute("CREATE TABLE PHONEBOOK (NAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
			stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Chris Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
			stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Dave Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
			cn.commit();
			cn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	/**
	 * Method inserts Person data into Database
	 * @param Person
	 * @return void
	 * @throws Exception
	 */
	public static void insertPerson(Person person) {
		try {
		Connection cn = getConnection();
		Statement stmt = cn.createStatement();
		String insertQuery= "INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('"+person.name+"','"+person.phoneNumber+"', '"+person.address+"')";
		stmt.execute(insertQuery);
	}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		}
			
	/**
	 * Method finds person in Database and retrieve data
	 * @param Person
	 * @return void
	 * @throws Exception
	 */
	public static Person findPerson(Person person) {
		Person returnPerson = new Person();
		try {
		Connection cn = getConnection();
		Statement stmt = cn.createStatement();
				
		String selectQuery= "SELECT * FROM PHONEBOOK WHERE NAME ='"+person.name+"';";
		ResultSet results=  stmt.executeQuery(selectQuery);
		while(results.next()){
			returnPerson.name=results.getString("NAME");
			returnPerson.phoneNumber=results.getString("PHONENUMBER");
			returnPerson.address=results.getString("ADDRESS");
				
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnPerson;
	}
			
	/**
	 * Method  retrieve all data from PhoneBook table
	 * @param Person
	 * @return void
	 * @throws Exception
	 */
	public static List getPhoneBook() {
		
		List phoneBook = new ArrayList();
		Person returnPerson = new Person();
		
		try {
		Connection cn = getConnection();
		Statement stmt = cn.createStatement();
				
		String selectQuery= "SELECT * FROM PHONEBOOK;";
		ResultSet results=  stmt.executeQuery(selectQuery);
		while(results.next()){
			returnPerson.name=results.getString("NAME");
			returnPerson.phoneNumber=results.getString("PHONENUMBER");
			returnPerson.address=results.getString("ADDRESS");
			phoneBook.add(returnPerson);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return phoneBook;
	}		
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
	}
}                                                   
