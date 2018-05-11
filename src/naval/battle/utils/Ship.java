package naval.battle.utils;

public abstract class Ship {

    private Tile startingTile;
    private Orientation orientation;
    private Integer shipSize;

    public Ship(Tile startingTile, Orientation orientation, Integer shipSize) {
        this.startingTile = startingTile;
        this.orientation = orientation;
        this.shipSize = shipSize;
    }

    public void placeShip(String[][] board, Tile startingTile, Orientation orientation) {


    }

    public Tile getStartingTile() { return startingTile; }

    public void setStartingTile(Tile startingTile) { this.startingTile = startingTile; }

    public Orientation getOrientation() { return orientation; }

    public void setOrientation(Orientation orientation) { this.orientation = orientation; }

    public Integer getShipSize() { return shipSize; }

    public void setShipSize(Integer shipSize) { this.shipSize = shipSize; }
}
