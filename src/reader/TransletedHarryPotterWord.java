package reader;

public class TransletedHarryPotterWord extends HarryPotterWord {
    public String translation;
    public TransletedHarryPotterWord(String word, int count,String tr) {
        super(word, count);
        translation = tr;
    }
    public String getTranslation(){ return translation; }

    public void setTranslation(String setTr){ translation = setTr; }
}
