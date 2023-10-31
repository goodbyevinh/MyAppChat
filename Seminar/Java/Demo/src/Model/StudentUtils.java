package Model;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentUtils {
    // final không muốn thay đổi list
    // cách bình thường
    public static List<Student> getNormalStudent (final List<Student> students) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (student.getAvg() >=5 && student.getAvg() <=7) {
                results.add(student);
            }
        }
        return results;
    }

    // Nếu muốn thêm các method khác như hs giỏi , khá thì phải tạo hàm mới , thì ta sử dụng tham số functionalInterface
    // để truyền hành vi vào hàm filter cho mỗi hành vi khác nhau
    public static List<Student> filter (final List<Student> students, Filterable filterable) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (filterable.filter(student)) {
                results.add(student);
            }
        }
        return results;
    }


    public static List<Student> filterPredicate (final List<Student> students, Predicate<Student> predicate) {
        List<Student> results = new LinkedList<>();
        for (Student student : students) {
            if (predicate.test(student)) {
                results.add(student);
            }
        }
        return results;
    }

// sử dụng stream không thay đổi gia trị trong students
    public static List<Student> filterUsingStream (final List<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static Student getBadStudent(List<Student> students) {
        return students.stream()
                .filter(student -> student.getAvg() < 5)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("have no bad student"));
    }

    public static Optional<Student> getBadStudentOptional(List<Student> students) {
        return students.stream()
                .filter(student -> student.getAvg() < 5)
                .findFirst();
    }

    public static Student getExellentStudent(List<Student> students) {
        return students.stream()
                .filter(student -> student.getAvg() == 10)
                .findFirst()
                .orElseGet(
                        () -> students.stream()
                                .max(
                                        (s1, s2) -> {
                                            float result = s1.getAvg() - s2.getAvg();
                                            return Float.floatToIntBits(result);
                                        }
                                )
                                .get()
                );

//        return students.stream()
//                .max((s1, s2) -> {
//                    float result = s1.getAvg() - s2.getAvg();
//                    return Float.floatToIntBits(result);
//                })
//                .get()
    }
}
