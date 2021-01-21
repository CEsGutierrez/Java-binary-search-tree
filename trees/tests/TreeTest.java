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
    Tree testTree = createShortTestTree();
    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(0);
      add(-5);
      add(5);
    }};
    assertEquals(expectedList, testTree.levelOrderView());
  }

  @Test
  public void addNodeToEmptyTree() throws Exception {
    Tree testTree = new Tree();
    Integer expectedValue = 1;

    Node newRoot = new Node(expectedValue);
    testTree.add(newRoot);
    assertEquals(expectedValue, testTree.getRoot().getValue());
  }

  @Test
  public void addNode() throws Exception {
    Tree testTree = createShortTestTree();
    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(0);
      add(-5);
      add(5);
      add(2);
    }};

    Node newNode = new Node(2);
    testTree.add(newNode);

    assertEquals(expectedList, testTree.levelOrderView());
  }

  @Test
  public void addOrdersNodes() throws Exception {
    Tree testTree = new Tree();

    testTree.add(new Node (0));
    testTree.add(new Node (-5));
    testTree.add(new Node (2));
    testTree.add(new Node (5));
    testTree.add(new Node (1));

    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(0);
      add(-5);
      add(2);
      add(1);
      add(5);
    }};

    Node newNode = new Node(2);
    testTree.add(newNode);

    assertEquals(expectedList, testTree.levelOrderView());
  }

  @Test
  public void addIgnoresDuplicates() throws Exception {
    Tree testTree = new Tree();

    testTree.add(new Node (0));
    testTree.add(new Node (-5));
    testTree.add(new Node (2));
    testTree.add(new Node (2));
    testTree.add(new Node (2));
    testTree.add(new Node (2));

    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(0);
      add(-5);
      add(2);
    }};

    Node newNode = new Node(2);
    testTree.add(newNode);

    assertEquals(expectedList, testTree.levelOrderView());
  }

  @Test
  public void findsIncludedNode() throws Exception {
    Tree testTree = createShortTestTree();
    Node expectedNode = testTree.getRoot().getRightNode();

    assertTrue(testTree.includes(expectedNode));
  }

  @Test
  public void findsIncludedValue() throws Exception {
    Tree testTree = createShortTestTree();
    assertTrue(testTree.includes(5));
  }

  @Test
  public void doesNotFindExcludedNode() throws Exception {
    Tree testTree = createShortTestTree();
    Node unexpectedNode = new Node(1);

    assertFalse(testTree.includes(unexpectedNode));
  }

  @Test
  public void doesNotFindExcludedValue() throws Exception {
    Tree testTree = createShortTestTree();
    assertFalse(testTree.includes(2));
  }

  @Test
  public void throwsExceptionIfSearchingEmptyNode() throws Exception {
    thrown.expect(Exception.class);

    Tree testTree = createShortTestTree();
    Node unexpectedNode = new Node();

    testTree.includes(unexpectedNode);
  }

  @Test
  public void findsNullValue() throws Exception {
    thrown.expect(Exception.class);

    Tree testTree = createShortTestTree();
    Integer nullInt = null;

    testTree.includes(nullInt);
  }

  @Test
  public void getDepthEmpty() {
    Node root = new Node();
    Tree testTree = new Tree(root);
    int expectedDepth = 1;

    assertEquals(expectedDepth, testTree.getDepth());
  }

  @Test
  public void getDepthMultiLevel() throws Exception {
    Tree shortTree = createLongTestTree();
    Node newNode = new Node(2);
    shortTree.add(newNode);
    int expectedDepth = 4;

    assertEquals(expectedDepth, shortTree.getDepth());
  }

  @Test
  public void doesNotGetDepthTreeSansRoot() {
    int expectedDepth = 0;

    Tree testTree = new Tree();
    assertEquals(expectedDepth,testTree.getDepth());
  }

  @Test
  public void removesLeaf() throws Exception {
    List<Integer> expectedValues = new ArrayList<>() {{
      add(0);
      add(-5);
      add(5);
      add(-8);
      add(2);
      add(7);
      add(17);
    }};

    Tree testTree = createLongTestTree();
    testTree.removeNode(-1);
    assertEquals(expectedValues, testTree.levelOrderView());
  }

  @Test
  public void removesMiddleNode() throws Exception {
    List<Integer> expectedValues = new ArrayList<>() {{
      add(0);
      add(-1);
      add(5);
      add(-8);
      add(2);
      add(7);
      add(17);
    }};

    Tree testTree = createLongTestTree();
    testTree.removeNode(-5);
    assertEquals(expectedValues, testTree.levelOrderView());
  }

  @Test
  public void removeRootNode() throws Exception {

    List<Integer> expectedValues = new ArrayList<>() {{
      add(2);
      add(-5);
      add(5);
      add(-8);
      add(-1);
      add(7);
      add(17);
    }};

    Tree testTree = createLongTestTree();
    testTree.removeNode(0);
    assertEquals(expectedValues, testTree.levelOrderView());

  }

  @Test
  public void inOrderTraversal() throws Exception {
    Tree testTree = createLongTestTree();
    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(-8);
      add(-5);
      add(-1);
      add(0);
      add(2);
      add(5);
      add(7);
      add(17);

    }};
    assertEquals(expectedList, testTree.inorderView());
  }


  @Test
  public void preOrderTraversal() throws Exception {
    Tree testTree = createLongTestTree();
    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(0);
      add(-5);
      add(-8);
      add(-1);
      add(5);
      add(2);
      add(7);
      add(17);
    }};
    assertEquals(expectedList, testTree.preorderView());
  }

  @Test
  public void postOrderTraversal() throws Exception {
    Tree testTree = createLongTestTree();
    List<Integer> expectedList = new ArrayList<Integer>() {{
      add(-8);
      add(-1);
      add(-5);
      add(2);
      add(17);
      add(7);
      add(5);
      add(0);
    }};
    assertEquals(expectedList, testTree.postorderView());
  }


  private Tree createShortTestTree() throws Exception {
    Tree tree = new Tree() {
      {
        add(new Node(0));
        add(new Node(-5));
        add(new Node(5));
      }};

    return tree;
  }

  private Tree createLongTestTree() throws Exception {
    Tree tree = new Tree() {{
      add(new Node(0));
      add(new Node(-5));
      add(new Node(5));
      add(new Node(-8));
      add(new Node(-1));
      add(new Node(2));
      add(new Node(7));
      add(new Node(17));
    }};

    return tree;
  }
}
