package naval.battle.utils;

import naval.battle.exceptions.AdjacentTilesException;
import naval.battle.exceptions.OverlapTilesException;
import naval.battle.exceptions.OversizeException;
import naval.battle.ships.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Class Board
// Represents the board of the game. (Only for one player)
public class Board {

    // Data members
    // Board: a 2d array of tiles
    // Boardsize: the size of the board. e.g. 8 = an 8x8 board
    private Tile[][] board;
    private Integer boardSize;

    // Constructor
    // Inputs:
    // boardSize: the size of the board.
    public Board(Integer boardSize) {
        this.boardSize = boardSize;
        this.board = new Tile[boardSize][boardSize];
    }

    // Method initBoard. initializes the board. Sets all tiles to type SEA
    public void initBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = new Tile(i, j, TileType.SEA);
            }
        }
    }

    // Method drawBoard. Prints the board in the command line.
    // Inputs:
    // Boolean hidden: if true the printed board shows no ships. Required to print the opponents board
    public void drawBoard(boolean hidden) {
        System.out.println();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            builder.append("\t").append(i);
        }
        System.out.print(builder.toString());
        System.out.println();

        for (int i = 0; i < boardSize; i++) {
            System.out.print(i);
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j].draw(hidden);
            }
            System.out.println();
        }
        System.out.println();

    }

    // Method getAdjacentTiles. Returns a List with all the adjacent tiles to a tile.
    // Inputs:
    // Tile: the tile for which you want to find adjacent tiles
    public List<Tile> getAdjacentTiles(Tile tile) {

        ArrayList<Tile> adjacentTiles = new ArrayList<>();

        if (isInsideBoard(tile.getX(), tile.getY())){
            if(isInsideBoard(tile.getX() + 1, tile.getY()))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()]);
            if(isInsideBoard(tile.getX() - 1, tile.getY()))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()]);
            if(isInsideBoard(tile.getX(),  tile.getY() + 1))
                adjacentTiles.add(board[tile.getX()][tile.getY()+1]);
            if(isInsideBoard(tile.getX(), tile.getY() - 1))
                adjacentTiles.add(board[tile.getX()][tile.getY()-1]);
            if(isInsideBoard(tile.getX() - 1, tile.getY() + 1))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()+1]);
            if(isInsideBoard(tile.getX() + 1, tile.getY() - 1))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()-1]);
            if(isInsideBoard(tile.getX() + 1, tile.getY() + 1))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()+1]);
            if(isInsideBoard(tile.getX() - 1, tile.getY() - 1))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()-1]);
        }
        return adjacentTiles;
    }

    // Method placeAllShips. Creates new instances of all ships and places them on the board
    public void placeAllShips() {
        placeShipRandom(new Carrier());
        placeShipRandom(new BattleShip());
        placeShipRandom(new Cruiser());
        placeShipRandom(new Destroyer());
        placeShipRandom(new Submarine());
        System.out.println("[Board] \tAll ships placed successfully!");
    }

    // Method placeShipRandom. Places a ship randomly on board
    // Inputs:
    // Ship: the type of ship to be placed
    private void placeShipRandom(Ship ship) {
        System.out.println("[Board] \tTrying to place ships...");
        boolean flag = true;
        // While loop is used in order to place the ship in a spot where no exception is raised.
        // If an exception is raised the program tries again to place the ship randomly
        while (flag) {
            try {
                ship.placeShip(this, new Tile(randomGenerator(0, boardSize), randomGenerator(0, boardSize)), getRandomOrientation());
                flag = false;
            } catch (OversizeException | OverlapTilesException | AdjacentTilesException e) {
                System.out.println("[Board] \tTrying to place ships...");
            }
        }

    }

    // Method: getRandomOrientation. Returns an orientation randomly
    // This method generates a random number, either 0 or 1. If 0 then returns Horizontal orientation
    // else returns Vertical orientation
    private Orientation getRandomOrientation() {
        int randomOrientation = randomGenerator(0,1);
        return (randomOrientation == 0) ? Orientation.HORIZONTAL : Orientation.VERTICAL;
    }

    // Method: isInsideBoard. Returns true if the tile is inside board
    // Inputs:
    // i, j: X, Y coordinates of the tile
    public boolean isInsideBoard(int i, int j) {
        boolean flag = false;
        if (i >= 0 && i <= boardSize-1 && j >= 0 && j <= boardSize-1) {
            flag = true;
        }
        return flag;
    }

    // Method: randomGenerator. Returns a random number in a range
    // Inputs:
    // minimum: minimum value of range
    // maximum: maximum value of range
    private int randomGenerator(int minimum, int maximum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        return rn.nextInt(range) + minimum;
    }

    // Method allShipsSunk. Returns true if all ships sunk on a board.
    public boolean allShipsSunk() {

        boolean allShipsSunk = true;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j].getType() == TileType.SHIP) {
                    allShipsSunk = false;
                    break;
                }
            }
            if (!allShipsSunk) break;
        }
        return allShipsSunk;
    }

    // Getter
    // Returns a Tile of the board
    public Tile getTile(Tile tile) {
        return board[tile.getX()][tile.getY()];
    }
}
