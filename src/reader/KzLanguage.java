package reader;

import java.util.Scanner;

public class KzLanguage {
    public static void main(String[] args) {
        Scanner inputFromConsole = new Scanner(System.in);//Read from keyboard
  //      String keyboard = "";
        for (int i = 0; i <10 ; i++) {
            String keyboard = inputFromConsole.nextLine();
            if (keyboard.contentEquals("стоп"))
                    break;
            else
                findInf(keyboard);
        }


   //     findInf(System.console().readLine());
    }
    static final String endings[] = new String[] {
            //Сетік
            "ның","нің",
            "дың","дің",
            "тың","тің",
            "ға","ге",
            "қа","ке",
            "а","е",
            "на","не",
            "ны","ні",
            "ды","ді",
            "ты","ті","н",
            "да","де",
            "та","те",
            "нда","нде",
            "нан","нен",
            "дан","ден",
            "тан","тен",
            "мен","менен",
            "бен","бенен",
            "пен","пенен",


            //Притяж
            "м","ң",
            "ым","ім",
            "ың","ің",
            "ңыз","ңіз","ыңыз","іңіз",
            "сы","сі","ы","і",

            "мыз","міз","ымыз","іміз",
            "ың","ің","ың","ің",
            "ыңыз","іңіз","ыңыз","іңіз",
            "ы","і","ы","і",

            //Личные
            "мын","мін","бын","бін","пын","пін",
            "сың","сің",
            "сыз","сіз",

            "мыз","міз","быз","біз","мыз","піз",
            "сыңдар","сіңдер",
            "сыздар","сіздер",

            //Множественн
            "лар","лер",
            "дар","дер",
            "тар","тер",

            //
    };
    static String findInf(String word){
        for (String ending : endings) {

            if (word.contains(ending))
                System.out.println(ending);
        }
        return "";

    }
}
