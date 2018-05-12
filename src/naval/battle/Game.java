package naval.battle;

import naval.battle.utils.Board;
import naval.battle.utils.Tile;
import naval.battle.utils.TileType;

import java.util.List;

public class Game {

    public static void main(String[] args) {
        Board board = new Board(8);
        board.initBoard();
        board.drawBoard();

        List<Tile> adjacent = board.getAdjacentTiles(new Tile(4,4, TileType.SEA));

        for (int i =0; i < adjacent.size(); i++) {
            System.out.println("Adjacent Tile " + i + ": X=" + adjacent.get(i).getX() + " | Y=" + adjacent.get(i).getY());
        }

        System.out.println(board.allShipsSunk());
    }
}
