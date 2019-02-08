public class QueenBoard {

  private int[][] board;
  public static void main(String[] args) {
    QueenBoard board = new QueenBoard(8);
    System.out.println(board);
    board.addQueen(0,0);
    board.addQueen(1,7);
    System.out.println(board);
  }
  public QueenBoard(int size) {
    board = new int[size][size];
  }

  private boolean addQueen(int row, int col) {
    if (board[row][col] != -1 && row < board.length && col < board[row].length) {
      board[row][col] = -1;
      return true;
    }
    return false;
  }
  private boolean removeQueen(int row, int col) {
    if (board[row][col] == -1 && row < board.length && col < board[row].length) {
      board[row][col] = 0;
      return true;
    }
    return false;
  }
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString() {
    String ans = "";
    for (int row = 0; row < board.length; row ++) {
      for (int col = 0; col < board[row].length; col ++) {
        if (board[row][col] == -1) {
          ans += "Q ";
        } else {
          ans += "_ ";
        }
      }
      ans += "\n";
    }
    return ans;
  }
  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve() {
    return true;
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() {
    return 0;
  }
}
