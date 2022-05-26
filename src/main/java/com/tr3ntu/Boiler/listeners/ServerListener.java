package com.tr3ntu.Boiler.listeners;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class ServerListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        final List<TextChannel> dontDoThis = event.getGuild().getTextChannelsByName("bot-spam", true);

        if (dontDoThis.isEmpty()) {
            return;
        }

        final TextChannel pleaseDontDoThisAtAll = dontDoThis.get(0);

        final String useGuildSpecificSettingsInstead = String.format("Welcome %s to %s",
                event.getMember().getUser().getAsTag(), event.getGuild().getName());

        pleaseDontDoThisAtAll.sendMessage(useGuildSpecificSettingsInstead).queue();
    }

    @Override
    public void onGuildMemberLeave(@Nonnull GuildMemberLeaveEvent event) {
        final List<TextChannel> dontDoThis = event.getGuild().getTextChannelsByName("bot-spam", true);

        if (dontDoThis.isEmpty()) {
            return;
        }

        final TextChannel pleaseDontDoThisAtAll = dontDoThis.get(0);

        final String useGuildSpecificSettingsInstead = String.format("Goodbye %s",
                event.getMember().getUser().getAsTag());

        pleaseDontDoThisAtAll.sendMessage(useGuildSpecificSettingsInstead).queue();
    }
}
