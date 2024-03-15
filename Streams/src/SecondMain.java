import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SecondMain {
    public static void main(String[] args) {
        var bStream = Stream.iterate(1, s -> s <= 15, s -> s+=1)
                .map(s -> "B" + s);
        List<Integer> listOfInteger = new ArrayList<>(15);
        for (int i = 16; i<= 30; i++){
            listOfInteger.add(i);
        }
        var iStream = listOfInteger.stream()
                .map(s -> "I" + s);

        Integer[] arrayOfInteger = new Integer[15];
        Arrays.setAll(arrayOfInteger, i -> i + 31);
        var nStream = Arrays.stream(arrayOfInteger)
                .map(s -> "N" + s);


        Integer[] arrayOfInteger2 = new Integer[15];
        Arrays.setAll(arrayOfInteger2, i -> i + 46);
        var gStream = Stream.of(arrayOfInteger2)
                .map(s -> "G" + s);

        var concatStream = Stream.concat(bStream, iStream);
        var concatStream2 = Stream.concat(nStream, gStream);
        Stream.concat(concatStream, concatStream2)
                .filter(s -> Integer.valueOf(s.substring(1)))
                .forEach(s -> System.out.print(s + " "));

    }
}
