// --== CS400 File Header Information ==--
// Name: Aryaman Agarwal
// Email: agarwal59@wisc.edu
// Team: GB
// Role: Data Wrangler 2
// TA: Dan
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * Stores the information like name, number and email for each contact on the
 * Badger Phone Book. Also has a toString() method to display all the
 * information.
 * 
 * @author Aryaman Agarwal
 */
public class ContactInfo implements Comparable<ContactInfo> {
	private String name;
	private String number;
	private String email;

	/**
	 * This constructor stores the name, number and email of the contact, as given
	 * by the user.
	 * 
	 * @param name   stores individual's first and last name
	 * @param number stores individual's phone number
	 * @param email  stores individual's email id
	 */
	public ContactInfo(String name, String number, String email) {
		this.name = name;
		this.number = number;
		this.email = email;
	}

	/**
	 * This constructor stores only the name and email of the contact, as given by
	 * the user.
	 * 
	 * @param name   stores individual's first and last name
	 * @param email  stores individual's email id
	 */
	public ContactInfo(String name, String email) {
		this.name = name;
		this.number = "";
		this.email = email;
	}
	
	/**
	 * Returns the name of the contact.
	 * 
	 * @return name of the contact
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number of the contact.
	 * 
	 * @return number of the contact
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Returns the email of the contact.
	 * 
	 * @return email of the contact
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the name parameter to the new name given.
	 * 
	 * @param newName new name to be stored
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Sets the number parameter to the new number given.
	 * 
	 * @param newNumber new number to be stored
	 */
	public void setNumber(String newNumber) {
		this.number = newNumber;
	}

	/**
	 * Sets the email parameter to the new email given.
	 * 
	 * @param newEmail new email to be stored
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	/**
	 * Displays all user information available.
	 * 
	 * @return string containing user information
	 */
	@Override
	public String toString() {
		return "Name: " + name + ", " + "Number: " + number + ", " + "Email: " + email + "\n";
	}
  
	/**
   * This method implements compareTo method in java.lang.Comparable<T> interface. This compareTo
   * method takes a ContactInfo type argument called anotherContact to be compared with the 
   * ContactInfo object which called this compareTo method.
   * 
   * @param anotherContact the another ContactInfo to be compared
   * @return 0 if these two ContactInfo's name are the same; greater than 0 if this ContactInfo's 
   *     name is lexicographically bigger than that of the other; less than 0 if this 
   *     ContactInfo'name is lexicographically smaller than that of the other.
   */
  public int compareTo(ContactInfo anotherContact) {
    return  name.toLowerCase().compareTo(anotherContact.name.toLowerCase());
  }

}
