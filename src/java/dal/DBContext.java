/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NamTQ
 * Date: 08/02/2026
 * Description: 
 *  - DBContext is responsible for establishing a connection to the database.
 *  - It loads database configuration from a properties file.
 *  - All DAO classes extend this class to reuse the database connection.
 */ 
public class DBContext {

    // Protected connection object so subclasses (DAO classes) can access it
    public Connection connection;    
    
    /**
     * Constructor:
     *  - Loads database configuration from ConnectDB.properties file.
     *  - Initializes SQL Server JDBC driver.
     *  - Establishes database connection using DriverManager.
     */
    public DBContext() {
        try {
            Properties properties = new Properties();       
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("../ConnectDB.properties");
            try {
                properties.load(inputStream);
            } catch (IOException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            String user = properties.getProperty("userID");
            String pass = properties.getProperty("password");
            String url = properties.getProperty("url");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
