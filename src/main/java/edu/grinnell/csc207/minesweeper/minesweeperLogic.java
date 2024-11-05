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
 * The logic behind parts of Minesweeper. This class does much of the heavy
 * lifting. It also checks for properties of the board.
 *
 * @author Alexander Pollock
 * @author <Insert Yourself>
 */
public class minesweeperLogic {


  /**
   * Insert the numbers of adjacent mines into the matrix given. This does not
   * return anything so make sure to input a clone.
   * 
   * @param Matrix The matrix to setup with numbers.
   *
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

} // minesweeperLogic
