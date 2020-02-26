import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SnakeGameTester {
  // This is a test to check that the exhaustive test can perform its basic functions and returns the correct array.
  // This array includes the row, column, and length.
  // The true values are arranged in a line in order to avoid any error that could arise if the snake was in varying shapes.
  // The true values were surrounded by false's in order to test the if-statement that does not include the edge of the array.
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

  // This test is to check of the exhaustive method works in returning the correct row value.
  // This is important because it makes sure that the tail is actually being found.
  // The snake was arranged in a shape other than straight in order to make sure that the program can handle varying shapes.
  // The head, as well as the tail, were placed on the right edge of the array in order to check if the boundaries are well-checked in the program.
  // This test passed, so the exhaustive method works in finding the row value.
  @Test
  public void testFindTailExhaustive2() {
    boolean[][] inputArray = {
        {false, false, true, true},
        {false, true, true, false},
        {false, true, false, false},
        {false, true, true, true},
        {false, false, false, true}};

    SnakeGame testTwo = new SnakeGame(inputArray, 0, 3);
    assertEquals(4, testTwo.findTailExhaustive()[0]);
  }

  // This test is to check of the exhaustive method works in returning the correct column value.
  // This test is important in order to make sure that the tail is successfully being located.
  // This test uses a bigger array in order to make sure that the program can handle different sized arrays.
  // The head was placed in the center of the array and the tail was placed at the edge of the array.
  // The tail was placed on the left edge in order to make sure that the program handles values in the left edge correctly.
  // This is in order to check if the method can handle both of them entering different if-statements referring to their boundaries.
  // This test passed, so the exhaustive method works in finding the column value of the tail.
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

    SnakeGame testThree = new SnakeGame(inputArray, 2, 7);
    assertEquals(0, testThree.findTailExhaustive()[1]);
  }

  // This test is to check of the exhaustive method works in returning the correct length of the snake.
  // This is important because the program should continue iterating even after the tail is found in order to find the length.
  // This test case uses the same array as the previous test in order to make sure that the method can find both the tail and the length.
  // This is to avoid the case where the method only works with certain arrays.
  // This test case makes sure that this mechanism is actually taking place.
  // This test passed, so the exhaustive method works in finding the length of the snake.
  @Test
  public void testFindTailExhaustive4() {
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

    SnakeGame testFour = new SnakeGame(inputArray, 2, 7);

    assertEquals(17, testFour.findTailExhaustive()[2]);
  }

  // This test is to check that the checks for the exhaustive method output the correct value.
  // This test case is important because it is important to make sure that the checks do not iterate once the tail is found.
  // The head, as well as the tail, were placed on edge positions because those are special locations checked in order to avoid going out of bounds.
  // This makes sure that the top left corner and right edge are being handled correctly.
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

  // This test is to check of the exhaustive method works in the smallest possible size.
  // This test is important because there needs to be a mechanism to cover this case.
  // This test failed at first because the code made sure that the head position was not the tail position.
  // This was fixed by adding a special case scenario.
  @Test
  public void testFindTailExhaustive6() {
    boolean[][] inputArray = {
        {true}};

    int[] expectedArray = {0, 0, 1};

    SnakeGame testSix = new SnakeGame(inputArray, 0, 0);

    for (int i = 0; i < expectedArray.length; i++) {
      assertEquals(expectedArray[i], testSix.findTailExhaustive()[i]);
    }
  }

  // This test is to make sure that the recursive method can correctly find the tail and length of a simple given array.
  // This test is important because it is important to make sure that the returned array matches what is expected.
  // The array is placed on the top left in order to make sure that the top left corner and the left edge is being handled correctly.
  // This test did not pass at first because it was incrementing the length and recursive checks too many times.
  // This was fixed by altering the public recursive checks method and returning the array returned by the private method directly rather than copying it and then returning it.
  // This test was successful, so the recursive method works at finding the tail indexes.
  @Test
  public void testFindTailRecursive1() {
    boolean[][] inputArray = {
        {true, false},
        {true, false},
        {true, false},
        {false, false},
        {false, false}};

    int[] expectedArray = {2, 0, 3};
    int[] a = {0, 0};

    SnakeGame testOne = new SnakeGame(inputArray, 0, 0);

    testOne.findTailRecursive();

    for (int i = 0; i < testOne.findTailExhaustive().length; i++) {
      assertEquals(expectedArray[i], testOne.findTailRecursive()[i]);
    }
  }

  // This test is to make sure that the recursive method works in finding the row value of the tail.
  // This test is important because it is necessary to know if the tail is being found successfully.
  // The tail was placed in the bottom left corner, which makes sure that the left edge and bottom edge are being handled correctly.
  // This will test that it is correctly iterating through the whole array and not just one row/column.
  // This test was successful, so varying shapes of the snake are also being handled correctly and the row value is correctly found.
  @Test
  public void testFindTailRecursive2() {
    boolean[][] inputArray = {
        {true, false, false, false, false, false},
        {true, false, false, false, false, true},
        {true, true, true, true, false, true},
        {true, false, false, true, true, true},
        {true, false, false, false, false, false}};

    SnakeGame testTwo = new SnakeGame(inputArray, 1, 5);

    assertEquals(0, testTwo.findTailRecursive()[0]);

  }

  // This test is to make sure that the column value is returned successfully by the recursive method.
  // This test is important because it is important to know that the tail is being successfully found.
  // This test uses a different sized array in order to make sure that it can be handled by the program.
  // The head was placed on the left edge and the snake zig-zags throughout the array, which helps check all edges except for the top.
  // These edges are all separate if-statements, so it is important to know that they are each being executed appropriately.
  // This test was successful, which means that the column value is successfully found by the recursive method.
  @Test
  public void testFindTailRecursive3() {
    boolean[][] inputArray = {
        {false, false, false},
        {true, false, false},
        {true, true, false},
        {false, true, false},
        {true, true, false},
        {true, false, false},
        {true, true, true},
        {false, false, true},
        {false, false, true},
        {true, true, true},
        {true, false, false},
        {true, true, false},
        {false, true, false}};

    SnakeGame testThree = new SnakeGame(inputArray, 1, 0);

    assertEquals(1, testThree.findTailRecursive()[1]);
  }

  // ***************************
  // This test is to make sure that the recursive method is returning the correct length of the snake.
  // This test is important because it is important to know that the length is iterating correctly for each cell that is examined.
  // This test was not working at first, but it was fixed by deleting an extra call in the public recursive method.
  @Test
  public void testFindTailRecursive5() {
    boolean[][] inputArray = {
        {false, true, false, false},
        {false, true, false, false},
        {false, true, true, false},
        {false, false, true, false}};

    SnakeGame testFive = new SnakeGame(inputArray, 0, 1);

    assertEquals(5, testFive.findTailRecursive()[2]);
  }

  // This test is to make sure that the recursive checks are iterating as expected.
  // This test is important because the checks should be iterating for every cell checked and not for additional or less times.
  // This test failed at first due to an over-count, which was fixed by removing an extra call to the method.
  // This test ended up being successful after this extra called was removed.

  @Test
  public void testFindTailRecursive4() {
    boolean[][] inputArray = {
        {false, true, false, false},
        {false, true, false, false},
        {false, true, true, false},
        {false, false, true, false}};

    SnakeGame testFour = new SnakeGame(inputArray, 0, 1);

    testFour.findTailRecursive();

    assertEquals(5, SnakeGame.getRecursiveChecksPublic());
  }

  // This test is to make sure that the smallest possible array is being taken into account.
  // This test is important because it is good for the program to take into consideration all possible arrays.
  // This test was at first unsuccessful due to an out-of-bounds exception, which was fixed by creating a separate if-conditional.
  // This conditional was placed in the public recursive method in order to avoid that step being executed in each recursive call.

  @Test
  public void testFindTailRecursive6() {
    boolean[][] inputArray = {
        {true}};

    int[] expectedArray = {0, 0, 1};
    int[] a = {0, 0};

    SnakeGame testSix = new SnakeGame(inputArray, 1, 0);

    for (int i = 0; i < testSix.findTailExhaustive().length; i++) {
      assertEquals(expectedArray[i], testSix.findTailRecursive()[i]);
    }
  }
}