/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

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
        if(row == c.row + 1 || row == c.row - 1)
            if(col == c.row + 2 || col == c.row - 2)
                return true;
        
        if(row == c.row + 2 || row == c.row - 2)
            if(col == c.row + 1 || col == c.row - 1)
                return true;
        return false;
    }

}
