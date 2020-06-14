package reader;

import sample.Qq;

import java.io.*;
import java.util.*;

public class CheckWithLinkedHashMap {
    
    Map<String,Integer> HarryWords = new LinkedHashMap();
    int Pointer=0;
    int maxStrLen = 0;
    Formatter fmt = new Formatter();
    Formatter Table = new Formatter();
    HashMap<String,String> EnglishDict = new HashMap<>();
    public CheckWithLinkedHashMap() {
        try (FileReader DictFile = new FileReader("ENRUS.TXT")){
            BufferedReader words = new BufferedReader(DictFile);

            String engWord;
            while ( (engWord = words.readLine()) != null){
                String rusWord = words.readLine();
                if (rusWord != null)
                    EnglishDict.put(engWord,rusWord);
            }
            System.out.println(EnglishDict.get("fuck"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    this.HarryWords.put(Word,words.nextInt());
                ;
            }
        }catch (IOException e1) {System.out.println("Ошыбка ,братан: ");}

        Scanner inputFromConsole = new Scanner(System.in);//Read from keyboard
        int cnt = Pointer;

        WordsToDelete.forEach(a ->{HarryWords.remove(a);});
        System.out.println(" ");

        ;

        final int maxSrtrLenFinal = maxStrLen;
        HarryWords.forEach((a,b)->{fmt.format(a + " " + b.toString() + " ");
            Table.format("%" + maxSrtrLenFinal + "s\t",a) ;
            Table.format("%s\n",b) ;
        });
    }
    void writeData(){
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
    public ArrayList<HarryPotterWord> GetHarrypotterWords(){
        ArrayList<HarryPotterWord> harryPotterWordArrayList = new ArrayList<HarryPotterWord>();
        HarryWords.forEach((key,value) ->{
            harryPotterWordArrayList.add(new HarryPotterWord(key,value));

        });
        return harryPotterWordArrayList;
    }
    public ArrayList<TransletedHarryPotterWord> GetTransletedHarryPotterWords(){
        ArrayList<TransletedHarryPotterWord> TransletedHarryPotterWordList = new ArrayList<TransletedHarryPotterWord>();
        HarryWords.forEach((key,value) ->{
            TransletedHarryPotterWord test = new TransletedHarryPotterWord(key, value, EnglishDict.get(key));
            TransletedHarryPotterWordList.add(test);
   //         System.out.println("TransletedHarryPotterWord.count " + test.count);
    //        System.out.println("TransletedHarryPotterWord.word " + test.word);
    //        System.out.println("TransletedHarryPotterWord.translate " + test.translate);
        });
        return TransletedHarryPotterWordList;
    }

    public ArrayList<Qq> GetQq(){
        ArrayList<Qq> qq = new ArrayList<Qq>();
        HarryWords.forEach((key,value) ->{
            Qq test = new Qq(key, value, EnglishDict.get(key));
            qq.add(test);
            //         System.out.println("TransletedHarryPotterWord.count " + test.count);
            //        System.out.println("TransletedHarryPotterWord.word " + test.word);
            //        System.out.println("TransletedHarryPotterWord.translate " + test.translate);
        });
        return qq;
    }
}
