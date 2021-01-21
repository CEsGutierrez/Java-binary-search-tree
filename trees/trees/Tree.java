package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic binary search tree implementation
 * */

public class Tree {
  private Node root;

  public Tree() {}

  public Tree(Node rootNode) {
    this.root = rootNode;
  }

  public Node getRoot() {
    return this.root;
  }

  public void assignRoot(Node rootNode) {
    this.root = rootNode;
  }

  public void add(Node newNode) throws Exception {
    if (newNode.getValue().equals(null)) { // can't add a node without a value
      return;
    }

    if (root == null) {
      assignRoot(newNode);
      return;
    }

    List<Node> queue = new ArrayList<>();
    queue.add(root);
    Integer targetValue = newNode.getValue(); // used for legibility

    while (queue.size() > 0) {
      Node temp = queue.remove(0);
      Integer tempValue = temp.getValue();

      // value already there, no action required
      if (tempValue.equals(targetValue)) {
        return;
      } else if (targetValue > tempValue) {
        if (temp.getRightNode() != null) {
          queue.add(temp.getRightNode());
        } else {
          temp.assignRight(newNode);
          return;
        }
      } else { // implied that targetValue < tempValue
        if (temp.getLeftNode() != null) {
          queue.add(temp.getLeftNode());
        } else {
          temp.assignLeft(newNode);
          return;
        }
      }
    }
  }

  public boolean includes (Integer targetValue) throws Exception {
    if (targetValue == null) {
      throw new Exception (
        "Cannot search for null value");
    }

    List<Node> queue = new ArrayList<>();
    queue.add(root);

    while (queue.size() > 0) {
      Node temp = queue.remove(0);
      Integer tempValue = temp.getValue();

      // value already there, no action required
      if (tempValue.equals(targetValue)) {
        return true;
      } else if (targetValue > tempValue) {
        if (temp.getRightNode() != null) {
          queue.add(temp.getRightNode());
        } else {
          return false;
        }
      } else { // implied that targetValue < tempValue
        if (temp.getLeftNode() != null) {
          queue.add(temp.getLeftNode());
        } else {
          return false;
        }
      }
    }
    return false;
  }

  public boolean includes (Node targetNode) throws Exception {
    if (targetNode.getValue() != null) {
      Integer targetValue = targetNode.getValue();
      return includes(targetValue);
    } else {
      throw new Exception (
          "Cannot search for empty node");
    }

  }

  public int getDepth() {
    if (root == null) {
      return 0;
    } else {
      return getDepthHelper(root);
    }
  }

  // recursive helper method
  private int getDepthHelper(Node node) {
    if (node == null) {
      return 0;
    }

    int leftDepth = getDepthHelper(node.getLeftNode());
    int rightDepth = getDepthHelper(node.getRightNode());

    if (leftDepth > rightDepth) {
      return (leftDepth + 1);
    } else {
      return (rightDepth + 1);
    }
  }

  private void removeChildNode(Node parentNode, String direction) throws Exception {

    Node targetNode;
    if (direction == "RIGHT") {
      targetNode = parentNode.getRightNode();
    } else {
      targetNode = parentNode.getLeftNode();
    }

    // it's a leaf, can be safely deleted
    if (targetNode.getLeftNode() == null && targetNode.getRightNode() == null) {
      if (direction == "RIGHT") {
        parentNode.assignRight(null);
      } else {
        parentNode.assignLeft( null);
      }
      return;
    }

    // if it has one child, substitute
    if(targetNode.getLeftNode() == null || targetNode.getRightNode() == null) {

      // L side
      if (parentNode.getLeftNode().equals(targetNode) && targetNode.getLeftNode() != null) {
        parentNode.assignLeft(targetNode.getLeftNode());
        targetNode.assignLeft(null);
        return;
      } else if (parentNode.getLeftNode().equals(targetNode)) {
        parentNode.assignLeft(targetNode.getRightNode());
        targetNode.assignRight(null);
        return;
      }

      // R side
      else if (targetNode.getLeftNode() != null) {
        parentNode.assignRight(targetNode.getLeftNode());
        targetNode.assignLeft(null);
        return;
      } else {
        parentNode.assignRight(targetNode.getRightNode());
        targetNode.assignRight(null);
        return;
      }
    } else {
      // it has two children, use helper
      removeNodeTwoChildren(targetNode, direction);
    }
  }

