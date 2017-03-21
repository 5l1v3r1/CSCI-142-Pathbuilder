package Players.DDP3945;

import java.util.*;
import Interface.*;

/**
 * Graph class.  Holds representation of a graph as well as functions to 
 * interact with the graph.
 *
 * @author Duc Phan - ddp3945
 */
public class Graph {

    private Node graph[][];
    private int R;
    private int C;

    public Graph(int dim) {
        this.R = 2 * dim + 1;
        this.C = 2 * dim + 1;
        this.graph = new Node[R][C];
        for (int r = 0; r <= 2 * dim; r += 2) {
            for (int c = 1; c <= 2 * dim; c += 2) {
                this.graph[r][c] = new Node(r, c);
                this.graph[c][r] = new Node(c, r);
            }
        }
    }

    public Node[][] getGraph() {
        return graph;
    }

    public boolean[][] BFS(ArrayList<Coordinate> start) {
        boolean[][] visited = new boolean[R][C];

        for (boolean[] u: visited) {
            for (boolean v: u) {
                v = false;
            }
        }

        List<Node> queue = new ArrayList<>();
        for (Coordinate coordinate: start) {
            int r = coordinate.getRow();
            int c = coordinate.getCol();
            queue.add(this.graph[r][c]);
            visited[r][c] = true;
        }

        while (!queue.isEmpty()) {
            Node u = queue.remove(0);

            for (Node v: u.getNeighbors()) {
                int r = v.getCoordinate().getRow();
                int c = v.getCoordinate().getCol();
                if (visited[r][c]) {
                    continue;
                }
                queue.add(this.graph[r][c]);
                visited[r][c] = true;
            }
        }
        return visited;
    }
}
