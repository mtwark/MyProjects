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
public class Node {
    
    Chesspiece c;
    Node next;
    
    public Node(Chesspiece iC)
    {
        c = iC;
        next = null;
    }
    
}
