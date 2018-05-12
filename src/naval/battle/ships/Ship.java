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

public abstract class Ship {


    private Integer shipSize;

    Ship(Integer shipSize) {
        this.shipSize = shipSize;
    }

    public void placeShip(Board board, Tile startingTile, Orientation orientation) throws OversizeException, OverlapTilesException, AdjacentTilesException {
        if (!shipIsInsideBoard(board, startingTile, orientation))
            throw new OversizeException(this.getClass().getSimpleName() + " size out of bounds.");
        if (overlapsShip(board, startingTile, orientation))
            throw new OverlapTilesException(this.getClass().getSimpleName() + " overlaps other ship.");
        if (isAdjacentToShip(board, startingTile, orientation))
            throw new AdjacentTilesException(this.getClass().getSimpleName() + " is adjacent to another ship.");
        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < shipSize; i++) {
                board.board[startingTile.getX() + i][startingTile.getY()].setType(TileType.SHIP);
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < shipSize; i++) {
                board.board[startingTile.getX()][startingTile.getY() + i].setType(TileType.SHIP);
            }
        }
        System.out.println("[Ship]  \t" + this.getClass().getSimpleName() + " placed successfully!");

    }

    private boolean shipIsInsideBoard(Board board, Tile startingTile, Orientation orientation) {

        boolean shipIsInsideBoard = true;

        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < shipSize; i++) {
                if (!board.isInsideBoard(startingTile.getX() + i, startingTile.getY())) {
                    shipIsInsideBoard = false;
                    break;
                }
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < shipSize; i++) {
                if (!board.isInsideBoard(startingTile.getX(), startingTile.getY() + i)) {
                    shipIsInsideBoard = false;
                    break;
                }
            }
        }
        return shipIsInsideBoard;
    }

    private boolean overlapsShip(Board board, Tile startingTile, Orientation orientation) {

        boolean overlapsShip = false;

        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < shipSize; i++) {
                if (board.board[startingTile.getX() + i][startingTile.getY()].getType() == TileType.SHIP) {
                    overlapsShip = true;
                    break;
                }
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < shipSize; i++) {
                if (board.board[startingTile.getX()][startingTile.getY() + i].getType() == TileType.SHIP) {
                    overlapsShip = true;
                    break;
                }
            }
        }
        return overlapsShip;
    }

    private boolean isAdjacentToShip(Board board, Tile startingTile, Orientation orientation) {

        boolean isAdjacentToShip = false;
        List<Tile> adjacentTiles = new ArrayList<>();

        if (orientation == Orientation.VERTICAL) {
            for (int i = 0; i < shipSize; i++) {
                List<Tile> tileAdjacentTiles = board.getAdjacentTiles(new Tile(startingTile.getX() + i, startingTile.getY()));
                for (Tile tileAdjacentTile : tileAdjacentTiles) {
                    if (!adjacentTiles.contains(tileAdjacentTile))
                        adjacentTiles.add(tileAdjacentTile);
                }
            }
        } else if (orientation == Orientation.HORIZONTAL) {
            for (int i = 0; i < shipSize; i++) {
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

        return isAdjacentToShip;
    }
}
