//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

// ******************* what kinds of tests? do we test how many checks they made in each too?
// ******************** Do some check the number of recursive/exhaustive checks?

public class SnakeGameTester {
  // This is a test to check that the exhaustive test can perform its basic functions.
  // A simple linear snake is used to avoid any confounding error in the code.
  // This test passed, so the exhaustive method works in finding the tail when the snake is a straight line.
  @Test
  public void testFindTailExhaustive1() {
    boolean[][] inputArray = {
        {false, false, false, false},
        {false, true, false, false},
        {false, true, false, false},
        {false, true, false, false},
        {false, false, false, false}};

    // Expected array found manually.
    // Array in the order of row, col, length.
    int[] expectedArray = {3, 1, 3};

    // Create an instance of the Snake Game.
    SnakeGame testOne = new SnakeGame(inputArray, 1, 1);

    for (int i = 0; i < expectedArray.length; i++) {
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

    SnakeGame testTwo = new SnakeGame(inputArray, 0, 3);

//    for (int i = 0; i < expectedArray.length; i++) {
//      System.out.print(testTwo.findTailExhaustive()[i] + " ");
//    }

    for (int i = 0; i < expectedArray.length; i++) {
      assertEquals(expectedArray[i], testTwo.findTailExhaustive()[i]);
    }
  }

  // This test is to check of the exhaustive method works in arrays of differing sizes.
  // This test passed, so the exhaustive method works in finding the tail with arrays of varying sizes.
  @Test
  public void testFindTailExhaustive3() {
    boolean[][] inputArray = {
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false},
        {false, true, true, true, false, false, true, true},
        {true, true, false, true, false, false, true, false},
        {false, false, false, true, true, false, true, false},
        {false, false, false, false, true, false, true, false},
        {false, false, false, false, true, true, true, false},
        {false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false}};

    int[] expectedArray = {3, 0, 17};

    SnakeGame testThree = new SnakeGame(inputArray, 2, 7);

//        for (int i = 0; i < expectedArray.length; i++) {
//      System.out.print(testThree.findTailExhaustive()[i] + " ");
//    }

    for (int i = 0; i < expectedArray.length; i++) {
      assertEquals(expectedArray[i], testThree.findTailExhaustive()[i]);
    }
  }

  // This test is to check of the exhaustive method works in the smallest possible size.
  // This test failed at first because the code made sure that the head position was not the tail position.
  // This was fixed by adding a special case scenario.
  @Test
  public void testFindTailExhaustive4() {
    boolean[][] inputArray = {
        {true}};

    int[] expectedArray = {0, 0, 1};

    SnakeGame testFour = new SnakeGame(inputArray, 0, 0);

//    for (int i = 0; i < expectedArray.length; i++) {
//      System.out.print(testFour.findTailExhaustive()[i] + " ");
//    }

    for (int i = 0; i < expectedArray.length; i++) {
      assertEquals(expectedArray[i], testFour.findTailExhaustive()[i]);
    }
  }

  // This test is to check that the checks for the exhaustive method output the correct value.
  // This test failed at first because my code only iterated exhaustive checks if the value at the indexes was true.
  // This error was fixed and the test passed by iterating if the tail was not found.
  @Test
  public void testFindTailExhaustive5() {
    boolean[][] inputArray = {
        {true, true, false},
        {false, true, false},
        {false, true, true},
        {false, false, false}};

    SnakeGame testFive = new SnakeGame(inputArray, 0, 0);
    testFive.findTailExhaustive();
    assertEquals(9, SnakeGame.getExhaustiveChecksPublic());
  }

  @Test
  public void testFindTailRecursive1() {
    boolean[][] inputArray = {
        {true, false},
        {true, false},
        {true, false},
        {true, false},
        {false, false}};

    int[] expectedArray = {2, 0, 3};
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