// --== CS400 File Header Information ==--
// Name: Kexiang Fang
// Email: kfang25@wisc.edu
// Team: GB
// Role: Back End Developer 1
// TA: Dan
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This is the back end part of Badger Phone Book. This class creates a functional red-black tree
 * that user can know the number of contacts stored in this Badger Phone Book, know whether this 
 * Badger Phone Book is empty or not, lookup for some info of a person, get the whole list of 
 * contacts in this Badger Phone Book, and insert some new contacts. numOfContacts, isEmpty, lookup
 * methods are based on the inner class RedBlackTreeExtended which extends the original 
 * RedBlackTree class to have these functionalities.
 * 
 * @author Kexiang Fang
 *
 */
public class PhoneBookBackEnd{
  
  // Using the extended version of RedBlackTree, RedBlackTreeExtended, to create a new red-black
  // tree, which only stores ContactInfo
  private RedBlackTreeExtended<ContactInfo> tree;
  
  public PhoneBookBackEnd () {
    tree = new RedBlackTreeExtended<ContactInfo>();
  }
  
  
  /**
   * Inner class that extends original RedBlackTree class to have some additional functionalities 
   * including get the number of nodes stored in this red-black tree, know whether this red-black
   * tree is empty, and lookup for a node.
   * 
   * @author Kexiang Fang
   *
   * @param <T> the type of nodes stored in this red-black tree
   */
  @SuppressWarnings("rawtypes")
  protected class RedBlackTreeExtended<T extends Comparable<T>> extends RedBlackTree {
    
    /**
     * This method returns the number of nodes in this red-black tree
     * 
     * @param root the root of the subtree of every recursively calling  
     * @return the number of nodes stored in this red-black tree
     */
    public int numOfNodes(Node<T> root) { 
      if (root == null) {
        return 0;
      }
      else {
        return 1 + numOfNodes(root.leftChild) + numOfNodes(root.rightChild);
      }
    }
    
    /**
     * This method returns whether this red-black tree is empty or not.
     * 
     * @return true if this red-black tree is empty; false otherwise
     */
    public boolean isEmpty() { 
      return super.root == null;
    }
    
    /**
     * This method search for a node with given key and get the data value of this node. It will 
     * return null is no matching node is found. This method uses an private auxiliary recursive 
     * method with the same name (i.e., the lookup method is overloaded)
     * 
     * @param key the key used to search for a node
     * @return the data value of the matching node
     */
    @SuppressWarnings("unchecked")
    public T lookup(T key) {
      return (T) lookup(root, key);
    }
    
    /**
     * This is the private auxiliary recursive method that helps lookup(T key) method to search for
     * a node with given key and return the data value of this node. 
     * 
     * @param node the current node 
     * @param key the search key
     * @return the data value of the matching node
     */
    private T lookup(Node<T> node, T key) { 
      if (node == null) {
        return null; 
      }
      if (node.data.compareTo(key) == 0) {
        return node.data;
      }
      if (node.data.compareTo(key) > 0) { //this node's key > key; look in left subtree
        return lookup(node.leftChild, key);
      } 
      else { //this node's key < key; look in right subtree
        return lookup(node.rightChild, key);
      }
    } 
  }
  
  
  /**
   * This method calls numOfNodes method in RedBlackTreeExtended class to get the number of 
   * contacts stored in this Badger Phone Book
   * 
   * @return the number of contacts stored in this Badger Phone Book 
   */
  @SuppressWarnings("unchecked")
  public int numOfContacts() {
    return tree.numOfNodes(tree.root);
  }
  
  /**
   * This method calls isEmpty method in RedBlackTreeExtended class to get whether this Badger 
   * Phone Book is empty or not. 
   * 
   * @return true if this Badger Phone Book is empty; false otherwise
   */
  public boolean isPhoneBookEmpty() { 
    return tree.isEmpty();
  }
  
  /**
   * This method calls lookup method in RedBlackTreeExtended class to get the ContactInfo given by
   * the search key (the name of a person)
   * Front end developer can use this returned ContactInfo object to design user command for 
   * getting name and email address by using ContactInfo object’s getName() and getEmail() methods
   * correspondingly. 
   * 
   * @param key the key used to search for ContactInfo
   * @return ContactInfo the contact information of a person
   */
  public ContactInfo search(String key) { 
    ContactInfo contactWithKey = new ContactInfo(key,"","");
    return tree.lookup(contactWithKey);
  } 
  
  /**
   * This method calls toString method in RedBlackTree class to perform a level order traversal 
   * of this Badger Phone Book. The string representations of each data value within this Badger 
   * Phone Book are assembled into a comma separated string within brackets (similar to many 
   * implementations of java.util.Collection). And each data value is represented in this way:
   * "Name: " + name + "\n" + "Number: " + number + "\n" + "Email ID: " + email
   * 
   * @return string containing all contact information of this Badger Phone Book in level order
   */
  public String getBadgerPhoneBook() { 
    return tree.toString();
  }
  
  /**
   * This method calls insert method in RedBlackTree class to add a new contact in this Badger 
   * Phone Book. If no NullPointerException or IllegalArgumentException was thrown from insert 
   * method and caught by this method, the insert operation works; fails otherwise.  
   * 
   * @param contact a reference to a ContactInfo object which might has a person's name, phone 
   *     number, and email
   * @return true if a new ContactInfo object is successfully added to this Badger Phone Book, 
   *     false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean addContact(ContactInfo contact) { 
    try {
      tree.insert(contact);
      return true;
    }
    catch (NullPointerException | IllegalArgumentException e) {
      return false;
    }
  }
  
  public void preload() {
    ContactTest contacts = new ContactTest();
    for (int i = 0; i < contacts.list.length; i++) {
        if (contacts.list[i] != null) {
          this.addContact(contacts.list[i]);
        }
    }
  }
  
}
