package tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import trees.Node;
import trees.Tree;

import static org.junit.Assert.assertEquals;

public class TreeTest {


  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Test
  public void instantiatedEmpty() {
    Tree testTree = new Tree();
    assertEquals(null, testTree.getRoot());
  }

  @Test
  public void instantiatedWithRoot() {
    Node rootNode = new Node();
    Tree testTree = new Tree(rootNode);

    assertEquals(rootNode, testTree.getRoot());
  }

  @Test
  public void assignRoot() {
    Node rootNode = new Node();
    Tree testTree = new Tree();

    testTree.assignRoot(rootNode);

    assertEquals(rootNode, testTree.getRoot());
  }

  @Test
  public void reassignRoot() {
    Node initialNode = new Node();
    Node assignedNode = new Node();
    Tree testTree = new Tree(initialNode);

    testTree.assignRoot(assignedNode);

    assertEquals(assignedNode, testTree.getRoot());
  }

  @Test
  public void addNode() throws Exception {
    createTestTree();

  }

  private Tree createTestTree() throws Exception {
    Node root = new Node(0);
    Node right = new Node (5);
    Node left = new Node (-5);

    Tree tree = new Tree(root);
    tree.getRoot().assignLeft(left);
    tree.getRoot().assignRight(right);

    return tree;
  }

}
