package edu.grinnell.csc207.minesweeper;

import edu.grinnell.csc207.util.Matrix;

/**
 * The logic behind parts of Minesweeper. This class does much of the heavy
 * lifting. It also checks for properties of the board.
 *
 * @author Alexander Pollock
 * @author Leo Goldman
 */
public class MinesweeperLogic {

  /**
   * Insert the numbers of adjacent mines into the matrix given. This does not
   * return anything so make sure to input a clone.
   *
   * @param toModify The matrix to setup with numbers.
   */
  public static void numberSetup(Matrix<Character> toModify) {
    for (int row = 1; row < toModify.height(); row++) {
      for (int col = 1; col < toModify.width(); col++) {
        int surroundCount = 0;
        if (toModify.get(row, col) == ' ') {
          for (int surroundRow = -1; surroundRow < 2; surroundRow++) {
            for (int surroundCol = -1; surroundCol < 2; surroundCol++) {
              try {
                if (toModify.get(row + surroundRow, col + surroundCol) == 'm'
                    && row + surroundRow != 0) {
                  surroundCount++;
                } // if
              } catch (Exception e) {
                // There isn't a mine over the edge
              } // try/catch
            } // for
          } // for
          toModify.set(row, col, (char) (surroundCount + ((int) ('0'))));
        } // if
      } // for
    } // for
  } // numberSetup

  /**
   * Processes the number.
   *
   * @param actual
   *               The actual board.
   * @param shown
   *               The shown board.
   * @param row
   *               The row.
   * @param col
   *               The column
   * @return
   *         The revealed number.
   */
  public static int processNumber(Matrix<Character> actual, Matrix<Character> shown, int row,
      int col) {
    // Check if loc already revealed.
    try {
      if (shown.get(row, col) != '-') {
        return 0;
      } // if
    } catch (Exception e) {
      return 0;
    } // try

    // Get the result from the actual Matrix.
    // Set the result to the same loc in the shown Matrix.
    char result = actual.get(row, col);
    shown.set(row, col, result);

    // If mine
    if (result == 'm') {
      return -1;
    } // if

    int revealed = 1;

    // If 0
    if (result == '0') {
      shown.set(row, col, ' ');
      // Process surrounding spaces
      for (int x = -1; x < 2; x++) {
        for (int y = -1; y < 2; y++) {
          if (x == 0 && y == 0) {
            continue;
          } // if
          revealed += processNumber(actual, shown, row + y, col + x);
        } // for
      } // for
    } // if
    return revealed;
  } // processNumber()
} // minesweeperLogic
