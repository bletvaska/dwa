package dwa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
    HOWTO:
    from project root run:
        javac src/dwa/Initialization.java
        java -cp "src:lib/sqlite-jdbc-3.8.7.jar" dwa.Initialization
*/

public class Initialization {

    public static final String[] QUERIES = {
            "DROP TABLE IF EXISTS user",
            "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "login VARCHAR(32) NOT NULL, " +
                    "password VARCHAR(32) NOT NULL, " +
                    "email VARCHAR(64) NULL, " +
                    "role INTEGER DEFAULT 0" +
                    ")",

            "INSERT INTO user VALUES (1, 'admin', 'admin', 'admin@cde.sk', 'admin')",
            "INSERT INTO user VALUES (2, 'jano', 'jahodka', 'jaho@host.sk', 'user')",
            "INSERT INTO user VALUES (3, 'anicka', 'jahodka', 'anca@host.sk', 'user')",


            "DROP TABLE IF EXISTS message",
            "CREATE TABLE message(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "sender INTEGER," +
                    "receiver INTEGER," +
                    "message VARCHAR(512)," +
                    "FOREIGN KEY(sender) REFERENCES user(id)" +
                    "FOREIGN KEY(receiver) REFERENCES user(id)" +
                    ")",
            "INSERT INTO message VALUES (NULL, 1, 2, 'Welcome to the dwa system')",
            "INSERT INTO message VALUES (NULL, 2, 3, 'You are so sweet... :-*')",
            "INSERT INTO message VALUES (NULL, 3, 1, 'Do you wanna date?')",
            "INSERT INTO message VALUES (NULL, 1, 3, 'Of course. When and where?')",
            "INSERT INTO message VALUES (NULL, 3, 1, 'Tonight at 6pm?')",
            "INSERT INTO message VALUES (NULL, 1, 3, 'Deal')",
    };


    public static void main(String[] args) throws Exception {

        try{
            // connect
            Class.forName(Configuration.DRIVER_CLASS);
            Connection con = DriverManager.getConnection(Configuration.URL);
            System.out.println("Database opened successfully.");

            Statement stmt = con.createStatement();

            // process queries
            for(String query : QUERIES) {
                stmt.executeUpdate(query);
            }

            stmt.close();
            con.close();

            // well done
            System.out.println("Database populated successfully.");
        }catch(Exception ex){
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }
}
