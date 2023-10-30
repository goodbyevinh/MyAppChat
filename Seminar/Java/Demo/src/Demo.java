import Model.Filterable;
import Model.Student;

import java.util.LinkedList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        List<Student> students = new LinkedList<>();

        students.add(new Student("sv1", "Vinh", 5));
        students.add(new Student("sv2", "Huy", 6.5f));
        students.add(new Student("sv3", "Vỹ", 7.5f));

        students.forEach(student -> System.out.println(student.getName()));

        List<Student> middleStudent = filter(students, student -> student.getAvg() > 5 && student.getAvg() <= 7);
        List<Student> goodStudent = filter(students, student -> student.getAvg() > 7 && student.getAvg() <= 9);
        List<Student> excellentStudent = filter(students, student -> student.getAvg() > 9 && student.getAvg() <= 10);
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
}
