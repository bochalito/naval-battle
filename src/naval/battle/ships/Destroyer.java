package naval.battle.ships;

import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;

public class Destroyer extends Ship {

    private final Integer SIZE = 2;

    public Destroyer(Tile startingTile, Orientation orientation, Integer shipSize) {
        super(startingTile, orientation, shipSize);
    }

    public Integer getSize() {
        return SIZE;
    }
}
