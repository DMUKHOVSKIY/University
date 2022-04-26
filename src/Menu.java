import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
1)Создаём студентов и прописываем их оценки (отедельно делать нельзя)
2)Создаем группу и добавляем туда студентов (отдельно делать нельзя)
3)Создаем университет и добавляем туда группы (отдкльно делать нельзя)
4)После каждого пререзапуска программы файлы перезаписываются
*/

public class Menu {
    boolean doWork = true;
    private Student student;
    private Group group;
    private University university;
    private DataBase dataBaseUniversity;
    private DataBase dataBaseGroup;
    private DataBase dataBaseStudent;
    private ArrayList<Student> students;
    private ArrayList<Group> groups;
    private ArrayList<University> universities;

    {
        try {
            dataBaseUniversity = new DataBase("University.txt");
            dataBaseGroup = new DataBase("Group.txt");
            dataBaseStudent = new DataBase("Student.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            doWork = true;
            System.out.println("___________________________________\n" +
                    "Hello, what do you want to do?\n" +
                    "___________________________________\n" +
                    "choose 1 to work with a Student\n" +
                    "choose 2 to work with a Group\n" +
                    "choose 3 to work with a University\n" +
                    "___________________________________\n" +
                    "choose 4 to get Student from the DataBase\n" +
                    "choose 5 to get Group from the DataBase\n" +
                    "choose 6 to get University from the DataBase\n" +
                    "choose 7 to exit");

            Action(enterTheNumber());
        }
    }

    private void Action(int i) {
        switch (i) {
            case 1: {
                System.out.println("You want to create new Student? (Yes,No)");
                if (enterTheString().equals("Yes")) {
                    createNewStudent();
                } else {
                    while (doWork) {
                        System.out.println("Choose 1 to get average mark of a Student\n" +
                                "Choose 2 to exit");
                        workWithStudent(enterTheNumber());
                    }
                }
            }
            break;
            case 2: {
                System.out.println("You want to create new Group? (Yes,No)");
                if (enterTheString().equals("Yes")) {
                    createNewGroup();
                } else {
                    while (doWork) {
                        System.out.println("Choose 1 to sort Students in the Group by average mark\n" +
                                "Choose 2 to exit");
                        workWithGroup(enterTheNumber(), group, student);
                    }
                }
            }
            break;
            case 3: {
                System.out.println("You want to create new University? (Yes,No)");
                if (enterTheString().equals("Yes")) {
                    createNewUniversity();
                } else {
                    while (doWork) {
                        System.out.println("Choose 1 to sort Groups int the University by average marks\n" +
                                "Choose 2 to exit");
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
                System.out.println(getGroup(enterTheNumber()).toString());
            }
            break;
            case 6: {
                System.out.println("Enter the name of University");
                System.out.println(getUniversity(enterTheString()));
            }
            break;
            case 7: {
                System.out.println("Conclusion...");
                dataBaseStudent.oosClose();
                dataBaseGroup.oosClose();
                dataBaseUniversity.oosClose();
                return;
            }
            default:
                defaultBlock();
        }
    }

    private void createNewStudent() {
        student = addStudent();
        studentAddMark();
        dataBaseStudent.addData(student);
    }

    private void createNewGroup() {
        group = addGroup();
        groupAddStudent();
        dataBaseGroup.addData(group);
    }

    private void createNewUniversity() {
        university = addUniversity();
        universityAddGroup();
        dataBaseUniversity.addData(university);
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

    private void studentAddMark() {
        String choice = "Yes";
        while (choice.equals("Yes")) {
            System.out.println("Enter the subject: ");
            String subject = enterTheString();
            System.out.println("Enter the mark: ");
            int mark = enterTheNumber();
            student.addMark(new Marks(subject, mark));
            System.out.println("You want to add another mark? (Yes,No)");
            choice = enterTheString();
        }
    }

    private void groupAddStudent() {
        String choice = "Yes";
        while (choice.equals("Yes")) {
            System.out.println("Enter the name of the Student to add");
            Student st = getStudent(enterTheString(), enterTheString());
            group.addStudent(st);
            System.out.println("You want to add another Student? (Yes,No)");
            choice = enterTheString();
        }
    }

    private void universityAddGroup() {
        String choice = "Yes";
        while (choice.equals("Yes")) {
            System.out.println("Enter the name of the Group to add");
            university.addGroup(getGroup(enterTheNumber()));
            System.out.println("You want to add another Group? (Yes,No)");
            choice = enterTheString();
        }
    }

    private void workWithStudent(int i) {
        switch (i) {
            case 1:
                System.out.println("Enter the name of the Student");
                System.out.println(getStudent(enterTheString(), enterTheString()).getAverageMarkOfStudent());
                break;
            case 2:
                doWork = false;
                break;
            default:
                defaultBlock();
        }
    }

    private void workWithGroup(int i, Group group, Student student) {
        switch (i) {
            case 1: {
                System.out.println("Enter the name of the Group");
                System.out.println("Enter the type of sorting (Ascending or Descending):\n");
                System.out.println(getGroup(enterTheNumber()).sortStudentsByMarks(enterTheString()));
            }
            break;
            case 2:
                doWork = false;
                break;
            default:
                defaultBlock();
        }
    }

    private void workWithUniversity(int i, University university, Group group) {
        switch (i) {
            case 1: {
                System.out.println("Enter the name of the University");
                String name = enterTheString();
                System.out.println("Enter the type of sorting (Ascending or Descending:\n)");
                System.out.println(getUniversity(name).sortGroupsByMarks(enterTheString()));
            }
            break;
            case 2:
                doWork = false;
                break;
            default:
                defaultBlock();
        }
    }

    private Student getStudent(String name, String surname) {
        students = dataBaseStudent.readData("Student.txt");

        for (Student student : students) {
            if (student.getName().equals(name) && student.getSurname().equals(surname))
                return student;
        }
        System.out.println("Can't find the student");
        return null;
    }

    private Group getGroup(int groupNumber) {
        groups = dataBaseGroup.readData("Group.txt");

        for (Group group : groups) {
            if (group.getGroupNumber() == groupNumber)
                return group;
        }
        System.out.println("Can't find the group");
        return null;
    }

    private University getUniversity(String nameOfUniversity) {
        universities = dataBaseUniversity.readData("University.txt");

        for (University university : universities) {
            if (university.getNameOfUniversity().equals(nameOfUniversity))
                return university;
        }
        System.out.println("Can't find the university");
        return null;
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
