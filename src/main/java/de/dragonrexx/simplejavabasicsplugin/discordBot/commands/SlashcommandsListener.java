package de.dragonrexx.simplejavabasicsplugin.discordBot.commands;

import de.dragonrexx.simplejavabasicsplugin.SimpleJavaBasicsPlugin;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.Objects;

public class SlashcommandsListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event){
        String message = Objects.requireNonNull(event.getOption("msg")).getAsString();
        String broadcastmsg = Objects.requireNonNull(event.getOption("broadcastmsg")).getAsString();
        User user = Objects.requireNonNull(event.getMember()).getUser();
        Role broadcaster = event.getGuild().getRoleById(SimpleJavaBasicsPlugin.getFileBuilder().getString("DISCORDTOMC_BROADCASTER_ID"));
        String PlayersOnServer = Bukkit.getServer().getMaxPlayers() + " / " + Bukkit.getServer().getOnlinePlayers().size();

        if(event.getName().equalsIgnoreCase("avatar")){
            event.getChannel().sendMessage(user.getAvatarUrl()).queue();
        }

        if(event.getName().equalsIgnoreCase("serverstatus")){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(Bukkit.getServer().getName());
            embedBuilder.setColor(Color.RED);
            embedBuilder.setDescription(Bukkit.getServer().getMotd());
            embedBuilder.addField("IP:", Bukkit.getServer().getIp(), true);
            embedBuilder.addField("PLAYERS:", PlayersOnServer, true);
            embedBuilder.addField("VERSION:", Bukkit.getServer().getVersion(), true);
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        }

        if(event.getName().equalsIgnoreCase("mctodc")){
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage("§3[SimpleJavaBasicsPlugin] §6" + user.getName() + " >> " + message);
            });
        }

        if(event.getName().equalsIgnoreCase("broadcastermsg")) {
            if (event.getMember().getRoles().contains(broadcaster)) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendTitle(broadcastmsg, "§3Euer ServerTeam", 20, 100, 20);
                });
            }
        }
    }
}
