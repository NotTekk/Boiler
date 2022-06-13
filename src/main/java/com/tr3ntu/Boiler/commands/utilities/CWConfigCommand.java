package com.tr3ntu.Boiler.commands.utilities;

import com.tr3ntu.Boiler.Config;
import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import net.dv8tion.jda.api.entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CWConfigCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        System.out.println("test");
        Writer output;
        User user = ctx.getAuthor();
        String msg = String.valueOf(ctx.getArgs()).replaceAll("[\\[\\]]", "");
        ArrayList<String> WORDS = new ArrayList<>();

        if(user.getId().equals(Config.get("OWNER_ID"))) {
            try {
                Scanner s = new Scanner(new File("cursewords.txt"));
                while (s.hasNext()) {
                    WORDS.add(s.next());
                }
                s.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error " + e);
            }

            if (!WORDS.contains(msg)) {
                try {
                    output = new BufferedWriter(new FileWriter("cursewords.txt", true));
                    output.write("\n" + msg);
                    output.close();
                    System.out.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "cw";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return ICommand.super.getAliases();
    }
}
