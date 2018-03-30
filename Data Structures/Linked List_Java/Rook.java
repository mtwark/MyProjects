/*
Matthew Wark
mtwark
Rook.java
Class containing data and functions relevant to rook chess pieces. Extends the Chesspiece superclass
 */

/**
 *
 * @author Matthew
 */
public class Rook extends Chesspiece{

    public Rook(int c, int r)
    {
        super(c, r);
        name = "Rook";
    }
    
    public Rook(int c, int r, String n)
    {
        super(c, r, n);
    }

    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(col == c.col)
            isAtt = true;
        if(row == c.row)
            isAtt = true;
        
        return isAtt;
    }

}
