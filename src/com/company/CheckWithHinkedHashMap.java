package com.company;
import java.io.*;
import java.util.*;

public class CheckWithHinkedHashMap {


    public static void main(String[] args) {
        int Pointer=0;
        int maxStrLen = 0;
        Map<String,Integer> HarryWords = new LinkedHashMap();
        ArrayList<String> WordsToDelete = new ArrayList<String>();
        try (FileReader fr2 = new FileReader("cnt.txt")){
            Scanner cnttxt = new Scanner(fr2);
            if (cnttxt.hasNextInt())
                Pointer = cnttxt.nextInt();
            else
                Pointer = 0;
        }catch (IOException e){
            try (FileWriter fr2 = new FileWriter("cnt.txt"))
            {fr2.write("0");Pointer=0;}catch (IOException e4 ){System.out.println("Ошыбка ,братан: ");}
        }
        try(FileReader fr = new FileReader("out.txt"); )
        {
            Scanner words = new Scanner(fr);
            while(words.hasNext()){
                String Word = words.next();
                if (Word.length()>maxStrLen)
                    maxStrLen = Word.length();
                if (words.hasNextInt())
                    HarryWords.put(Word,words.nextInt());
                ;
            }
        }catch (IOException e1) {System.out.println("Ошыбка ,братан: ");}

        Scanner inputFromConsole = new Scanner(System.in);//Read from keyboard
        int cnt = Pointer;
        for (Map.Entry<String, Integer> entry : HarryWords.entrySet()) {
            if (cnt > 0){
                cnt--;
                continue;
            }
            String word = entry.getKey();
            Integer count = entry.getValue();

            System.out.println("do u know the word : " + word + "\n y/n or s for stop\n");
            String keyboard;
            keyboard = inputFromConsole.next();
            if (keyboard.contentEquals("s"))
                    break;
            else if (keyboard.contains("n"))
                    Pointer += 1;
                else
                    WordsToDelete.add(word);
        }
        WordsToDelete.forEach(a ->{HarryWords.remove(a);});
        System.out.println(" ");

        ;
        Formatter fmt = new Formatter();
        Formatter Table = new Formatter();
        final int maxSrtrLenFinal = maxStrLen;
        HarryWords.forEach((a,b)->{fmt.format(a + " " + b.toString() + " ");
            Table.format("%" + maxSrtrLenFinal + "s\t",a) ;
            Table.format("%s\n",b) ;
        });


        try (FileWriter fout = new FileWriter("out.txt");
             FileWriter fout2 = new FileWriter("cnt.txt");
             FileWriter fout3 = new FileWriter("WordsTable.txt");
             ){


            Integer PointerOut= Pointer;


            fout.flush();
            fout.write(fmt.toString());
            fout2.flush();
            fout2.write(PointerOut.toString());
            fout2.flush();
            fout3.write(Table.toString());
            fmt.flush();


        }catch (IOException e){System.out.println("Ошыбка ,братан: ");}

    }



}
