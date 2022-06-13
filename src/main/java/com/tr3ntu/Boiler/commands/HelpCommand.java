package com.tr3ntu.Boiler.commands;

import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

public class HelpCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public void handle(CommandContext ctx) {

        String args = ctx.getMessage().getContentRaw();

        if(args.contains("music")) {
            EmbedBuilder m = new EmbedBuilder();
            m.setColor(new Color(230, 0, 126));
            m.setTitle(":notes:   Music Help");
            m.addField("Player Commands:", "`play <YT Link / Search Pattern> - Plays song based on user input`\n" +
                    "`pause - Pauses the song`\n" +
                    "`resume - Resumes the song`\n" +
                    "`stops - Stops the player and clears the queue`\n" +
                    "`repeat - Sets the bot to repeat the song/playlist`\n" +
                    "`skip - Skips the current song`\n" +
                    "`setvol - Sets the volume of the bot`\n" +
                    "`queue - Shows the current queue`\n" +
                    "`nowpl - Shows the current playing song`\n\n", false);
            m.addField("Player Utils:", "`join - joins the user's current voice channel`\n" +
                    "`leave - Leaves the voice channel`\n\n\n", false);
            m.addField("Stuck? Join our Official Server!", "https://discord.gg/MhVZCW3ZMg", false);
            m.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");

            ctx.getChannel().sendMessageEmbeds(m.build()).queue();
            m.clear();
            return;
        } else if (args.contains("admin") && ctx.getMember().hasPermission(Permission.MANAGE_SERVER)) {
            EmbedBuilder m = new EmbedBuilder();
            m.setColor(new Color(230, 73, 0));
            m.setTitle(":hammer:   Admin Help");
            m.addField("Admin Commands:", "`moderation <true / false> - Enables or disables moderation`\n" +
                    "`addcw - Adds word to the Black List`\n" +
                    "`removecw - Removes word to the Black List`\n" +
                    "`setlog - Stops the player and clears the queue`\n" +
                    "`removelog - Sets the bot to repeat the song/playlist`\n\n\n", false);
            m.addField("Stuck? Join our Official Server!", "https://discord.gg/MhVZCW3ZMg", false);
            m.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");

            ctx.getChannel().sendMessageEmbeds(m.build()).queue();
            m.clear();
            return;
        } else if (args.contains("admin") && !ctx.getMember().hasPermission(Permission.MANAGE_SERVER)) {
            EmbedBuilder m = new EmbedBuilder();
            m.setColor(new Color(255, 0, 0));
            m.setTitle(":x:   Oops!");
            m.addField("You don't have permission to use this command! **Admins only!**", "`Try the command help again!`\n\n\n", false);
            m.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");

            ctx.getChannel().sendMessageEmbeds(m.build()).queue();
            m.clear();
            return;
        } else {

            EmbedBuilder m = new EmbedBuilder();
            m.setColor(new Color(96, 0, 160));
            m.setTitle(":bookmark_tabs:   Help\n\n");
            m.addField("Utilities:\n", "`gituser <git-username> - Shows github user info (user GH profile username)`\n" +
                    "`ping - Checks the bot connection`\n\n", false);
            m.addField("Fun:", "`ym <user>- Makes YO MAMMA jokes with the mentioned user`\n" +
                    "`gif <input> - search and displays gifs based on users input`\n\n", false);
            m.addField("Minecraft:", "`mcstatus <ip> - Provides information about the mentioned server`\n\n", false);
            m.addField("Bot:", "`info - Provides information about the bot and invite to its official server`\n\n\n", false);
            m.setDescription("\n\n**Music Help?** `help music`\n" + "**Admin Help?** `help admin`\n\n");
            m.addField("Stuck? Join our Official Server!", "https://discord.gg/MhVZCW3ZMg", false);
            m.setFooter("\n\nCreated by Tr3ntu#0001", "https://images-ext-1.discordapp.net/external/JLnQ9CN_ZuN_yF8nDAHN22SwE2njO3Uepz_UpIigFUw/https/cdn.discordapp.com/avatars/571414793454485511/a_cd71c8973dc4be2f6fa650561b081d84.gif");

            ctx.getChannel().sendMessageEmbeds(m.build()).queue();
            m.clear();

        }

        LOGGER.info("Help Command - Executed");

    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return ICommand.super.getAliases();
    }
}