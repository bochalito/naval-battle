package naval.battle.exceptions;

// Custom exception. Raised when player tries to place a ship outside the board.
public class OversizeException extends Exception {

    // Constructor
    // Input: Message of exception.
    public OversizeException(String message) {
        super(message);
    }
}
