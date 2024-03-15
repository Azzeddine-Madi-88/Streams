import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class MainMapping {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python MasterClass", 50);
        Course jmc = new Course("JMC", "Java MasterClass", 100);
        Course jgames = new Course("JAGAME", "Creating Games in Java");

        List<Student> students = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

        var mappedStudents = students.stream()
                .collect(Collectors.toMap(Student::getCountryCode,
                        v -> new ArrayList<Student>(List.of(v)),
                         (k, v) -> {
                             ArrayList<Student> newList = new ArrayList<Student>(k);
                             newList.addAll(v);
                             return newList;
                }));

        //System.out.println(mappedStudents);

        var mappedStudents2 = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));
        mappedStudents2.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("----------------------------------");
        int minAge = 25;
        var youngerSet = students.stream()
                .collect(groupingBy(Student::getCountryCode,
                         filtering(s -> s.getAge() <= minAge, toList())));

        System.out.println(youngerSet);

        var experienced = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience));

        System.out.println("Experienced Students = " + experienced.get(true).size());

        var experienced2 = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience,
                        counting()));

        System.out.println("Experienced Students = " + experienced2.get(true));

        var experienced3 = students.stream()
                .collect(partitioningBy(s -> s.hasProgrammingExperience() && s.getMonthsSinceActive() == 0,
                        counting()));

        System.out.println("Experienced Students = " + experienced3.get(true));

        System.out.println("**********************************");

        var multiLevel = students.stream()
                .collect(groupingBy(Student::getCountryCode,
                        groupingBy(Student::getGender)));

        multiLevel.forEach((k, v) -> {
            System.out.println(k);
            v.forEach((k2, v2 ) -> System.out.println("\t" + k2 + " " + v2.size()));
        });

        long studentBodyCount = 0;
        for(var list : experienced.values()) {
            studentBodyCount += list.size();
        }

        System.out.println(studentBodyCount);

        studentBodyCount = experienced.values().stream()
                .map(l -> l.stream()
                                .filter(s -> s.getMonthsSinceActive() <= 3)
                                .count()
                )
                .mapToLong(l -> l)
                .sum();
        System.out.println(studentBodyCount);

        long count = experienced.values().stream()
                .flatMap(Collection::stream)
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println(count);

        count = multiLevel.values().stream()
                .flatMap(m -> m.values().stream())
                .flatMap(Collection::stream)
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println(count);


    }
}
