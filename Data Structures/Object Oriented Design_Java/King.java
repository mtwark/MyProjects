/*
Matthew Wark
mtwark
King.java
Class containing data and functions relevant to king chess pieces. Extends the Chesspiece superclass
 */

/**
 *
 * @author mattw
 */
public class King extends Chesspiece {
    
    
    public King(int c, int r)
    {
        super(c, r);
        range = 1;
        name = "King";
        
    }
    
    public King(int c, int r, String n)
    {
        super(c, r, n);
        range = 1;
        image = ("k".equals(n)) ? "\u2654": "\u265A";
    }
    
    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(row == c.row)
            if((col == c.col + 1) || (col == c.col - 1))
                isAtt = true;
        if(col == c.col)
            if((row == c.row + 1) || (row == c.row - 1))
                isAtt = true;
			
		//if on the same diagonal as another piece, check if it's only one square away
        if(bPos == c.bPos)
            if(((col == c.col + 1) && (col == c.row + 1)) || ((col == c.col - 1) && (col == c.row - 1)))
                isAtt = true;
        if(bNeg == c.bNeg)
            if(((col == c.col + 1) && (col == c.row - 1)) || ((col == c.col - 1) && (col == c.row + 1)))
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
        updateDiag(pieces);
    }

}
