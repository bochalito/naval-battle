package naval.battle.utils;

import naval.battle.exceptions.AdjacentTilesException;
import naval.battle.exceptions.OverlapTilesException;
import naval.battle.exceptions.OversizeException;
import naval.battle.ships.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    public Tile[][] board;
    private Integer boardSize;

    public Board(Integer length) {
        this.boardSize = length;
        this.board = new Tile[length][length];
    }

    public void initBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = new Tile(i, j, TileType.SEA);
            }
        }
    }

    public void drawBoard() {
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
                this.board[i][j].draw();
            }
            System.out.println();
        }
        System.out.println();

    }

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

    public void placeAllShips() {
        placeShipRandom(new Carrier());
        placeShipRandom(new BattleShip());
        placeShipRandom(new Cruiser());
        placeShipRandom(new Destroyer());
        placeShipRandom(new Submarine());
        System.out.println("[Board] \tAll ships placed successfully!");
    }

    private void placeShipRandom(Ship ship) {
        System.out.println("[Board] \tTrying to place ships...");
        boolean flag = true;
        do {
            try {
                ship.placeShip(this, new Tile(randomGenerator(0, boardSize), randomGenerator(0, boardSize)), getRandomOrientation());
                flag = false;
            } catch (OversizeException | OverlapTilesException | AdjacentTilesException e) {
                System.out.println("[Board] \tTrying to place ships...");
            }
        } while (flag);

    }

    private Orientation getRandomOrientation() {
        int randomOrientation = randomGenerator(0,1);
        return (randomOrientation == 0) ? Orientation.HORIZONTAL : Orientation.VERTICAL;
    }

    public boolean isInsideBoard(int i, int j) {
        boolean flag = false;
        if (i >= 0 && i <= boardSize-1 && j >= 0 && j <= boardSize-1) {
            flag = true;
        }
        return flag;
    }

    private int randomGenerator(int minimum, int maximum) {
        Random rn = new Random();
        int range = maximum - minimum + 1;
        return rn.nextInt(range) + minimum;
    }

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
}
