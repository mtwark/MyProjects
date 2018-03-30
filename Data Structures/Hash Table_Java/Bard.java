/*
Matthew Wark
mtwark

Bard.java
Program that takes in a text file and returns statistics on the words used.
 */

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;


public class Bard
{

    public static void main(String[] args) throws IOException
    {
        ArrayList<Word> words = new ArrayList<Word>();
        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        File bard = new File("shakespeare.txt");
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        FileWriter fw = new FileWriter(outputFile);
        Scanner shake = new Scanner(bard);
        Scanner in = new Scanner(inputFile);

        /*ArrayList<Word> test = new ArrayList<Word>();
        test.add(new Word("dog", 3));
        test.add(new Word("cat", 2));
        test.add(new Word("a", 1));
        test.add(new Word("eac", 2));
        test.add(new Word("the", 2));
        test.add(new Word("for", 3));
        test.add(new Word("z", 6));
        Collections.sort(test, new FreqComparator());
        for(int i = 0; i < test.size(); i++) System.out.println(test.get(i).text);*/
        //Getting Data from Bard
        while (shake.hasNextLine())
        {
            String line[] = fixLine(shake.nextLine());

            //convert line
            for (int i = 0; i < line.length; i++)
            {
                if (ht.containsKey(line[i]))
                {
                    ht.put(line[i], ht.get(line[i]) + 1);
                } else
                {
                    ht.put(line[i], 1);
                }
            }

        }
        Set<String> keys = ht.keySet();
        for (String key : keys)
        {
            words.add(new Word(key, ht.get(key)));
        }

        //Sorting
        Collections.sort(words, new FreqComparator());

        //Processing input
        while (in.hasNextInt())
        {
            int wordLength = in.nextInt();
            int rank = in.nextInt();
            //ArrayList<ArrayList<Word>> arr = new ArrayList<ArrayList<Word>>();
            int i = 0;
            while(2 > 1)
            {
                if(words.get(i).length < wordLength)
                {
                    fw.write("-\n");
                    break;
                }
                if(words.get(i).length == wordLength)
                {
                    if(words.get(i + rank).length == wordLength)
                    {
                        fw.write(words.get(i + rank).text + "\n");
                        break;
                    }
                    fw.write("-\n");
                    break;
                }
                i++;
            }
            
            
        }
        fw.close();

    }

    public static String[] fixLine(String line)
    {
        line = line.replace(".", " ");
        line = line.replace("?", " ");
        line = line.replace(";", " ");
        line = line.replace(":", " ");
        line = line.replace("!", " ");
        line = line.replace(",", " ");
        line = line.replace("[", " ");
        line = line.replace("]", " ");
        line = line.toLowerCase();
        return line.trim().split("\\s+");
    }
    

}
