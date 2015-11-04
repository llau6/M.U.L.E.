package MULE.Model;

/**
 * Manage tile selection and conditions
 */
public class TileManager {
    private static boolean[][] tiles = new boolean[5][9];
    private static int currRow;
    private static int currCol;

    /**
     * Sets the current land's condition of taken
     * @return boolean condition of taken in current row column
     */
    public static boolean isTaken() {
        return tiles[currRow][currCol];
    }

    /**
     * Sets the land's condition of taken
     * @param i Row index
     * @param j Column index
     * @return boolean condition of taken in specified row column
     */
    public static boolean isTaken(int i, int j) {
        return tiles[i][j];
    }

    /**
     * Sets the land's row
     * @param r Row
     */
    public static void setCurrRow(int r) {
        TileManager.currRow = r;
    }

    /**
     * Sets the land's column
     * @param c Column
     */
    public static void setCurrCol(int c) {
        TileManager.currCol = c;
    }

    /**
     * Sets the current player's land
     * @param b Sets the tile based on current row and column
     */
    public static void setTaken(boolean b) {
        tiles[currRow][currCol] = b;
    }

    /**
     * Sets the current player's land
     * @param i Row index
     * @param j Column index
     */
    public static void setTaken(int i, int j) {
        tiles[i][j] = true;
    }

    /**
     * Sets the current player's land
     */
    public static void setPlayerLand() {
        GameManager.currentPlayer.setLands("No mule", currRow, currCol);
    }

    /**
     * Retrieves the tile type
     * @param i Row
     * @param j Column
     * @return Tile type
     */
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
