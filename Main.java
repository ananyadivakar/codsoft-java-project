import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private final String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name.trim();
        this.rollNumber = rollNumber.trim();
        this.grade = grade.trim();
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setGrade(String grade) {
        this.grade = grade.trim();
    }

    @Override
    public String toString() {
        return "📘 Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        // Don't call overridable methods in constructor
    }

    public void init() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(s -> s.getRollNumber().equalsIgnoreCase(rollNumber));
        saveToFile();
    }

    public void editStudent(String rollNumber, String newName, String newGrade) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                s.setName(newName);
                s.setGrade(newGrade);
                break;
            }
        }
        saveToFile();
    }

    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                students = (List<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading students.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.init();

        try (Scanner sc = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\n===== Student Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student");
                System.out.println("3. Remove Student");
                System.out.println("4. Search Student");
                System.out.println("5. Display All Students");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                while (!sc.hasNextInt()) {
                    System.out.print("❌ Invalid input. Enter a number: ");
                    sc.next();
                }

                choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter roll number: ");
                        String roll = sc.nextLine();
                        System.out.print("Enter grade: ");
                        String grade = sc.nextLine();

                        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                            System.out.println("❌ All fields are required!");
                        } else {
                            sms.addStudent(new Student(name, roll, grade));
                            System.out.println("✅ Student added.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter roll number to edit: ");
                        String roll = sc.nextLine();
                        Student s = sms.searchStudent(roll);
                        if (s == null) {
                            System.out.println("❌ Student not found.");
                        } else {
                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine();
                            System.out.print("Enter new grade: ");
                            String newGrade = sc.nextLine();
                            sms.editStudent(roll, newName, newGrade);
                            System.out.println("✅ Student updated.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter roll number to remove: ");
                        String roll = sc.nextLine();
                        sms.removeStudent(roll);
                        System.out.println("✅ Student removed (if existed).");
                    }
                    case 4 -> {
                        System.out.print("Enter roll number to search: ");
                        String roll = sc.nextLine();
                        Student s = sms.searchStudent(roll);
                        if (s == null) {
                            System.out.println("❌ Student not found.");
                        } else {
                            System.out.println("🔍 " + s);
                        }
                    }
                    case 5 -> sms.displayAllStudents();
                    case 6 -> System.out.println("👋 Exiting...");
                    default -> System.out.println("❌ Invalid choice.");
                }

            } while (choice != 6);
        }
    }
}
