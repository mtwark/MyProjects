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
        range = 7;
        name = "Bishop";
    }
    
    public Bishop(int c, int r, String n)
    {
        super(c, r, n);
        range = 7;        
        image = ("b".equals(n)) ? "\u2657": "\u265D";

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

    public void updatePaths(LinkedList pieces)
    {
        for(int i = 0; i < paths.length; i++)
        {
            for(int j = 0; j < paths.length; j++)
            {
                paths[i][j] = 0;
            }
        }
        updateDiag(pieces);
    }
    
}
