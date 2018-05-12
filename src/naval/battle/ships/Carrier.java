package naval.battle.ships;

import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;

public class Carrier extends Ship {

    private final Integer SIZE = 5;

    public Carrier(Tile startingTile, Orientation orientation, Integer shipSize) {
        super(startingTile, orientation, shipSize);
    }

    public Integer getSize() {
        return SIZE;
    }

}
