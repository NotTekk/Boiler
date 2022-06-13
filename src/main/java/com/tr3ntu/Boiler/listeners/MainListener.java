package com.tr3ntu.Boiler.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.requests.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.*;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainListener.class);

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        
        Guild guild = event.getGuild();
        TextChannel channel = event.getGuild().getTextChannels().stream().filter(TextChannel::canTalk).findFirst().orElse(null);

        if (!(channel == null)) {
            EmbedBuilder server = new EmbedBuilder();
            server.setColor(Color.CYAN);
            server.setTitle("**Hallo! Na Du?**\n");
            server.setDescription("Thank you for adding **Boiler** to your server!\n" +
                    "**Boiler** is a multipurpose bot! Music, Chat Moderation, Minecraft, Fun, GitHub and much **MORE**!\n\n" +
                    "Please use `@Boiler help` to get started~\n\n" +
                    "FYI: Admins with Manage Server perm:\n" +
                    "If you would like to set up the Chat Moderation, you must activate the moderation for your server by using `moderation true` (it is `false` by default), afterwards you can set up the **Black Listed Words** by using `addcw <word>`! ;)\n" +
                    "If you would like to have a logger channel (To know who bad behaves :smiling_imp:), create a channel and set it as your logger by using `setlog <channel name>`!\n\n" +
                    "For Support, Updates and Suggestions please join BoilerDev Official Server!\n" + "https://discord.gg/MhVZCW3ZMg");
            server.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");
            channel.sendMessageEmbeds(server.build()).queue();
            server.clear();
        }
    }
}
