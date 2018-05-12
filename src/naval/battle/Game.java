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
//        Carrier carrier = new Carrier();
//        Destroyer destroyer = new Destroyer();
//        BattleShip battleShip = new BattleShip();
//        try {
//            carrier.placeShip(board, new Tile(1,1), Orientation.VERTICAL);
//            destroyer.placeShip(board, new Tile(1,1), Orientation.HORIZONTAL);
//        } catch (OversizeException | OverlapTilesException | AdjacentTilesException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            battleShip.placeShip(board, new Tile(1 ,2), Orientation.HORIZONTAL);
//        } catch (OversizeException | AdjacentTilesException | OverlapTilesException e) {
//            System.out.println(e.getMessage());
//        }
        board.placeAllShips();

        board.drawBoard();

    }
}
