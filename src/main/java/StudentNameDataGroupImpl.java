public class StudentNameDataGroupImpl implements StudentNameDataGroup {
    private final Node[] nodes = new Node[80];

    @Override
    public void addStudent(Student student) {
        int letter = student.getFamily().charAt(0) - 1025;
        if (nodes[letter] == null) {
            nodes[letter] = new Node(student, null);
        } else {
            Node newNodeToBucket = new Node(student, null);
            Node lastNode = nodes[letter].getNextNode();
            if (lastNode == null) {
                nodes[letter].setNextNode(newNodeToBucket);
            } else {
                while (lastNode.getNextNode() != null) {
                    lastNode = lastNode.getNextNode();
                }
                lastNode.setNextNode(newNodeToBucket);
            }
        }
    }

    @Override
    public Student[] getStudents(String firstLetter) {
        int letter = firstLetter.charAt(0) - 1025;
        int nodesCount = 0;
        if (nodes[letter] != null) {
            nodesCount++;
            Node nextNode = nodes[letter].getNextNode();
            while (nextNode != null) {
                nodesCount++;
                nextNode = nextNode.getNextNode();
            }
        }
        if (nodesCount == 0) {
            return new Student[0];
        }
        Student[] students = new Student[nodesCount];
        students[0] = nodes[letter].getStudent();
        if (nodesCount == 1) return students;
        Node nextNode = nodes[letter].getNextNode();
        for (int i = 1; i < students.length; i++) {
            students[i] = nextNode.getStudent();
            nextNode = nextNode.getNextNode();
        }
        return students;
    }

    @Override
    public Student getStudentByFamily(String family) {
        Student[] studentsOnSameLetter = getStudents(family.substring(0, 1));
        for (int i = 0; i < studentsOnSameLetter.length; i++) {
            if (studentsOnSameLetter[i].getFamily().equals(family)) {
                return studentsOnSameLetter[i];
            }
        }
        return null;
    }
}