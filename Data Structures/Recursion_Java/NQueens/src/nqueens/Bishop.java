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
public class Bishop extends Chesspiece{

    public Bishop(int c, int r)
    {
        super(c, r);
        name = "Bishop";
    }
    
    public Bishop(int c, int r, String n)
    {
        super(c, r, n);
    }

    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(bPos == c.bPos)
            return true;
        if(bNeg == c.bNeg)
            return true;
        
        return false;
    }

}