  // helper method that substitutes target node with the  minimum value from right sub tree
  //explanation: http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/BST-delete2.html
  private void removeNodeTwoChildren(Node targetNode, String side) throws Exception {
    Node successor = targetNode.getRightNode();
    Node parentOfSuccessor = targetNode;

    while (successor.getLeftNode() != null) {
      parentOfSuccessor = successor;
      successor = successor.getLeftNode();
    }

    targetNode.assignValue(successor.getValue());

    if (side.equals("RIGHT")) {
      parentOfSuccessor.assignLeft(null);
    } else {
      parentOfSuccessor.assignRight(null);
    }
    return;
  }

  public void removeNode(Integer targetValue) throws Exception {
    if (targetValue == null) {
      throw new Exception (
          "Cannot search for null value");
    }

    if (targetValue == root.getValue()) {
      removeNodeTwoChildren(root, "RIGHT");
    }

    List<Node> queue = new ArrayList<>();
    queue.add(root);

    while (queue.size() > 0) {
      Node temp = queue.remove(0);

      if (temp.getLeftNode() != null && temp.getLeftNode().getValue() != null) {
        if (targetValue == temp.getLeftNode().getValue()) {
          removeChildNode(temp, "LEFT");
        } else {
            queue.add(temp.getLeftNode());
        }
      }
      if (temp.getRightNode() != null && temp.getRightNode().getValue() != null) {
        if (targetValue == temp.getRightNode().getValue()) {
          removeChildNode(temp, "RIGHT");
        } else {
          queue.add(temp.getRightNode());
        }
      }
    }
  }

  /**
   *  level-order traversal
   * */

  public List<Integer> levelOrderView() {
    List<Integer> valuesCollection = new ArrayList<>();

    List<Node> queue = new ArrayList<>();
    queue.add(root);

    while (queue.size() > 0) {
      Node temp = queue.remove(0);
      if (temp.getLeftNode() != null) {
        queue.add(temp.getLeftNode());
      }
      if (temp.getRightNode() != null) {
        queue.add(temp.getRightNode());
      }
      valuesCollection.add(temp.getValue());
    }
    return valuesCollection;
  }

  /**
   * in-order traversal with recursive helper method
   * */

  public List<Integer> inorderView() {
    if (root.getValue() == null) {
      return new ArrayList<>();
    }
    return inOrderViewHelper(root, new ArrayList<Integer>());
  }

  /**
   * pre-order traversal with recursive helper method
   */

  private List<Integer> inOrderViewHelper(Node node, List<Integer> valuesCollection) {

    if (node == null) {
      return valuesCollection;
    }

    inOrderViewHelper(node.getLeftNode(), valuesCollection);

    valuesCollection.add(node.getValue());

    inOrderViewHelper(node.getRightNode(), valuesCollection);

    return valuesCollection;
  }

  public List<Integer> preorderView() {
    if (root.getValue() == null) {
      return new ArrayList<>();
    }
    return preOrderViewHelper(root, new ArrayList<Integer>());
  }

  private List<Integer> preOrderViewHelper(Node node, List<Integer> valuesCollection) {

    if (node == null) {
      return valuesCollection;
    }
    valuesCollection.add(node.getValue());

    preOrderViewHelper(node.getLeftNode(), valuesCollection);

    preOrderViewHelper(node.getRightNode(), valuesCollection);

    return valuesCollection;
  }

  /**
   * post-order traversal with recursive helper method
   */

  public List<Integer> postorderView() {
    if (root.getValue() == null) {
      return new ArrayList<>();
    }
    return postOrderViewHelper(root, new ArrayList<Integer>());
  }

  private List<Integer> postOrderViewHelper(Node node, List<Integer> valuesCollection) {

    if (node == null) {
      return valuesCollection;
    }

    postOrderViewHelper(node.getLeftNode(), valuesCollection);

    postOrderViewHelper(node.getRightNode(), valuesCollection);

    valuesCollection.add(node.getValue());

    return valuesCollection;
  }

}
