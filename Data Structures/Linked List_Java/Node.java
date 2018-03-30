/*
Matthew Wark
mtwark
Node.java
Node class meant to hold a chesspiece object, and a reference to the next node in a linked list.
 */
/**
 *
 * @author mattw
 */
public class Node {
    
    Chesspiece c;
    Node next;
    
    public Node(Chesspiece iC)
    {
        c = iC;
        next = null;
    }
    
}
