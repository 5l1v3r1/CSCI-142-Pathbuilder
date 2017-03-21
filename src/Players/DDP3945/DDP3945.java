package Players.DDP3945;
import Interface.*;

import java.util.ArrayList;

/**
 * Created by ptnega on 20/03/2017.
 *
 */
public class DDP3945 implements PlayerModulePart1{

    private int playerId;
    private Graph board;

    /**
     * Method called to initialize a player module. Required task for Part 1. Note that for tournaments of multiple games, only one instance of each PlayerModule is created. The initPlayer method is called at the beginning of each game, and must be able to reset the player for the next game.
     * @param dim size of the smaller dimension of the playing area for one player. The grid of nodes for that player is of size dim x (dim+1).
     * @param playerId id (1 or 2) for this player.
     */
    public void initPlayer(int dim, int playerId) {
        this.playerId = playerId;
        this.board = new Graph(dim);
    }

    /**
     * Method called after every move of the game. Used to keep internal game state current. Required task for Part 1. Note that the engine will only call this method after verifying the validity of the current move. Thus, you do not need to verify the move provided to this method. It is guaranteed to be a valid move.
     * @param m PlayerMove representing the most recent move
     */
    public void lastMove(PlayerMove m) {
        Coordinate coordinate = m.getCoordinate();
        int playerID = m.getPlayerId();
        if (!isValidMove(coordinate, playerID, this.board.getGraph().length)) {
            return;
        }

        int r = coordinate.getRow();
        int c = coordinate.getCol();

        if (playerID == 1) {
            Node u = this.board.getGraph()[r][c - 1];
            Node v = this.board.getGraph()[r][c + 1];
            u.addNeighbor(v);
            v.addNeighbor(u);
        } else if (playerID == 2) {
            Node u = this.board.getGraph()[r - 1][c];
            Node v = this.board.getGraph()[r + 1][c];
            u.addNeighbor(v);
            v.addNeighbor(u);
        }
    }

    /**
     * Indicates that the other player has been invalidated. Required task for Part 2.
     */
    public void otherPlayerInvalidated() {

    }

    /**
     * Generates the next move for this player. Note that it is recommended that updating internal game state does NOT occur inside of this method. See lastMove. An initial, working version of this method is required for Part 2. It may be refined subsequently.
     * @return a PlayerMove object representing the next move.
     */
    public PlayerMove move() {
        return null;
    }

    /**
     * Part 1 task that tests if a player has won the game given a set of PREMOVEs.
     * @param id player to test for a winning path.
     * @return boolean value indicating if the player has a winning path.
     */
    public boolean hasWonGame(int id) {
        int dim = (this.board.getGraph().length - 1) / 2;
        ArrayList<Coordinate> start = new ArrayList<>();
        boolean[][] visited;
        if (id == 1) {
            for (int r = 1; r <= 2 * dim; r += 2) {
                start.add(new Coordinate(r, 0));
            }
            visited = this.board.BFS(start);
            for (int r = 1; r <= 2 * dim; r += 2) {
                if (visited[r][2 * dim]) {
                    return true;
                }
            }
            return false;
        } else if (id == 2) {
            for (int c = 1; c <= 2 * dim; c += 2) {
                start.add(new Coordinate(0, c));
            }
            visited = this.board.BFS(start);
            for (int c = 1; c <= 2 * dim; c += 2) {
                if (visited[2 * dim][c]) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }


    private boolean isValidMove(Coordinate coordinate, int playerId, int dim) {
        return (1 <= coordinate.getCol() &&
                coordinate.getCol() <= (2 * dim - 1) &&
                1 <= coordinate.getRow() &&
                coordinate.getRow() <= (2 * dim - 1)
        );
    }
}
