import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    record Person(String firstName, String lastName){
        @Override
        public String toString() {
            return firstName +  " " + lastName;
        }
    }
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Main.Person("Lucy", "Van Pelt"),
                new Person("Sally", "Brown"),
                new Person("Linus", "Van Pelt"),
                new Person("PepperMint", "Patty"),
                new Person("Charlie", "Brown")));

        //Custom Comparator using Anonymous class
        var comparatorLastName = new Comparator<Person>(){

            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        };

        people.sort(comparatorLastName);
        System.out.println(people);

        people.sort(
                (o1, o2) -> o1.lastName.compareTo(o2.lastName)
        );

        System.out.println(people);

        interface EnhancedComparator<T> extends Comparator<T>{
            int secondLevel(T o1, T o2);
        }

        var comparatorSecondLevel = new EnhancedComparator<Person>(){

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName().compareTo(o2.lastName());
                return result == 0 ? secondLevel(o1,o2) : result;
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };

        people.sort(comparatorSecondLevel);
        System.out.println(people);

        people.sort(
                new EnhancedComparator<>(){

                    @Override
                    public int compare(Person o1, Person o2) {
                        int result = o1.lastName().compareTo(o2.lastName());
                        return result == 0 ? secondLevel(o1,o2) : result;
                    }

                    @Override
                    public int secondLevel(Person o1, Person o2) {
                        return o1.firstName().compareTo(o2.firstName());
                    }
                }
        );

        System.out.println(people);

    }
}