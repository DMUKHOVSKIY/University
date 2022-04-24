import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private List<Marks> listOfMarks = new ArrayList<>();
    private String name;
    private String surname;

    public Student(String name, String surname) {
        this.name = name;
        this.surname= surname;
    }

    public void addMark(Marks mark) {
        listOfMarks.add(mark);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Marks> getListOfMarks() {
        return listOfMarks;
    }

    public double getAverageMarkOfStudent() {
        int sum = 0;
        for (int i = 0; i < listOfMarks.size(); i++) {
            sum += listOfMarks.get(i).getMark();
        }
        return sum / listOfMarks.size();
    }

    @Override
    public String toString() {
        return "\nStudent:" + name + listOfMarks;
    }

}
