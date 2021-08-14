// --== CS400 File Header Information ==--
// Name: Rowan Kulp
// Email: rkulp@wisc.edu
// Team:GB
// TA: Dan Kiel
// Role: Test Engineer
// Lecturer: Florien Heimerl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * TestRedBlackTreeImplementation uses junit to test the PhoneBookBackEnd and
 * PhoneBookImplementation classes, which use the object ContactInfo, uploads data with
 * ContactTester and has data stored using the class RedBlackTree.
 *
 * @author Rowan
 *
 */
public class TestRedBlackTreeImplementation {
  /**
   * Adds contacts to PhoneBookBackEnd and checks that the look up method can find them.
   *
   */
  @Test
  public void testRBTExtraMethods() {
    PhoneBookBackEnd test = new PhoneBookBackEnd();


    // Creates new contacts
    ContactInfo c1 = new ContactInfo("Aditya Akella", "6088900122", "akella@cs.wisc.edu");
    ContactInfo c2 = new ContactInfo("Aws Albarghouthi", "6082627946", "aws@cs.wisc.edu");
    ContactInfo c3 = new ContactInfo("Andrea Arpaci-Dusseau", "6082656013", "dusseau@cs.wisc.edu");
    ContactInfo c4 = new ContactInfo("Remzi Arpaci-Dusseau", "6082637764", "remzi@cs.wisc.edu");
    // Adds contacts
    test.addContact(c1);
    test.addContact(c2);
    test.addContact(c3);
    test.addContact(c4);

    // Checks that methods return what they are supposed to
    if (test.search(c1.getName()) == null || !test.search(c1.getName()).equals(c1)) {
    	fail("Contact 1 was not correctly looked up");
    }
    if (test.search(c3.getName()) == null || !test.search(c3.getName()).equals(c3)) {
    	fail("Contact 3 was not correctly looked up");
    }
    if (test.search("Fake") != null) {
    	fail("A fake contact was looked up");
    }
  }

  /**
   * Adds several contacts and confirms that the email and name associated with the contact is
   * correct.
   */
  @Test
  public void testPhoneBook() {
    PhoneBookBackEnd test = new PhoneBookBackEnd();
    // Creates and adds two new contacts
    ContactInfo rowan = new ContactInfo("Rowan", "1234567890", "rkulp@wisc.edu");
    test.addContact(rowan);
    ContactInfo nicole = new ContactInfo("Nicole", "0987654321", "newelsh@wisc.edu");
    test.addContact(nicole);

    // Checks that the correct emails and names are returned when called
    if (!rowan.getEmail().equals("rkulp@wisc.edu")) {
    	fail("Did not return Rowan's email address");
    }
    if (!rowan.getName().equals("Rowan")) {
    	fail("Did not return Rowan's name");
    }
    if (!nicole.getEmail().equals("newelsh@wisc.edu")) {
    	fail("Did not return Nicole's email address");
    }
    if (!nicole.getName().equals("Nicole")) {
    	fail("Did not return Nicole's name");
    }
  }

  /**
   * Tests that the addInfo method adds the info for the computer science TAs and professors, that
   * the isEmpty method works, and that the contact can be looked up.
   */
  @Test
  public void testInfo() {
    PhoneBookBackEnd test = new PhoneBookBackEnd();

    // Checks that the test is considered empty
    if (test.isPhoneBookEmpty() != true) {
    	fail("Said it was not empty when the tree was empty");
    }

    // Adds info to the tree so it is no longer empty
    test.preload();

    // Checks that the tree is not considered empty
    if (test.isPhoneBookEmpty() == true) {
    	fail("Said the tree was empty when it was not");
    }

    // Uses a contact in the tree and checks that its in the tree and can be looked
    // up
    ContactInfo tester = new ContactInfo("Gary Dahl", "6082629415", "dahl@cs.wisc.edu");
    if (test.search(tester.getName()) == null 
        || test.search(tester.getName()).compareTo(tester) != 0) {
    	fail("Could not find a preloaded contact. Gary is lost!");
    }
  }

}
