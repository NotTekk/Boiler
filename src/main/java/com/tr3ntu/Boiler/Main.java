package com.tr3ntu.Boiler;

import com.tr3ntu.Boiler.listeners.CommandListener;
import com.tr3ntu.Boiler.listeners.MainListener;
import com.tr3ntu.Boiler.listeners.PrivateListener;
import com.tr3ntu.Boiler.listeners.ServerListener;
import com.tr3ntu.Boiler.sql.SQLConnection;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.sql.SQLException;
import java.util.EnumSet;

public class Main {

    public static void main(String[] args) throws SQLException {

        SQLConnection.getConnection();

        try {
            JDABuilder.createDefault(
                            Config.get("TOKEN"),
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_VOICE_STATES,
                            GatewayIntent.DIRECT_MESSAGES
                    )
                    .disableCache(EnumSet.of(
                            CacheFlag.CLIENT_STATUS,
                            CacheFlag.ACTIVITY,
                            CacheFlag.EMOTE
                    ))
                    .enableCache(CacheFlag.VOICE_STATE)
                    .addEventListeners(
                            new MainListener(),
                            new CommandListener(),
                            new ServerListener(),
                            new PrivateListener())
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .setActivity(Activity.watching(Config.get("WATCHING")))
                    .build();

        } catch(Exception e) {
            System.out.println("Error found:" + e);
        }
    }
}
