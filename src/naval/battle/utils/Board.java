package naval.battle.utils;

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
        return null;
    }
}
