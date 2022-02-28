/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * Last updated 03/16/2021.
 * 
 * @author Ian Skelskey, Ruben Acuna
 * @version 1.0
 */
import java.lang.String;

public class SkelskeyMatrix implements Matrix {
  private int[][] manipMatrix;
  private int rows = 0;
  private int cols = 0;

  public SkelskeyMatrix(int[][] matrix) {
    if (matrix.length == 0) {
      rows = 0;
      cols = 0;
    } else {
      rows = matrix.length;
      cols = matrix[0].length;
    }
    manipMatrix = new int[rows][cols];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        manipMatrix[i][j] = matrix[i][j];
      }
    }
  }

  /**
   * Returns the element at particular point in the matrix.
   * 
   * @param y y position
   * @param x x position
   * @return element
   */
  @Override
  public int getElement(int y, int x) {
    return manipMatrix[y][x];
  }

  /**
   * Returns the number of rows in the matrix.
   * 
   * @return rows
   */
  @Override
  public int getRows() {
    return rows;
  }

  /**
   * Returns the number of columns in the matrix.
   * 
   * @return columns
   */
  @Override
  public int getColumns() {
    return cols;
  }

  /**
   * Returns this matrix scaled by a factor. That is, computes kA where k is a
   * constant and A is a matrix (this object).
   * 
   * @param scalar scalar
   * @return matrix
   */
  @Override
  public Matrix scale(int scalar) {
    int temp[][] = new int[getRows()][getColumns()];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        temp[i][j] = scalar * manipMatrix[i][j];
      }
    }
    SkelskeyMatrix scaleMatrix = new SkelskeyMatrix(temp);
    return scaleMatrix;
  }

  /**
   * Returns this matrix added with another matrix. That is, computes A+B where A
   * and B are matrices (this object, and another respectively).
   * 
   * @param other addend
   * @return matrix
   * @throws RuntimeException if matrices do not have matching dimensions.
   */
  @Override
  public Matrix plus(Matrix other) {
    int temp = 0;
      SkelskeyMatrix resultMatrix;
    if ((getRows() == other.getRows()) && (getColumns() == other.getColumns())) {
      int sum[][] = new int[getRows()][getColumns()];
      for (int i = 0; i < getRows(); i++) {
        for (int j = 0; j < getColumns(); j++) {
          temp = getElement(i, j) + other.getElement(i, j);
          sum[i][j] = temp;
        }
      }
      resultMatrix = new SkelskeyMatrix(sum);
      return resultMatrix;
    } else {
      throw new RuntimeException("Matrix dimensions do not match.");
    }

  }

  /**
   * Returns this matrix subtracted by another matrix. That is, computes A-B where
   * A and B are matrices (this object, and another respectively).
   * 
   * @param other subtrahend
   * @return matrix
   * @throws RuntimeException if matrices do not have matching dimensions.
   */
  @Override
  public Matrix minus(Matrix other) {
    int temp = 0;
    SkelskeyMatrix resultMatrix;
    if ((getRows() == other.getRows()) && (getColumns() == other.getColumns())) {
    int diff[][] = new int[getRows()][getColumns()];
    //System.out.println(this);
    //System.out.println(other);
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          temp = getElement(i,j) - other.getElement(i, j);
          diff[i][j] = temp;
        }
      }
      resultMatrix = new SkelskeyMatrix(diff);
      return resultMatrix;
    }else{
      throw new RuntimeException("Matrix dimensions do not match.");
    }
  }

  /**
   * Returns this matrix multiplied by another matrix (using dot products). That
   * is, computes AB where A and B are matrices (this object, and another
   * respectively).
   * 
   * @param other multiplicand
   * @return matrix
   * @throws RuntimeException if matrices do not have matching dimensions.
   */
  @Override
  public Matrix multiply(Matrix other) {
    SkelskeyMatrix resultMatrix;
    if ((getRows() == other.getRows()) && (getColumns() == other.getColumns())) {
      int prod[][] = new int[getRows()][getColumns()];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          prod[i][j] = getElement(i, j) * other.getElement(i, j);
        }
      }
      resultMatrix = new SkelskeyMatrix(prod);
      return resultMatrix;
    }else{
      throw new RuntimeException("Matrix dimensions do not match.");
    }
  }

  /**
   * Returns true if this matrix matches another matrix.
   * 
   * @param other another matrix
   * @return equality
   */
  public boolean equals(SkelskeyMatrix other) {			//This should override the matrix interface but it does not.
    if (this == null || other == null || this.getRows() == 0 || other.getRows() == 0) {
      return false;
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (getElement(i, j) != other.getElement(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns a string representation of this matrix. A new line character will
   * separate each row, while a space will separate each column.
   * 
   * @return string representation
   */
  @Override
  public String toString() {
    String printMatrix = "";
    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < getColumns(); j++) {
        printMatrix += (getElement(i, j) + " ");
      }
      printMatrix += "\n";
    }
    return printMatrix;
  }

  // TODO: implement interface.


}