package com.tr3ntu.Boiler.commands.utilities;

import com.tr3ntu.Boiler.utils.CommandContext;
import com.tr3ntu.Boiler.utils.ICommand;

public class WhoIsCommand implements ICommand {
    @Override
    public boolean handle(CommandContext ctx) {
        String msg = String.valueOf(ctx.getArgs()).replaceAll("[\\[\\]]", "");

        System.out.println(msg);
        return false;
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
