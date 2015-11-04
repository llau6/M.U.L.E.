package MULE.Model;

/**
 * Created by Lily on 10/25/2015.
 */
public final class TileManager {
    private TileManager() {}
    private static boolean[][] tiles = new boolean[5][9];
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

    public static void setTiles(boolean[][] updatedTiles) {
        tiles = updatedTiles;
    }

    public static TileType getTileType (int i, int j) {
        TileType tile;
        if ((i == 0 && j == 4)
                || (i == 1 && j == 4)
                || (i == 3 && j == 4)
                || (i == 4 && j == 4)) {
            tile = TileType.RIVER;
        } else if ((i == 0 && j == 2)
                || (i == 1 && j == 1)
                || (i == 2 && j == 8)) {
            tile = TileType.MOUNTAIN1;
        } else if ((i == 3 && j == 1)
                || (i == 3 && j == 6)
                || (i == 4 && j == 2)
                || (i == 4 && j == 8)) {
            tile = TileType.MOUNTAIN2;
        } else if ((i == 0 && j == 6)
                || (i == 1 && j == 8)
                || (i == 2 && j == 0)) {
            tile = TileType.MOUNTAIN3;
        } else {
            tile = TileType.PLAIN;
        }
        return tile;
    }

}
