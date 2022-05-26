package com.tr3ntu.Boiler.commands.utilities;

import com.tr3ntu.Boiler.utils.CommandContext;
import com.tr3ntu.Boiler.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class PingCommand implements ICommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingCommand.class);

    private static long inputTime;

    public static void setInputTime(long inputTimeLong) {
        inputTime = inputTimeLong;
    }

    private Color getColorByPing(long ping) {
        if (ping < 100)
            return Color.cyan;
        if (ping < 175)
            return Color.green;
        if (ping < 250)
            return Color.yellow;
        if (ping < 500)
            return Color.orange;
        return Color.red;
    }

    @Override
    public boolean handle(CommandContext ctx) {

        ctx.getChannel().sendTyping().queue();

        try {
            Thread.sleep(1 * 500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        long ping = ctx.getJDA().getGatewayPing();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(getColorByPing(ping));
        builder.setTitle("\uD83C\uDFD3  Pong!");
        builder.setDescription("Ping : " + ctx.getJDA().getGatewayPing() + " ms");

        ctx.getChannel().sendMessageEmbeds(builder.build()).queue();

        LOGGER.info("Ping Command - Executed");

        return false;
    }

    @Override
    public String getHelp() {
        return "Shows the current ping from the bot to the discord servers";
    }

    @Override
    public String getName() {
        return "ping";
    }
}
