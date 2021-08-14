// --== CS400 File Header Information ==--
// Name: Tyler Kortekaas
// Email: kortekaas@wisc.edu
// Team: GB
// Role: Front End Developer 2
// TA: Dan
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.Scanner;

/**
 * Front end development for a phonebook program. Allows user to input multiple commands, such as
 * adding a contact, editing one, looking up an existing one, displaying the current contacts, and
 * quitting the program. Comes preloaded with a variety of contacts.
 * 
 * @author tylerkortekaas - Front End Developer 2
 *
 */
public class PhoneBookImplementation {
  private static final String STARTUP_MSG =
      ("==================================\nWelcome to the phone book program!");
  private static PhoneBookBackEnd phonebook = new PhoneBookBackEnd();
  private static Scanner scnr = new Scanner(System.in);


  public static void main(String args[]) {
    System.out.println(STARTUP_MSG);
    // loads in data from data wranglers
    loadContacts();
    // Initial menu
    System.out.println("What would you like to do?\n" + "1) Add a contact - (a)\n"
        + "2) Edit an existing contact - (e)\n" + "3) Display contacts - (d)\n"
        + "4) Lookup contact - (l)\n" + "5) Quit app - (q)");
    String command = scnr.nextLine().trim().toLowerCase();
    // Quits program when 'q' is typed
    while (!command.equals("q")) {
      mainMenu(command);
      // Continues to reprint and ask for new input until 'q'
      System.out.println("What would you like to do?\n" + "1) Add a contact - (a)\n"
          + "2) Edit an existing contact - (e)\n" + "3) Display contacts - (d)\n"
          + "4) Lookup contact - (l)\n" + "5) Quit app - (q)");
      command = scnr.nextLine().trim().toLowerCase();
    }
    // End of program
    System.out.println("Exiting program. Have a nice day!");
    System.out.println("=================================");
  }

  /**
   * Adds contacts from data wrangler. If user does not want to see the contacts initially, they can
   * opt out.
   */
  public static void loadContacts() {
    phonebook.preload();
    System.out.println("Would you like to see the current contacts? (y/n)");
    String input = scnr.nextLine().trim();
    if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
      System.out.println("Incorrect input. Loading menu.\n");
    } else if (input.equalsIgnoreCase("y")) {
      displayContacts();
    } else if (input.equalsIgnoreCase("n")) {
      System.out.println("Loading menu.\n");
    }
  }

  /**
   * Looks up an existing contact. If the phonebook is empty or name not present, returns false.
   * 
   * @param contactName String of name to check if present in phonebook
   * @return true if successfully located, false if not
   */
  public static boolean lookupContact(String contactName) {
    if (phonebook.isPhoneBookEmpty() || phonebook.search(contactName) == null) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Adds a contact, using user input for each part of the contact. If contact is already present,
   * does not add. If successfully added, prints out how many contacts are present.
   */
  public static void addContact() {
    System.out.println("Please input the name you'd like to add: ");
    String nameInput = scnr.nextLine().trim();
    System.out.println("Please input the email you'd like to add: ");
    String emailInput = scnr.nextLine().trim();
    System.out.println("Please input the number you'd like to add: ");
    String numberInput = scnr.nextLine().trim();

    ContactInfo newContact = new ContactInfo(nameInput, numberInput, emailInput);
    if (lookupContact(newContact.getName()) == false) {
      if (phonebook.addContact(newContact)) {
        System.out.println("New contact added!");
        System.out
            .println("There are now " + phonebook.numOfContacts() + " contacts in the phonebook.");
        System.out.println("");
      } else {
        System.out.println("The addContact operation failed.");
      }
    }
  }

  /**
   * Edits a contact using a name to search for it. Can change phone number and email. If not
   * present, does not edit anything. If successful, prints out new contact.
   */
  public static void editContact() {
    System.out.println("Please enter the name you'd like to search for: ");
    String nameInput = scnr.nextLine().trim();
    if (lookupContact(nameInput) == false) {
      System.out.println("No contact by that name to modify.");
    }
    if (lookupContact(nameInput)) {
      System.out.println("Please input the new phone number.");
      String numberInput = scnr.nextLine().trim();
      System.out.println("Please input the new email: ");
      String emailInput = scnr.nextLine().trim();
      phonebook.search(nameInput).setNumber(numberInput);
      phonebook.search(nameInput).setEmail(emailInput);
      System.out.println(
          "Contact updated successfully! New contact: " + phonebook.search(nameInput).toString());
    }
  }

  /**
   * Displays the current contacts, and shows how many are present in the phonebook.
   */
  public static void displayContacts() {
    try {
      System.out.println(phonebook.getBadgerPhoneBook());
      System.out.println("There are " + phonebook.numOfContacts() + " contacts in the phonebook.");
    } catch (NullPointerException e) {
      System.out.println("There are currently 0 contacts in the phonebook.");
    }
    System.out.println("");

  }

  /**
   * Main menu, called continuously from main until 'q' is input. Uses switch statement to call
   * different methods, then finishes off any implementation needed for each method.
   * 
   * @param command String of what command to pass in
   */
  public static void mainMenu(String command) {
    switch (command) {
      // If not one of the following cases, asks user to reinput.
      default:
        System.out.println("Improper command.");
        break;
      // add function
      case "a":
        addContact();
        break;
      // edit function
      case "e":
        editContact();
        break;
      // display function
      case "d":
        displayContacts();
        break;
      // lookup function, prints out the details of the contact if the name is present
      case "l":
        System.out.println("Please enter the name you'd like to search for: ");
        String nameInput = scnr.nextLine().trim();
        if (lookupContact(nameInput) == false) {
          System.out.println("Name not found in phonebook.");
        } else {
          System.out.println("Here is the current contact information for " + nameInput + ".");
          System.out.println("Phone number: " + phonebook.search(nameInput).getNumber());
          System.out.println("Email: " + phonebook.search(nameInput).getEmail());
        }
        break;
    }
  }
}


