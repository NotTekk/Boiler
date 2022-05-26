package com.tr3ntu.Boiler.sql;

import com.tr3ntu.Boiler.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SQLConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLConnection.class);

    String query;

    public Connection sqlconnected() {

        Connection c = null;
        String driver = Config.get("DRIVER");
        String url = Config.get("URL");
        String db = Config.get("DB");
        String uname = Config.get("UNAME");
        String password = Config.get("PASSWORD");

        try {
            Class.forName(driver);
            c = DriverManager.getConnection(url + db, uname, password);
            if(c == null) {
                LOGGER.info("Connection is not established");
            }
            LOGGER.info("SQL - Connection established");
            return c;
        } catch (Exception e) {
            LOGGER.warn("Error:" + e);
        }
        return c;
    }

/*    public ArrayList<String> getReport() {
        try {
            Statement sql = new SQLConnection().sqlconnected().createStatement();
            ResultSet resultSet = sql.executeQuery("SELECT word FROM CURSE_WORDS;");
            if (!resultSet.next()) {
                sql.close();
                resultSet.close();
                return null;
            }
            ArrayList<String> rowValues = new ArrayList<String>();
            int columnCount = resultSet.getMetaData().getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int idx = 0; idx < columnCount; idx++) {
                columnNames[idx] = resultSet.getMetaData().getColumnName(idx);
            }

            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    rowValues.add(resultSet.getString(columnName));
                }
            }
            sql.close();
            resultSet.close();
            return rowValues;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    } */
}
