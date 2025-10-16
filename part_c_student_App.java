import java.util.*;
public class part_c_student_App {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            part_c_student_DAO dao = new part_c_student_DAO();
            int choice;

            do {
                System.out.println("\n--- Student Management ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Marks");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID, Name, Department, Marks: ");
                        int id = sc.nextInt();
                        String name = sc.next();
                        String dept = sc.next();
                        double marks = sc.nextDouble();
                        dao.addStudent(new part_c_student(id, name, dept, marks));
                        System.out.println("Student Added!");
                        break;

                    case 2:
                        dao.viewStudents();
                        break;

                    case 3:
                        System.out.print("Enter ID and new Marks: ");
                        int sid = sc.nextInt();
                        double newMarks = sc.nextDouble();
                        dao.updateStudent(sid, newMarks);
                        System.out.println("Updated Successfully!");
                        break;

                    case 4:
                        System.out.print("Enter ID to Delete: ");
                        int delId = sc.nextInt();
                        dao.deleteStudent(delId);
                        System.out.println("Deleted Successfully!");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
