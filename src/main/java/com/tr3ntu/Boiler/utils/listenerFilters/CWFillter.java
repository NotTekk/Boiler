package com.tr3ntu.Boiler.utils.listenerFilters;

import com.tr3ntu.Boiler.sql.SQLUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import org.slf4j.*;

public class CWFillter {

    public void filter(GuildMessageReceivedEvent event, long guildId, Logger LOGGER) {
        if(!event.getMessage().getContentRaw().contains("cw")) {
            if (SQLUtils.getModerated(guildId).equals("true")) {
                String message = event.getMessage().getContentRaw().toLowerCase();
                for (String curseWord : SQLUtils.getCurseWords(guildId)) {
                    if (message.contains(curseWord)) {
                        if (!event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_MANAGE)) {
                            LOGGER.warn("No permission to delete messages in #" + event.getChannel().getName() + " at " + event.getGuild().getName());
                            return;
                        }
                        event.getMessage().delete().queue();
                        EmbedBuilder msg = new EmbedBuilder();
                        msg.setColor(Color.RED)
                                .setTitle(" :x: **Oops!**" + "\n")
                                .setDescription(event.getAuthor().getName() + " you cannot say curse words at **" + event.getGuild().getName() + "**! Your message has been deleted!" + "\n\n" + "Deleted message: **" + message + "**\n\n")
                                .setFooter("Your message and behavior have been reported to an Admin.");
                        Objects.requireNonNull(event.getMember()).getUser().openPrivateChannel().queue(channel -> channel.sendMessageEmbeds(msg.build()).queue());

                        long channelId = SQLUtils.getLoggerChannel(guildId);
                        final TextChannel mod = event.getGuild().getTextChannelById(channelId);

                        if (mod == null) {
                            LOGGER.warn("No logger channel found for " + event.getGuild().getName());
                            return;
                        }

                        EmbedBuilder msg1 = new EmbedBuilder();
                        msg1.setColor(Color.RED)
                                .setTitle(" :x: **Bad Behavior Detected!**")
                                .setDescription(event.getMessage().getAuthor().getName() + " said: **" + message + "**")
                                .setFooter("The user has been informed of the rules and that his message was reported to the Admins!");

                        final TextChannel mode = mod;
                        mode.sendMessageEmbeds(msg1.build()).queue();
                        msg1.clear();

                        return;
                    }
                }
            }
        }
    }
}
