package com.tr3ntu.Boiler.commands.utilities;

import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;

public class WhoIsCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        String msg = String.valueOf(ctx.getArgs()).replaceAll("[\\[\\]]", "");

        System.out.println(msg);
    }

    @Override
    public String getName() {
        return "whois";
    }

    @Override
    public String getHelp() {
        return "test";
    }
}
