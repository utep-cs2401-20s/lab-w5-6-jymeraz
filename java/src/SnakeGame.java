public class SnakeGame {
  private boolean[][] game;
  private int[] headPosition;
  private static int exhaustiveChecks;
  private static int recursiveChecks;
  // Initialize tail position here in order to be able to edit it in the recursive method.
  private int[] tailPosition;

  public SnakeGame(){
    game = new boolean[1][1];
  }

  public SnakeGame(boolean[][] inputArray, int row, int col){
    game = new boolean[inputArray.length][inputArray[0].length];

    for (int i = 0; i < inputArray.length; i++){
      for (int j = 0; j < inputArray[i].length; j++){
        game[i][j] = inputArray[i][j];
      }
    }

    headPosition = new int[2];
    headPosition[0] = row;
    headPosition[1] = col;
    tailPosition = new int[3];
  }

  public int[] findTailExhaustive() {
    // Reset the counter to equal to zero before checking through the array.
    resetCounters();
    int length = 0;
    tailPosition[0] = -1;
    tailPosition[1] = -1;
    boolean foundTail = false;

    // Check if the tail and the head are the same value.
    if(game.length == 1 && game[0].length == 1){
      // Iterate exhaustive checks.
      exhaustiveChecks++;
      tailPosition[0] = 0;
      tailPosition[1] = 0;
      tailPosition[2] = 1;
      // Return the tail position array.
      return tailPosition;
    }

    // Iterate through the array to find the tail.
    for(int i = 0; i < game.length; i++){
      for (int j = 0; j < game[i].length; j++) {
        // Only iterate exhaustive checks if the tail is not found.
        if(!foundTail){
          exhaustiveChecks++;
        }
        // If the snake exists, check if it is the tail and iterate the length.
        if (game[i][j]) {
          length++;
          if (!foundTail) {
            // Check that the position only has one neighbor and that it is not the head.
            if (i == 0 && j == 0) {
              if ((!game[i][j + 1] || !game[i + 1][j])
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else if (i == 0 && j == game[i].length - 1) {
              if ((!game[i][j - 1] || !game[i + 1][j])
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else if (i == 0) {
              if (((game[i][j - 1] && !game[i + 1][j] && !game[i][j + 1])
                      || (!game[i][j - 1] && game[i + 1][j] && !game[i][j + 1])
                      || (!game[i][j - 1] && !game[i + 1][j] && game[i][j + 1]))
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else if (i == game.length - 1 && j == 0) {
              if ((!game[i + 1][j] || !game[i][j + 1])
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else if (j == 0) {
              if (((game[i - 1][j] && !game[i][j + 1] && !game[i + 1][j])
                      || (!game[i - 1][j] && game[i][j + 1] && !game[i + 1][j])
                      || (!game[i - 1][j] && !game[i][j + 1] && game[i + 1][j]))
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else if (i == game.length - 1 && j == game[i].length - 1) {
              if ((!game[i - 1][j] || !game[i][j - 1])
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else if (j == game[i].length - 1) {
              if (((game[i - 1][j] && !game[i][j - 1] && !game[i + 1][j])
                      || (!game[i - 1][j] && game[i][j - 1] && !game[i + 1][j])
                      || (!game[i - 1][j] && !game[i][j - 1] && game[i + 1][j]))
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else if (i == game.length - 1) {
              if (((game[i][j - 1] && !game[i - 1][j] && !game[i][j + 1])
                  || (!game[i][j - 1] && game[i - 1][j] && !game[i][j + 1])
                  || (!game[i][j - 1] && !game[i - 1][j] && game[i][j + 1]))
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            } else {
              if (((game[i][j - 1] && !game[i - 1][j] && !game[i][j + 1] && !game[i + 1][j])
                      || (!game[i][j - 1] && game[i - 1][j] && !game[i][j + 1] && !game[i + 1][j])
                      || (!game[i][j - 1] && !game[i - 1][j] && game[i][j + 1] && !game[i + 1][j])
                      || (!game[i][j - 1] && !game[i - 1][j] && !game[i][j + 1] && game[i + 1][j]))
                  && (i != headPosition[0] || j != headPosition[1])) {
                tailPosition[0] = i;
                tailPosition[1] = j;
                foundTail = true;
              }
            }
          }
        }
      }
    }
    // Set the second index in the tail position equal to the length.
    tailPosition[2] = length;
    // Return the tail position array.
    return tailPosition;
  }

  public int[] findTailRecursive(){
    // Reset the counters.
    resetCounters();
    tailPosition[2] = 0;

    // Initialize two arrays with the same contents as headPosition to avoid overwriting to the headPosition array.
    int[] currentPosition = new int[2];
    int[] previousPosition = new int[2];

    for(int i = 0; i < headPosition.length; i++){
      currentPosition[i] = headPosition[i];
    }
    for(int i = 0; i < headPosition.length; i++){
      previousPosition[i] = headPosition[i];
    }

    // Check if the tail and the head are the same value.
    if(game.length == 1 && game[0].length == 1){
      // Iterate exhaustive checks.
      exhaustiveChecks++;
      tailPosition[0] = 0;
      tailPosition[1] = 0;
      tailPosition[2] = 1;
      // Return the tail position array.
      return tailPosition;
    }

    // Return the array returned by the recursive method.
    return findTailRecursive(currentPosition, previousPosition);
  }

  private int[] findTailRecursive(int[] currentPosition, int[] previousPosition){
    // Iterate recursive Checks and increase the length by 1.
    recursiveChecks++;
    tailPosition[2]++;
    int rowCurrent = currentPosition[0];
    int colCurrent = currentPosition[1];

    int rowPrevious = previousPosition[0];
    int colPrevious = previousPosition[1];

    // Base case for recursive method.
    // Check whether the position has one neighbor and is not the head.
    if (rowCurrent != headPosition[0] || colCurrent != headPosition[1]) {
      if (rowCurrent == 0 && colCurrent == 0) {
        if (game[rowCurrent][colCurrent + 1] && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent + 1] && game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else if (rowCurrent == 0 && colCurrent == game[rowCurrent].length - 1) {
        if (game[rowCurrent][colCurrent - 1] && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1] && game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else if (rowCurrent == 0) {
        if (game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent + 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1]
            && game[rowCurrent + 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent + 1][colCurrent]
            && game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else if (rowCurrent == game.length - 1 && colCurrent == 0) {
        if (game[rowCurrent + 1][colCurrent] && !game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent + 1][colCurrent] && game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else if (colCurrent == 0) {
        if (game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]
            && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent - 1][colCurrent]
            && game[rowCurrent][colCurrent + 1]
            && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]
            && game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else if (rowCurrent == game.length - 1 && colCurrent == game[rowCurrent].length - 1) {
        if (game[rowCurrent - 1][colCurrent] && !game[rowCurrent][colCurrent - 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent - 1][colCurrent] && game[rowCurrent][colCurrent - 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else if (colCurrent == game[rowCurrent].length - 1) {
        if (game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent - 1][colCurrent]
            && game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent - 1]
            && game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else if (rowCurrent == game.length - 1) {
        if (game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1]
            && game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent - 1][colCurrent]
            && game[rowCurrent][colCurrent + 1]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      } else {
        if (game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]
            && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1]
            && game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]
            && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent - 1][colCurrent]
            && game[rowCurrent][colCurrent + 1]
            && !game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
        if (!game[rowCurrent][colCurrent - 1]
            && !game[rowCurrent - 1][colCurrent]
            && !game[rowCurrent][colCurrent + 1]
            && game[rowCurrent + 1][colCurrent]) {
          tailPosition[0] = rowCurrent;
          tailPosition[1] = colCurrent;
          return tailPosition;
        }
      }
    }

    // Recursive calls if base case is not entered.
    // Call the method again if the position next to the current position is not empty and is not the previous position.
    if(rowCurrent == 0 && colCurrent == 0){
      if (game[rowCurrent][colCurrent + 1] && (rowCurrent != rowPrevious || colCurrent + 1 != colPrevious)) {
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }

    } else if (rowCurrent == 0 && colCurrent == game[rowCurrent].length - 1){
      if(game[rowCurrent][colCurrent - 1] && (rowCurrent != rowPrevious || colCurrent - 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    } else if (rowCurrent == 0){
      if (game[rowCurrent][colCurrent - 1] && (rowCurrent != rowPrevious || colCurrent - 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent][colCurrent + 1] && (rowCurrent != rowPrevious || colCurrent + 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    } else if (rowCurrent == game.length - 1 && colCurrent == 0){
      if (game[rowCurrent - 1][colCurrent] && (rowCurrent - 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent][colCurrent + 1] && (rowCurrent != rowPrevious || colCurrent + 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    } else if (colCurrent == 0) {
      if (game[rowCurrent - 1][colCurrent] && (rowCurrent - 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent][colCurrent + 1] && (rowCurrent != rowPrevious || colCurrent + 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    } else if (rowCurrent == game.length - 1 && colCurrent == game[rowCurrent].length - 1){
      if (game[rowCurrent - 1][colCurrent] && (rowCurrent - 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent][colCurrent - 1] && (rowCurrent != rowPrevious || colCurrent - 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    } else if (colCurrent == game[rowCurrent].length - 1){
      if (game[rowCurrent - 1][colCurrent] && (rowCurrent - 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent][colCurrent - 1] && (rowCurrent != rowPrevious || colCurrent - 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    } else if (rowCurrent == game.length - 1){
      if (game[rowCurrent][colCurrent - 1] && (rowCurrent != rowPrevious || colCurrent - 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent - 1][colCurrent] && (rowCurrent - 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent][colCurrent + 1] && (rowCurrent != rowPrevious || colCurrent + 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    } else {
      if(game[rowCurrent][colCurrent - 1] && (rowCurrent != rowPrevious || colCurrent - 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent - 1][colCurrent] && (rowCurrent - 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent - 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent][colCurrent + 1] && (rowCurrent != rowPrevious || colCurrent + 1 != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[1] = colCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
      if(game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
        previousPosition[0] = currentPosition[0];
        previousPosition[1] = currentPosition[1];
        currentPosition[0] = rowCurrent + 1;
        return findTailRecursive(currentPosition, previousPosition);
      }
    }

    // Call is never executed.
    return tailPosition;
  }

  // Reset the counters to avoid previous count from being taken into consideration.
  private void resetCounters(){
    exhaustiveChecks = 0;
    recursiveChecks = 0;
  }

  // Create private methods for recursive and exhaustive checks.
  private static int getRecursiveChecks(){
    return recursiveChecks;
  }
  private static int getExhaustiveChecks(){
    return exhaustiveChecks;
  }

  // Create public methods for checks in order to access them for testing.
  public static int getRecursiveChecksPublic(){
    return recursiveChecks;
  }
  public static int getExhaustiveChecksPublic(){
    return exhaustiveChecks;
  }

}
