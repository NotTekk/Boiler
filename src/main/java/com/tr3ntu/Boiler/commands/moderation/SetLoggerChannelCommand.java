package com.tr3ntu.Boiler.commands.moderation;

import com.tr3ntu.Boiler.sql.SQLUtils;
import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static com.tr3ntu.Boiler.sql.SQLUtils.setLoggerChannel;

public class SetLoggerChannelCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final List<String> args = ctx.getArgs();
        final Member member = ctx.getMember();

        if (!member.hasPermission(Permission.MANAGE_SERVER)) {
            channel.sendMessage("You don't have permission to use this command").queue();
            return;
        }

        if (args.isEmpty()) {
            channel.sendMessage("You need to specify a channel").queue();
            return;
        }

        final String channelName = String.join("", args);
        final long channelId = ctx.getGuild().getTextChannelsByName(channelName, true).get(0).getIdLong();
        setLoggerChannel(ctx.getGuild().getIdLong(), channelId);

        channel.sendMessageFormat("Logger channel set to `%s`", channelName).queue();
    }

    @Override
    public String getName() {
        return "setlog";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
