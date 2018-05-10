package naval.battle.utils;

public class Tile {

    private Integer x, y;

    private TileType type;

    public Tile(Integer x, Integer y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public String draw() {
        String tile;
        switch (this.type) {
            case HIT:
                tile = "X";
                break;
            case SEA:
                tile = "~";
                break;
            case MISS:
                tile = "o";
                break;
            case SHIP:
                tile = "s";
                break;
            default:
                tile = null;
                break;
        }
        return tile;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}
