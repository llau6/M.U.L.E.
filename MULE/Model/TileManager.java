package MULE.Model;

/**
 * Created by Lily on 10/25/2015.
 */
public final class TileManager {
    private TileManager() {}
    private static boolean[][] tiles = new boolean[5][9];

    private static TileType[][] tileTypes = new TileType[5][9];
    private static int currRow;
    private static int currCol;

    public static boolean isTaken() {
        return tiles[currRow][currCol];
    }

    public static boolean isTaken(int i, int j) {
        return tiles[i][j];
    }

    public static void setCurrRow(int r) {
        TileManager.currRow = r;
    }

    public static void setCurrCol(int c) {
        TileManager.currCol = c;
    }

    public static void setTaken(boolean b) {
        tiles[currRow][currCol] = b;
    }

    public static void setTaken(int i, int j) {
        tiles[i][j] = true;
    }

    public static void setPlayerLand() {
        GameManager.getCurrentPlayer().setLands("No mule", currRow, currCol);
    }

    public static boolean[][] getTiles() {
        return tiles;
    }

    public static void setTileTypes(int i, int j, TileType t) {
        tileTypes[i][j] = t;
    }

    public static void setTiles(boolean[][] updatedTiles) {
        tiles = updatedTiles;
    }

    public static TileType getTileType (int i, int j) {
        if (i == 2 && (j == 3 || j == 5)) {
            return TileType.PLAIN;
        } else if (i != 2 && j == 4) {
            return TileType.RIVER;
        } else {
            return tileTypes[i][j];
        }
    }
}
