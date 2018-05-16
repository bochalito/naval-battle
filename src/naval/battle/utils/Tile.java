package naval.battle.utils;

// Class Tile
// Represents a tile of the board.
public class Tile {

    // Data members
    // Coordinates of tile
    private Integer x, y;

    // Type of tile based on TileType enum
    private TileType type;

    // Constructor
    // Inputs:
    // X, Y: Coordinates of tile
    public Tile(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    // Constructor
    // Inputs:
    // X, Y, Type: Coordinates of tile and  type of tile
    public Tile(Integer x, Integer y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Method: draw. Draws the tile on the command line
    // Inputs:
    // Boolean hidden: If true then the ships are hidden. Used to draw the opponents board
    void draw(boolean hidden) {
        String tile;
        switch (this.type) {
            case HIT:
                tile = "\t\u001B[32mX\u001B[0m";
                break;
            case SEA:
                tile = "\t\u001B[34m~\u001B[0m";
                break;
            case MISS:
                tile = "\t\u001B[31mo\u001B[0m";
                break;
            case SHIP:
                if (hidden)
                    tile = "\t\u001B[34m~\u001B[0m";
                else
                    tile = "\t\u001B[32ms\u001B[0m";
                break;
            default:
                tile = null;
                break;
        }
        System.out.print(tile);
    }

    // Getter
    // Returns the y coordinate of the tile
    public Integer getY() {
        return y;
    }

    // Getter
    // Returns the x coordinate of the tile
    public Integer getX() {
        return x;
    }

    // Getter
    // Returns the type of the tile
    public TileType getType() {
        return type;
    }

    // Setter
    // Sets the type of the tile
    public void setType(TileType type) {
        this.type = type;
    }
}
