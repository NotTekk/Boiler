package com.tr3ntu.Boiler.commands.fun;

import com.tr3ntu.Boiler.Config;
import com.tr3ntu.Boiler.utils.commandUtils.CommandContext;
import com.tr3ntu.Boiler.utils.commandUtils.ICommand;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GifCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {

        String url;
        JSONArray array;
        String query = "";

        for(String arg : ctx.getArgs()) {
            query += arg.toLowerCase() + "+";
            query = query.substring(0, query.length()-1);
        }

        OkHttpClient caller = new OkHttpClient();
        Request request = new Request.Builder().url("http://api.giphy.com/v1/gifs/search?q=" + query + Config.get("GIF_API_KEY")).build();

        try {
            Random rand = new Random();
            Response response = caller.newCall(request).execute();
            JSONObject json = new JSONObject(Objects.requireNonNull(response.body()).string());
            array = json.getJSONArray("data");
            //Random GIF returned by the API
            int gifIndex = rand.nextInt(Math.abs(array.length()));
            url = (String) array.getJSONObject(gifIndex).get("url");
            ctx.getChannel().sendMessage(url).queue();
        } catch (IOException | NullPointerException e) {
            ctx.getChannel().sendMessage("No GIF found :cry:").queue();
        }


    }

    @Override
    public String getName() {
        return "gif";
    }

    @Override
    public String getHelp() {
        return "shows a gif based on user input";
    }

}
