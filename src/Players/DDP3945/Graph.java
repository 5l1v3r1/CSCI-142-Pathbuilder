package Players.DDP3945;

import java.util.*;
import Interface.Coordinate;

/**
 * Graph class. Holds representation of a graph as well as functions to
 * interact with the graph.
 *
 * @author Duc Phan - ddp3945
 */
public class Graph {

    /**
     * The graph is represented by a 2-D array. Each element in the array is either a player's id indicating that the coordinate is a move made by that player or a 0 indicating that no move is made on that coordinate
     */
    private int graph[][];

    /**
     * The number of rows on the graph
     */
    private int R;

    /**
     * The number of columns on the graph
     */
    private int C;

    /**
     * Helper array of number to iterate to the adjacent coordinates
     */
    private int dr[] = {-1, 0, 0, 1};

    /**
     * Helper array of number to iterate to the adjacent coordinates
     */
    private int dc[] = {0, -1, 1, 0};

    /**
     * Constructor for the graph.
     * @param dim the dimension of the separate grid of a player
     */
    public Graph(int dim) {
        this.R = 2 * dim + 1;
        this.C = 2 * dim + 1;
        this.graph = new int[R][C];
        for (int r = 0; r < R; r ++) {
            for (int c = 0; c < C; c ++) {
                this.graph[r][c] = 0;
            }
        }
    }

    /**
     * Get the graph
     * @return a 2-D array representing the graph
     */
    public int[][] getGraph() {
        return graph;
    }

    /**
     * Get the dimension of the graph
     * @return the dimension
     */
    public int getDim() {
        return this.R;
    }

    public boolean BFS(ArrayList<Coordinate> start,int id) {
        boolean[][] visited = new boolean[R][C];

        for (boolean[] u: visited) {
            for (boolean v: u) {
                v = false;
            }
        }

        List<Coordinate> queue = new ArrayList<>();
        for (Coordinate coordinate: start) {
            int r = coordinate.getRow();
            int c = coordinate.getCol();
            queue.add(coordinate);
            visited[r][c] = true;
        }

        while (!queue.isEmpty()) {
            Coordinate u = queue.remove(0);
            int r = u.getRow();
            int c = u.getCol();

            if ( (id == 1 && visited[r][C - 1]) || (id == 2 && visited[R - 1][c]) ) {
                return true;
            }

            for (int i = 0; i < 4; i ++) {
                int rr = u.getRow() + dr[i];
                int cc = u.getCol() + dc[i];

                if (!isValidCoordinate(rr, cc)) {
                    continue;
                }
                if (this.graph[rr][cc] == id) {
                    rr += dr[i];
                    cc += dc[i];
                    if (visited[rr][cc]) {
                        continue;
                    }
                    if ( (id == 1 && visited[rr][C - 1]) || (id == 2 && visited[R - 1][cc]) ) {
                        return true;
                    }
                    queue.add(new Coordinate(rr, cc));
                    visited[rr][cc] = true;
                }
            }
        }
        return false;
    }

    /**
     * A helper method to determine if a coordinate is inside the graph or not
     * @param r The row number of the coordinate
     * @param c The column number of the coordinate
     * @return true if the coordinate is inside the graph, false otherwise.
     */
    private boolean isValidCoordinate(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
