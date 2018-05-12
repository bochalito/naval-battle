package naval.battle.ships;

import naval.battle.utils.Orientation;
import naval.battle.utils.Tile;

public class BattleShip extends Ship {

    private final Integer SIZE = 4;

    public BattleShip(Tile startingTile, Orientation orientation, Integer shipSize) {
        super(startingTile, orientation, shipSize);
    }

    public Integer getSize() {
        return SIZE;
    }
}
