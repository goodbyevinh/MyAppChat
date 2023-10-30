package Model;

public class Student {

    private String code;
    private String name;
    private float avg;

    public Student(String code, String name, float avg) {
        this.code = code;
        this.name = name;
        this.avg = avg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }
}
