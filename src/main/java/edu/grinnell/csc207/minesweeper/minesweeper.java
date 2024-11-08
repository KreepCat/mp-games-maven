package edu.grinnell.csc207.minesweeper;


import edu.grinnell.csc207.util.ArrayUtils;
import edu.grinnell.csc207.util.IOUtils;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * The code for the UI of Minesweeper. It only handles the UI side of things and has no idea if the
 * player is being a fool
 *
 * @author Alexander Pollock
 * @author <Insert Yourself>
 */

public class minesweeper {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default width.
   */
  static final int DEFAULT_WIDTH = 10;

  /**
   * The default number of rows.
   */
  static final int DEFAULT_HEIGHT = 10;

  /**
   * The default number of mines.
   */
  static final int DEFAULT_MINES = 10;

  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

  /**
   * Print the insturctions.
   *
   * @param pen The printwriter used to print the instructions.
   */
  public static void printInstructions(PrintWriter pen) {
    pen.println("""
        Welcome to Minesweeper.

        Your game board is going to start empty. Clicking a
        space will reveal the number of adjacent mines.

        Your goal is to fill as much of the board as possible
        without setting off a mine.

        You will select a value by inputting it's associated values


        After each move, the number of adjacent mines will be revealed
        or a mine will be set off (if this is the case game over).
        """);
  } // printInstructions(PrintWriter)



  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the game.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) throws IOException {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);
    printInstructions(pen);
    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;
    int mines = DEFAULT_MINES;
    int setupStorage = 16;

    pen.println(
        "Please enter a width or accept the default of: " + DEFAULT_WIDTH + " by pressing enter");
    while (true) {
      try {
        setupStorage = Integer.valueOf(eyes.nextLine());
      } catch (Exception e) {
        break;
      } // try/catch

      if (setupStorage > 1 && setupStorage < 14) {
        width = setupStorage;
        break;
      } else {
        pen.println("Please input a valid value (2-13)");
      } // if/else
    } // while
    pen.println("You have selected a width of: " + width);

    pen.println(
        "Please enter a height or accept the default of: " + DEFAULT_HEIGHT + " by pressing enter");
    while (true) {
      try {
        setupStorage = Integer.valueOf(eyes.nextLine());
      } catch (Exception e) {
        break;
      } // try/catch
      if (setupStorage > 1 && setupStorage < 14) {
        height = setupStorage;
        break;
      } else {
        pen.println("Please input a valid value (2-13)");
      } // if/else
    } // while
    pen.println("You have selected a width of: " + height);

    pen.println("Please enter the number of mines or accept the default of: " + DEFAULT_MINES
        + " by pressing enter");
    while (true) {
      try {
        setupStorage = Integer.valueOf(eyes.nextLine());
      } catch (Exception e) {
        break;
      } // try/catch
      if (setupStorage < width * height) {
        mines = setupStorage;
        break;
      } else {
        pen.println("Please input a valid number of mines, less than: " + width * height);
      } // if/else
    } // while
    pen.println("You have selected: " + mines + " mines.");

    Matrix<Character> shown = new MatrixV0<Character>(width + 1, height + 1, ' ');
    setupStorage = 'a';
    for (int w = 1; w < width + 1; w++) {
      shown.set(0, w, ((char) setupStorage));
      setupStorage++;
    } // for
    setupStorage = 'z';
    for (int h = 1; h < height + 1; h++) {
      shown.set(h, 0, ((char) setupStorage));
      setupStorage--;
    } // for
    Matrix<Character> actual = shown.clone();

    // Set the shown Matrix to a default full board.
    setupStorage = '-';
    shown.fillRegion(1, 1, height + 1, width + 1, (char) setupStorage);

    int t_mines = mines;
    while (t_mines > 0) {
      int mineRow = (int) (Math.random() * height + 1);
      int mineCol = (int) (Math.random() * width + 1);
      if (actual.get(mineRow, mineCol) == ' ') {
        actual.set(mineRow, mineCol, 'm');
        t_mines--;
      } // if
    } // while
    //Matrix.print(pen, shown);

    minesweeperLogic.numberSetup(actual);

    int uncovered = 0;
    int needToBeUncovered = (width * height) - mines;
    while (uncovered != needToBeUncovered) {
      Matrix.print(pen, shown);
      pen.println("Please input a value of the form col+row, ex: az");
      pen.println("To flag/unflag a mine, input a value with an f, ex: azf");
      String values = eyes.nextLine();

      // Take the input and set to integer values.
      int col = ((int) values.charAt(0)) - 96; 
      int row = (-(int) values.charAt(1) + 123);

      // Check if the input is valid.
      if (col < 1 || row < 1 || col > width || row > height) {
        pen.println("Invalid input");
        continue;
      } // if

      // Check if flag.
      if (values.length() == 3) {
        if (values.charAt(2) == 'f') {
          char loc = shown.get(row, col);
          if (loc == 'X') {
            shown.set(row, col, '-');
          }
          if (loc == '-') {
            shown.set(row, col, 'X');
          }
          continue;
        } // if
      } // if

      int revd = minesweeperLogic.processNumber(actual, shown, row, col);
      if (revd == -1) {
        pen.println("YOU LOST");
        break;
      } else {
        uncovered += revd;
        pen.println(uncovered + "/" + needToBeUncovered);
      }

    } // while




    // And we're done
    pen.close();
  } // main(String[])
}
