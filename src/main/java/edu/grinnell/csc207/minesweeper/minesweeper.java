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

        Command-line arguments:

        * -w width - set up the width of the board (max 15)
        * -h height - set up the height of the board (max 15)
        * -m mines - the number of mines in the game

        Your game board is going to start empty. Clicking a
        space will reveal the number of adjacent mines

        Your goal is to fill as much of the board as possible
        without setting off a mine

        You will select a value by inputting it's width and height


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
      } //try/catch

      if (setupStorage > 1 && setupStorage < 16) {
        width = setupStorage;
        break;
      } else {
        pen.println("Please input a valid value (2-15)");
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
      } //try/catch
      if (setupStorage > 1 && setupStorage < 16) {
        height = setupStorage;
        break;
      } else {
        pen.println("Please input a valid value (2-15)");
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
          } //try/catch
      if (setupStorage < width * height) {
        mines = setupStorage;
        break;
      } else {
        pen.println("Please input a valid number of mines, less than: " + width * height);
      } // if/else
    } // while
    pen.println("You have selected: " + mines + " mines.");



    // And we're done
    pen.close();
  } // main(String[])
}
