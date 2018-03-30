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
public class Rook extends Chesspiece{

    public Rook(int c, int r)
    {
        super(c, r);
        name = "Rook";
    }
    
    public Rook(int c, int r, String n)
    {
        super(c, r, n);
    }

    @Override
    public boolean isAttacking(Chesspiece c)
    {
        if(c.col == c.col)
            return true;
        if(row == c.row)
            return true;
        
        return false;
    }

}
