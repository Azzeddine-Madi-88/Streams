import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");
        var students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .toList();
        var australianStudents = students.stream()
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());
        System.out.println("# of Australian Students is " + australianStudents.size());

        var studentsUnderThirty = students.stream()
                .filter(s -> s.getAgeEnrolled() < 30)
                .collect(Collectors.toSet());
        System.out.println("# of students under 30 is " + studentsUnderThirty.size());

            students.stream()
                .filter(s -> s.getAgeEnrolled() < 30 && s.getCountryCode().equals("AU"))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId))))
                .forEach(s -> System.out.println("Student ID: " + s.getStudentId() + " Country code: " + s.getCountryCode() + " Age: " + s.getAgeEnrolled()));
        System.out.println("********************************************************");

        Set<Student> youngAussies = new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngAussies.addAll(australianStudents);
        youngAussies.retainAll(studentsUnderThirty);
        youngAussies.forEach(s -> System.out.println("Student ID: " + s.getStudentId() + " Country code: " + s.getCountryCode() + " Age: " + s.getAgeEnrolled()));

        Set<Student> youngAussies2 = students.stream()
                .filter(s -> s.getAgeEnrolled() < 30 && s.getCountryCode().equals("AU"))
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)), TreeSet::add, TreeSet::addAll);
        youngAussies2.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();

        String countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce("X**", (r, v) -> r + " " + v);
        System.out.println(countryList);


    }
}
