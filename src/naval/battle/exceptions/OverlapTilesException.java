package naval.battle.exceptions;

// Custom exception. Raised when a placement of a new ship overlaps another already placed ship
public class OverlapTilesException extends Exception {

    // Constructor
    // Input: Message of exception.
    public OverlapTilesException(String message) {
        super(message);
    }
}
