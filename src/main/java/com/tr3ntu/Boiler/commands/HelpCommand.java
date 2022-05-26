package com.tr3ntu.Boiler.commands;

import com.tr3ntu.Boiler.utils.CommandContext;
import com.tr3ntu.Boiler.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class HelpCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public boolean handle(CommandContext ctx) {

        EmbedBuilder m = new EmbedBuilder();
        m.setColor(new Color(96,0,160));
        m.setTitle(":bookmark_tabs:   Help\n\n");
        m.addField("Utilities:\n", "`gituser <git-username> - Shows github user info (use github profile username)`\n" +
                "`ping - Checks the bot connection`\n\n", false);
        m.addField("Fun:", "`ym <user>- Makes YO MAMMA jokes with the mentioned user`\n" +
                "`gif <input> - search and displays gifs based on users input`\n\n", false);
        m.addField("Minecraft:", "`mcstatus <ip> - Provides information about the mentioned server`\n\n", false);
        m.addField("Bot:", "`info - Provides information about the bot and invite to its official server`\n\n\n", false);
        m.addField("Stuck? Join our Official Server!", "https://discord.gg/MhVZCW3ZMg", false);
        m.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");

        ctx.getChannel().sendMessageEmbeds(m.build()).queue();
        m.clear();

        LOGGER.info("Help Command - Executed");

        return false;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return null;
    }
}