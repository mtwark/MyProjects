/*
Matthew Wark
mtwark
Chesspiece.java

Class for creating new chesspieces. Parent class for the Queen class.
*/

public class Chesspiece {
    //row and column for each placed queen
    int row;
    int col;
    
    /*for finding diagonal attacks
    value of b from y = mx + b formula for both a positive slope a negative slope, given the col = x and row = y
    if b values of two queens match, they must be on same diagonal*/
    int bPos;
    int bNeg;
    public boolean isAttacking(Chesspiece c) {return true;}
}
