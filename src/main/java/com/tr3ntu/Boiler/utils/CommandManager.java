package com.tr3ntu.Boiler.utils;

import com.tr3ntu.Boiler.commands.BotInfoCommand;
import com.tr3ntu.Boiler.commands.HelpCommand;
import com.tr3ntu.Boiler.commands.fun.GifCommand;
import com.tr3ntu.Boiler.commands.fun.YoMamaCommand;
import com.tr3ntu.Boiler.commands.mcUtilities.ServerStatusCommand;
import com.tr3ntu.Boiler.commands.music.*;
import com.tr3ntu.Boiler.commands.utilities.CWConfigCommand;
import com.tr3ntu.Boiler.commands.utilities.GitHubCommand;
import com.tr3ntu.Boiler.commands.utilities.PingCommand;
import com.tr3ntu.Boiler.commands.utilities.WhoIsCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {

        addCommand(new HelpCommand());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A utils with this name is already present");
        }
        //General
        commands.add(new BotInfoCommand());
        commands.add(new HelpCommand());
        //fun
        commands.add(new YoMamaCommand());
        commands.add(new GifCommand());
        //MCUtils
        commands.add(new ServerStatusCommand());
        //Utils
        commands.add(new PingCommand());
        commands.add(new GitHubCommand());
        commands.add(new WhoIsCommand());
        commands.add(new CWConfigCommand());
        //Music
        commands.add(new JoinCommand());
        commands.add(new PlayCommand());
        commands.add(new SkipCommand());
        commands.add(new StopCommand());
        commands.add(new QueueCommand());
        commands.add(new LeaveCommand());
        commands.add(new RepeatCommand());
        commands.add(new NowPlayingCommand());
        commands.add(new ResumeCommand());
        commands.add(new PauseCommand());
        commands.add(new SetVolumeCommand());

    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    public void handle(GuildMessageReceivedEvent event, String prefix) {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }

}