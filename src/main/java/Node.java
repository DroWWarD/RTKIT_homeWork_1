public class Node {
    private Student student;
    private Node nextNode;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node(Student student, Node nextNode) {
        this.student = student;
        this.nextNode = nextNode;
    }

}
