package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class University implements Serializable {
    private List<Group> listOfGroup = new ArrayList<>();
    private String nameOfUniversity;
    private static final String ASCENDING = "Ascending";
    private transient static final String DESCENDING = "Descending";


    public University(String nameOfUniversity) {
        this.nameOfUniversity = nameOfUniversity;
    }

    public void addGroup(Group group) {
        listOfGroup.add(group);
    }

    public String getNameOfUniversity() {
        return nameOfUniversity;
    }

    public List<Group> getListOfGroup() {
        return listOfGroup;
    }

    public List<Group> sortGroupsByMarks(String type) {
        if (type.equals(ASCENDING))
            listOfGroup.sort(University::compareAsc);
        else if (type.equals(DESCENDING))
            listOfGroup.sort(University::compareDesc);
        else
            System.out.println("Enter the correct type of sorting!");
        return listOfGroup;
    }

    private static int compareAsc(Group o1, Group o2) {
        return Double.compare(o1.getAverageMarkOfGroup(), o2.getAverageMarkOfGroup());
    }

    private static int compareDesc(Group o1, Group o2) {
        return -Double.compare(o1.getAverageMarkOfGroup(), o2.getAverageMarkOfGroup());
    }

    @Override
    public String toString() {
        return "\nentity.University:\n" + nameOfUniversity + listOfGroup;
    }

}
