package com.tr3ntu.Boiler.sql;

import com.tr3ntu.Boiler.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;

import static com.tr3ntu.Boiler.sql.SQLConnection.getConnection;

public class SQLCreateStatement {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLCreateStatement.class);

    public void createStatement() {
        try (final Statement statement = getConnection().createStatement()) {
            final String defaultPrefix = Config.get("prefix");

            // language=SQLite
            statement.execute("CREATE TABLE IF NOT EXISTS guild_settings (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "guild_id VARCHAR(20) NOT NULL," +
                    "prefix VARCHAR(255) NOT NULL DEFAULT '" + defaultPrefix + "'," +
                    "moderated VARCHAR(10) NOT NULL" + ");");

            statement.execute("CREATE TABLE IF NOT EXISTS curse_words (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "guild_id VARCHAR(20) NOT NULL," +
                    "word VARCHAR(255) NOT NULL " + ");");

            statement.close();

            LOGGER.info("Table initialised");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
