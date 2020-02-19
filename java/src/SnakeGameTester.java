//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

// what kinds of tests? do we test how many checks they made in each too?
// Do some check the number of recursive/exhaustive checks?

public class SnakeGameTester {
  // This is a test to check that the exhaustive test can perform its basic functions.
  @Test
  public void testFindTailExhaustive1() {
    boolean[][] inputArray = {
        {false, false, false, false},
        {false, true, false, false},
        {false, true, false, false},
        {false, true, false, false},
        {false, false, false, false}};

    int[] expectedArray = {3, 1, 3};

    SnakeGame testOne = new SnakeGame(inputArray, 1, 1);

//    for (int i = 0; i < expectedArray.length; i++) {
//      System.out.print(testOne.findTailExhaustive()[i] + " ");
//    }
//    System.out.println();
//    System.out.print(testOne.getExhaustiveChecks() + " ");

    for (int i = 0; i < testOne.findTailExhaustive().length; i++) {
      assertEquals(expectedArray[i], testOne.findTailExhaustive()[i]);
    }
  }

  // This test is to check of the exhaustive method works in arrays where the snake is not just in a straight line.
  // This test passed, so the exhaustive method works in finding the tail with varying shapes.
  @Test
  public void testFindTailExhaustive2() {
    boolean[][] inputArray = {
        {false, false, true, true},
        {false, true, true, false},
        {false, true, false, false},
        {false, true, true, true},
        {false, false, false, true}};

    int[] expectedArray = {4, 3, 9};

    SnakeGame testOne = new SnakeGame(inputArray, 0, 3);

    for (int i = 0; i < expectedArray.length; i++) {
      System.out.print(testOne.findTailExhaustive()[i] + " ");
    }

    for (int i = 0; i < testOne.findTailExhaustive().length; i++) {
      assertEquals(expectedArray[i], testOne.findTailExhaustive()[i]);
    }
  }

  @Test
  public void testFindTailRecursive1() {
    boolean[][] inputArray = {
        {true, false, false, false},
        {true, false, false, false},
        {true, false, false, false},
        {false, false, false, false},
        {false, false, false, false}};

    int[] expectedArray = {2, 0, 6};
    int[] a = {0, 0};

    SnakeGame testOne = new SnakeGame(inputArray, 0, 0);


    for (int i = 0; i < expectedArray.length; i++) {
      System.out.print(testOne.findTailRecursive()[i] + " ");
    }
//    for (int i = 0; i < testOne.findTailExhaustive().length; i++) {
//      assertEquals(expectedArray[i], testOne.findTailRecursive()[i]);
//    }
  }

  /*
  // This test is to make sure that
  @Test
  public void testFindTailExhaustive3() {
    boolean[][] inputArray = {
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true},
        {true, true, true, true, true}};

    int[] expectedArray = {4, 3, 9};

    SnakeGame testOne = new SnakeGame(inputArray, 0, 3);

    for (int i = 0; i < expectedArray.length; i++) {
      System.out.print(testOne.findTailExhaustive()[i] + " ");
    }

    for (int i = 0; i < testOne.findTailExhaustive().length; i++) {
      assertEquals(expectedArray[i], testOne.findTailExhaustive()[i]);
    }
  } */
}