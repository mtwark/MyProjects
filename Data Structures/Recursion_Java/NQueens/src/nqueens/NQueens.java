/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Matthew
 */
public class NQueens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        //open file to read NQueen data from
        File file = new File("test-input.txt");
        Scanner in = new Scanner(file);
        FileWriter fw = new FileWriter("out1.txt");
        int n;
        int row, col;
        Chesspiece pieces[];
        int firstQueenCol;
        String inputText;
        
        while(in.hasNextLine())
        {
            inputText = in.nextLine();
            Chessboard board = new Chessboard(inputText);
            if(board.checkIfSafe())
            {
                fw.write("a");
            }
            
        }

        //pull numbers from text file until there are no more 
        /*while (in.hasNextLine() && in.hasNextInt()) {
            n = in.nextInt();
            if (n == 0) {
                n = in.nextInt();
                n = in.nextInt();
                System.out.println("Vacuosly True");
            } else {
                pieces = new Queen[n];
                firstQueenCol = in.nextInt() - 1;
                pieces[firstQueenCol] = new Queen(firstQueenCol, in.nextInt() - 1);

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
*/
        
    }

    /*
    public static boolean nQueens(int n, Chesspiece q[]) {
        if (n < 0) {
            return true;
        }
        int col = q.length - n - 1;
        if (q[col] != null) {
            if (nQueens(n - 1, q)) {
                return true;
            } else {
                return false;
            }
        }
        int row = 0;

        //queen placement
        while (row < q.length)
        {
            q[col] = new Queen(col, row);
            if (allSafe(q)) {
                if (nQueens(n - 1, q)) {
                    return true;
                }
                else
                {
                    row += 1;
                }
            } else {
                System.out.println("Moving " + (col + 1) + " up one spot to row " + (row + 1));
                row += 1;
            }

        }
        q[col] = null;
        return false;

    }*/

    public static boolean checkIfSafe(LinkedList w, LinkedList b)
    {
        for(int i = 0; i < w.length; i++)
        {
            for(int j = 0; j < b.length; j++)
                if(w.getNode(i).c.isAttacking(b.getNode(j).c))
                    {
                        return false;
                    }
        }
        return true;
    }
    
    /*
    public static boolean allSafe(Chesspiece c[]) {
        for (int i = 0; i < c.length - 1; i++) {
            for(int j = i + 1; j < c.length; j++)
                if((c[i] != null && c[j] != null))
                    if(c[i].isAttacking(c[j]))
                        return false;
        }
        return true;
    }*/
}
