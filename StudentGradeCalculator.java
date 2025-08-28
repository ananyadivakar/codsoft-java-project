public class StudentGradeCalculator {
    public static void main(String[] args) {
        int[] marks = {85, 90, 78, 92, 88}; // Example marks
        int total = 0;

        for (int mark : marks) {
            total += mark;
        }

        double average = (double) total / marks.length;
        String grade;

        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("Total Marks: " + total);
        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
    }
}
