package naval.battle;

import naval.battle.utils.Board;

public class Game {

    public static void main(String[] args) {
        Board board = new Board(8);
        board.initBoard();
        board.drawBoard();
    }
}
