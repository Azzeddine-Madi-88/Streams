import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;


class PlainOld{
    private static int last_id = 1;
    private int id;
    public PlainOld(){
        id = PlainOld.last_id++;
        System.out.println("Creating a PlainOld Object: id = " + id);
    }

    @Override
    public String toString() {
        return "Plain Old Object Number: %-5d".formatted(id);
    }
}
public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "Anna", "Bob", "Chuck", "Dave"
        ));

        list.forEach(
                System.out::println
        );

        calculator(
                (val1, val2) -> val1 + val2,
                5.6,
                2.3
        );

        Supplier<PlainOld> reference1 = PlainOld::new;
        //reference1.get();

        System.out.println("Getting array");
        PlainOld[] resultArray =  seedArray(reference1, 10);
        Arrays.asList(resultArray).forEach(System.out::println);

        UnaryOperator<String> concatOp = s -> s.concat(s);
        System.out.println(concatOp.apply("Burger "));

        calculator(
                String::concat,
                "Hello ",
                "World"
        );

        BiFunction<String, String, String> b2 = String::concat;

        BiFunction<String, Integer, String> b3 = String::repeat;

        UnaryOperator<String> u1 = String::toUpperCase;

        String result = "Hello".transform(u1);
        System.out.println(result);

        result = result.transform(String::toLowerCase);
        System.out.println(result);

        Function<String, Boolean> f0 = String::isEmpty;

        Boolean result2 = "xxx".transform(f0);
        System.out.println(result2 ? "it is empty" : "Not empty");

        PrintStream myOut = System.out;

        list.forEach(myOut::println);

    }

    private static <T> void calculator(BinaryOperator<T> function, T value1, T value2){
        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
    }

    private static  PlainOld[] seedArray(Supplier<PlainOld> reference, int count){
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }
}