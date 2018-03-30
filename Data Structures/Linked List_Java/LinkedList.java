/*
Matthew Wark
mtwark
LinkedList.java
Linked List class made to hold a set of Nodes, which hold chesspieces. Contains a length data member, as well as methods to access nodes at specific indexes, and to search the list for specific pieces.
 */

/**
 *
 * @author mattw
 */
public class LinkedList {
    
	//The head will be the start of the list and length will be an int equal to the number of elements in the list.
    Node head;
    int length = 0;
    
    public LinkedList()
    {
        head = null;
    }
    
	//inserts a new Node at the start of the list holding a chesspiece passed as an argument
    public void insert(Chesspiece iC)
    {
        Node latest = new Node(iC);
        latest.next = head;
        head = latest;
        length++;
    }
    public void delete(int toRemove)
    {
        //didn't actually need to write this. Will add later
    }
    
	//not used outside of testing, but may be useful to have a function to print all Nodes in the list
    public void printNodes()
    {
        Node current = head;
        while(current != null)
        {
            System.out.print(current.c.name);
            System.out.print(current.c.col + 1);
            System.out.print(current.c.row + 1);
            current = current.next;
            
                   
        }
    }
    
	//method that traverses the list until it hits null or the index being passed. Returns a reference to the node if found, null if not
    Node getNode(int index)
    {
        int counter = 0;
        Node current = head;
        while(current != null && counter < index)
        {
            current = current.next;
            counter++;
        }
        return current;
    }
    
	//method that searches for a node by its' name. Returns the index of said node if found, or -1 if not found in the list.
    int find(String toFind)
    {
        
        boolean found = false;
        int counter = 0;
        while(!found && counter < length)
        {

            if(getNode(counter).c.name.substring(1).equals(toFind))
            {
                found = true;
                break;
            }  
            counter++;
        }
        if(counter == length)
            counter = -1;
        return counter;
    }
}
