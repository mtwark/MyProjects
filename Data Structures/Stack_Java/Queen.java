/*
Matthew Wark
mtwark
Queen.java

Sets up Queen object with position members, b values
corresponding to positive and negative slopes to check
diagonal attacks, and a method to check if it is attacking
another piece.*/

public class Queen extends Chesspiece{
    
    public Queen(int c, int r, boolean m)
    {
        col = c;
        row = r;
        bPos = row - col;
        bNeg = row + col;
        movable = m;
    }
    
    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(row == c.row) 
            return true;
        if(col == c.col)
            return true;
        if(bPos == c.bPos)
            return true;
        if(bNeg == c.bNeg)
            return true;
        
        return false;
    }
    

}
