import Model.Filterable;
import Model.Student;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) {

        List<Student> students = getStudentDB();

        students.forEach(student -> {

            if (student.getName().equals("Vinh")) {
                students.remove(student);
            }
        });

    }

    public static List<Student> filter (final List<Student> students, Filterable filterable) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (filterable.filter(student)) {
                results.add(student);
            }
        }
        return results;
    }

    public static List<Student> filterNormalStudnet (final List<Student> students) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (student.getAvg() > 5 && student.getAvg() <= 7) {
                results.add(student);
            }
        }
        return results;
    }

    public static List<Student> filterMiddleStudnet (final List<Student> students) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (student.getAvg() > 5 && student.getAvg() <= 7) {
                results.add(student);
            }
        }
        return results;
    }
    public static List<Student> filterGoodStudnet (final List<Student> students) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (student.getAvg() > 7 && student.getAvg() <= 9) {
                results.add(student);
            }
        }
        return results;
    }

    public static List<Student> filterExcellentStudnet (final List<Student> students) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (student.getAvg() > 9 && student.getAvg() <= 10) {
                results.add(student);
            }
        }
        return results;
    }

    public static List<String> getNameExcellentStudent(List<Student> students) {

        return students.stream()
                .filter(student -> student.getAvg() > 9 && student.getAvg() <= 10)
                .sorted((s1, s2) ->  Float.valueOf(s1.getAvg()).compareTo(Float.valueOf(s2.getAvg())))
                .map(student -> student.getName())
                .collect(Collectors.toList());
    }

    public static List<Student> getStudentDB() {

        List<Student> students = new ArrayList<>();

        students.add(new Student("sv1", "Name1", 3));
        students.add(new Student("sv2", "Name2", 6.5f));
        students.add(new Student("sv3", "Name3", 7.5f));
        students.add(new Student("sv4", "Name4", 8));
        students.add(new Student("sv5", "Name5", 9.5f));
        students.add(new Student("sv6", "Name6", 7.5f));
        students.add(new Student("sv7", "Name7", 10));
        students.add(new Student("sv8", "Name8", 9.5f));
        students.add(new Student("sv9", "Name9", 9.1f));
        students.add(new Student("sv10", "Name10", 9.2f));
        students.add(new Student("sv11", "Name11", 9.6f));
        students.add(new Student("sv12", "Name12", 9.9f));
        students.add(new Student("sv13", "Name13", 7));
        students.add(new Student("sv14", "Name14", 3));
        students.add(new Student("sv15", "Name15", 2));
        students.add(new Student("sv16", "Name16", 7));
        students.add(new Student("sv17", "Name17", 5.5f));
        students.add(new Student("sv18", "Name18", 9.9f));

        return students;
    }

    public static void parallelReduce() {

        List<Integer> numbers = List.of(1,2,3,4,5);

        System.out.println(numbers.stream().reduce(10, (a, b) -> a + b));
        System.out.println(numbers.parallelStream().reduce(10, (a, b) -> a + b));

    }

    public static void subList() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);

        List<Integer> ints2 = ints.subList(0, 3);
        Stream stream = ints2.stream();
        ints2.add(4);
        ints2.add(5);
        stream.forEach(System.out::println);
    }
}
