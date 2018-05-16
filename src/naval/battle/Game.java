package naval.battle;

import naval.battle.exceptions.AdjacentTilesException;
import naval.battle.exceptions.OverlapTilesException;
import naval.battle.exceptions.OversizeException;
import naval.battle.ships.*;
import naval.battle.utils.Board;
import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;
import naval.battle.utils.TileType;

import java.util.Random;
import java.util.Scanner;

// Class Game
// The main class of our program
public class Game {

    // Data members
    // boardSize: represents the size of the boards.
    // noOfShots: the number of shots needed to be taken in order the game to stop
    private static Integer boardSize = 10;
    private static Integer noOfShots = 5;

    // Method main.
    // Creates new instances of the board, two new players.
    // Asks the player if he/she wants to place his/her ships randomly
    // Places the computer ships randomly
    // Then the program runs till all ships of a player sunk or after a specific number of shots taken
    // Finally it prints the winner and the statistics of each player
    public static void main(String[] args) {

        Board playersBoard = new Board(boardSize);
        playersBoard.initBoard();
        Board computerBoard = new Board(boardSize);
        computerBoard.initBoard();
        Player player = new Player("YOU", playersBoard);
        Player computer = new Player("COMPUTER", computerBoard);

        if (randomPlace()) {
            player.placeAllShips();
        } else {
            System.out.println("[Game] \tPlacing carrier.");
            placeShipManually(new Carrier(), player);
            playersBoard.drawBoard(false);

            System.out.println("[Game] \tPlacing cruiser.");
            placeShipManually(new Cruiser(), player);
            playersBoard.drawBoard(false);

            System.out.println("[Game] \tPlacing battleship.");
            placeShipManually(new BattleShip(), player);
            playersBoard.drawBoard(false);

            System.out.println("[Game] \tPlacing destroyer.");
            placeShipManually(new Destroyer(), player);
            playersBoard.drawBoard(false);

            System.out.println("[Game] \tPlacing submarine.");
            placeShipManually(new Submarine(), player);
            playersBoard.drawBoard(false);
        }
        computer.placeAllShips();
        while ((!(computerBoard.allShipsSunk() || playersBoard.allShipsSunk())) && noOfShots > 0) {
            System.out.println(computer.getName().toUpperCase() + " BOARD");
            computerBoard.drawBoard(true);
            System.out.println(player.getName().toUpperCase() + " BOARD");
            playersBoard.drawBoard(false);
            System.out.print("[Game] \tEnter coordinates to fire: ");
            Integer[] playerShots = getInput();
            player.fire(computerBoard, new Tile(playerShots[0], playerShots[1]));
            Integer[] computerShots = getRandInput();
            computer.fire(playersBoard, new Tile(computerShots[0], computerShots[1]));
            noOfShots--;
        }
        if (playersBoard.allShipsSunk()) {
            System.out.println("YOU WON");
        } else if (computerBoard.allShipsSunk()) {
            System.out.println("COMPUTER WON");
        }
        player.getStats();
        computer.getStats();

    }

    // Method getInput. Returns an array of 2 integers that represents a tile
    // This method reads from the keyboard 2 integer values. This values represent the X, Y coordinates on the board
    private static Integer[] getInput() {
        Scanner scanner = new Scanner(System.in);
        boolean insideBoard = true;
        Integer[] input = new Integer[2];
        while (insideBoard) {
            input[0] = scanner.nextInt();
            input[1] = scanner.nextInt();
            if (input[0] < boardSize && input[1] < boardSize)
                insideBoard = false;
            else
                System.out.println("[Game] \tWrong coordinates please try again.");
        }
        return input;
    }

    // Method getRandInput. Returns an array with 2 random integer values
    // This values represent X and Y coordinates of a tile on the board
    private static Integer[] getRandInput() {
        Random rn = new Random();
        int range = boardSize;
        Integer[] randInputs = new Integer[2];
        randInputs[0] = rn.nextInt(range);
        randInputs[1] = rn.nextInt(range);
        return randInputs;
    }

    // Method getOrientation. Reads from the command line an input.
    // If the input is H/h/V/v then the method returns an orientation based on the input
    // H/h -> Horizontal orientation
    // V/v -> Vertical orientation
    // If the user enters any other letter the game asks again for input
    private static Orientation getOrientation() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!(input.equals("H") || input.equals("V") || input.equals("h") || input.equals("v"))) {
            System.out.print("[Game] \tSet ship orientation (H/V): ");
            input = scanner.nextLine();
        }
        Orientation orientation = null;
        if (input.equals("H") || input.equals("h"))
            orientation = Orientation.HORIZONTAL;
        else if (input.equals("V") || input.equals("v")) {
            orientation = Orientation.VERTICAL;
        }
        return orientation;
    }

    // Method randomPlace. Reads an input from the command line
    // If the input is Y/y/N/n then the method returns
    // true if Y/y, false if N/n
    // In any other occasion the method asks again for valid input
    private static boolean randomPlace() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!(input.equals("Y") || input.equals("y") || input.equals("N") || input.equals("n"))) {
            System.out.print("[Game] \tDo you want to place your ships randomly? (Y/N): ");
            input = scanner.nextLine();
        }
        boolean randomPlacement = false;
        if (input.equals("Y") || input.equals("y"))
            randomPlacement = true;
        else if (input.equals("N") || input.equals("n"))
            randomPlacement = false;
        return randomPlacement;
    }

    // Method placeShipManually. This method was created in order to place a ship manually entering coordinates from
    // the command line. If an exception is raised then the program asks for new coordinates to place the ship
    // Inputs:
    // Ship: the ship to be placed
    // Player: the player instance
    private static void placeShipManually(Ship ship, Player player) {
        boolean raisesException;
        do {
            try {
                System.out.print("[Game] \tEnter coordinates to place ship: ");
                Integer[] shipStartingTile = getInput();
                player.placeShip(ship, new Tile(shipStartingTile[0], shipStartingTile[1], TileType.SHIP), getOrientation());
                System.out.println("[Game] \tTrying to place " + ship.getClass().getSimpleName() + ": ");
                raisesException = false;
            } catch (OversizeException | OverlapTilesException | AdjacentTilesException e) {
                System.out.println(e.getMessage());
                raisesException = true;
            }
        } while (raisesException);
    }
}
