public class ClassroomDataGroupImpl implements ClassroomDataGroup {

    private Node[] nodes = new Node[12];

    @Override
    public void addStudent(Student student) {
        int group = student.getGroup();
        if (group >= nodes.length){
            Node[] newNodes = new Node[group+1];
            System.arraycopy(nodes, 0, newNodes, 0, nodes.length);
            nodes = newNodes;
        }else {
            if (nodes[group] == null){
                nodes[group] = new Node(student, null);
            }else {
                Node newNodeToBucket = new Node(student, null);
                Node lastNode = nodes[group].getNextNode();
                if (lastNode == null){
                    nodes[group].setNextNode(newNodeToBucket);
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
    public Student[] getStudents(int group) {
        int nodesCount = 0;
        if (nodes[group] != null){
            nodesCount++;
            Node nextNode = nodes[group].getNextNode();
            while (nextNode != null) {
                nodesCount++;
                nextNode = nextNode.getNextNode();
            }
        }
        if (nodesCount == 0){
            return new Student[0];
        }
        Student[] students = new Student[nodesCount];
        students[0] = nodes[group].getStudent();
        if (nodesCount == 1) return students;
        Node nextNode = nodes[group].getNextNode();
        for (int i = 1; i < students.length; i++) {
            students[i] = nextNode.getStudent();
            nextNode = nextNode.getNextNode();
        }
        return students;
    }
}
