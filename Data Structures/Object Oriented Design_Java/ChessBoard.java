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
        String inputPositions;
        int inputCommands[];
        String tempCommands[];
        String inputs[];
        
        
        //reading input file
        while(in.hasNextLine())
        {
            
            //store the line of text and pass to board to be processed
            inputs = in.nextLine().split(":");
            inputPositions = inputs[0];
            tempCommands = inputs[1].trim().split(" ");
            
            
            inputCommands = new int[tempCommands.length];
            for(int i = 0; i < tempCommands.length; i++)
                inputCommands[i] = Integer.parseInt(tempCommands[i]) - 1;
            
            
            Board board = new Board(inputPositions);
            
            
            boolean valid = true;
            for(int i = 0; i < inputCommands.length; i += 4)
            {   
                if(!board.movePiece(inputCommands[i], inputCommands[i + 1], inputCommands[i + 2], inputCommands[i + 3]))
                {
                    
                    fw.write((inputCommands[i] + 1) + " ");
                    fw.write((inputCommands[i + 1] + 1) + " ");
                    fw.write((inputCommands[i + 2] + 1) + " ");
                    fw.write((inputCommands[i + 3] + 1) + " ");
                    fw.write("illegal\n");
                    System.out.println(board.errorMsg);
                    valid = false;
                    break;
                }
                else
                {
                    valid = true;
                }
            }
            if(valid)
                fw.write("legal\n");
            
        }
        fw.close();
        
    }

}

