public class Test {

    public static void main(String[] args) {

        Board board = new Board("25037460");
        System.out.println(board);

        System.out.println("A STAR");

        long time = System.currentTimeMillis();

        AStar.algorithm(board);
        System.out.println("Time " + (double)(System.currentTimeMillis() - time)/1000);


        System.out.println("\nBFS");

        time = System.currentTimeMillis();

        BFS.algorithm(board);
        System.out.println("Time " + (double)(System.currentTimeMillis() - time)/1000);

    }
}
