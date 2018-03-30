/*
Matthew Wark
mtwark
Chesspiece.java
Superclass containing data and functions relevant to all chesspieces.
 */

/**
 *
 * @author mattw
 */
public class Chesspiece {
    //row and column for each placed piece
    int row;
    int col;
    
    //for finding diagonal attacks
    //value of b from y = mx + b formula for both a positive slope a negative slope, given the col = x and row = y
    //if b values of two queens match, they must be on same diagonal
    int bPos;
    int bNeg;
    boolean isAtt = false;
    String team;
    String name;
    String image;
    String type;
    int range;
    int paths[][];
    
    public Chesspiece()
    {
    }
    
	//Can be created with or without a name as an argument. The name will be set to the piece name if no name is passed.
    public Chesspiece(int c, int r)
    {
        col = c;
        row = r;
        bPos = row - col;
        bNeg = row + col;
        paths = new int[8][8];
        
    }
    
    public Chesspiece(int c, int r, String iname)
    {
        col = c;
        row = r;
        bPos = row - col;
        bNeg = row + col;
        name = iname;
        paths = new int[8][8];

        
        if(name.charAt(0) == 'q' || name.charAt(0) == 'b' || name.charAt(0) == 'r' || name.charAt(0) == 'k' || name.charAt(0) == 'n' || name.charAt(0) == 'p')
            team = "White";
        else
            team = "Black";
    }
	//dummy function to be overwritten by each subclass
    public boolean isAttacking(Chesspiece c) {return true;}
    
    public boolean isAttacking(int c, int r)
    {
        Chesspiece dummy = new Chesspiece(c, r);
        return isAttacking(dummy);
    }
    
    //dummy
    public void updatePaths(LinkedList pieces)
    {
        return;
    }
    
        public void printPaths()
    {
        int counter = 0;
        /*String bSquare = "\u25A0";
        String wSquare = "\u25A1";
        String square;*/
        
        for(int i = paths.length-1; i >= 0 ; i--)
        {
            for(int j = 0; j < paths.length ; j++)
            {
                /*square = (counter % 2 == 0) ? bSquare: wSquare;
                square = (paths[i][j] == 1) ? "\u25A3" : square;
                counter++;
                System.out.print(square);*/
                if(i == row && j == col)
                    System.out.print(image);
                else if(paths[j][i] == 1)
                    System.out.print("\u25A1");
                else if(paths[j][i] == 2)
                    System.out.print("\u25A3");
                else
                    System.out.print("\u25A0");
            }
            counter++;
            System.out.print("\n");
        }
    }
        
        public void updateDiag(LinkedList pieces)
    {
        //start at col, row and work up and over
                //start at col, row and work way over right, then left
        int x, y, moves;
        x = y = moves = 0;
        for(int i = 1; i > -2; i -= 2)
        {
            x = col + i;
            y = row + i;
            moves = 0;
        while(x < 8 && x >= 0 && y < 8 && y >= 0 && moves < range)
        {
            if(pieces.find(x, y) == -1)
            {
                paths[x][y] = 1;
            } else if(!pieces.getPiece(x, y).team.equals(team))
            {
                paths[x][y] = 2;
                break;
            } else
            {
                paths[x][y] = 0;
                break;
            }
            moves++;
            x += i;
            y += i;
        }
        }
        
        
        for(int i = 1; i > -2; i -= 2)
        {
            x = col + i;
            y = row - i;
            moves = 0;
        while(x < 8 && x >= 0 && y < 8 && y >= 0 && moves < range)
        {
            if(pieces.find(x, y) == -1)
            {
                paths[x][y] = 1;
            } else if(!pieces.getPiece(x, y).team.equals(team))
            {
                paths[x][y] = 2;
                break;
            } else
            {
                paths[x][y] = 0;
                break;
            }
            x += i;
            y -= i;
            moves++;
        }
        }
    }
        
            public void updateHorz(LinkedList pieces)
    {
        //start at col, row and work way over right, then left
        for(int i = 1; i > -2; i -= 2)
        {
            int moves = 0;
            int x = col + i;
        while(x < 8 && x >= 0 && moves < range)
        {
            if(pieces.find(x, row) == -1)
            {
                paths[x][row] = 1;
            } else if(!pieces.getPiece(x, row).team.equals(team))
            {
                paths[x][row] = 2;
                break;
            } else
            {
                paths[x][row] = 0;
                break;
            }
            moves++;
            x += i;
        }
        }
    }

    public void updateVert(LinkedList pieces)
    {
        //start at col, ro and work way up, then down
                //start at col, row and work way over right, then left
        
        for(int i = 1; i > -2; i -= 2)
        {
            int moves = 0;
            int y = row + i;
        while(y < 8 && y >= 0 && moves < range)
        {
            if(pieces.find(col, y) == -1)
            {
                paths[col][y] = 1;
            } else if(!pieces.getPiece(col, y).team.equals(team))
            {
                paths[col][y] = 2;
                break;
            } else
            {
                paths[col][y] = 0;
                break;
            }
            moves++;
            y += i;
        }
        }
    }

}
