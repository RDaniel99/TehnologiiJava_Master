package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private Database INSTANCE;
    private Connection connection = null;

    private Database() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lostandfound?characterEncoding=latin1&useConfigs=maxPerformance",
                    "root", "password");
        }
        catch (Exception e) {
            System.out.println("Ceva nu e bine la BD");
        }
    }

    public synchronized Database getINSTANCE() {

        if(INSTANCE == null) {
            INSTANCE = new Database();
        }

        return INSTANCE;
    }


}
