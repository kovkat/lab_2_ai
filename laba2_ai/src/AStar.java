import java.util.*;

public class AStar {
    private static long counterOfStatesAStar = 0;
    private static long iterationsAStar = 0;
    public AStar(){}

    public static void algorithm(Board board){
        Queue<Board> queue = new PriorityQueue<>();
        Set<Board> visited = new HashSet<>();

        queue.add(board);
        visited.add(board);

        Board current = new Board();

        boolean flag = queue.element().isSafe();
        current.indexes = Board.cloneArray(queue.element().indexes);

        while (!flag) {
            iterationsAStar++;
            flag = queue.element().isSafe();
//            System.out.println("current\n" + queue.element());
            current.indexes = Board.cloneArray(queue.element().indexes);
            visited.add(queue.element());
            generateNextStateAStar(queue, Objects.requireNonNull(queue.poll()), visited);
        }

        System.out.println("Answer\n" + current);
        System.out.println("iterations " + iterationsAStar);
        System.out.println("counterOfStates " + counterOfStatesAStar);
        System.out.println("queue.size() " + queue.size());

    }

    public static void generateNextStateAStar(Queue<Board> queue, Board board, Set<Board> visited) {
        Board newBoard = new Board();
        newBoard.indexes = Board.cloneArray(board.indexes);

        for (int i = 0; i < board.indexes.length; i++) {
            for (int j = 0; j < board.indexes.length; j++) {
                if (board.indexes[j][i] == 1) {
                    newBoard.indexes[j][i] = 0;
                    for (int k = 0; k < board.indexes.length; k++) {
                        if (k != j) {
                            newBoard.indexes[k][i] = 1;
                            counterOfStatesAStar++;

                            Board state = new Board();
                            state.indexes = Board.cloneArray(newBoard.indexes);

                            if (!queue.contains(state) || !visited.contains(state)) {

//                                System.out.println("============ | " + counter);
//                                System.out.println(state);
//                                System.out.println("============");


                                queue.add(state);
                            }
                            newBoard.indexes[k][i] = 0;
                        }

                    }
                    newBoard.indexes[j][i] = 1;
                }
            }
        }
    }
}
