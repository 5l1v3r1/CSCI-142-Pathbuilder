package Players.DDP3945;
import Interface.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a node in a graph.
 *
 * @author Duc Phan - ddp3945
 */
public class Node {

    private Coordinate coordinate;
    private List<Node> neighbors;

    public Node(int row, int col) {
        this.coordinate = new Coordinate(row, col);
        this.neighbors = new ArrayList<>();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node u) {
        this.neighbors.add(u);
    }
}
