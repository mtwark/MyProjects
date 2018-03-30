/*
Matthew Wark
mtwark
Board.java
Class containing data and functions relevant to a chessboard. Reads individual pieces and their positions, sets the positions along with the team of the pieces. Contains methods to check if pieces are placed in valid positions, and to check if a piece is attacking another.
 */

/**
 *
 * @author mattw
 */
public class Board {
    String inputs[];
    LinkedList whiteTeam;
    LinkedList blackTeam;
    int checkSpace[];
    boolean valid;
    
    public Board(String inputLine)
    {
		//Created two seperate teams for lists as opposed to using just one list and excluisvely using a member of the pieces as a way to make testing if attacking easier.
        valid = true;
        whiteTeam = new LinkedList();
        blackTeam = new LinkedList();
        int startRow, startCol;
		
		//Seperate the text from the file into individual characters in a String[]
        inputs = inputLine.split(" ");
		
		//This 2 element array holds the location on the board to be checked
        checkSpace = new int[2];
        checkSpace[0] = Integer.parseInt(inputs[0]) - 1;
        checkSpace[1] = Integer.parseInt(inputs[1].substring(0, 1)) - 1;
        
        for(int i = 2; i < inputs.length; i += 3)
        {
			//get col and row for piece to be created from text from file.
            startCol = Integer.parseInt(inputs[i + 1]) - 1;
            startRow = Integer.parseInt(inputs[i + 2]) - 1;
			
			//Test if placing the piece on a taken square. If so, break the loop to skip assigning a team.
            valid = checkIfValid(startCol, startRow);
            if(!valid)
                break;
			
			
			//Set piece type based on letter input, and place into the list corresponding to the team of the piece.
            switch(inputs[i])
            {
                case "k":
                    System.out.println("Adding white team king");
                    whiteTeam.insert(new King(startCol, startRow, "k" + startCol + startRow));
                    break;
                case "q":
                    System.out.println("Adding white team queen\n");
                    whiteTeam.insert(new Queen(startCol, startRow, "q" + startCol + startRow));
                    break;
                case "r":
                    System.out.println("Adding white team rook");
                    whiteTeam.insert(new Rook(startCol, startRow, "r" + startCol + startRow));
                    break;
                case "n":
                    System.out.println("Adding white team knight");
                    whiteTeam.insert(new Knight(startCol, startRow, "n" + startCol + startRow));
                    break;
                case "b":
                    System.out.println("Adding white team bishop");
                    whiteTeam.insert(new Bishop(startCol, startRow, "b" + startCol + startRow));
                    break;
                case "p":
                    System.out.println("Adding white team pawn");
                    blackTeam.insert(new Pawn(startCol, startRow, "p" + startCol + startRow));
                    break;
                case "K":
                    System.out.println("Adding black team king");
                    blackTeam.insert(new King(startCol, startRow, "K" + startCol + startRow));
                    break;
                case "Q":
                    System.out.println("Adding black team queen\n");
                    blackTeam.insert(new Queen(startCol, startRow, "Q" + startCol + startRow));
                    break;
                case "R":
                    System.out.println("Adding black team rook");
                    blackTeam.insert(new Rook(startCol, startRow, "R" + startCol + startRow));
                    break;
                case "N":
                    System.out.println("Adding black team knight");
                    blackTeam.insert(new Knight(startCol, startRow, "N" + startCol + startRow));
                    break;
                case "B":
                    System.out.println("Adding black team bishop");
                    blackTeam.insert(new Bishop(startCol, startRow, "B" + startCol + startRow));
                    break;
                case "P":
                    System.out.println("Adding black team pawn");
                    blackTeam.insert(new Pawn(startCol, startRow, "P" + startCol + startRow));
                    break;
            }
        }
    }
    boolean checkIfValid(int col, int row)
    {
        boolean valid = true;
        if(whiteTeam.length > 0)
        {
        for(int j = 0; j < whiteTeam.length; j++)
            {
                if((whiteTeam.getNode(j).c.col == col) &&
                   (whiteTeam.getNode(j).c.row == row))
                {
                    valid = false;
                    break;
                }
            }
        }
        
        if(blackTeam.length > 0)
        {
            for(int j = 0; j < blackTeam.length; j++)
            {
                if(blackTeam.getNode(j).c.col == col && 
                   blackTeam.getNode(j).c.row == row)
                {
                    valid = false;
                    break;
                }
            }
        }
            return valid;
    }
    
    boolean checkIfAttacking(Chesspiece c)
    {
        if("White".equals(c.team))
            for(int i = 0; i < blackTeam.length; i++)
                if(c.isAttacking(blackTeam.getNode(i).c))
                    return true;
                if("Black".equals(c.team))
            for(int i = 0; i < whiteTeam.length; i++)
                if(c.isAttacking(whiteTeam.getNode(i).c))
                    return true;
        
        return false;
    }
            
    
}
