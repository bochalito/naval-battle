package naval.battle.ships;

import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;

public class Submarine extends Ship {

    private final Integer SIZE = 3;

    public Submarine(Tile startingTile, Orientation orientation, Integer shipSize) {
        super(startingTile, orientation, shipSize);
    }

    public Integer getSize() {
        return SIZE;
    }
}
