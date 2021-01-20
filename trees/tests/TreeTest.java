package tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import trees.Node;
import trees.Tree;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
  public void viewTree() throws Exception {
    Tree testTree = createTestTree();
    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(0);
      add(-5);
      add(5);
    }};
    assertEquals(expectedList, testTree.view());
  }

  @Test
  public void addNode() throws Exception {
    Tree testTree = createTestTree();
    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(0);
      add(-5);
      add(5);
      add(2);
    }};

    Node newNode = new Node(2);
    testTree.add(newNode);

    assertEquals(expectedList, testTree.view());
  }

  @Test
  public void findsIncludedNode() throws Exception {
    Tree testTree = createTestTree();
    Node expectedNode = testTree.getRoot().getRightNode();

    assertTrue(testTree.includes(expectedNode));
  }

  @Test
  public void findsIncludedValue() throws Exception {
    Tree testTree = createTestTree();
    assertTrue(testTree.includes(5));
  }

  @Test
  public void doesNotFindExcludedNode() throws Exception {
    Tree testTree = createTestTree();
    Node unexpectedNode = new Node(1);

    assertFalse(testTree.includes(unexpectedNode));
  }

  @Test
  public void doesNotFindExcludedValue() throws Exception {
    Tree testTree = createTestTree();
    assertFalse(testTree.includes(2));
  }

  @Test
  public void throwsExceptionIfSearchingEmptyNode() throws Exception {
    thrown.expect(Exception.class);

    Tree testTree = createTestTree();
    Node unexpectedNode = new Node();

    testTree.includes(unexpectedNode);
  }

  @Test
  public void findsNullValue() throws Exception {
    thrown.expect(Exception.class);

    Tree testTree = createTestTree();
    Integer nullInt = null;

    testTree.includes(nullInt);
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
