import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"
        ));

        for(String elem : list){
            System.out.println(elem);
        }
        System.out.println("--------------");
        list.forEach(elem -> System.out.println(elem));
        System.out.println("------------");
        list.forEach(elem -> {
            String upCaseEelem = elem.toUpperCase();
            int i =0;
            i=i+10;
            System.out.println(upCaseEelem+ " ==> " + i);
        });

        System.out.println("------------");
        list.forEach(
                new Consumer<>() {
                    @Override
                    public void accept(String elem) {
                        String upCaseEelem = elem.toUpperCase();
                        int i =0;
                        i=i+10;
                        System.out.println(upCaseEelem+ " *** " + i);
                    }
                }
        );

        int result = calculator(
                (value1, value2) -> value1+value2,
                5,4
        );

        System.out.println("Result from main: " + result);

        var result2 = calculator(
                (value1, value2) -> value1/value2,
                5.0,4.5
        );

        System.out.println("Result from main: " + result2);

        var result3 = calculator(
                (value1, value2) -> value1.toUpperCase()+ " " +value2.toUpperCase(),
                "Value 1","Value 2"
        );

        System.out.println("Result from main: " + result3);

        var result4 =((BinaryOperator<Integer>) (value1, value2) -> value1+value2).apply(50,60);

        System.out.println("Result from main: " + result4);

        var result5 =((Operation<Integer>) (value) -> value[0]+value[1]).operate(50,60);

        System.out.println("Result from main: " + result5);

        var coords = Arrays.asList(
                new double[]{47.2160, -95.2348},
                new double[]{29.1566, -89.2495},
                new double[]{35.1556, -90.0659});
        coords.forEach(
                (doubleArray) -> System.out.println(Arrays.toString(doubleArray))
        );

        BiConsumer<Double, Double> biConsumer = (t1, t2) -> System.out.println("Longitude: " + t1 + " Latitude: " + t2);

        coords.forEach(
                (doubleArray) -> {
                    processPoint(doubleArray[0], doubleArray[1],
                            biConsumer
                            );
                }
        );

        list.removeIf(s -> s.equalsIgnoreCase("BRAVO"));
        System.out.println(list);
        list.addAll(List.of("echo", "easy", "earnest"));
        list.forEach(s -> System.out.println(s));
        System.out.println("--------------");
        list.removeIf(s -> s.startsWith("ea"));
        list.forEach(s -> System.out.println(s));

        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        System.out.println(list);

        String[] emptyStrings = new String[10];
        System.out.println(Arrays.toString(emptyStrings));
        Arrays.fill(emptyStrings, "");
        System.out.println(Arrays.toString(emptyStrings));
        Arrays.setAll(emptyStrings, i -> i+1 + "burger");
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, i -> "" + (i+1) + ". "
            + switch (i){
            case 0 -> "one";
            case 1 -> "two";
            case 2 -> "three";
            default -> "";
                }
        );
        System.out.println(Arrays.toString(emptyStrings));

        String[] names = {"Ann", "Bob", "Carol", "Ed", "Fred"};

        String[] randomNames = randomleySelectedValues(3, names,
                () -> (new Random()).nextInt(0, names.length)
                );
        System.out.println(Arrays.toString(randomNames));

        Consumer<String> printTheParts = s-> {
          for(String part : s.split(" ")){
              System.out.println(part);
          }
        };
        printTheParts.accept("Part1 Part2 PartSomething");

        Consumer<String> printThePartsForEach = s-> Arrays.asList(s.split(" ")).forEach(elem -> System.out.println(elem));
        printThePartsForEach.accept("Part1 Part2 PartSomething");

        UnaryOperator<String> everySecondChar = source ->
        {
            StringBuilder returnVal = new StringBuilder();
          for(int i =0 ; i < source.length(); i++){
              if(i % 2 ==1){
                  returnVal.append(source.charAt(i));
              }
          }
          return returnVal.toString();
        };

        System.out.println(everySecondCharacter(everySecondChar, "1234567890"));

    }

    public static String everySecondCharacter(UnaryOperator<String> unaryOperator, String value){
        return unaryOperator.apply(value);
    }

    public static <T> T calculator(BinaryOperator<T> function, T value1, T value2){
        T result = function.apply(value1, value2);
        System.out.println("Result of Operation: " + result);
        return result;
    }

    public static <T> T calculator2(Operation<T> function, T value1, T value2){
        T result = function.operate(value1, value2);
        System.out.println("Result of Operation: " + result);
        return result;
    }

    public static <T> void processPoint(T t1, T t2, BiConsumer<T,T> consumer){
        consumer.accept(t1, t2);
    }

    public static String[] randomleySelectedValues(int count, String[] values, Supplier<Integer> s){
        String[] selectedValues = new String[count];
        for(int i=0; i < count; i++){
            selectedValues[i] = values[s.get()];
        }
        return selectedValues;
    }
}