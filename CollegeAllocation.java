import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CollegeAllocation {

    private static final int COLLEGES = 20;
    private static final int BRANCHES = 5;
    private static final int SEATS = 60;
    private static final int PREFERENCES = 3;
    private static final int MAX_LEN = 50;

    static class Student {
        int rank;
        String name;
        int[] preferences;

        public Student(int rank, String name, int[] preferences) {
            this.rank = rank;
            this.name = name;
            this.preferences = preferences;
        }
    }

    public static void allocateSeats(Student[] students, int[][] collegeAllotment) {
        int allotted;
        for (Student student : students) {
            allotted = 0;
            for (int j = 0; j < PREFERENCES && allotted < SEATS; j++) {
                for (int k = 0; k < COLLEGES * BRANCHES && allotted < SEATS; k++) {
                    if (collegeAllotment[k / BRANCHES][k % BRANCHES] == -1) {
                        collegeAllotment[k / BRANCHES][k % BRANCHES] = student.rank;
                        allotted++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Student[] students = null;
        int[][] collegeAllotment = new int[COLLEGES][BRANCHES];
        BufferedReader reader = null;
        String line = null;

        try {
            reader = new BufferedReader(new FileReader("input1.csv"));

            int n = 0;
            while ((line = reader.readLine()) != null) {
                n++;
            }

            students = new Student[n];
            reader.close();

            reader = new BufferedReader(new FileReader("input1.csv"));
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                String[] parts = line.split(",");
                int rank = Integer.parseInt(parts[0]);
                String name = parts[1];
                int[] preferences = new int[PREFERENCES];
                for (int j = 0; j < PREFERENCES; j++) {
                    preferences[j] = Integer.parseInt(parts[j+2]);
                }
                students[i] = new Student(rank, name, preferences);
            }
            reader.close();

            for (int i = 0; i < COLLEGES; i++) {
                for (int j = 0; j < BRANCHES; j++) {
                    collegeAllotment[i][j] = -1;
                }
            }

            allocateSeats(students, collegeAllotment);

            System.out.println("Allocation of Seats:");
            for (int i = 0; i < COLLEGES; i++) {
                for (int j = 0; j < BRANCHES; j++) {
                    System.out.printf("College %d Branch %d: %d\n", i + 1, j + 1, collegeAllotment[i][j]);
                }
            }

        } catch (IOException e) {
            System.out.println("Error opening file");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}