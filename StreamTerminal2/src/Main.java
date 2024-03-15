import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python MasterClass");
        Course jmc = new Course("JMC", "Java MasterClass");
//        Student tim = new Student("AU", 2019, 30, "M", true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

        Student[] students = new Student[1000];
        Arrays.setAll(students, i -> Student.getRandomStudent(jmc, pymc));

//        var maleStudents = Arrays
//                .stream(students)
//                .filter(s -> s.getGender().equals("M"));

//        System.out.println("# of male students " + maleStudents.count());

        for(String gender : List.of("M", "F", "U")) {
            var myStudents = Arrays.stream(students)
                    .filter(s -> s.getGender().equals(gender));
            System.out.printf("# of %s students is %d%n", gender.equals("M") ? "Male" : gender.equals("F") ? "Female" : "Unselected gender", myStudents.count());
        }

        List<Predicate<Student>> list = List.of(
                s -> s.getAge() < 30,
                s -> s.getAge() >= 30 && s.getAge() < 60
        );

        System.out.println("*******************************************");

        long total = 0;
        for(int i = 0; i < list.size(); i++) {
            var studentNumber = Arrays.stream(students)
                    .filter(list.get(i))
                    .count();
            System.out.println("number of students with age " + (i == 0 ? "less than 30 " : "between 30 and 60 ") + "is " + studentNumber);
            total = total + studentNumber;
        }
        System.out.println("number of students with age greater than 60 is " + (1000 - total));

        System.out.println("******************************************");

        var ageStream = Arrays.stream(students)
                .mapToInt(Student::getAgeEnrolled)
                .summaryStatistics();
        System.out.println(ageStream);

        var currentAgeStream = Arrays.stream(students)
                .mapToInt(Student::getAge)
                .summaryStatistics();
        System.out.println(currentAgeStream);

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        var longTimeLearners = Arrays.stream(students)
                .filter(s -> s.getMonthsSinceActive() < 12 && (s.getAge() - s.getAgeEnrolled()) >= 7)
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toArray(Student[]::new);

        var learners = Arrays.stream(students)
                .filter(s -> s.getMonthsSinceActive() < 12 && (s.getAge() - s.getAgeEnrolled()) >= 7)
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList());

        Collections.shuffle(learners);
    }
}