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
public class Board
{

    String positions[];
    LinkedList pieces;
    int checkSpace[];
    boolean valid;
    String inputLine;
    String inputs[];
    int startingTurn;
    int turnCounter;
    String errorMsg = "";

    public Board(String in)
    {
        inputLine = in;
        initializeBoard();

    }

    void initializeBoard()
    {
        //Created two seperate teams for lists as opposed to using just one list and excluisvely using a member of the pieces as a way to make testing if attacking easier.
        valid = true;
        turnCounter = 0;
        pieces = new LinkedList();
        int startRow, startCol;

        //Seperate the text from the file into individual characters in a String[]
        positions = inputLine.split(" ");

        for (int i = 0; i < positions.length; i += 3)
        {
            //get col and row for piece to be created from text from file.
            startCol = Integer.parseInt(positions[i + 1]) - 1;
            startRow = Integer.parseInt(positions[i + 2]) - 1;

            //Test if placing the piece on a taken square. If so, break the loop to skip assigning a team.
            valid = checkIfValid(startCol, startRow);
            if (!valid)
            {
                break;
            }

            if (null != positions[i])
            //Set piece type based on letter input, and place into the list corresponding to the team of the piece.
            {
                switch (positions[i])
                {
                    case "k":
                    case "K":
                        pieces.insert(new King(startCol, startRow, positions[i]));
                        break;
                    case "q":
                    case "Q":
                        pieces.insert(new Queen(startCol, startRow, positions[i]));
                        break;
                    case "r":
                    case "R":
                        pieces.insert(new Rook(startCol, startRow, positions[i]));
                        break;
                    case "n":
                    case "N":
                        pieces.insert(new Knight(startCol, startRow, positions[i]));
                        break;
                    case "b":
                    case "B":
                        pieces.insert(new Bishop(startCol, startRow, positions[i]));
                        break;
                    case "p":
                    case "P":
                        pieces.insert(new Pawn(startCol, startRow, positions[i]));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    boolean checkIfValid(int col, int row)
    {
        if (pieces.length > 0)
        {
            for (int j = 0; j < pieces.length; j++)
            {
                if ((pieces.getNode(j).c.col == col) && (pieces.getNode(j).c.row == row))
                {
                    return false;
                }
            }
        }

        return true;
    }

    boolean movePiece(int startCol, int startRow, int newCol, int newRow)
    {
        Chesspiece movingPiece;
        int index = -1;
        index = pieces.find(startCol, startRow);


        //find the piece at the start spot, else, return invalid move
        if (index != -1)
        {
            movingPiece = pieces.getNode(index).c;
        } 
        else
        {
            errorMsg = "No piece found at given location.";
            return false;
        }
        
        if (!movingPiece.isAttacking(newCol, newRow) && !"Pawn".equals(movingPiece.type))
        {
            errorMsg = "Not a valid move for this piece.";
            return false;
        } 
        else
        {
        if(!checkTurn(movingPiece))
        {
            return false;
        }
        else
        {        
             movingPiece.updatePaths(pieces);
             /*System.out.print("\n");
             movingPiece.printPaths();*/
             if(movingPiece.paths[newCol][newRow] == 1)
             {
                 movingPiece.col = newCol;
                 movingPiece.row = newRow;
                 movingPiece.updatePaths(pieces);
             }
             else if(movingPiece.paths[newCol][newRow] == 2)
             {
                 pieces.delete(pieces.find(newCol, newRow));
                 movingPiece.col = newCol;
                 movingPiece.row = newRow;
                 movingPiece.updatePaths(pieces);
             }
             else
             {
                 errorMsg = "Error, move is blocked.";
                 return false;
             }
        }
        }
        if((turnCounter % 2 == 0 && checkCheck("White")) || (turnCounter % 2 == 1 && checkCheck("Black")))
        {
            errorMsg = "Error, this move will put the teams king in check.";
            return false;
        }
    
        turnCounter++;
        return true;
    }
    
    boolean checkTurn(Chesspiece movingPiece)
    {
        //make sure it's the moving team of the moving piece's turn 
        if (turnCounter % 2 == 0 && !"White".equals(movingPiece.team))
        {
            errorMsg = "Error, it is not that team's turn.";
            return false;
        }
        if (turnCounter % 2 == 1 && !"Black".equals(movingPiece.team))
        {
            errorMsg = "Error, it is not that team's turn.";
            return false;
        }
        return true;
    }

    String occupied(int c, int r)
    {
        if(pieces.find(c, r) != -1)
            if("White".equals(pieces.getPiece(c, r).team))
                return "White";
            else
                return "Black";
        else
            return "None";
    }
    
    //check if given teams king is in check
    boolean checkCheck(String team)
    {
        String k = ("White".equals(team)) ? "k" : "K";
        int col = pieces.getPiece(pieces.find(k)).col;
        int row = pieces.getPiece(pieces.find(k)).row;
        
        if(pieces.find(k) == -1)
        {
            return false;
        }
        //check every piece on the other team and try to move it to that spot
        for(int i = 0; i < pieces.length; i++)
        {
            if(!pieces.getPiece(i).team.equals(team))
            {
                pieces.getPiece(i).updatePaths(pieces);
                if(pieces.getPiece(i).paths[col][row] != 0)
                    return true;
            }
        }
        return false;
    }
}
