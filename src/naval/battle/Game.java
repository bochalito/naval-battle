package naval.battle;

import naval.battle.exceptions.AdjacentTilesException;
import naval.battle.exceptions.OverlapTilesException;
import naval.battle.exceptions.OversizeException;
import naval.battle.ships.BattleShip;
import naval.battle.ships.Carrier;
import naval.battle.ships.Destroyer;
import naval.battle.utils.Board;
import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;
import naval.battle.utils.TileType;

import java.util.List;

public class Game {

    public static void main(String[] args)  {
        Board board = new Board(8);
        board.initBoard();
        Carrier carrier = new Carrier();
        Destroyer destroyer = new Destroyer();
        BattleShip battleShip = new BattleShip();
        try {
            carrier.placeShip(board, new Tile(1,1), Orientation.VERTICAL);
            destroyer.placeShip(board, new Tile(1,1), Orientation.HORIZONTAL);
        } catch (OversizeException | OverlapTilesException | AdjacentTilesException e) {
            System.out.println(e.getMessage());
        }

        try {
            battleShip.placeShip(board, new Tile(1 ,3), Orientation.HORIZONTAL);
        } catch (OversizeException | AdjacentTilesException | OverlapTilesException e) {
            System.out.println(e.getMessage());
        }
        List<Tile> adjacent = board.getAdjacentTiles(new Tile(2,1));

        for (int i =0; i < adjacent.size(); i++) {
            System.out.println("Adjacent Tile " + i + ": X=" + adjacent.get(i).getX() + " | Y=" + adjacent.get(i).getY() + " | Type: " + adjacent.get(i).getType().toString());
        }
        board.drawBoard();



        System.out.println(board.allShipsSunk());
    }
}
