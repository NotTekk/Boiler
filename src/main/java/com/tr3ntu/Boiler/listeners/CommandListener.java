package com.tr3ntu.Boiler.listeners;

import com.tr3ntu.Boiler.Config;
import com.tr3ntu.Boiler.utils.CommandManager;
import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class CommandListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandListener.class);
    private final CommandManager manager = new CommandManager();
    private final ArrayList<String> CURSE_WORDS = new ArrayList<>();
    private final ArrayList<String> WORDS = new ArrayList<>();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        if(user.isBot() || event.isWebhookMessage()) {
            return;
        }

        final long guildId = event.getGuild().getIdLong();
        String prefix = "<@979349838024015882> ";
        String raw = event.getMessage().getContentRaw();

        if(raw.equalsIgnoreCase(prefix + "shutdown")
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

        if(raw.startsWith(prefix)) {
            manager.handle(event, prefix);
        }

        if(event.getMessage().getContentRaw().contains("cw")) {
            try {
                WORDS.clear();
                Scanner s1 = new Scanner(new File("cursewords.txt"));
                while(s1.hasNext()) {
                    WORDS.add(s1.next());
                }
                s1.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(!event.getMessage().getContentRaw().contains("cw")) {
            if (CURSE_WORDS.isEmpty() || !CURSE_WORDS.equals(WORDS)) {
                try {
                    CURSE_WORDS.clear();
                    Scanner s2 = new Scanner(new File("cursewords.txt"));
                    while (s2.hasNext()) {
                        CURSE_WORDS.add(s2.next());
                    }
                    s2.close();
                    System.out.println("YES!");
                } catch (FileNotFoundException e) {
                    System.out.println("Error " + e);
                }
            }
        }

        String message = event.getMessage().getContentRaw().toLowerCase();
        for(String curseWord : CURSE_WORDS) {
            if(message.contains(curseWord)) {
                if(!event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_MANAGE)) {
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


                final List<TextChannel> mod = event.getGuild().getTextChannelsByName("bot-moderation", true);

                if(mod.isEmpty()) {
                    return;
                }

                EmbedBuilder msg1 = new EmbedBuilder();
                msg1.setColor(Color.RED)
                        .setTitle(" :x: **Bad Behavior Detected!**")
                        .setDescription(event.getMessage().getAuthor().getName() + " said: **" + message + "**")
                        .setFooter("The user has been informed of the rules and that his message was reported to the Admins!");

                final TextChannel mode = mod.get(0);
                mode.sendMessageEmbeds(msg1.build()).queue();
                msg1.clear();

                return;
            }
        }
    }
}