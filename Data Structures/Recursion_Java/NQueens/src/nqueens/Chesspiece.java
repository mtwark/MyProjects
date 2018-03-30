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
public class Chesspiece {
    //row and column for each placed piece
    int row;
    int col;
    
    //for finding diagonal attacks
    //value of b from y = mx + b formula for both a positive slope a negative slope, given the col = x and row = y
    //if b values of two queens match, they must be on same diagonal
    int bPos;
    int bNeg;
    
    String name = "";
    public Chesspiece(int c, int r)
    {
        col = c;
        row = r;
        bPos = row - col;
        bNeg = row + col;
    }
    
    public Chesspiece(int c, int r, String iname)
    {
        col = c;
        row = r;
        bPos = row - col;
        bNeg = row + col;
        name = iname;
    }
    public boolean isAttacking(Chesspiece c) {return true;}
}
