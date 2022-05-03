package de.dragonrexx.simplejavabasicsplugin.discordBot;

import de.dragonrexx.simplejavabasicsplugin.SimpleJavaBasicsPlugin;
import de.dragonrexx.simplejavabasicsplugin.discordBot.commands.SlashcommandsListener;
import de.dragonrexx.simplejavabasicsplugin.discordBot.listener.MessageListener;
import de.dragonrexx.simplejavabasicsplugin.utils.FileBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import javax.security.auth.login.LoginException;

public class DiscordMcBot {

    private static JDA discordBot;
    private final FileBuilder fileBuilder = SimpleJavaBasicsPlugin.getFileBuilder();

    public void McBot() throws LoginException {
        discordBot = JDABuilder.createDefault(fileBuilder.getString("DISCORD_TOKEN")).build();
        discordBot.getPresence().setStatus(OnlineStatus.ONLINE);
        discordBot.getPresence().setActivity(Activity.listening("www.Cubyx.eu"));
        discordBot.addEventListener(new MessageListener());
        discordBot.addEventListener(new SlashcommandsListener());
        initSlashCommands();
    }

    public void shutdownBot() {
        discordBot.getPresence().setStatus(OnlineStatus.OFFLINE);
        discordBot.getPresence().setActivity(Activity.listening("The Bot is not Online"));
        discordBot.shutdown();
    }

    public void initSlashCommands(){
        discordBot.updateCommands().addCommands(new CommandData("avatar", "With this Command you can get your Avatar Picture")).queue();
        discordBot.updateCommands().addCommands(new CommandData("serverstatus", "This Command sent you a Embed from the Minecraft Server")).queue();
        discordBot.updateCommands().addCommands(new CommandData("mctodc", "With this Command you can sent Messages to the Minecraft Server").addOption(OptionType.STRING, "msg", "Please put your Message here", true)).queue();
        discordBot.updateCommands().addCommands(new CommandData("broadcastermsg", "With this Command you can sent Broadcaster Messages to the Minecraft Server").addOption(OptionType.STRING, "broadcastmsg", "Please put your Broadcast Message here", true)).queue();
        discordBot.updateCommands().queue();

        }

    public static JDA getDiscordBot() {
        return discordBot;
    }
}
