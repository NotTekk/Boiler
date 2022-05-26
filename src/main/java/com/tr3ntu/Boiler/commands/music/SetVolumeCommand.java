package com.tr3ntu.Boiler.commands.music;

import com.tr3ntu.Boiler.audioHandler.GuildMusicManager;
import com.tr3ntu.Boiler.audioHandler.PlayerManager;
import com.tr3ntu.Boiler.utils.CommandContext;
import com.tr3ntu.Boiler.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class SetVolumeCommand implements ICommand {
    @Override
    public boolean handle(CommandContext ctx) {
        String s = ctx.getMessage().getContentRaw().split(" ")[2];
        final int volume = Integer.parseInt(s);
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inVoiceChannel()) {
            EmbedBuilder m = new EmbedBuilder();
            m.setTitle(" \u274c  Boiler needs to be in a voice channel for this to work!");
            m.setColor(Color.red);

            channel.sendMessageEmbeds(m.build()).queue();
            m.clear();
            return false;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            EmbedBuilder m = new EmbedBuilder();
            m.setTitle(" \u26d4  You need to be in a voice channel for this command to work!");
            m.setColor(Color.red);

            channel.sendMessageEmbeds(m.build()).queue();
            m.clear();
            return false;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            EmbedBuilder m = new EmbedBuilder();
            m.setTitle(" \u26d4  You need to be in the same voice channel as Boiler for this command to work!");
            m.setColor(Color.red);

            channel.sendMessageEmbeds(m.build()).queue();
            m.clear();
            return false;
        }

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());

        if (volume > 100) {
            EmbedBuilder m = new EmbedBuilder();
            m.setTitle(" \u274c  Volume cannot be higher than 200!");
            m.setColor(Color.red);

            channel.sendMessageEmbeds(m.build()).queue();
            m.clear();
            return false;
        } else if (volume < 0) {
            EmbedBuilder m = new EmbedBuilder();
            m.setTitle(" \u274c  Volume cannot be lower than 0!");
            m.setColor(Color.red);

            channel.sendMessageEmbeds(m.build()).queue();
            m.clear();
            return false;
        }

        musicManager.scheduler.player.setVolume(volume);

        EmbedBuilder m = new EmbedBuilder();
        m.setTitle("\uD83D\uDCC0  Volume has been set to: " + volume + "%");
        m.setColor(Color.green);

        channel.sendMessageEmbeds(m.build()).queue();
        m.clear();
        return false;
    }

    @Override
    public String getName() {
        return "volume";
    }

    @Override
    public String getHelp() {
        return "Stops the current song and clears the queue";
    }
}

