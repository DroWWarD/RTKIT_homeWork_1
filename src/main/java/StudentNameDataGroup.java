public interface StudentNameDataGroup {
    public void addStudent(Student student);
    public Student[] getStudents(String firstLetter);
    public Student getStudentByFamily(String family);
}
