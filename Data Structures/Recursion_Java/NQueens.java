/*
Matthew Wark
mtwark
NQueens.java

Program uses recursion to test if "n" queens can be placed onto an
nxn board without attacking each other. Program takes in
a starting queens position, and the size of the board/
number of queens to be places. Final positions of queens
are sent to an output file if successful. If not, No Solution
is sent to the output file.
*/

import java.util.Scanner;
import java.io.*;


public class NQueens {


    public static void main(String[] args) throws IOException {

        //open file to read NQueen data from and set up FileWriter for output
        File file = new File(args[0]);
        Scanner in = new Scanner(file);
        FileWriter fw = new FileWriter(args[1]);

	
        int n;
        Chesspiece pieces[];


	//Only used to place first queen
        int firstQueenCol;

        //pull numbers from text file until there are no more 
        while (in.hasNextLine() && in.hasNextInt()) {
            n = in.nextInt();

            if (n == 0) {
                n = in.nextInt();
                n = in.nextInt();
                System.out.println("Vacuosly True");
            } else {

		//set up a new Chesspiece array containing Queen objects
                pieces = new Queen[n];

		//Read the data from the input file
                firstQueenCol = in.nextInt() - 1;
                pieces[firstQueenCol] = new Queen(firstQueenCol, in.nextInt() - 1);
		
		
		/*First call to the recursive method. If the placement is possible,
		This will return true and print the positions of each queen in the
		array. If not, it will print No Solution.*/
                if (nQueens(n - 1, pieces)) {
                    for (int i = 0; i < pieces.length; i++) {
                        fw.write((pieces[i].col + 1) + " " + (pieces[i].row + 1) + " ");
                    }
                    fw.write('\n');
                } else {
                    fw.write("No solution\n");
                }
            }
        }
        fw.close();

    }

    public static boolean nQueens(int n, Chesspiece q[]) {
	
	/*Base case for the recursive method. If this is 
	true for any of the recursive calls, they should
	all return true.*/
        if (n < 0) {
            return true;
        }
	
	
	//Set column to be worked on in this call
        int col = q.length - n - 1;

	//Test if a queen has been placed here already
        if (q[col] != null) {
            if (nQueens(n - 1, q)) {
                return true;
            } else {
                return false;
            }
        }

	//Row to begin attempting to place in
        int row = 0;

        //Attempt to place queen until reaching the top of the board
        while (row < q.length)
        {
	    
	    //Create new queen for this column and set to 0,0	
            q[col] = new Queen(col, row);

	    //Check if placement is safe, then call nqueens(n-1), or move up a space
            if (allSafe(q)) {
				

                if (nQueens(n - 1, q)) {
                    return true;
                }
                else
                {
                    row += 1;
                }
            } else {
                row += 1;
            }

        }
	/*if the top of the board is reached without placing a queen,
	set that queen to null so it doesn't interfere with allSafe()
	calls, then return false for nQueens*/
        q[col] = null;
        return false;

    }


    /*This method checks every queen in the array against each other every time,
    unless they haven't been intialized to anything. It's a little redundant,
    and could definitely be more efficient, but it works.*/
    public static boolean allSafe(Chesspiece c[]) {
        for (int i = 0; i < c.length - 1; i++) {
            for(int j = i + 1; j < c.length; j++)
                if((c[i] != null && c[j] != null))
                    if(c[i].isAttacking(c[j]))
                        return false;
        }
        return true;
    }
}
