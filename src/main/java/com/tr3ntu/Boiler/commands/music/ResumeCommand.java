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

public class ResumeCommand implements ICommand {
    @Override
    public boolean handle(CommandContext ctx) {
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

        musicManager.scheduler.player.setPaused(false);

        EmbedBuilder m = new EmbedBuilder();
        m.setTitle("\uD83D\uDCC0  The player has been resumed");
        m.setColor(Color.cyan);

        channel.sendMessageEmbeds(m.build()).queue();
        m.clear();
        return false;
    }

    @Override
    public String getName() {
        return "resume";
    }

    @Override
    public String getHelp() {
        return "Resumes the player";
    }
}

