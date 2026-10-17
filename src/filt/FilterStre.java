package filt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterStre {
    public static void main(String[] args) {

        System.out.println("dropWhile");
        dropWhile();

    }

    public  static void filter(){
        List<Integer> list =Stream.of(1,3,4,5,6,8,10).
                filter(n-> n%2 ==0)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    public static void takeWhile(){
       Stream.of(2,4,6,5,6,7,8,9,10)
                .takeWhile(n -> n % 2 == 0)
                        .forEach(System.out::println);
    }
    public static void dropWhile(){
        Stream.of(9,6,8,10,3,7)
                .dropWhile(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}
