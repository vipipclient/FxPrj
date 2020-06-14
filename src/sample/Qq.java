package sample;

public class Qq {
    public String word;
    public int count;
    public String translation;

    public Qq(String t, int c, String tr){
        word=t;
        count=c;
        translation = tr;
    }
    public String getWord(){
        return word;
    }
    public int getCount(){
        return count;
    }
    public String getTranslation(){
        return translation;
    }
    @Override
    public String toString() {
        return word + " " + count + " ";
    }
}
