package com.tr3ntu.Boiler.commands.utilities;

import com.tr3ntu.Boiler.Config;
import com.tr3ntu.Boiler.utils.CommandContext;
import com.tr3ntu.Boiler.utils.ICommand;
import net.dv8tion.jda.api.entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CWConfigCommand implements ICommand {
    @Override
    public boolean handle(CommandContext ctx) {
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
        return false;
    }

    @Override
    public String getName() {
        return "cw";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
