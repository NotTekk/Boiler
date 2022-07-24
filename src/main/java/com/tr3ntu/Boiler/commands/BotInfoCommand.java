package com.tr3ntu.Boiler.commands;

import com.tr3ntu.Boiler.Config;
import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BotInfoCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {

        List<Guild> guilds = ctx.getJDA().getGuilds();

        EmbedBuilder m = new EmbedBuilder();
        m.setColor(Color.BLUE);
        m.setTitle("\uD83E\uDD16   Bot Info\n");
        m.setThumbnail(ctx.getSelfUser().getAvatarUrl());
        m.addField(":id: Bot ID", ctx.getSelfUser().getId(), true);
        m.addField(":id: Version", Config.get("VERSION"), true);
        m.addField("\uD83C\uDF10 Server Count", "In " + String.valueOf(guilds.size()) + " servers",true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
        m.addField(":clock2: Creation date", ctx.getSelfUser().getTimeCreated().format(formatter), true);
        m.setDescription("Boiler's Official Community Server: " + "https://discord.gg/MhVZCW3ZMg\n");
        m.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");


        ctx.getChannel().sendMessageEmbeds(m.build()).queue();
        m.clear();

    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
