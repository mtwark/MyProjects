/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

/**
 *
 * @author mattw
 */
public class LinkedList {
    
    Node head;
    int length = 0;
    
    public LinkedList()
    {
        head = null;
    }
    
    public void insert(Chesspiece iC)
    {
        Node latest = new Node(iC);
        latest.next = head;
        head = latest;
        length++;
    }
    public void delete(int toRemove)
    {
        
    }
    
    public void traverse(int index)
    {
        Node current = head;
        while(current != null)
        {
            current = current.next;
        }
    }
    
    Node getNode(int index)
    {
        int counter = 0;
        Node current = head;
        while(current != null && counter < index)
        {
            current = current.next;
        }
        return current;
    }
    
    boolean find(int toFind)
    {
        
        return false;
    }
}
