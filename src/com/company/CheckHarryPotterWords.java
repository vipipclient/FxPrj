package com.company;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.*;
public class CheckHarryPotterWords {

    public static  void main(String args[]){
        Date DateStart = new Date();

        int Pointer = 0 ;
        StringBuffer out = new StringBuffer();
        //Read file with words and the file cnt.txt which contains number of the last word you stopped at
        try (FileReader fr = new FileReader("out.txt"); FileReader fr2 = new FileReader("cnt.txt"))
        {
            Scanner words = new Scanner(fr);
            while(words.hasNext()){
                out.append(words.next() + " ");
            }
            Scanner cnttxt = new Scanner(fr2);
            if (cnttxt.hasNextInt())
                Pointer = cnttxt.nextInt();
        }catch (IOException e2) {System.out.println("Ошыбка ,братан: ");}

        boolean STOP = false; //Indicates that user wants to stop session
        StringBuffer out2 = new StringBuffer();

        Scanner inputFromConsole = new Scanner(System.in);//Read from keyboard
        StringTokenizer st = new StringTokenizer(out.toString());//Tokenizer parsing the text by words.
        //Pass worked out words
        int cnt = 0;
        for (cnt=0;cnt<Pointer;cnt++)
            if (st.hasMoreTokens())
                out2.append(st.nextToken() + " ");

        while(st.hasMoreTokens()){
//            STOP = true;
            if (STOP == true) {
                out2.append(st.nextToken() + " ");
            }else{
                String wbuf = st.nextToken();
                System.out.println("words left : " + st.countTokens()/2 +" ,Pointer " + Pointer);
                String keyboard;
                System.out.println("do u know the word : " + wbuf +"\n y/n or s for stop\n");
                keyboard = inputFromConsole.next();
                if (keyboard.contentEquals("s")){
                    STOP = true ;
                    out2.append(wbuf + " ");
                    out2.append(st.nextToken() + " ");
                }else if (keyboard.contains("n")){
                    out2.append(wbuf + " " + st.nextToken() + " ");
                    Pointer+=2;}
                else
                     st.nextToken();
                }
            }

        StringTokenizer st2 = new StringTokenizer(out2.toString()," \r/,\\«.'~@#$%^_+{}[]><=&-*?!—:;`»()1234567890\n");
        StringBuffer outTable = new StringBuffer();
        Formatter fmt = new Formatter();
        String FmtStr = "";

        int maxStrLen = 0;
        while (st2.hasMoreTokens()){
            String word = st2.nextToken();
            if (word.length() > maxStrLen)
                {maxStrLen = word.length() ;System.out.println(word + " " + word.length()+" " +maxStrLen);}
            if (st2.hasMoreTokens())
                st2.nextToken();
            else
                System.out.println("No Tkenes last :" + word );
        }
        System.out.println("maxStrLen "+maxStrLen );



        st2 = new StringTokenizer(out2.toString());

        while (st2.hasMoreTokens()){
            String word = st2.nextToken();
            fmt.format("%" + maxStrLen + "s\t",word);
            if (st2.hasMoreTokens())
                fmt.format("%s\n",st2.nextToken());

  //          FmtStr  = fmt.format("%20s\t%s\n",st2.nextToken(),st2.nextToken()).toString();
        }
        FmtStr = fmt.toString();
        fmt.close();



        try (FileWriter fout = new FileWriter("out.txt");
             FileWriter fout2 = new FileWriter("cnt.txt");
             FileWriter fout3 = new FileWriter("WordsTable.txt");                                                   ){

            String outTableStr = outTable.toString();
            char buffer[] = new char[out.length()];
            char buffer2[] = new char[outTableStr.length()];
            Integer asd= Pointer;



            out2.getChars(0,out2.length(),buffer,0);
            outTableStr.getChars(0,outTableStr.length(),buffer2,0);
            fout.flush();
            fout.write(buffer);
            fout2.flush();
            fout2.write(asd.toString());
            fout2.flush();
            fout3.write(FmtStr);



        }catch (IOException e){System.out.println("Ошыбка ,братан: ");}

        Date EndTime = new Date();

        System.out.println("Programm Ex time " + (EndTime.getTime()-DateStart.getTime()));
        System.out.println(DateStart + " " +  EndTime);
    }

}
