package com.company;
 /*
 * This class maintains a Linked list of nodes that contain integer keys.
 */
public class IntSet {
	public  Node head = null;		// A Linked List to represernt the set. This is always maintained in sorted order.
	private Node sortNode = null;  // Node to be used for sorting purposes later, empty until print method called.
	private int capacity;	// The maximum allocated memory for the set.
	private int size;		// The number of elements currently stored in the set.

	/* Node class for our Linked List */
	public class Node
	{
		int key;
		Node next;

		Node(int k)
		{
			key = k;
		}
	}

	public IntSet()
	{
		size = 0;
		capacity = 10;
	}
	
	/* Find if a key is present in the Linked List. Returns true if the key is present, otehrwise false.*/
	public boolean find(int key)
	{
		Node n = head;
		while (n != null)
		{
			if (n.key == key)
			{
				return true;
			}
			n = n.next;
		}
		return false;
	}
// This method inserts a new node with key (newkey) into the Linked List, this method adds the new node in order by traversing the list each time it inserts a new node.
	 public void insert(int newkey)
	 {
	 	assert (!find(newkey));
		 Node addedNode = new Node(newkey); //Create the new node
		 Node currentNode = head;
		 Node previousNode = null;  // Keep track of previous and currentNode in order to determine correct location.
		 while (currentNode != null && newkey > currentNode.key)  //Traverse the linked list while looking at each Node key.
		 {
			 previousNode = currentNode;
			 currentNode = currentNode.next;  // Traversal as long as the newkey is greter than the older key, when it isn't anymore it is time to insert.
		 }
		 // Time for insertion now
		 if (previousNode == null)  // Incase the desired position for the new node is the head, if you insert 0 or 1 in this case.
		 {
			 head = addedNode;
		 }
		 else
			 previousNode.next = addedNode;  // Node before needs to make sure to point to the added node.

		 addedNode.next = currentNode; //New node.next now points to the correct next node, which was the currentNode.
		 size++;
	 }


	/* Remove one Node with a certain key from the set. The nodes before and after the deleted node are connected*/
	public void remove(int key)
	{
		assert (find(key));  // Make sure the node with desired key actually exists before we attempt to remove it.
		Node tempNode = head;
		Node prevNode = null;


		// Don't need to worry if Linked List is empty because of Assert :)


		// If the head itself contains the key we are looking to delete.
		if (tempNode != null && tempNode.key == key)
		{
			head = tempNode.next;
			size--;
			return;
		}

		// Traverse the linked list until the node we are at is the one we are looking for.
		while (tempNode != null && tempNode.key != key)
		{
			prevNode = tempNode;
			tempNode = tempNode.next;
		}

		// Emergency break in the event we have a null node.
		if (tempNode == null)
		{
			return;
		}

		// Unlink the node and decrease the size of the Linked List by 1, no need to worry about any exceptions because of our assert and null checker.
		prevNode.next = tempNode.next;
		size--;
	}
	
	/* Print the contents of the LinkedList, the LinkedList will already be in sorted order so this becomes a simple print! */
	public void print()
	{
		Node tempNode = head;
		// Traverse the entire list, printing the contents along the way.
		while (tempNode != null)
		{
			System.out.println(tempNode.key);
			tempNode = tempNode.next;

		}
	}

}
