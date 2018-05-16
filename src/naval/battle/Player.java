package naval.battle;

import naval.battle.exceptions.AdjacentTilesException;
import naval.battle.exceptions.OverlapTilesException;
import naval.battle.exceptions.OversizeException;
import naval.battle.ships.Ship;
import naval.battle.utils.Board;
import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;
import naval.battle.utils.TileType;

// Class Player
// Represents  a player of the game
class Player {

    // Data members
    // Name: the name of the player
    // Board: the board of the player
    // Shots: number of shots taken
    // SuccessfulShots: number of successful shots taken
    // RepeatedShots: number of shots taken in an already hit tile
    private String name;
    private Board board;
    private Integer shots;
    private Integer successfulShots;
    private Integer repeatedShots;

    // Constructor
    // Inputs:
    // Name: the name of the player
    // Board: the board of the player
    Player(String name, Board board) {
        this.name = name;
        this.shots = 0;
        this.successfulShots = 0;
        this.repeatedShots = 0;
        this.board = board;
    }

    // Method: placeAllShips
    // Calls the boards method placeAllShips. Places all ships randomly
    void placeAllShips() {
        getBoard().placeAllShips();
    }

    // Method: placeShip. Places a ship on the board
    // Calls the Ship's method placeShip.
    // Inputs:
    // Ship: the type of ship to be placed
    // StartingTile: the preferred starting tile of the ship
    // Orientation: the orientation of the ship
    void placeShip(Ship ship, Tile startingTile, Orientation orientation)
            throws OverlapTilesException, AdjacentTilesException, OversizeException {
        ship.placeShip(getBoard(), startingTile, orientation);
    }

    // Method getStats. Prints the statistics of the player at the end of the game
    void getStats() {
        System.out.println(getName() + " stats:");
        System.out.println("\tShots fired: " + getShots());
        System.out.println("\tSuccessful shots fired: " + getSuccessfulShots());
        System.out.println("\tRepeated shots fired: " + getRepeatedShots());
    }

    // Method fire. Takes a shot in a specific tile.
    // Inputs:
    // Board: the opponent's board
    // Tile: the specific tile of the board where the player fires
    void fire(Board board, Tile tile) {
        switch (board.getTile(tile).getType()) {
            case SEA:
                board.getTile(tile).setType(TileType.MISS);
                shots++;
                System.out.println(String.format("[Player] \t%s shoot in tile (%d, %d) %s.",
                        getName(), tile.getX(), tile.getY(), board.getTile(tile).getType().toString()));
                break;
            case SHIP:
                board.getTile(tile).setType(TileType.HIT);
                shots++;
                successfulShots++;
                System.out.println(String.format("[Player] \t%s shoot in tile (%d, %d) %s.",
                        getName(), tile.getX(), tile.getY(), board.getTile(tile).getType().toString()));
                break;
            case HIT:
                board.getTile(tile).setType(TileType.HIT);
                shots++;
                repeatedShots++;
                System.out.println(String.format("[Player] \t%s shoot in tile (%d, %d) Already %s.",
                        getName(), tile.getX(), tile.getY(), board.getTile(tile).getType().toString()));
                break;
            case MISS:
                board.getTile(tile).setType(TileType.MISS);
                shots++;
                repeatedShots++;
                System.out.println(String.format("[Player] \t%s shoot in tile (%d, %d) Already %s.",
                        getName(), tile.getX(), tile.getY(), board.getTile(tile).getType().toString()));
                break;
        }
    }

    // Getter
    // Returns the player's name
    String getName() {
        return name;
    }

    // Getter
    // Returns the number of shots the player has fired
    private Integer getShots() {
        return shots;
    }

    // Getter
    // Returns the number of successful shots the player has taken
    private Integer getSuccessfulShots() {
        return successfulShots;
    }

    // Getter
    // Returns the number of repeated shots the player has taken in a tile
    private Integer getRepeatedShots() {
        return repeatedShots;
    }

    // Getter
    // Returns the players board
    private Board getBoard() {
        return this.board;
    }
}
