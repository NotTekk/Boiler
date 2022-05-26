package com.tr3ntu.Boiler.utils;

import java.util.Arrays;
import java.util.List;

public interface ICommand {
    boolean handle(CommandContext ctx);

    String getName();

    String getHelp();

    default List<String> getAliases() {
        return List.of();
    }
}
