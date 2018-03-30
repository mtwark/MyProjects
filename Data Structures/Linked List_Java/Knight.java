/*
Matthew Wark
mtwark
Knight.java
Class containing data and functions relevant to knight chess pieces. Extends the Chesspiece superclass
 */
/**
 *
 * @author Matthew
 */
public class Knight extends Chesspiece{
     
    public Knight(int c, int r)
    {
        super(c, r);
        name = "Knight";
        
    }
    
    public Knight(int c, int r, String n)
    {
        super(c, r, n);
    }
    @Override
    public boolean isAttacking(Chesspiece c)
    {
        isAtt = false;
        if(row == c.row + 1)
            if(col == c.col + 2 || col == c.col - 2)
                isAtt = true;        
        if(row == c.row + 2)
            if(col == c.col + 1 || col == c.col - 1)
                isAtt = true;
        if(row == c.row - 1)
            if(col == c.col + 2 || col == c.col - 2)
                isAtt = true;        
        if(row == c.row - 2)
            if(col == c.col + 1 || col == c.col - 1)
                isAtt = true;
        
        return isAtt;
    }
}
