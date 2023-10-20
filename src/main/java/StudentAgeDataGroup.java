public interface StudentAgeDataGroup {
    public void addStudent(Student student);
    public Student[] getStudents(int age);
    public Student[] getStudentsElderThen(int age);
}
