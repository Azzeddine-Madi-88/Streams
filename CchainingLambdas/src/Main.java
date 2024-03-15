import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        String name = "Tim ";
        Function<String, String> uCase = String::toUpperCase;
        System.out.println(name.transform(uCase));

        Function<String, String> lastName = s -> s.concat(" Bushaka");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);

        System.out.println(uCaseLastName.apply(name));

        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(name));

        Function<String, String[]> f0 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name)));

      String[] names = {"Ann", "Bob", "Carol"};

        Predicate<String> p1 = s -> s.equals("TIM");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Tim");
        Predicate<String> p3 = s -> s.startsWith("T");
        Predicate<String> p4 = s -> s.endsWith("e");

        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1 = " + combined1.test(name.trim()));

        Predicate<String> combined2 = p3.and(p4);
        System.out.println("combined2 = " + combined2.test(name.trim()));

        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println("combined3 = " + combined3.test(name.trim()));

        record Person(String firstName, String lastName){}

        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Mickey", "Mouse")));
        list.sort(
                (o1, o2) -> (o1.firstName()+o1.lastName()).compareTo(o2.firstName()+o2.lastName())
        );

        list.forEach(System.out::println);

        System.out.println("------------------------");

        list.sort(
                Comparator.comparing(Person::lastName)
        );

        System.out.println("------------------------");

        list.sort(
                Comparator.comparing(Person::lastName).thenComparing(Person::firstName)
        );

        list.forEach(System.out::println);


        System.out.println("------------------------");

        list.sort(
                Comparator.comparing(Person::lastName).thenComparing(Person::firstName).reversed()
        );

        list.forEach(System.out::println);






    }
}