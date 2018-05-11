package naval.battle.utils;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Tile[][] board;
    private Integer length;

    public Board(Integer length) {
        this.length = length;
        this.board = new Tile[length][length];
    }

    public void initBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this.board[i][j] = new Tile(i, j, TileType.SEA);
            }
        }
    }

    public void drawBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("\t").append(i + 1);
        }
        System.out.print(builder.toString());
        System.out.println();

        for (int i = 0; i < length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < length; j++) {
                this.board[i][j].draw();
            }
            System.out.println();
        }
    }

    public List<Tile> getAdjacentTiles(Tile tile) {
        ArrayList<Tile> adjacentTiles = new ArrayList<>();

        if (isCabin(tile.getX(), tile.getY(), length)){
            if(isCabin(tile.getX() + 1, tile.getY(), length))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()]);
            if(isCabin(tile.getX() - 1, tile.getY(), length))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()]);
            if(isCabin(tile.getX(),  tile.getY() + 1, length))
                adjacentTiles.add(board[tile.getX()][tile.getY()+1]);
            if(isCabin(tile.getX(), tile.getY() - 1, length))
                adjacentTiles.add(board[tile.getX()][tile.getY()-1]);
            if(isCabin(tile.getX() - 1, tile.getY() + 1, length))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()+1]);
            if(isCabin(tile.getX() + 1, tile.getY() - 1, length))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()-1]);
            if(isCabin(tile.getX() + 1, tile.getY() + 1, length))
                adjacentTiles.add(board[tile.getX()+1][tile.getY()+1]);
            if(isCabin(tile.getX() - 1, tile.getY() - 1, length))
                adjacentTiles.add(board[tile.getX()-1][tile.getY()-1]);
        }

        return adjacentTiles;
    }

    public boolean isCabin(int i, int j, int length) {
        boolean flag = false;
        if (i >= 0 && i <= length && j >= 0 && j <= length) {
            flag = true;
        }
        return flag;
    }
}
