import java.util.Arrays;

public class Board implements Comparable<Board> {
    public int[][] indexes;

    public Board() {
    }

    public Board(String state) {
        int size = state.length();
        indexes = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                indexes[i][j] = 0;
        }

        for (int i = 0; i < size; i++) {
            indexes[Integer.parseInt(String.valueOf(state.charAt(i)))][i] = 1;
        }
    }

    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < indexes.length; i++) {
            for (int j = 0; j < indexes.length; j++)
                boardString.append(indexes[i][j]).append(" ");
            boardString.append("\n");
        }
        return boardString.toString();
    }


    @Override
    public boolean equals(Object o) {
        Board other = (Board) o;
        for (int i = 0; i < indexes.length; i++) {
            for (int j = 0; j < indexes.length; j++) {
                if (this.indexes[i][j] != other.indexes[i][j])
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(indexes);
    }

    public boolean isSafe() {
        for (int i = 0; i < indexes.length; i++) {
            for (int j = 0; j < indexes.length; j++) {
                if (indexes[i][j] == 1) {
                    if (!checkPosition(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    boolean checkPosition(int i, int j) {
        for (int k = j + 1; k < indexes.length; k++) {
            if (indexes[i][k] == 1) {
                return false;
            }
        }

        int n;
        int m;
        for (n = i + 1, m = j + 1; n < indexes.length && m < indexes.length; n++, m++) {
            if (indexes[n][m] == 1) {
                return false;
            }
        }


        for (n = i + 1, m = j - 1; n < indexes.length && m >= 0; n++, m--) {
            if (indexes[n][m] == 1) {
                return false;
            }
        }
        return true;
    }

    public int getHeuristic() {
        int heuristic = 0;

        boolean rightDown = false;
        boolean leftDown = false;
        boolean rightUp = false;
        boolean leftUp = false;
        boolean right = false;
        boolean left = false;
        for (int i = 0; i < indexes.length; i++) {
            for (int j = 0; j < indexes.length; j++) {
                if (indexes[i][j] == 1) {
                    int n;
                    int m;

                    for (n = i + 1, m = j + 1; n < indexes.length && m < indexes.length && !rightDown; n++, m++) {
                        if (indexes[n][m] == 1) {
                            heuristic++;
                            rightDown = true;
                        }
                    }

                    for (n = i + 1, m = j - 1; n < indexes.length && m >= 0 && !leftDown; n++, m--) {
                        if (indexes[n][m] == 1) {
                            heuristic++;
                            leftDown = true;
                        }
                    }

                    for (n = i - 1, m = j + 1; n >= 0 && m < indexes.length && !rightUp; n--, m++) {
                        if (indexes[n][m] == 1) {
                            heuristic++;
                            rightUp = true;
                        }
                    }

                    for (n = i - 1, m = j - 1; n >= 0 && m >= 0 && !leftUp; n--, m--) {
                        if (indexes[n][m] == 1) {
                            heuristic++;
                            leftUp = true;
                        }
                    }

                    for (m = j + 1; m < indexes.length && !right; m++) {
                        if (indexes[i][m] == 1) {
                            heuristic++;
                            right = true;
                        }
                    }

                    for (m = j - 1; m >= 0 && !left; m--) {
                        if (indexes[i][m] == 1) {
                            heuristic++;
                            left = true;
                        }
                    }

                }
                left = right = leftDown = leftUp = rightUp = rightDown = false;
            }
        }
        return heuristic;
    }


    @Override
    public int compareTo(Board board) {
        if (this.getHeuristic() == board.getHeuristic())
            return 0;
        else {
            if (this.getHeuristic() > board.getHeuristic())
                return 1;
            else
                return -1;
        }
    }
}
