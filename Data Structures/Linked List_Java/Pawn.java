/*
Matthew Wark
mtwark
Pawn.java
Class containing data and functions relevant to pawn chess pieces. Extends the Chesspiece superclass
 */

/**
 *
 * @author mattw
 */
public class Pawn extends Chesspiece {
        public Pawn(int c, int r)
    {
        super(c, r);
        name = "Pawn";
    }
    
    public Pawn(int c, int r, String n)
    {
        super(c, r, n);
    }
    
    @Override
    public boolean isAttacking(Chesspiece c)
    {
		
		//if the team is black, can only attack diaganally moving down the board
        if(team == "Black")
        {
        if(bPos == c.bPos)
            if(row == c.row -1)
                isAtt = true;
        if(bNeg == c.bNeg)
            if(row == c.row -1)
                isAtt = true;
        }
        else
		
		//if the team is white, can only attack diaganally moving up the board
        {
        if(bPos == c.bPos)
            if(row == c.row + 1)
                isAtt = true;
        if(bNeg == c.bNeg)
            if(row == c.row + 1)
                isAtt = true;
        }
        
        return isAtt;
    }
}
