package com.tr3ntu.Boiler.commands.fun;

import com.tr3ntu.Boiler.utils.CommandContext;
import com.tr3ntu.Boiler.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class YoMamaCommand implements ICommand {

    @Override
    public boolean handle(CommandContext ctx) {

        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        Color randomColor = new Color(r, g, b);

        List<User> mentionedUsers = ctx.getMessage().getMentionedUsers();
        List<User> modifiable = new ArrayList<>(mentionedUsers);
        modifiable.remove(0);

        if (ctx.getMessage().getMentionedUsers().isEmpty())
        {
            ctx.getChannel().sendMessage("No user mentioned.");
        }
        else {
            String joke;
            OkHttpClient caller = new OkHttpClient();
            Request request = new Request.Builder().url("http://api.yomomma.info/").build();
            for(User u : modifiable) {
                try {
                    Response response = caller.newCall(request).execute();
                    JSONObject json = new JSONObject(Objects.requireNonNull(response.body()).string());
                    joke = (String) json.get("joke");
                    EmbedBuilder m = new EmbedBuilder();
                    m.setColor(randomColor);
                    m.setTitle(":eggplant: **Yo Mama!**");
                    m.setDescription(u.getAsMention() + " " + joke + "!");
                    ctx.getChannel().sendMessageEmbeds(m.build()).queue();
                } catch (IOException | NullPointerException e) {
                    ctx.getChannel().sendMessage("No joke found").queue();
                }
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "ym";
    }

    @Override
    public String getHelp() {
        return "Yo Mamma Joke";
    }
}
