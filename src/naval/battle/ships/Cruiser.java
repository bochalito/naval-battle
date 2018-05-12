package naval.battle.ships;

import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;

public class Cruiser extends Ship {

    private final Integer SIZE = 3;

    public Cruiser(Tile startingTile, Orientation orientation, Integer shipSize) {
        super(startingTile, orientation, shipSize);
    }

    public Integer getSize() {
        return SIZE;
    }
}
