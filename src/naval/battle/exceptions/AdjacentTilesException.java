package naval.battle.exceptions;

// Custom exception. Raised when there is a ship adjacent to a placement of a new ship.
public class AdjacentTilesException extends Exception {

    // Constructor
    // Input: message of exception.
    public AdjacentTilesException(String message) {
        super(message);
    }
}
