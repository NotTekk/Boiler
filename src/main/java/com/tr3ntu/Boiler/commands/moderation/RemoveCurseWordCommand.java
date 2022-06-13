package com.tr3ntu.Boiler.commands.moderation;

import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static com.tr3ntu.Boiler.sql.SQLUtils.removeCurseWord;

public class RemoveCurseWordCommand implements ICommand {

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
            channel.sendMessage("You need to specify the word").queue();
            return;
        }

        final String curseWord = String.join("", args);
        removeCurseWord(ctx.getGuild().getIdLong(), curseWord);

        channel.sendMessageFormat("`%s` removed from the Curse Words List", curseWord).queue();
    }

    @Override
    public String getName() {
        return "removecw";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
