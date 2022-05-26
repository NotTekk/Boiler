package com.tr3ntu.Boiler.listeners;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PrivateListener extends ListenerAdapter {

    private final String[] arr = {"Yes", "No"};

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }

        if(event.getMessage().getContentRaw().endsWith("?")) {
            Random random = new Random();
            int select = random.nextInt(arr.length);
            event.getMessage().getChannel().sendMessage(arr[select]).queue();
        }
    }
}
