// --== CS400 File Header Information ==-- 
// Name: Aryaman Agarwal
// Email: agarwal59@wisc.edu 
// Team: GB
// TA: Dan
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

/**
 * Red Black Tree implementation with a Node inner class for representing the
 * nodes within a tree. You can use this class' insert method to build a red
 * black tree, and its toString method to display the level order (breadth
 * first) traversal of values in that tree.
 * 
 * @author Aryaman Agarwal
 *
 * @param <T> datatype of the node
 */
public class RedBlackTree<T extends Comparable<T>> {
	/**
	 * This class represents a node holding a single value within a binary tree the
	 * parent, left, and right child references are always be maintained.
	 */
	protected static class Node<T> {
		public T data;
		public Node<T> parent; // null for root node
		public Node<T> leftChild;
		public Node<T> rightChild;
		public boolean isBlack;

		public Node(T data) {
			this.data = data;
			this.isBlack = false;
		}

		/**
		 * @return true when this node has a parent and is the left child of that
		 *         parent, otherwise return false
		 */
		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

		/**
		 * This method performs a level order traversal of the tree rooted at the
		 * current node. The string representations of each data value within this tree
		 * are assembled into a comma separated string within brackets (similar to many
		 * implementations of java.util.Collection).
		 * 
		 * @return string containing the values of this tree in level order
		 */
		@Override
		public String toString() { // display subtree in order traversal
			String output = "[";
			LinkedList<Node<T>> q = new LinkedList<>();
			q.add(this);
			while (!q.isEmpty()) {
				Node<T> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
			return output + "]";
		}
	}

	protected Node<T> root; // reference to root node of tree, null when empty

	/**
	 * Performs a naive insertion into a binary search tree: adding the input data
	 * value to a new node in a leaf position within the tree. After this insertion,
	 * no attempt is made to restructure or balance the tree. This tree will not
	 * hold null references, nor duplicate data values.
	 * 
	 * @param data to be added into this binary search tree
	 * @throws NullPointerException     when the provided data argument is null
	 * @throws IllegalArgumentException when the tree already contains data
	 */
	public void insert(T data) throws NullPointerException, IllegalArgumentException {
		// null references cannot be stored within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		Node<T> newNode = new Node<>(data);
		if (root == null) {
			root = newNode;
		} // add first node to an empty tree
		else {
			insertHelper(newNode, root); // recursively insert into subtree
		}
		root.isBlack = true;
	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newNode should be inserted, and then extend this tree by
	 * the newNode in that position.
	 * 
	 * @param newNode is the new node that is being added to this tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descenedent beneath
	 * @throws IllegalArgumentException when the newNode and subtree contain equal
	 *                                  data references (as defined by
	 *                                  Comparable.compareTo())
	 */
	private void insertHelper(Node<T> newNode, Node<T> subtree) {
		int compare = newNode.data.compareTo(subtree.data);
		// do not allow duplicate values to be stored within this tree
		if (compare == 0)
			throw new IllegalArgumentException("This RedBlackTree already contains that value.");

		// store newNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				// otherwise continue recursive search for location to insert
			} else
				insertHelper(newNode, subtree.leftChild);
		}

		// store newNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				// otherwise continue recursive search for location to insert
			} else
				insertHelper(newNode, subtree.rightChild);
		}
	}

	/**
	 * Takes a reference to a newly added red node as its only parameter. Method may
	 * be called recursively, in which case the input parameter may reference a
	 * different red node in the tree that potentially has a red parent node. This
	 * method resolves red child under red parent red black tree property violations
	 * that are introduced by inserting new nodes into a red black tree. While doing
	 * so, all other red black tree properties must also be preserved.
	 * 
	 * @param newNode new Red node inserted into the tree
	 */
	private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
		if (newNode.parent == null) { // node added is the root, so coloring it black
			newNode.isBlack = true;
			return;
		}

		if (newNode.parent.isBlack == true) { // since parent is black, no rules will be violated
			return;
		} else { // case where node's parent is red
			Node<T> parent = newNode.parent;
			Node<T> grandparent = parent.parent;
			Node<T> uncle = null;

			// getting uncle since restructuring will depend on parent's sibling
			if (grandparent != null && parent.isLeftChild()) {
				uncle = grandparent.rightChild;
			} else if (grandparent != null && !parent.isLeftChild()) {
				uncle = grandparent.leftChild;
			}

			// when uncle is red, we just recolor and check for grandparent's tree
			// violations
			if (uncle != null && uncle.isBlack == false) {
				parent.isBlack = true;
				uncle.isBlack = true;
				grandparent.isBlack = false;

				// to remove a possible double-red scenario between grandparent and its parent
				enforceRBTreePropertiesAfterInsert(grandparent);
				return;
			}

			// when uncle is black or null
			if (newNode.isLeftChild() && parent.isLeftChild()) {
				rotate(parent, grandparent);
				grandparent.isBlack = false;
				newNode.isBlack = false;
			} else if (!newNode.isLeftChild() && !parent.isLeftChild()) {
				rotate(parent, grandparent);
				grandparent.isBlack = false;
				newNode.isBlack = false;
			} else if (!newNode.isLeftChild() && parent.isLeftChild()) {
				rotate(newNode, parent);
				rotate(grandparent.leftChild, grandparent);
				grandparent.isBlack = false;
				newNode.isBlack = true;
			} else if (newNode.isLeftChild() && !parent.isLeftChild()) {
				rotate(newNode, parent);
				rotate(grandparent.rightChild, grandparent);
				grandparent.isBlack = false;
				newNode.isBlack = true;
			}
			return;
		}
	}

	/**
	 * This method performs a level order traversal of the tree. The string
	 * representations of each data value within this tree are assembled into a
	 * comma separated string within brackets (similar to many implementations of
	 * java.util.Collection, like java.util.ArrayList, LinkedList, etc).
	 * 
	 * @return string containing the values of this tree in level order
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * Performs the rotation operation on the provided nodes within this BST. When
	 * the provided child is a leftChild of the provided parent, this method will
	 * perform a right rotation (sometimes called a left-right rotation). When the
	 * provided child is a rightChild of the provided parent, this method will
	 * perform a left rotation (sometimes called a right-left rotation). When the
	 * provided nodes are not related in one of these ways, this method will throw
	 * an IllegalArgumentException.
	 * 
	 * @param child  is the node being rotated from child to parent position
	 *               (between these two node arguments)
	 * @param parent is the node being rotated from parent to child position
	 *               (between these two node arguments)
	 * @throws IllegalArgumentException when the provided child and parent node
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
		if (parent != null) {
			if (!child.isLeftChild()) { // left rotation
				parent.rightChild = child.leftChild;
				if (child.leftChild != null)
					child.leftChild.parent = parent;
				child.parent = parent.parent;
				if (parent.parent == null)
					root = child;
				else if (parent == parent.parent.leftChild)
					parent.parent.leftChild = child;
				else
					parent.parent.rightChild = child;
				child.leftChild = parent;
				parent.parent = child;
			} else if (child.isLeftChild()) { // right rotation
				parent.leftChild = child.rightChild;
				if (child.rightChild != null)
					child.rightChild.parent = parent;
				child.parent = parent.parent;
				if (parent.parent == null)
					root = child;
				else if (parent == parent.parent.leftChild)
					parent.parent.leftChild = child;
				else
					parent.parent.rightChild = child;
				child.rightChild = parent;
				parent.parent = child;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
