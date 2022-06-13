package com.tr3ntu.Boiler.listeners;

import com.tr3ntu.Boiler.Config;
import com.tr3ntu.Boiler.sql.SQLUtils;
import com.tr3ntu.Boiler.utils.commandUtils.CommandManager;
import com.tr3ntu.Boiler.utils.listenerFilters.CWFillter;
import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.*;

public class CommandListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandListener.class);
    private final CommandManager manager = new CommandManager();
    public static final Map<Long, String> PREFIXES = new HashMap<>();
    public final String DEFAULT_PREFIX = "<@979349838024015882> ";

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        User user = event.getAuthor();

        if (user.isBot() || event.isWebhookMessage()) {
            return;
        }

        final long guildId = event.getGuild().getIdLong();
        String prefix = PREFIXES.computeIfAbsent(guildId, k -> SQLUtils.getPrefix(guildId, DEFAULT_PREFIX));
        String raw = event.getMessage().getContentRaw();

        if (raw.equalsIgnoreCase(prefix + "shutdown")
                && user.getId().equals(Config.get("OWNER_ID"))) {
            EmbedBuilder m = new EmbedBuilder();
            m.setColor(Color.RED);
            m.setTitle("**Shutting Down**");
            event.getChannel().sendMessageEmbeds(m.build()).queue();
            m.clear();

            LOGGER.info("Shutting down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());

            return;
        }

        if (raw.startsWith(prefix)) {
            manager.handle(event, prefix);
        }

        new CWFillter().filter(event, guildId, LOGGER);

    }
}