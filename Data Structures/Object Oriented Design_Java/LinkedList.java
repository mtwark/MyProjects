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
        //in case anything is needed w node before discarding, can return this
        Node temp;
        if(toRemove == 0)
            head = head.next;
        else
            getNode(toRemove - 1).next = getNode(toRemove + 1);
        length--;
    }
    
	//not used outside of testing, but may be useful to have a function to print all Nodes in the list
    public void printNodes()
    {
        Node current = head;
        while(current != null)
        {
            System.out.println(current.c.name);
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
        int counter = 0;
        while(counter < length)
        {
            if(getNode(counter).c.name.charAt(0) == toFind.charAt(0))
                break;
            counter++;
        }
        if(counter == length)
            counter = -1;
        return counter;
    }
    
    int find(int c, int r)
    {
        int counter = 0;
        while(counter < length)
        {
            if((getNode(counter).c.col == c) && (getNode(counter).c.row == r))
                break;
            counter++;
        }
        if(counter == length)
            counter = -1;
        return counter;
    }
    
    Chesspiece getPiece(int c, int r)
    {
        return getNode(find(c, r)).c;
    }
    
    Chesspiece getPiece(int index)
    {
        return getNode(index).c;
    }
}
