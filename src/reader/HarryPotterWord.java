package reader;

public class HarryPotterWord {
    public String word;
    public int count;

    public HarryPotterWord(String t, int c){
        this.word=t;
        this.count=c;
    }
    public String getWord(){
        return word;
    }
    public int getCount(){
        return count;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return word + " " + count + " ";
    }
}
