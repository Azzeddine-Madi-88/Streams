import java.util.Comparator;
import java.util.stream.Stream;

public class MainTerminalOptional {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");
        var students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .toList();
        int minAge = 21;
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Empty"));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Empty"));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Empty"));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Empty"));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse(System.out::println, () -> System.out.println("No students found"));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .map(Student::getCountryCode)
                .distinct()
                .reduce((c1, c2) -> String.join(",", c1, c2))
                .ifPresentOrElse(System.out::println, () -> System.out.println("No students found"));

        students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .map(c -> String.join(",", c))
                .filter(c -> c.contains("FR"))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Empty"));

    }
}
