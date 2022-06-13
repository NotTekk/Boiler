package com.tr3ntu.Boiler.commands.moderation;

import com.tr3ntu.Boiler.sql.SQLConnection;
import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.tr3ntu.Boiler.sql.SQLUtils.addCurseWord;

public class AddCurseWordCommand implements ICommand {
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
            channel.sendMessage("You need to specify a prefix").queue();
            return;
        }

        final String curseWord = String.join("", args);
        addCurseWord(ctx.getGuild().getIdLong(), curseWord);

        channel.sendMessageFormat("`%s` added to the Curse Words List", curseWord).queue();
    }

    @Override
    public String getName() {
        return "addcw";
    }

    @Override
    public String getHelp() {
        return null;
    }

}
