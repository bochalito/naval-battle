package naval.battle;

import naval.battle.exceptions.AdjacentTilesException;
import naval.battle.exceptions.OverlapTilesException;
import naval.battle.exceptions.OversizeException;
import naval.battle.ships.Ship;
import naval.battle.utils.Board;
import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;

public class Player {

    private String name;
    private Board board;
    private Integer shots;
    private Integer successfulShots;
    private Integer repeatedShots;

    Player(String name, Board board) {
        this.name = name;
        this.shots = 0;
        this.successfulShots = 0;
        this.repeatedShots = 0;
        this.board = board;
    }

    void placeAllShips() {
        board.placeAllShips();
    }

    void placeShip(Ship ship, Tile startingTile, Orientation orientation) throws OverlapTilesException, AdjacentTilesException, OversizeException {
        ship.placeShip(board, startingTile, orientation);
    }

    void getStats() {
        System.out.println(name + " stats:");
        System.out.println("\tShots fired: " + shots);
        System.out.println("\tSuccessful shots fired: " + successfulShots);
        System.out.println("\tRepeated shots fired: " + repeatedShots);


    }

    void fire(Board board, Tile tile) {
        switch (board.board[tile.getX()][tile.getY()].getType()) {
            case SEA:
                break;
            case SHIP:
                break;
            case HIT:
                break;
            case MISS:
                break;
        }
        System.out.println(board.board[tile.getX()][tile.getY()].getType());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getSuccessfulShots() {
        return successfulShots;
    }

    public void setSuccessfulShots(Integer successfulShots) {
        this.successfulShots = successfulShots;
    }

    public Integer getRepeatedShots() {
        return repeatedShots;
    }

    public void setRepeatedShots(Integer repeatedShots) {
        this.repeatedShots = repeatedShots;
    }
}
