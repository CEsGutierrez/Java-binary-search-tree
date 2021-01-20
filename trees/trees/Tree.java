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

  public List<Integer> view() {
    List<Integer> valueCollection = new ArrayList<>();

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
      valueCollection.add(temp.getValue());
    }
    return valueCollection;
  }

  public void add(Node newNode) throws Exception {
    if (newNode.getValue().equals(null)) { // can't add a node without a value
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


  // includes node

  // get depth

  // remove node

  // view entire tree level by level



}
