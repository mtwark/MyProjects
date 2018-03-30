/*
Matthew Wark
mtwark
Queen.java
Class containing data and functions relevant to queen chess pieces. Extends the Chesspiece superclass
 */

/**
 *
 * @author Matthew
 */
public class Queen extends Chesspiece{
    
    public Queen(int c, int r)
    {
        super(c, r);
        name = "Queen";
    }
    
    public Queen(int c, int r, String n)
    {
        super(c, r, n);
    }
    
    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(row == c.row) 
            isAtt = true;
        if(col == c.col)
            isAtt = true;
        if(bPos == c.bPos)
            isAtt = true;
        if(bNeg == c.bNeg)
            isAtt = true;
        
        return isAtt;
    }

}
