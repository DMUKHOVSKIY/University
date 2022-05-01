package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
    private ArrayList<Student> listOfStudents = new ArrayList<>();
    private int groupNumber;
    private transient static final String ASCENDING = "Ascending";
    private transient static final String DESCENDING = "Descending";

    public Group(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void addStudent(Student student) {
        listOfStudents.add(student);
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

    public double getAverageMarkOfGroup() {
        int sum = 0;
        for (int i = 0; i < listOfStudents.size(); i++) {
            sum += listOfStudents.get(i).getAverageMarkOfStudent();
        }
        return sum / listOfStudents.size();
    }

    public List<Student> sortStudentsByMarks(String type) {
        if (type.equals(ASCENDING))
            listOfStudents.sort(Group::compareAsc);
        else if (type.equals(DESCENDING))
            listOfStudents.sort(Group::compareDesc);
        else
            System.out.println("Enter the correct type of sorting!");
        return listOfStudents;
    }

    private static int compareAsc(Student o1, Student o2) {
        return Double.compare(o1.getAverageMarkOfStudent(), o2.getAverageMarkOfStudent());
    }

    private static int compareDesc(Student o1, Student o2) {
        return -Double.compare(o1.getAverageMarkOfStudent(), o2.getAverageMarkOfStudent());
    }

    @Override
    public String toString() {
        return "\nentity.Group:" + groupNumber + listOfStudents;
    }

}
