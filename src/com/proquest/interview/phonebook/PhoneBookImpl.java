package com.proquest.interview.phonebook;

import java.util.List;

import com.ibm.websphere.personalization.resources.cm.AddPermissionNotGrantedException;
import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
	public List people;
	
	/**
	 * Method inserts Person data into Database
	 * @param Person
	 * @return void
	 */
	@Override
	public void addPerson(Person newPerson) {
		//Insert person in database 
		DatabaseUtil.insertPerson(newPerson);
	}
	
	@Override
	public Person findPerson(String firstName, String lastName) {
		Person person =new Person();
		person.name = firstName +" " + lastName;
		
		person = DatabaseUtil.findPerson(person);
		return person;
	}
	
	public static void main(String[] args) {
		
		
		PhoneBookImpl phoneBookImpl = new PhoneBookImpl();
		Person foundPerson =new Person();
		List<Person> phonebook;
		DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

		/* TODO: create person objects and put them in the PhoneBook and database
		 * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/ 
		
		Person personJohn =new Person("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
		Person personCynthia =new Person("Cynthia Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI");
		phoneBookImpl.addPerson(personJohn);
		phoneBookImpl.addPerson(personCynthia);
				
		// TODO: print the phone book out to System.out
		phonebook = DatabaseUtil.getPhoneBook();
		for (Person person: phonebook ){
			
			System.out.println("NAME: "+person.name+", PHONE NUMBER: " + person.phoneNumber + ", ADDRESS: " +person.address);
			
		}
			
		// TODO: find Cynthia Smith and print out just her entry
		foundPerson  = phoneBookImpl.findPerson("Cynthia", "Smith");
		System.out.println("NAME: "+foundPerson.name+", PHONE NUMBER: " + foundPerson.phoneNumber + ", ADDRESS: " +foundPerson.address);

		// TODO: insert the new person objects into the database
		phoneBookImpl.addPerson(new Person("test name","(111) 222-3333","test street, testcity"));
	}
}


