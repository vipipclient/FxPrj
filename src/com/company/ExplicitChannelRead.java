package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.*;
/*
* 1)Open file of Harry Potter book,copy the text of file in String in,close file
* 2)parse the tokens and write them in : StringTokenizer st
* 3)create HashTable without repetition ,for each word count the number of occurrences in the text
* 4)Sort the words alphabetically using TreeMap
* 5)HarryPotterWord class Sort words by the number of occurrences
* */
public class ExplicitChannelRead {
    public static  void main(String args[]){
        int count = 0;
        int TotalWords = 0;
        String in = "";
        //1)open file with terxt of Harry Potter
        try (SeekableByteChannel fChan = Files.newByteChannel(Paths.get("C:\\Users\\Ilyas\\IdeaProjects\\test\\src\\test.txt")))
        {
            System.out.println("fChan.size()" + fChan.size());
            in =  new String(Files.readAllBytes(Paths.get("C:\\Users\\Ilyas\\IdeaProjects\\test\\src\\test.txt")));
        }catch (IOException e2) {System.out.println("Ошыбка ,братан: ");}

        //2)Tokenizer
        StringTokenizer st = new StringTokenizer(in," \r/,\\«.'~@#$%^_+{}[]><=&-*?!—:;`»()1234567890\n");

        //3)add all tokens to Hashtable without repetition ,for each word count the number of occurrences in the text
        Hashtable<String,Integer> Harry = new Hashtable<String,Integer>();
        Map<String,Integer>  HarryHashMap = new HashMap<String, Integer>();
        String a;//auxiliary variable
        int cnt;//number of repittition
        TotalWords = st.countTokens();
        while (st.hasMoreTokens()){
            a = st.nextToken().toLowerCase();
            if (Harry.containsKey(a)){
                cnt = Harry.get(a);
                HarryHashMap.put(a,cnt + 1);//<<<<<<<<<<<new realisation
                Harry.put(a,cnt + 1);//if the word is repeated , increase the counter
            }else{
                Harry.put(a,1); //else, create element
                HarryHashMap.put(a,1);//<<<<<<<<<<<new realisation
            }

        }

        //4) Sort the words alphabetically using TreeMap

        Enumeration<String> wordsOftext;

        String words ;
        wordsOftext = Harry.keys();
        TreeMap<String,Integer> SortWords = new TreeMap<String,Integer>(new ReverseCmp());
        while (wordsOftext.hasMoreElements()){
            words = wordsOftext.nextElement();
            SortWords.put(words,HarryHashMap.get(words));//<<<<<<<<<<<<<<<<<<<<<<

        }
        //5)Creating an array to put objects of the HarryPotterWord class in
        //Create ArrayList of HarryPotterWord
        // Sort words by the number of occurrences
        ArrayList<HarryPotterWord> HarryPotterWordArray = new ArrayList();
        for (String s : SortWords.descendingKeySet()) {
            HarryPotterWordArray.add(new HarryPotterWord(s,HarryHashMap.get(s)));//<<<<<<<<<<<<<
        }
        HarryPotterWordArray.sort(new MyCmp());


        Map<String, Integer> SortedHarryHashMap = sortByValues(HarryHashMap);//<<<<<<<<<<<<<<<<<<<<<<<<<<
        try (FileWriter fout = new FileWriter("out.txt");
             FileWriter fout2 = new FileWriter("SortedHarryHashMap.txt")){
 //           SortedHarryHashMap.forEach((key,value) ->{fout2.write(key.toString());});
            StringBuffer buf_SortedHarryHashMap = new StringBuffer();
            SortedHarryHashMap.forEach((key,value) -> {
                buf_SortedHarryHashMap.append(String.format(key + " " + value.toString() + " "));
            });
            fout2.write(buf_SortedHarryHashMap.toString());
            for (HarryPotterWord o : HarryPotterWordArray)
                fout.write(o.toString());
        }catch (IOException e){System.out.println("Ошыбка ,братан: ");}




 /*       for (HarryPotterWord o : HarryPotterWordArray) {
            System.out.println(o.toString());


        }*/

        System.out.println("Total words = " + TotalWords);
        System.out.println("Total Unique words = " + HarryPotterWordArray.size());


    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =  new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k1).compareTo(map.get(k2));
                if (compare == 0) {
                    return k1.toString().compareTo(k2.toString());
                }
                else return compare;
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}
class HarryPotterWord {
    String word;
    int count;

    HarryPotterWord(String t, int c){
        word=t;
        count=c;
    }
    public String getword(){
        return word;
    }
    public int getCount(){
        return count;
    }

    @Override
    public String toString() {
        return word + " " + count + " ";
    }
}

class MyCmp implements Comparator<HarryPotterWord>{

    public int compare(HarryPotterWord word1, HarryPotterWord word2){
        return (word1.getCount() - word2.getCount());

    }
}
class ReverseCmp implements Comparator<String>{

    public int compare(String word1, String word2){
        String aStr = word1;
        String bStr = word2;
        return bStr.compareTo(aStr);

    }
}











/*
   public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =  new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0) return 1;
                else return compare;
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
*/
//           System.out.println(Harry);
  /*          do {
                count = fChan.read(mBuf);
                System.out.println("count = " + count);
                if (count != -1) {
                    mBuf.rewind();
                    for (int i=0; i < count; i++)
                        System.out.print((char) mBuf.get());
                }
            } while (count != -1);*/