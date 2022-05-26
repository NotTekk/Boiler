package com.tr3ntu.Boiler.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Objects;

public class MainListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainListener.class);

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        Guild guild = event.getGuild();

        EmbedBuilder server = new EmbedBuilder();
        server.setColor(Color.CYAN);
        server.setTitle("**Hallo! Na Du?**\n");
        server.setDescription("Thank you for adding **Boiler** to your server!\n" +
                "**Boiler** is a multipurpose bot! Logger, Chat Auto-Moderation, Fun, and Coding!\n\n" +
                "Please use `@Boiler help` to get started~\n\n" +
                "Important! If you would like to activate the Chat Auto-Moderation just give **Boiler** the `Manage Messages` permission! And simply create a message channel called `bot-moderation`, this is the bad behaviour LOGGER for admins ;)\n\n" +
                "For Support, Updates and Suggestions please join Boiler Dev Official Server!\n" + "https://discord.gg/MhVZCW3ZMg");
        server.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");
        Objects.requireNonNull(guild.getDefaultChannel()).sendMessageEmbeds(server.build()).queue();
        server.clear();

    }

}
