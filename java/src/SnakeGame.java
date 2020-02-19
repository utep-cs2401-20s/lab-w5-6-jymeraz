public class SnakeGame {
  private boolean[][] game;
  private int[] headPosition = new int[2];
  private static int exhaustiveChecks = 0;
  private static int recursiveChecks = 0;
  //private int[] tailPosition = new int[3];

  public SnakeGame(){

    game = new boolean[1][1];
  }

  public SnakeGame(boolean[][] inputArray, int row, int col){
    // ********* Why do i have to initialize the column size? It gives me an error if I dont. **********
    game = new boolean[inputArray.length][inputArray[0].length];

    for (int i = 0; i < inputArray.length; i++){
      for (int j = 0; j < inputArray[i].length; j++){
        game[i][j] = inputArray[i][j];
      }
    }

    headPosition[0] = row;
    headPosition[1] = col;
  }

  // Returns right after the tail is found???
  // Where to call resetCounters??
  public int[] findTailExhaustive(){
    resetCounters();
    int length = 0;
    int[] tailPosition = new int[3];

    for (int i = 0; i < game.length; i++) {
      for (int j = 0; j < game[i].length; j++) {
        if (game[i][j]) {
          length++;
        }
      }
    }
    tailPosition[2] = length;

    for(int i = 0; i < game.length; i++){
      for (int j = 0; j < game[i].length; j++) {
        exhaustiveChecks++;
        if(game[i][j]){

          // Check the (top, bottom, left, right) neighbors around to make sure that they are not true.
          // Only one neighbor can be true.
          // Check that the coordinates are not the head.
          if(i == 0 && j == 0){
            if ((!game[i][j + 1] || !game[i + 1][j])
                && (i != headPosition[0] || j != headPosition[1])) {
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          } else if (i == 0 && j == game[i].length - 1){
            if((!game[i][j - 1] || !game[i + 1][j]) && (i != headPosition[0] || j != headPosition[1])){
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          } else if (i == 0){
            if (((game[i][j - 1] && !game[i + 1][j] && !game[i][j + 1])
                    || (!game[i][j - 1] && game[i + 1][j] && !game[i][j + 1])
                    || (!game[i][j - 1] && !game[i + 1][j] && game[i][j + 1]))
                && (i != headPosition[0] || j != headPosition[1])) {
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          } else if (i == game.length - 1 && j == 0){
            if ((!game[i + 1][j] || !game[i][j + 1])
                && (i != headPosition[0] || j != headPosition[1])) {
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          } else if (j == 0) {
            if (((game[i - 1][j] && !game[i][j + 1] && !game[i + 1][j])
                    || (!game[i - 1][j] && game[i][j + 1] && !game[i + 1][j])
                    || (!game[i - 1][j] && !game[i][j + 1] && game[i + 1][j]))
                && (i != headPosition[0] || j != headPosition[1])) {
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          } else if (i == game.length - 1 && j == game[i].length - 1){
            if ((!game[i - 1][j] || !game[i][j - 1])
                && (i != headPosition[0] || j != headPosition[1])) {
              tailPosition[0] = i;
              tailPosition[1] = j;
            }
          } else if (j == game[i].length - 1){
            if (((game[i - 1][j] && !game[i][j - 1] && !game[i + 1][j])
                    || (!game[i - 1][j] && game[i][j - 1] && !game[i + 1][j])
                    || (!game[i - 1][j] && !game[i][j - 1] && game[i + 1][j]))
                && (i != headPosition[0] || j != headPosition[1])) {
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          } else if (i == game.length - 1){
            if (((game[i][j - 1] && !game[i - 1][j] && !game[i][j + 1])
                    || (!game[i][j - 1] && game[i - 1][j] && !game[i][j + 1])
                    || (!game[i][j - 1] && !game[i - 1][j] && game[i][j + 1]))
                && (i != headPosition[0] || j != headPosition[1])) {
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          } else {
            if(((game[i][j - 1] && !game[i - 1][j] && !game[i][j + 1] && !game[i + 1][j])
                    || (!game[i][j - 1] && game[i - 1][j] && !game[i][j + 1] && !game[i + 1][j])
                    || (!game[i][j - 1] && !game[i - 1][j] && game[i][j + 1] && !game[i + 1][j])
                    || (!game[i][j - 1] && !game[i - 1][j] && !game[i][j + 1] && game[i + 1][j]))
                && (i != headPosition[0] || j != headPosition[1])){
              tailPosition[0] = i;
              tailPosition[1] = j;
              return tailPosition;
            }
          }
        }
      }
    }

    return tailPosition;
  }

  public int[] findTailRecursive(){
    // ********************
    //resetCounters();
    // ************************
    int[] tailPosition2 = new int[3];

    int[] currentPosition = new int[2];
    int[] previousPosition = new int[2];

    for(int i = 0; i < headPosition.length; i++){
      currentPosition[i] = headPosition[i];
    }
    for(int i = 0; i < headPosition.length; i++){
      previousPosition[i] = headPosition[i];
    }


    for(int i = 0; i < tailPosition2.length; i++){
      tailPosition2[i] = findTailRecursive(currentPosition, previousPosition)[i];
    }

    //tailPosition2 = findTailRecursive(headPosition, headPosition);
    //recursiveChecks++;
    tailPosition2[2] = recursiveChecks;
    return tailPosition2;
  }

//  private int[] findTailRecursive(int[] currentPosition, int[] previousPosition)
//  -- overloads the previous method, and is similar in definition,
//  but starts at a position other than the head position (used for the recursive calls),
//  also takes in the position of the previous body position (to exclude it from deciding the next position).
//  Increments the recursiveChecks counter with each (x',y') cell that is examined.
//  Hint: the call for starting from the head position made from the public method should be findTailRecursive(headPosition, headPosition).
  // Change to private rn.

  // Where do i put the recursive checks?
  private int[] findTailRecursive(int[] currentPosition, int[] previousPosition){
//    System.out.println();
//    System.out.println("recursive checks: " + recursiveChecks);
//    //System.out.println();
//    recursiveChecks++;
    int[] tailPosition = new int[3];
    int length = 0;
    int rowCurrent = currentPosition[0];
    int colCurrent = currentPosition[1];

    int rowPrevious = previousPosition[0];
    int colPrevious = previousPosition[1];

    // BASE CASE.
    // IF THERE ARE NO OTHER ONES AROUND IT, then return it.

    if (rowCurrent != headPosition[0] || colCurrent != headPosition[1]) {
      if (rowCurrent == 0 && colCurrent == 0) {
        // If the one on the right is not equal to the previous and it's empty AND if the one on the
        // bottom is not equal to the previous and its empty
        // if it is equal to the previous and it's not empty, it's fine
        // If the one on the right is equal to the previous AND the one on the bottom is empty *OR*
        // if the one on the bottom is equal to the right and the bottom is empty
        // Or, if one is true and one is not
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

        // if the one on the left is the same as the previous and the one on the bottom is empty
        // if the one on the bottom is the same as the previous and the left is empty.
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
        // Only one can be true.
        // if one is true and the rest are false. s
        // If one is equal to previous and the rest are empty
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
        // top and right
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

    // RECURSIVE CALLS HERE
    // Based on where the current position is, only check certain areas to avoid going out of bounds.
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
      if(!game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
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
      if (game[rowCurrent + 1][colCurrent] && (rowCurrent + 1 != rowPrevious || colCurrent != colPrevious)){
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
    // check if there is a cell that is not the previous, and make that the current.
    // based on where it is, check all the sides and call recursively from there.
    return tailPosition;
  }

  private void resetCounters(){
    exhaustiveChecks = 0;
    recursiveChecks = 0;
  }

  private static int getRecursiveChecks(){
    return recursiveChecks;
  }
  private static int getExhaustiveChecks(){
    return exhaustiveChecks;
  }

}
