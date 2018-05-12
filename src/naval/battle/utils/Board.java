package naval.battle.utils;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Tile[][] board;
    private Integer boardSize;

    public Board(Integer length) {
        this.boardSize = length;
        this.board = new Tile[length][length];
    }

    public void initBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = new Tile(i, j, TileType.SHIP);
            }
        }
    }

    public void drawBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            builder.append("\t").append(i + 1);
        }
        System.out.print(builder.toString());
        System.out.println();

        for (int i = 0; i < boardSize; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j].draw();
            }
            System.out.println();
        }
    }

    public List<Tile> getAdjacentTiles(Tile tile) {

        ArrayList<Tile> adjacentTiles = new ArrayList<>();

        if (isInsideBoard(tile.getX(), tile.getY(), boardSize)){
            if(isInsideBoard(tile.getX() + 1, tile.getY(), boardSize))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()]);
            if(isInsideBoard(tile.getX() - 1, tile.getY(), boardSize))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()]);
            if(isInsideBoard(tile.getX(),  tile.getY() + 1, boardSize))
                adjacentTiles.add(board[tile.getX()][tile.getY()+1]);
            if(isInsideBoard(tile.getX(), tile.getY() - 1, boardSize))
                adjacentTiles.add(board[tile.getX()][tile.getY()-1]);
            if(isInsideBoard(tile.getX() - 1, tile.getY() + 1, boardSize))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()+1]);
            if(isInsideBoard(tile.getX() + 1, tile.getY() - 1, boardSize))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()-1]);
            if(isInsideBoard(tile.getX() + 1, tile.getY() + 1, boardSize))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()+1]);
            if(isInsideBoard(tile.getX() - 1, tile.getY() - 1, boardSize))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()-1]);
        }
        return adjacentTiles;
    }

    private boolean isInsideBoard(int i, int j, int boardSize) {
        boolean flag = false;
        if (i >= 0 && i <= boardSize && j >= 0 && j <= boardSize) {
            flag = true;
        }
        return flag;
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
