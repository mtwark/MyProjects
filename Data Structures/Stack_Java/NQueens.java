/*
Matthew Wark
mtwark
NQueens.java

This program uses a combination of while loops and a stack to solve the NQueens problem
when multiple starting positions are given, without using recursion.

*/
import java.io.*;
import java.util.*;

public class NQueens
{

    public static void main(String[] args) throws IOException
    {

        File file = new File(args[0]);
        File out = new File(args[1]);
        Scanner in = new Scanner(file);
        FileWriter fw = new FileWriter(out);
        Scanner lineScan;
        

        int n;
        String line;
        Chesspiece q[];
        Stack<Chesspiece> s;
        int startCols[];
        while (in.hasNextLine() && in.hasNextInt())
        {
            //store line in string and set to scan that string for ints

            line = in.nextLine();
            lineScan = new Scanner(line);

            int col, row;
            col = row = 0;
            n = lineScan.nextInt();
            q = new Queen[n];
            s = new Stack<Chesspiece>();
            startCols = new int[20];
            for(int i = 0; i < startCols.length; i++) startCols[i] = -1;
            int counter = 0;

            while (lineScan.hasNextInt())
            {
                col = lineScan.nextInt() - 1;
                row = lineScan.nextInt() - 1;
                q[col] = new Queen(col, row, false);
                s.push(q[col]);
                startCols[counter] = col;
                counter++;
            }

            //test if all safe, if so, move on, else, no solution
            col = 0;
            row = 0;
            while (s.size() < n)
            {
                if (isIn(col, startCols))
                {
                    col++;
                } else
                {

                    
                    //Place queen in first row
                    q[col] = new Queen(col, row, true);

                    //if safe, place onto stack, else, move up
                    while (row < n)
                    {
                        q[col].row = row;
                        if (allSafe(q))
                        {
                            s.push(q[col]);
                            col++;
                            row = 0;
                            break;
                        }
                        row++;
                    }
                    
                    //CAN'T PLACE
                    
                    //move back a column and up a row
                    if(!allSafe(q))
                    {
                    q[col] = null;
if (isIn(col, startCols))
                    {
                        break;
                    }
                    
                    boolean none = false;
                    //row before is already at top
                    while ((s.peek().row + 1) >= n)
                    {
                        col = s.pop().col;
                        q[col] = null;
                        if (isIn(col, startCols))
                    {
                        none = true;
                        break;
                    }
                        
                    }
                    if(none)
                        break;

                    row = s.peek().row + 1;
                    col = s.pop().col;

                    if (isIn(col, startCols))
                    {
                        break;
                    }
                    }
                }

                
                
            }
            fw.write(printLocations(q));
            
        }
        fw.close();

    }


    /*This method checks every queen in the array against each other every time,
    unless they haven't been intialized to anything. It's a little redundant,
    and could definitely be more efficient, but it works.*/
    public static boolean allSafe(Chesspiece c[])
    {
        for (int i = 0; i < c.length - 1; i++)
        {
            for (int j = i + 1; j < c.length; j++)
            {
                if ((c[i] != null && c[j] != null))
                {
                    c[i].updateB();
                    c[j].updateB();
                    if (c[i].isAttacking(c[j]))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isIn(int val, int A[])
    {
        for (int i = 0; i < A.length; i++)
        {
            if (val == A[i])
            {
                return true;
            }
        }
        return false;
    }
    
    public static String printLocations(Chesspiece c[])
    {
        String line = "";
        for(int i = 0; i < c.length; i++)
        {
            if(c[i] == null)
            {
                line = "No solution";
                break;
            }
            else
            {
                line += ((c[i].col + 1) + " " + (c[i].row + 1) + " ");
            }
        }
        return line + "\n";
    }
}

