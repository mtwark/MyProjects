/*
Matthew Wark
mtwark
Pawn.java
Class containing data and functions relevant to pawn chess pieces. Extends the Chesspiece superclass
 */

/**
 *
 * @author mattw
 */
public class Pawn extends Chesspiece
{

    public Pawn(int c, int r)
    {
        super(c, r);
        range = 1;
        name = "Pawn";

    }

    public Pawn(int c, int r, String n)
    {
        super(c, r, n);
        range = ((r == 1 && "White".equals(team)) || (r == 6 && "Black".equals(team))) ? 2 : 1;
        image = (team == "White") ? "\u2659" : "\u265F";
        type = "Pawn";
    }

    @Override
    public boolean isAttacking(Chesspiece c)
    {

        //if the team is black, can only attack diaganally moving down the board
        if (team == "Black")
        {
            if (bPos == c.bPos)
            {
                if (row == c.row - 1)
                {
                    isAtt = true;
                }
            }
            if (bNeg == c.bNeg)
            {
                if (row == c.row - 1)
                {
                    isAtt = true;
                }
            }
        } else

        //if the team is white, can only attack diaganally moving up the board
        {
            if (bPos == c.bPos)
            {
                if (row == c.row + 1)
                {
                    isAtt = true;
                }
            }
            if (bNeg == c.bNeg)
            {
                if (row == c.row + 1)
                {
                    isAtt = true;
                }
            }
        }

        return isAtt;
    }

    public void updatePaths(LinkedList pieces)
    {
        int x, y, moves, step;
        x = y = moves = step = 0;

        for (int i = 0; i < paths.length; i++)
        {
            for (int j = 0; j < paths.length; j++)
            {
                paths[i][j] = 0;
            }
        }

        step = ("White".equals(team)) ? 1 : -1;
        y = row + step;
        while (y < 8 && y >= 0 && moves < range)
        {
            if (pieces.find(col, y) == -1)
            {
                paths[col][y] = 1;
            } else
            {
                paths[col][y] = 0;
                break;
            }
            moves++;
            y += step;
        }
        
        
        
        //Attacks
        if (col + step >= 0 && col + step < 8 && row + step >= 0 && row + step < 8)
        {
            if ((pieces.find(col + step, row + step) != -1) && (!pieces.getPiece(pieces.find(col + step, row + step)).team.equals(team)))
            {
                paths[col + step][row + step] = 2;
            }
        }
        if (col - step >= 0 && col - step < 8 && row + step >= 0 && row + step < 8)
        {
            if ((pieces.find(col - step, row + step) != -1) && (!pieces.getPiece(pieces.find(col - step, row + step)).team.equals(team)))
            {
                paths[col - step][row + step] = 2;
            }
        }
    }

}
