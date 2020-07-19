package sample;

import java.util.*;
import java.util.stream.Stream;

interface Printable{
    <T> void  println(T x);
}
class MyComp {
    public static int compare(Integer integer, Integer t1) {
        return t1 - integer  ;
    }
    public static int compare(String t1, String t2) {
        return t1.compareTo(t2)  ;
    }
}
public class Tasks {


    public static void main(String[] args) {
        LinkedList<Integer> list =new LinkedList<Integer>();
        list.add(-20);
        list.add(1);
        list.add(2);
        list.add(10);
        list.add(5);
        list.addFirst(0);
        list.addFirst(0);
        System.out.println(list.pop());
        System.out.println(list.stream().max((a,b)->{return a-b;}));
        ;
        System.out.println(list.stream()
                                    .filter(num->{return (num>5);})
                                        .count());
        ;


    }
}
