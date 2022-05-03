package de.dragonrexx.simplejavabasicsplugin.discordBot.listener;

import de.dragonrexx.simplejavabasicsplugin.SimpleJavaBasicsPlugin;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.awt.*;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String message = event.getMessage().getContentRaw();
        User user = event.getMember().getUser();
        Role broadcaster = event.getGuild().getRoleById(SimpleJavaBasicsPlugin.getFileBuilder().getString("DISCORDTOMC_BROADCASTER_ID"));
        String PlayersOnServer = Bukkit.getServer().getMaxPlayers() + " / " + Bukkit.getServer().getOnlinePlayers().size();
        MessageChannel channel = event.getChannel();

        if(message.equalsIgnoreCase("//avatar")){
            event.getChannel().sendMessage(user.getAvatarUrl()).queue();
        }

        if(message.equalsIgnoreCase("//serverstatus")){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(Bukkit.getServer().getName());
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription(Bukkit.getServer().getMotd());
            embedBuilder.addField("IP:", Bukkit.getServer().getIp(), true);
            embedBuilder.addField("PLAYERS:", PlayersOnServer, true);
            embedBuilder.addField("VERSION:", Bukkit.getServer().getVersion(), true);
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }

        if(message.startsWith("//mctodc")){
            String message1 = message.replace("//mctodc", user.getName() + " >> ");
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage("§3[SimpleJavaBasicsPlugin] §6" + message1);
            });
        }

        if(message.startsWith("//broadcastermsg")){
            if (event.getMember().getRoles().contains(broadcaster)) {
                String broadcastmsg = message.replace("//broadcastermsg", "");
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendTitle(broadcastmsg, "§3Euer ServerTeam", 20, 100, 20);
                });
            }
        }
    }
}
