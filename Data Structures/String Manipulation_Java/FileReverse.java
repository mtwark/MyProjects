//Matthew Wark
//mtwark
//FileReverse.java
//Reverses the characters in strings pulled from a txt document and prints them, each on their own line, to a txt document.

import java.io.*;
import java.util.Scanner;

class FileReverse{
   public static void main(String[] args) throws IOException{

      // check number of command line arguments is at least 2
      if(args.length < 2){
         System.out.println("Usage: java -jar FileTokens.jar <input file> <output file>");
         System.exit(1);
      }

      // open files
      Scanner in = new Scanner(new File(args[0]));
      PrintWriter out = new PrintWriter(new FileWriter(args[1]));

      // read lines from in, extract and print tokens from each line
      while( in.hasNextLine() ){
          
         String line = in.nextLine().trim() + " "; 
         
         // split line around white space 
         String[] token = line.split("\\s");
         
         for(int i = 0; i < token.length; i++)
         {
             out.println(reverseString(token[i]));
         }
         
      }

      // close files
      in.close();
      out.close();
   }
   
   public static String reverseString(String s)
   {
       char[] c = s.toCharArray();
       String rString;
       int counter = 0;
       for(int i = s.length() - 1; i >= 0; i--)
       {
           c[counter] = s.charAt(i);
           counter++;
       }
       rString = new String(c);
       return rString;
   }
   
}

