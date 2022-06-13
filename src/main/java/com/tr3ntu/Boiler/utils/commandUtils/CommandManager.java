package com.tr3ntu.Boiler.utils.commandUtils;

import com.tr3ntu.Boiler.commands.BotInfoCommand;
import com.tr3ntu.Boiler.commands.HelpCommand;
import com.tr3ntu.Boiler.commands.SetPrefixCommand;
import com.tr3ntu.Boiler.commands.fun.GifCommand;
import com.tr3ntu.Boiler.commands.fun.YoMamaCommand;
import com.tr3ntu.Boiler.commands.mcUtilities.ServerStatusCommand;
import com.tr3ntu.Boiler.commands.moderation.*;
import com.tr3ntu.Boiler.commands.music.*;
import com.tr3ntu.Boiler.commands.utilities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        //General
        addCommand(new BotInfoCommand());
        addCommand(new HelpCommand());
        //fun
        addCommand(new YoMamaCommand());
        addCommand(new GifCommand());
        //MCUtils
        addCommand(new ServerStatusCommand());
        //Utils
        addCommand(new PingCommand());
        addCommand(new SetPrefixCommand());
        addCommand(new GitHubCommand());
        addCommand(new WhoIsCommand());
        addCommand(new CWConfigCommand());
        //Management
        addCommand(new ActivateModerationCommand());
        addCommand(new AddCurseWordCommand());
        addCommand(new RemoveCurseWordCommand());
        addCommand(new SetLoggerChannelCommand());
        addCommand(new RemoveLoggerChannelCommand());
        //Music
        addCommand(new JoinCommand());
        addCommand(new PlayCommand());
        addCommand(new SkipCommand());
        addCommand(new StopCommand());
        addCommand(new QueueCommand());
        addCommand(new LeaveCommand());
        addCommand(new RepeatCommand());
        addCommand(new NowPlayingCommand());
        addCommand(new ResumeCommand());
        addCommand(new PauseCommand());
        addCommand(new SetVolumeCommand());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A utils with this name is already present");
        }

        commands.add(cmd);

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