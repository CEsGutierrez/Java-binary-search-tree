package trees;

public class Tree {
  private Node root;

  public Tree() {
    System.out.println("New tree instantiated");
  }

  public Tree(Node rootNode) {
    this.root = rootNode;
    System.out.println("New tree instantiated, root: " + rootNode + " with value: " + rootNode.getValue());
  }

  public Node getRoot() {
    return this.root;
  }

  public void assignRoot(Node rootNode) {
    this.root = rootNode;
  }

  // add node

  // search for node

  // includes node

  // get depth

  // remove node
}
