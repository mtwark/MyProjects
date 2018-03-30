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
        image = ("n".equals(n)) ? "\u2658": "\u265E";

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
    
    public void updatePaths(LinkedList pieces)
    {
        for(int i = 0; i < paths.length; i++)
        {
            for(int j = 0; j < paths.length; j++)
            {
                if(isAttacking(i, j) && pieces.find(i, j) == -1)
                {
                    paths[i][j] = 1;
                }
                else if(isAttacking(i, j) && !pieces.getPiece(pieces.find(i, j)).team.equals(team))
                {
                    paths[i][j] = 2;
                }
                else
                {
                    paths[i][j] = 0;
                }
            }
        }
       // printPaths();
    }

}
