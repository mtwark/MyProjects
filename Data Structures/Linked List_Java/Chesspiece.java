/*
Matthew Wark
mtwark
Chesspiece.java
Superclass containing data and functions relevant to all chesspieces.
 */

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
    boolean isAtt = false;
    String team;
    String name;
    
    public Chesspiece()
    {
    }
    
	//Can be created with or without a name as an argument. The name will be set to the piece name if no name is passed.
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
        if(name.charAt(0) == 'q' || name.charAt(0) == 'b' || name.charAt(0) == 'r' || name.charAt(0) == 'k')
            team = "White";
        else
            team = "Black";
    }
	//dummy function to be overwritten by each subclass
    public boolean isAttacking(Chesspiece c) {return true;}

}
