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
public class Queen extends Chesspiece{
    
    public Queen(int c, int r)
    {
        super(c, r);
        name = "Queen";
    }
    
    public Queen(int c, int r, String n)
    {
        super(c, r, n);
    }
    
    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(row == c.row) 
            return true;
        if(col == c.col)
            return true;
        if(bPos == c.bPos)
            return true;
        if(bNeg == c.bNeg)
            return true;
        
        return false;
    }

}
