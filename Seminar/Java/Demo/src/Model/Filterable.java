package Model;

@FunctionalInterface
public interface Filterable {
    boolean filter(Student student);

    static int methodStatic(int input) {
        return 0;
    }

    default int methodDefault(int input) {
        return 0;
    }
}
