package MULE.Model;


import javafx.scene.paint.Color;

import java.sql.*;


/**
 * Created by jatin1 on 10/31/15.
 */
public class Database {
    private Database() {

    }
    private static Connection getConnection() throws Exception {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:8889/WildCats";
            String username = "root";
            String password = "wildcats123";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Jatin probably doesn't have the server running right now");
        }
        return null;
    }

    //method to load everything relevant into the db, should only be called at the END of a round, BEFORE the next one starts
    public static void saveGameInfo() throws Exception {
        try {
            clearTable();
            Connection con = getConnection();
            String ownershipArr = serializeBool();
            PreparedStatement info = con.prepareStatement("INSERT INTO gameData (difficulty, round, boolArr) VALUES ('" + GameManager.getDifficulty() + "', " +
                    "'" + GameManager.getCurrentRoundNumber() + "', '" + ownershipArr + "')");
            info.executeUpdate();
            for (Player p : GameManager.players) {
                savePlayerInfo(p);
            }
            saveStoreInfo();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("We updated the DB for the store info");
        }
    }


    //helper method to save store quantities
    private static void saveStoreInfo() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement info = con.prepareStatement("INSERT INTO storeData (foodQuantity, oreQuantity, energyQuantity, muleQuantity) VALUES ('" + StoreManager.getFoodQuantity() + "', " +
                    "'" + StoreManager.getOreQuantity() + "', '" + StoreManager.getEnergyQuantity() + "', '" + StoreManager.getMuleQuantity() + "')");
            info.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("We updated the DB for store");
        }
    }

    //helper method to load player info into the db
    private static void savePlayerInfo(Player toSave) throws Exception {
        try {
            Connection con = getConnection();
            String muleArr = serializeMule(toSave);
            PreparedStatement info = con.prepareStatement("INSERT INTO players (name, foodCount, energyCount, oreCount, score, race, color, mules) VALUES ('" + toSave.getName() + "', " +
                    "'" + toSave.getFoodCount() + "', '" + toSave.getEnergyCount() + "', '" + toSave.getOreCount() + "', '" + toSave.getScore() + "', '" + toSave.getRace() + "', '" + toSave.getColor() + "', '" + muleArr + "')");
            info.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("We updated the DB for the player named " + toSave);
        }
    }


    //deletes every table of the db to avoid duplicate rows
    private static void clearTable() throws Exception {
        Connection con = getConnection();

        Statement clearMe = con.createStatement();
        clearMe.executeUpdate("TRUNCATE players");
        clearMe.executeUpdate("TRUNCATE gameData");
        clearMe.executeUpdate("TRUNCATE storeData");
    }

    public static void loadGameFromDB() throws Exception {
        Connection con = getConnection();

        //loads game info
        PreparedStatement gameStatement = con.prepareStatement("SELECT * FROM gameData");
        ResultSet queryGame = gameStatement.executeQuery();
        while (queryGame.next()) {
            GameManager.setDifficulty(queryGame.getString("difficulty"));
            GameManager.setCurrentRoundNumber(queryGame.getInt("round"));
            deserializeBool(queryGame.getString("boolArr"));
        }

        //loads store info
        PreparedStatement storeStatement = con.prepareStatement("SELECT * FROM storeData");
        ResultSet queryStore = storeStatement.executeQuery();
        if (queryStore.next()) {
            StoreManager.setFoodQuantity(queryStore.getInt("foodQuantity"));
            StoreManager.setOreQuantity(queryStore.getInt("oreQuantity"));
            StoreManager.setEnergyQuantity(queryStore.getInt("energyQuantity"));
            StoreManager.setMuleQuantity(queryStore.getInt("muleQuantity"));
        }

        //loads player info
        PreparedStatement playersStatement = con.prepareStatement("SELECT * FROM players");
        ResultSet query = playersStatement.executeQuery();
        while (query.next()) {
            //creates each player
            String raceName = query.getString("race");
            Player p = new Player(query.getString("name"), new Race(raceName, "MULE/View/images/MULE_" + raceName + ".png"), Color.web(query.getString("color")));
            p.setFoodCount(query.getInt("foodCount"));
            p.setEnergyCount(query.getInt("energyCount"));
            p.setOreCount(query.getInt("oreCount"));
            p.setScore(query.getInt("score"));
            deserializeMule(query.getString("mules"), p);

            //adds them to the priority queue
            GameManager.players.add(p);
        }
        GameManager.setCurrentPlayer(GameManager.players.peek());
        for (Player p : GameManager.players) {
            GameManager.getOrderedPlayers().add(p);
        }
    }


    //creates a stromg representation of the land ownership array
    private static String serializeBool() {
        String res = "";
        boolean[][] tiles = TileManager.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j]) {
                    res += "1";
                } else {
                    res += "0";
                }
            }
        }
        return res;
    }

    //takes the string representation and updates the TileManager attribute
    private static void deserializeBool(String bool) {
        boolean[][] tiles = TileManager.getTiles();
        int k = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (bool.charAt(k++) == '1') {
                    tiles[i][j] = true;
                }
            }
        }
        TileManager.setTiles(tiles);
    }

    //creates a string representation of the lands/mules owned by each player
    private static String serializeMule(Player p) {
        String res = "";
        int [][] lands = p.getLands();
        for (int i = 0; i < lands.length; i++) {
            for (int j = 0; j < lands[i].length; j++) {
                res += lands[i][j];
            }
        }
        return res;
    }


    //takes the string representation and sets the players lands/mules
    private static void deserializeMule(String mule, Player p) {
        int[][] lands = p.getLands();
        int k = 0;
        for (int i = 0; i < lands.length; i++) {
            for (int j = 0; j < lands[i].length; j++) {
                lands[i][j] = Character.getNumericValue(mule.charAt(k++));
            }
        }
        p.setLands(lands);
    }
}
