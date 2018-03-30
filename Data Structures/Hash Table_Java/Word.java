/*
Matthew Wark
mtwark

Word.java
Class that stores a word and information about the word such as length, and frequency.
Also contains comparators to sort by it's data fields.
*/

import java.util.*;

class Word implements Comparable<Word>
{
    String text;
    int length;
    int frequency;
    Word(){}
    
    public Word(String t, int freq)
    {
        text = t;
        length = t.length();
        frequency = freq;
    }
    
    public int compareTo(Word a)
    {
        return this.text.compareTo(a.text);
    }
    
}

class LengthComparator implements Comparator<Word>
{
    public int compare(Word a, Word b)
    {
        return b.length - a.length;
    }
}
class FreqComparator implements Comparator<Word>
{
    public int compare(Word a, Word b)
    {
        if(b.length == a.length)
        {
            if(a.frequency == b.frequency)
            {
                return a.text.compareTo(b.text);
            }
            else
            {
                return b.frequency - a.frequency;
            }
        }
        return b.length - a.length;
    }
}


