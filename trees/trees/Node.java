package trees;

/** Basic binary tree node implementation
 * Uses Integer for value to be able to have a null value to diferentiate it from a value of 0
 */

public class Node {

  private Integer value;
  private Node rightNode;
  private Node leftNode;

  public Node() {}

  public Node(Integer value) {
    this.value = value;
  }

  public void assignValue(Integer value) {
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

  public Integer getValue() {
    return this.value;
  }

  public Node getRightNode() {
    return this.rightNode;
  }

  public Node getLeftNode() {
    return this.leftNode;
  }
}