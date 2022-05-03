package de.dragonrexx.simplejavabasicsplugin.commands;

import de.dragonrexx.simplejavabasicsplugin.SimpleJavaBasicsPlugin;
import de.dragonrexx.simplejavabasicsplugin.discordBot.DiscordMcBot;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class McToDcCommand implements CommandExecutor {

    private final String guildId = SimpleJavaBasicsPlugin.getFileBuilder().getString("DISCORD_GUILD_ID");
    private final String channelId = SimpleJavaBasicsPlugin.getFileBuilder().getString("DISCORDTOMC_CHANNEL_ID");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player))return false;
        Player player = (Player) sender;
        TextChannel textChannelById = DiscordMcBot.getDiscordBot().getGuildById(guildId).getTextChannelById(channelId);

        String message = player.getName() + " >> " + args[0];
        textChannelById.sendMessage(message).queue();
        player.sendMessage("§3[SimpleJavaBasicsPlugin] §6Your Message is sent to the Discord Server");
        return false;
    }
    // /mctodc <channel> <message>
}
