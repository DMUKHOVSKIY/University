import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    boolean doWork = true;
    private Student student;
    private Group group;
    private University university;
    private DataBase dataBaseUniversity;
    private DataBase dataBaseGroup;
    private DataBase dataBaseStudent;

    {
        try {
            dataBaseUniversity = new DataBase("University.dat");
            dataBaseGroup = new DataBase("Group.dat");
            dataBaseStudent = new DataBase("Student.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            doWork = true;
            System.out.println("Hello, what do you want to do?\n" +
                    "choose 1 to create the Student\n" +
                    "choose 2 to create the Group\n" +
                    "choose 3 to create the University\n" +
                    "choose 4 to get Student from the DataBase\n" +
                    "choose 5 to get Group from the DataBase\n" +
                    "choose 6 to get University from the DataBase\n" +
                    "choose 7 to get Students from the Group\n" +
                    "choose 8 to get all information about the University\n" +
                    "choose 9 to exit");

            Action(enterTheNumber());
        }
    }

    private void Action(int i) {
        switch (i) {
            case 1: {
                System.out.println("You want to create new Student? (Yes,No)");
                if (enterTheString().equals("Yes")) {
                    student = addStudent();
                    dataBaseStudent.addData(student);
                } else {
                    while (doWork) {
                        System.out.println("Choose 1 to add the Marks to the Student\n" +
                                "Choose 2 to get average mark of a Student\n" +
                                "Choose 3 to exit");
                        workWithStudent(enterTheNumber(), student);
                    }
                }
            }
            break;
            case 2: {
                System.out.println("You want to create new Group? (Yes,No)");
                if (enterTheString().equals("Yes")) {
                    group = addGroup();
                    dataBaseGroup.addData(group);
                } else {
                    while (doWork) {
                        System.out.println("Choose 1 to add the student to the Group\n" +
                                "Choose 2 to sort Students in the Group by average mark\n" +
                                "Choose 3 to exit");
                        workWithGroup(enterTheNumber(), group, student);
                    }
                }
            }
            break;
            case 3: {
                System.out.println("You want to create new Group? (Yes,No)");
                if (enterTheString().equals("Yes")) {
                    university = addUniversity();
                    dataBaseUniversity.addData(university);
                } else {
                    while (doWork) {
                        System.out.println("Choose 1 to add the Group to the University\n" +
                                "Choose 2 to sort Groups int the University by average marks\n" +
                                "Choose 3 to exit");
                        workWithUniversity(enterTheNumber(), university, group);
                    }
                }
            }
            break;
            case 4: {
                System.out.println("Enter the name and the surname of the Student");
                System.out.println(getStudent(enterTheString(), enterTheString()));
            }
            break;
            case 5: {
                System.out.println("Enter the number of the Group");
                System.out.println(getGroup(enterTheNumber()));
            }
            break;
            case 6: {
                System.out.println("Enter the name of University");
                System.out.println(getUniversity(enterTheString()));
            }
            break;
            case 7: {
                System.out.println("Enter the number of the Group");
                getStudentOfGroup(enterTheNumber());
            }
            break;
            case 8: {
                System.out.println("Enter the name of the University");
                getAllUniversityInformation(enterTheString());
            }
            break;
            case 9: {
                System.out.println("Conclusion...");
                return;
            }
            default:
                defaultBlock();
        }
    }

    private University addUniversity() {
        System.out.println("Enter the name of the University");
        return new University(enterTheString());
    }

    private Group addGroup() {
        System.out.println("Enter the number of the Group");
        return new Group(enterTheNumber());
    }

    private Student addStudent() {
        System.out.println("Enter the name and the surname of the Student");
        return new Student(enterTheString(), enterTheString());
    }

    private void workWithStudent(int i, Student student) {
        switch (i) {
            case 1: {
                System.out.println("Enter the subject: ");
                String subject = enterTheString();
                System.out.println("Enter the mark: ");
                int mark = enterTheNumber();

                student.addMark(new Marks(subject, mark));
            }
            break;
            case 2:
                System.out.println(student.getAverageMarkOfStudent());
                break;
            case 3:
                doWork = false;

                break;
            default:
                defaultBlock();
        }
    }

    private void workWithGroup(int i, Group group, Student student) {
        switch (i) {
            case 1:
                group.addStudent(student);
                break;
            case 2: {
                System.out.println("Enter the type of sorting (Ascending or Descending):\n");
                System.out.println(group.sortStudentsByMarks(enterTheString()));
            }
            break;
            case 3:
                doWork = false;
                break;
            default:
                defaultBlock();
        }
    }

    private void workWithUniversity(int i, University university, Group group) {
        switch (i) {
            case 1:
                university.addGroup(group);
                break;
            case 2: {
                System.out.println("Enter the type of sorting (Ascending or Descending:\n)");
                System.out.println(university.sortGroupsByMarks(enterTheString()));
            }
            break;
            case 3:
                doWork = false;
                break;
            default:
                defaultBlock();
        }
    }

    private Student getStudent(String name, String surname) {
        ArrayList<Student> list = dataBaseStudent.readData();

        for (Student student : list) {
            if (student.getName().equals(name) && student.getSurname().equals(surname))
                return student;
            else
                System.out.println("Can't find the student");
        }
        return null;
    }

    private Group getGroup(int groupNumber) {
        ArrayList<Group> list = dataBaseGroup.readData();

        for (Group group : list) {
            if (group.getGroupNumber() == groupNumber)
                return group;
            else
                System.out.println("Can't find the group");
        }

        return null;
    }

    private void getStudentOfGroup(int numberOfGroup) {
        Group groupTmp = getGroup(numberOfGroup);
        System.out.println(groupTmp.getListOfStudents());
    }


    private University getUniversity(String nameOfUniversity) {
        ArrayList<University> list = dataBaseUniversity.readData();

        for (University university : list) {
            if (university.getNameOfUniversity().equals(nameOfUniversity))
                return university;
            else
                System.out.println("Can't find the student");
        }
        return null;
    }

    private void getAllUniversityInformation(String nameOfUniversity) {
        University universityTmp = getUniversity(nameOfUniversity);
        System.out.println(universityTmp.toString());
    }

    private String enterTheString() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext())
            return scanner.next();
        else System.out.println("Enter the String!");
        return enterTheString();
    }

    private int enterTheNumber() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt())
            return scanner.nextInt();
        else
            System.out.println("Enter the number!");
        return enterTheNumber();

    }

    private void defaultBlock() {
        System.out.println("Fuck you. enter correct number!");

    }
}
