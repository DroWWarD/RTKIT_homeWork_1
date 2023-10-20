public class StudentAgeDataGroupImpl implements StudentAgeDataGroup{
    private Node[] nodes = new Node[10];
    @Override
    public void addStudent(Student student) {
        int age = student.getAge();
        if (age >= nodes.length){
            Node[] newNodes = new Node[age+1];
            System.arraycopy(nodes, 0, newNodes, 0, nodes.length);
            nodes = newNodes;
        }else {
            if (nodes[age] == null){
                nodes[age] = new Node(student, null);
            }else {
                Node newNodeToBucket = new Node(student, null);
                Node lastNode = nodes[age].getNextNode();
                if (lastNode == null){
                    nodes[age].setNextNode(newNodeToBucket);
                }else {
                    while (lastNode.getNextNode() != null){
                        lastNode = lastNode.getNextNode();
                    }
                    lastNode.setNextNode(newNodeToBucket);
                }
            }
        }
    }

    @Override
    public Student[] getStudents(int age) {
        int nodesCount = 0;
        if (nodes[age] != null){
            nodesCount++;
            Node nextNode = nodes[age].getNextNode();
            while (nextNode != null) {
                nodesCount++;
                nextNode = nextNode.getNextNode();
            }
        }
        if (nodesCount == 0){
            return new Student[0];
        }
        Student[] students = new Student[nodesCount];
        students[0] = nodes[age].getStudent();
        if (nodesCount == 1) return students;
        Node nextNode = nodes[age].getNextNode();
        for (int i = 1; i < students.length; i++) {
            students[i] = nextNode.getStudent();
            nextNode = nextNode.getNextNode();
        }
        return students;
    }

    @Override
    public Student[] getStudentsElderThen(int age) {
        Student[] resultSet = new Student[0];
        for (int i = age; i < nodes.length; i++) {
            Student[] students = getStudents(i);
            Student[] tempResultSet = resultSet;
            resultSet = new Student[resultSet.length + students.length];
            System.arraycopy(tempResultSet, 0, resultSet, 0, tempResultSet.length);
            System.arraycopy(students, 0, resultSet, tempResultSet.length, students.length);
        }
        return resultSet;
    }
}
