package sample;
interface  MyFunc<T>{
    boolean finc(T v1, T v2, int par);
}
class HighTemp {
    private int hTemp;
    HighTemp(int ht){hTemp = ht;}


//    boolean sameTemp(HighTemp ht2){
//        System.out.println("sameTemp(HighTemp ht2)");
//        return hTemp == ht2.hTemp;
//    }
    boolean sameTemp(HighTemp ht2,int par){
        System.out.println("sameTemp(HighTemp ht2)");
        return true;
    }
}
public class MethodLink {
    static <T> int counter(T[] vals, MyFunc<T> f, T v) {

        System.out.println(f.finc(vals[0], v,3));
        return 1;
    }


    public static void main(String[] args) {
        HighTemp[] highTemps = {new HighTemp(1)};
        counter(highTemps,HighTemp::sameTemp,new HighTemp(2));

    }
}