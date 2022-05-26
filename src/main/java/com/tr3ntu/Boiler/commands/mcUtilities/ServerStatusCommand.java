package com.tr3ntu.Boiler.commands.mcUtilities;

import com.tr3ntu.Boiler.utils.CommandContext;
import com.tr3ntu.Boiler.utils.DataHandling;
import com.tr3ntu.Boiler.utils.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class ServerStatusCommand implements ICommand {

    @Override
    public boolean handle(CommandContext ctx) {

        String msg = String.valueOf(ctx.getArgs()).replaceAll("[\\[\\]]", "");
        OkHttpClient caller = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.mcsrvstat.us/2/" + msg).build();
        EmbedBuilder builder = new EmbedBuilder();

        ctx.getChannel().sendTyping().queue();

        try {
            Response response = caller.newCall(request).execute();
            JSONObject json = new JSONObject(Objects.requireNonNull(response.body()).string());

            boolean online = json.getBoolean("online");
            String hostname = json.getString("hostname");
            String version = json.getString("version");
            String ip = json.getString("ip");
            String motd = json.getJSONObject("motd").getJSONArray("clean").toString().replaceAll("[\"\\[\\]]", "");
            String imageString = json.getString("icon");
            int port = json.getInt("port");
            int ponline = json.getJSONObject("players").getInt("online");
            int pmax = json.getJSONObject("players").getInt("max");

            BufferedImage img = new DataHandling().bufferedImage(imageString);
            new DataHandling().imageHandling(img);
            File file = new File("image.png");

            if(online) {
                builder.setColor(new Color(29, 144, 13));
                builder.setAuthor("MC Server status for " + hostname);
                builder.setThumbnail("attachment://image.png");
                builder.setDescription(":white_check_mark: The Server is Online!\n");
                builder.addField("MOTD ", motd, false);
                builder.addField("Players Online ", ponline + "/" + pmax, true);
                builder.addField("IP", String.valueOf(ip), true);
                builder.addField("Port ", String.valueOf(port), true);
                builder.addField("Version ", version, true);

                ctx.getChannel().sendMessageEmbeds(builder.build()).addFile(file, "image.png").queue();
                builder.clear();
            } else {
                builder.setColor(Color.red);
                builder.addField("The server is offline - ", hostname,true);
                builder.clear();
            }


        } catch (IOException | NullPointerException e) {
            ctx.getChannel().sendMessage("The MC Server Status API is currently Offline - Please try again later").queue();
        } catch (JSONException e) {
            ctx.getChannel().sendMessage("Server Not Found").queue();
        }

        return false;
    }

    @Override
    public String getName() {
        return "mcstatus";
    }

    @Override
    public String getHelp() {
        return "Returns info about the refered MC Server";
    }

}
