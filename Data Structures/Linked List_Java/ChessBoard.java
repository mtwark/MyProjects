/*
Matthew Wark
mtwark
ChessBoard.java
Main class, contains the file io objects and the logic to print the desired result. Holds and operates on a board object and the objects the board holds.
 */

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author mattw
 */
public class ChessBoard {
public static void main(String[] args) throws IOException {

        //open file to read NQueen data from
        File file = new File(args[0]);
        Scanner in = new Scanner(file);
        FileWriter fw = new FileWriter(args[1]);
        Chesspiece b, w;
        String inputText;
        char att;
        
		//These pieces are just used for readability, to avoid having to type out really long .this.that function calls below.
        w = b = new Chesspiece();
        
		//reading input file
        while(in.hasNextLine())
        {
            
			//store the line of text and pass to board to be processed
            inputText = in.nextLine();
            Board board = new Board(inputText);
            
			
            if(board.valid)
            {
				//again, these ints exist for readability and are equal to the index positions of the piece(if any) at the position to be checked
                int whiteIndex = board.whiteTeam.find(Integer.toString(board.checkSpace[0]) + Integer.toString(board.checkSpace[1]));
                int blackIndex = board.blackTeam.find(Integer.toString(board.checkSpace[0]) + Integer.toString(board.checkSpace[1]));
				
				//if there's a piece at the space being checked, set a ref to it to get info from below
                if(whiteIndex != -1)
                    w = board.whiteTeam.getNode(whiteIndex).c;
                if(blackIndex != -1)
                    b = board.blackTeam.getNode(blackIndex).c;
               
			   
				//realizing I only need one piece, not w and b. fix later.
                if(whiteIndex != -1)
                {
                    if(board.checkIfAttacking(w))
                        att = 'y';
                    else
                        att = 'n';
                    fw.write(w.name.charAt(0) + " " + att + "\n");
                }
                else if(blackIndex != -1)
                {               
                    if(board.checkIfAttacking(b))
                        att = 'y';
                    else
                        att = 'n';
                    fw.write(b.name.charAt(0) + " " + att + "\n");
                }
                else
                {
                    fw.write("-\n");
                }
                

            }
            else
            {
                fw.write("Invalid\n");
            }
            
        }
        fw.close();
        
    }

}

