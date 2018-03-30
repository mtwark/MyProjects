/*
Matthew Wark
mtwark
Bishop.java
Class containing data and functions relevant to bishop chess pieces. Extends the Chesspiece superclass
 */

/**
 *
 * @author Matthew
 */
public class Bishop extends Chesspiece{

    public Bishop(int c, int r)
    {
        super(c, r);
        name = "Bishop";
    }
    
    public Bishop(int c, int r, String n)
    {
        super(c, r, n);
    }

    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(bPos == c.bPos)
            isAtt = true;
        if(bNeg == c.bNeg)
            isAtt = true;
        
        return isAtt;
    }

}
