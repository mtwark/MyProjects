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
        range = 7;
        name = "Rook";
    }
    
    public Rook(int c, int r, String n)
    {
        super(c, r, n);
        range = 7;
        image = ("r".equals(n)) ? "\u2656": "\u265C";

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

    public void updatePaths(LinkedList pieces)
    {
        for(int i = 0; i < paths.length; i++)
        {
            for(int j = 0; j < paths.length; j++)
            {
                paths[i][j] = 0;
            }
        }
        updateVert(pieces);
        updateHorz(pieces);
    }
}
