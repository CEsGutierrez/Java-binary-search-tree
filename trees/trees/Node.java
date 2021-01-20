package trees;

public class Node {

  private int value;
  private Node rightNode;
  private Node leftNode;

  public Node() {
    System.out.println("New node instantiated");
  }

  public Node(int value) {
    this.value = value;
    System.out.println("New node instantiated with value: " + value);
  }

  public void assignValue(int value) {
    this.value = value;
  }

  public void assignRight(Node rightNode) throws Exception {
    if (this.leftNode != rightNode) {
      this.rightNode = rightNode;
    } else {
      throw new Exception (
          "This node was already assigned as left node" + rightNode
      );
    }

  }

  public void assignLeft(Node leftNode) throws Exception {
    if (this.rightNode != leftNode) {
      this.leftNode = leftNode;
    } else {
      throw new Exception (
          "This node was already assigned as right node" + leftNode
      );
    }

  }  

  public int getValue() {
    return this.value;
  }

  public Node getRightNode() {
    return this.rightNode;
  }

  public Node getLeftNode() {
    return this.leftNode;
  }
}