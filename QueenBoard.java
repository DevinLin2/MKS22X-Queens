public class QueenBoard {

  private int[][] board;

  public static void main(String[] args) {
    QueenBoard board = new QueenBoard(Integer.parseInt(args[0]));
    //board.solve();
    //System.out.println(board);
    System.out.println(board.countSolutions());
    System.out.println(board);
  }

  public QueenBoard(int size) { // loop to add 0 to each spot
    board = new int[size][size];
    for (int row = 0; row < board.length; row ++) {
      for (int col = 0; col < board[row].length; col ++) {
        board[row][col] = 0;
      }
    }
  }

  private boolean addQueen(int row, int col) {
    if (board[row][col] == 0 && row < board.length && col < board[row].length) {
      board[row][col] = -1;
      for (int r = 0; r < board.length; r ++) {
        for (int c = 0; c < board[row].length; c ++) {
          if (r == row && c != col) { // every spot in the row but the queen
            board[r][c] += 1;
          }
          if (c == col && r != row) { // every spot in the col but the queen
            board[r][c] += 1;
          }
          if (c != col && r != row) { // uses slope for diagonals
            if ((((r - row) / (c - col) == 1) || ((r - row) / (c - col) == -1)) && ((r - row) % (c - col) == 0)) {
              board[r][c] += 1;
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  private boolean removeQueen(int row, int col) {
    if (board[row][col] == -1 && row < board.length && col < board[row].length) {
      board[row][col] = 0;
      for (int r = 0; r < board.length; r ++) {
        for (int c = 0; c < board[row].length; c ++) {
          if (r == row && c != col) { // every spot in the row but the queen
            board[r][c] -= 1;
          }
          if (c == col && r != row) { // every spot in the col but the queen
            board[r][c] -= 1;
          }
          if (c != col && r != row) { // uses slope for diagonals
            if ((((r - row) / (c - col) == 1) || ((r - row) / (c - col) == -1)) && ((r - row) % (c - col) == 0)) {
              board[r][c] -= 1;
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
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
    return solveHelper(0);
  }
  public boolean solveHelper(int col) {
    if (col >= board.length) {
      return true;
    }
    for (int row = 0; row < board.length; row++) {
      if (board[row][col] == -1) {
        return solveHelper(col + 1);
      }
      if (addQueen(row,col) && solveHelper(col + 1)) {
        return true;
      }
      removeQueen(row,col);
    }
    return false;
  }
  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() {
    return countSolutionsHelper(0, 0, 0);
  }
  public int countSolutionsHelper(int row, int col, int ans) {
    int answer = 0;
    if (row < board.length && col < board[row].length && addQueen(row, col)) {
      for (int row1 = 0; row1 < board.length; row1++) {
        if (row1 < board.length && addQueen(row1, 1) && solveHelper(col)) {
          answer++;
        }
        removeAllButFirstQueens();
      }
      removeAllQueens();
      return countSolutionsHelper(row + 1, col, ans + answer);
    }
    return ans;
  }
  public void removeAllQueens() {
    for (int r = 0; r < board.length; r ++) {
      for (int c = 0; c < board[r].length; c ++) {
        removeQueen(r, c);
      }
    }
  }
  public void removeAllButFirstQueens() {
    for (int r = 0; r < board.length; r ++) {
      for (int c = 1; c < board[r].length; c ++) {
        removeQueen(r, c);
      }
    }
  }
}
