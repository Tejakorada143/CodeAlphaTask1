import java.util.ArrayList;
import java.util.Scanner;
public class codealphatask1 {
    public static void main(String[] args) {
        ArrayList<Double> grades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Enter the number of grades
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();

        // Collect grades
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("Enter grade for student " + (i + 1) + ": ");
            while (true) {
                try {
                    double grade = scanner.nextDouble();
                    grades.add(grade);
                    break; // Exit the loop if input is valid
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid grade.");
                    scanner.next(); // Clear the invalid input
                }
            }
        }

        // Calculate average, highest, and lowest
        if (!grades.isEmpty()) {
            double total = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);

            for (double grade : grades) {
                total += grade;
                if (grade > highest) {
                    highest = grade;
                }
                if (grade < lowest) {
                    lowest = grade;
                }
            }

            double average = total / grades.size();

            // Output results
            System.out.printf("Average grade: %.2f%n", average);
            System.out.printf("Highest grade: %.2f%n", highest);
            System.out.printf("Lowest grade: %.2f%n", lowest);
        } else {
            System.out.println("No grades entered.");
        }

        scanner.close();
    }
}
