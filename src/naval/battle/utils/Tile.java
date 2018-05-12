package naval.battle.utils;

public class Tile {

    private Integer x, y;

    private TileType type;

    public Tile(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Tile(Integer x, Integer y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    void draw() {
        String tile;
        switch (this.type) {
            case HIT:
                tile = "\tX";
                break;
            case SEA:
                tile = "\t~";
                break;
            case MISS:
                tile = "\to";
                break;
            case SHIP:
                tile = "\ts";
                break;
            default:
                tile = null;
                break;
        }
        System.out.print(tile);
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
