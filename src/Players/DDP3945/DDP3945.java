package Players.DDP3945;
import Interface.*;

import java.util.ArrayList;

/**
 * Created by ptnega on 20/03/2017.
 * A class to represent players
 * @author Duc Phan - ddp3945
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

        if (!isValidMove(coordinate, this.board.getDim())) {
            return;
        }

        int r = coordinate.getRow();
        int c = coordinate.getCol();

        if (this.board.getGraph()[r][c] != 0) {
            return;
        }

        this.board.getGraph()[r][c] = playerID;
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
        int dim = this.board.getDim();
        ArrayList<Coordinate> start = new ArrayList<>();
        boolean[][] visited;

        for (int r = 1; r < dim; r += 2) {
            if (id == 1) {
                start.add(new Coordinate(r, 0));
            } else if (id == 2) {
                start.add(new Coordinate(0, r));
            }
        }

        return this.board.BFS(start, id);
    }

    /**
     * A helper method to determine if a move is valid on the board or not
     * @param coordinate The coordinate of the move to verify
     * @param dim The dimension of the board
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidMove(Coordinate coordinate, int dim) {
        return (1 <= coordinate.getCol() &&
                coordinate.getCol() < dim &&
                1 <= coordinate.getRow() &&
                coordinate.getRow() < dim
        );
    }
}
