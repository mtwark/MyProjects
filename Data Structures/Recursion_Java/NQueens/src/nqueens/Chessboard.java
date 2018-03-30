/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

/**
 *
 * @author mattw
 */
public class Chessboard {
    String inputs[];
    LinkedList whiteTeam;
    LinkedList blackTeam;
    int checkSpace[];
    
    public Chessboard(String inputLine)
    {
        whiteTeam = new LinkedList();
        blackTeam = new LinkedList();
        int startRow, startCol;
        inputs = inputLine.split(" ");
        checkSpace = new int[2];
        checkSpace[0] = Integer.parseInt(inputs[0]);
        checkSpace[1] = Integer.parseInt(inputs[1].substring(0, 0));
        
        for(int i = 2; i < inputs.length; i += 3)
        {
            startCol = Integer.parseInt(inputs[i + 1]);
            startRow = Integer.parseInt(inputs[i + 2]);
            switch(inputs[i])
            {
                case "q":
                    whiteTeam.insert(new Queen(startCol, startRow));
                    break;
                case "r":
                    whiteTeam.insert(new Rook(startCol, startRow));
                    break;
                case "k":
                    whiteTeam.insert(new Knight(startCol, startRow));
                    break;
                case "b":
                    whiteTeam.insert(new Bishop(startCol, startRow));
                    break;
                case "Q":
                    blackTeam.insert(new Queen(startCol, startRow));
                    break;
                case "R":
                    blackTeam.insert(new Rook(startCol, startRow));
                    break;
                case "K":
                    blackTeam.insert(new Knight(startCol, startRow));
                    break;
                case "B":
                    blackTeam.insert(new Bishop(startCol, startRow));
                    break;
            }
        }
    }
    
    boolean checkIfSafe()
    {
        for(int i = 0; i < whiteTeam.length; i++)
        {
            for(int j = 0; j < blackTeam.length; j++)
                if(whiteTeam.getNode(i).c.isAttacking(blackTeam.getNode(j).c))
                    {
                        return false;
                    }
        }
        return true;
    }
    
}
