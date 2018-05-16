package naval.battle.ships;

import naval.battle.exceptions.AdjacentTilesException;
import naval.battle.exceptions.OverlapTilesException;
import naval.battle.exceptions.OversizeException;
import naval.battle.utils.Board;
import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;
import naval.battle.utils.TileType;

import java.util.ArrayList;
import java.util.List;

// Abstract class ship.
// Has all the methods of a ship. Inherited by all kinds of ships.
public abstract class Ship {

    // Ship size data member.
    private Integer shipSize;

    // Constructor
    // Initializes a ship with it's size.
    Ship(Integer shipSize) {
        this.shipSize = shipSize;
    }

    // Method: placeShip. Places the ship on the board
    // Inputs:
    // Board: The board. Depends if you are the player or the computer
    // Starting tile: The starting tile of the ship
    // Orientation: The orientation of the ship Vertical/Horizontal
    public void placeShip(Board board, Tile startingTile, Orientation orientation)
            throws OversizeException, OverlapTilesException, AdjacentTilesException {

        // Checks if the ship is inside the board, if it has adjacent ships and if overlaps another ship.
        // If no exception is raised then the ship is placed.
        if (!shipIsInsideBoard(board, startingTile, orientation))
            throw new OversizeException("[Ship] \t" + this.getClass().getSimpleName() + " size out of bounds.");
        if (overlapsShip(board, startingTile, orientation))
            throw new OverlapTilesException("[Ship] \t" + this.getClass().getSimpleName() + " overlaps other ship.");
        if (isAdjacentToShip(board, startingTile, orientation))
            throw new AdjacentTilesException("[Ship] \t" + this.getClass().getSimpleName() + " is adjacent to another ship.");

        // Places the ship depending on starting tile and orientation
        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < getShipSize(); i++) {
                board.getTile(new Tile(startingTile.getX() + i, startingTile.getY())).setType(TileType.SHIP);
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < getShipSize(); i++) {
                board.getTile(new Tile(startingTile.getX(), startingTile.getY() + i)).setType(TileType.SHIP);
            }
        }

        System.out.println("[Ship]  \t" + this.getClass().getSimpleName() + " placed successfully!");
    }

    // Method: shipIsInsideBoard. Checks if the ship that will be placed is inside the board.
    // Inputs:
    // Board: The board. Depends if you are the player or the computer
    // Starting tile: The starting tile of the ship
    // Orientation: The orientation of the ship Vertical/Horizontal
    private boolean shipIsInsideBoard(Board board, Tile startingTile, Orientation orientation) {

        boolean shipIsInsideBoard = true;

        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < getShipSize(); i++) {
                if (!board.isInsideBoard(startingTile.getX() + i, startingTile.getY())) {
                    shipIsInsideBoard = false;
                    break;
                }
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < getShipSize(); i++) {
                if (!board.isInsideBoard(startingTile.getX(), startingTile.getY() + i)) {
                    shipIsInsideBoard = false;
                    break;
                }
            }
        }
        // Returns true if the ship is inside board and false if not.
        return shipIsInsideBoard;
    }

    // Method: overlapsShip. Checks if the ship that will be placed overlaps another ship.
    // Inputs:
    // Board: The board. Depends if you are the player or the computer
    // Starting tile: The starting tile of the ship
    // Orientation: The orientation of the ship Vertical/Horizontal
    private boolean overlapsShip(Board board, Tile startingTile, Orientation orientation) {

        boolean overlapsShip = false;

        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < getShipSize(); i++) {
                if (board.getTile(new Tile(startingTile.getX() + i, startingTile.getY())).getType() == TileType.SHIP) {
                    overlapsShip = true;
                    break;
                }
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < getShipSize(); i++) {
                if (board.getTile(new Tile(startingTile.getX(), startingTile.getY() + i)).getType() == TileType.SHIP) {
                    overlapsShip = true;
                    break;
                }
            }
        }
        // Returns true if the new ship overlaps another ship and false if not.
        return overlapsShip;
    }

    // Method: isAdjacentToShip. Checks if the ship that will be placed is adjacent to another ship.
    // Inputs:
    // Board: The board. Depends if you are the player or the computer
    // Starting tile: The starting tile of the ship
    // Orientation: The orientation of the ship Vertical/Horizontal
    private boolean isAdjacentToShip(Board board, Tile startingTile, Orientation orientation) {

        boolean isAdjacentToShip = false;
        List<Tile> adjacentTiles = new ArrayList<>();

        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < getShipSize(); i++) {
                List<Tile> tileAdjacentTiles = board.getAdjacentTiles(new Tile(startingTile.getX() + i, startingTile.getY()));
                for (Tile tileAdjacentTile : tileAdjacentTiles) {
                    if (!adjacentTiles.contains(tileAdjacentTile))
                        adjacentTiles.add(tileAdjacentTile);
                }
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < getShipSize(); i++) {
                List<Tile> tileAdjacentTiles = board.getAdjacentTiles(new Tile(startingTile.getX(), startingTile.getY() + i));
                for (Tile tileAdjacentTile : tileAdjacentTiles) {
                    if (!adjacentTiles.contains(tileAdjacentTile))
                        adjacentTiles.add(tileAdjacentTile);
                }
            }
        }
        for (Tile adjacentTile : adjacentTiles) {
            if (adjacentTile.getType() == TileType.SHIP) {
                isAdjacentToShip = true;
                break;
            }
        }

        // Returns true if the ship is adjacent to another ship and false if not.
        return isAdjacentToShip;
    }

    // Getter. Returns the size of the ship.
    private Integer getShipSize() {
          return this.shipSize;
    }
}
