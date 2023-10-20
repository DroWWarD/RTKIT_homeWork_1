import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String studentsFile = "src/main/resources/students.csv";
    private static final int countSubjects = 6;
    private static final ClassroomDataGroup classRoomGroup = new ClassroomDataGroupImpl();
    private static final StudentAgeDataGroup studentAgeGroup = new StudentAgeDataGroupImpl();
    private static final StudentNameDataGroup studentNameGroup = new StudentNameDataGroupImpl();

    public static void main(String[] args) throws IOException {
        readStudentsFile();
        printAverageGrade(new int[] {10, 11});
        printExcellentStudentsElderThen(14);
        runSearchByFamily();
    }

    private static void runSearchByFamily() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------------\n" +
                "Для поиска учащегося по фамилии - введите ее в консоль:");
        String family = scanner.next();
        if (family.charAt(0) < 1025 || family.charAt(0) > 1105){
            System.out.println("Введите фамилию ученика, используя кириллицу!");
        }else {
            Student searchResult = studentNameGroup.getStudentByFamily(family);
            System.out.println(searchResult == null ? "Учащийся с фамилией " + family + " не найдем в списке" : searchResult);
        }
        scanner.close();
    }

    private static void printExcellentStudentsElderThen(int minAge) {
        Student[] students = studentAgeGroup.getStudentsElderThen(minAge);
        System.out.println("-------------------------------------------------------------------------------------\n" +
                "Отличники среди учеников старше " + minAge + " лет:");
        for (int i = 0; i < students.length; i++) {
            if (students[i].getPhysics() == 5 && students[i].getMathematics() == 5 &&
                    students[i].getRus() == 5 &&students[i].getLiterature() == 5 &&
                    students[i].getGeometry() == 5 &&students[i].getInformatics() == 5){
                System.out.println(students[i]);
            }
        }
    }

    private static void printAverageGrade(int[] classRooms) {
        String classes = "";
        Student[] resultSet = new Student[0];
        for (int i = 0; i < classRooms.length; i++) {
            Student[] students = classRoomGroup.getStudents(classRooms[i]);
            Student[] tempResultSet = resultSet;
            resultSet = new Student[resultSet.length + students.length];
            System.arraycopy(tempResultSet, 0, resultSet, 0, tempResultSet.length);
            System.arraycopy(students, 0, resultSet, tempResultSet.length, students.length);
            classes = classes + " " + classRooms[i];
        }
        long sumGrades = 0;
        for (int i = 0; i < resultSet.length; i++) {
            sumGrades = sumGrades + resultSet[i].getPhysics() + resultSet[i].getMathematics() +
                    resultSet[i].getRus() + resultSet[i].getLiterature() +
                    resultSet[i].getGeometry() + resultSet[i].getInformatics();
        }
        double averageGrade = (double) sumGrades / (double) countSubjects / (double) resultSet.length;
        String formattedDouble = String.format("%.2f", averageGrade);
        System.out.println("-------------------------------------------------------------------------------------\n" +
                "Средняя оценка по всем дисциплинам среди всех учеников " + classes + " классов = " + formattedDouble);
    }

    private static void readStudentsFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(studentsFile));
        while (br.ready()){
            String line = br.readLine();
            String[] splittedLine = line.split(";");
            if (!isInteger(splittedLine[2])){
                continue;
            }
            Student student = new Student(splittedLine[0], splittedLine[1],
                    Integer.parseInt(splittedLine[2]), Integer.parseInt(splittedLine[3]),
                    Integer.parseInt(splittedLine[4]),Integer.parseInt(splittedLine[5]),
                    Integer.parseInt(splittedLine[6]),Integer.parseInt(splittedLine[7]),
                    Integer.parseInt(splittedLine[8]),Integer.parseInt(splittedLine[9]));
            classRoomGroup.addStudent(student);
            studentAgeGroup.addStudent(student);
            studentNameGroup.addStudent(student);
        }
        br.close();
    }

    private static boolean isInteger(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
