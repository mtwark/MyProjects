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
        range = 7;
        name = "Queen";
    }
    
    public Queen(int c, int r, String n)
    {
        super(c, r, n);
        range = 7;
        image = ("q".equals(n)) ? "\u2655": "\u265B";

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
    @Override
    public void updatePaths(LinkedList pieces)
    {
        for(int i = 0; i < paths.length; i++)
        {
            for(int j = 0; j < paths.length; j++)
            {
                paths[i][j] = 0;
            }
        }
        paths[col][row] = 0;
        updateVert(pieces);
        updateHorz(pieces);
        updateDiag(pieces);
       // printPaths();
    }
}
