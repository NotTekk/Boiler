package com.tr3ntu.Boiler.commands.moderation;

import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static com.tr3ntu.Boiler.sql.SQLUtils.removeLoggerChannel;
import static com.tr3ntu.Boiler.sql.SQLUtils.setLoggerChannel;

public class RemoveLoggerChannelCommand implements ICommand {


    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final List<String> args = ctx.getArgs();
        final Member member = ctx.getMember();

        if (!member.hasPermission(Permission.MANAGE_SERVER)) {
            channel.sendMessage("You don't have permission to use this command").queue();
            return;
        }

        removeLoggerChannel(ctx.getGuild().getIdLong());

        channel.sendMessage("Logger channel removed").queue();
    }

    @Override
    public String getName() {
        return "removelog";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
