package de.dragonrexx.simplejavabasicsplugin;

import de.dragonrexx.simplejavabasicsplugin.commands.HealCommand;
import de.dragonrexx.simplejavabasicsplugin.commands.McToDcCommand;
import de.dragonrexx.simplejavabasicsplugin.discordBot.DiscordMcBot;
import de.dragonrexx.simplejavabasicsplugin.listener.JoinListener;
import de.dragonrexx.simplejavabasicsplugin.utils.FileBuilder;
import de.dragonrexx.simplejavabasicsplugin.utils.PluginSetup;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class SimpleJavaBasicsPlugin extends JavaPlugin implements PluginSetup {
    private static SimpleJavaBasicsPlugin instance;
    private final DiscordMcBot discordMcBot = new DiscordMcBot();
    private static final FileBuilder fileBuilder = new FileBuilder("plugins//DiscordBot", "config.yml");
    @Override
    public void onEnable() {
        initConfig();
        commandRegistry();
        eventRegistry();
        instance = this;
        if(!fileBuilder.getBoolean("DISCORD_BOT_CAN_START")){
            Bukkit.getConsoleSender().sendMessage("§3[Discord Bot] §4The Bot can Not Start");
            Bukkit.getConsoleSender().sendMessage("§3[Discord Bot] §4Because the the Bool §3DISCORD_BOT_CAN_START §4is set of False");
            Bukkit.getConsoleSender().sendMessage("§3[Discord Bot] §4Please set it of true");
        } else {
            try {
                discordMcBot.McBot();
            } catch (LoginException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onDisable() {
        if(!fileBuilder.getBoolean("DISCORD_BOT_CAN_START")){
            Bukkit.getConsoleSender().sendMessage("§3[Discord Bot] §4The Bot can Not shutdown");
            Bukkit.getConsoleSender().sendMessage("§3[Discord Bot] §4Because the the Bool §3DISCORD_BOT_CAN_START §4is set of False");
            Bukkit.getConsoleSender().sendMessage("§3[Discord Bot] §4Please set it of true");
        } else {
            discordMcBot.shutdownBot();
        }
    }

    @Override
    public void commandRegistry() {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("mctodc").setExecutor(new McToDcCommand());
    }

    @Override
    public void eventRegistry() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
    }

    public void initConfig(){
        if(!fileBuilder.exist()){
            fileBuilder.setValue("DISCORD_TOKEN", "Your Token Here");
            fileBuilder.setValue("DISCORD_GUILD_ID", "Your Guild id Here");
            fileBuilder.setValue("DISCORDTOMC_CHANNEL_ID", "Test_Channel_Id");
            fileBuilder.setValue("DISCORDTOMC_BROADCASTER_ID", "The Id of the Broadcaster role");
            fileBuilder.setValue("DISCORD_BOT_CAN_START", false);
            fileBuilder.save();
        }
    }

    public static SimpleJavaBasicsPlugin getInstance() {
        return instance;
    }

    public static FileBuilder getFileBuilder() {
        return fileBuilder;
    }
}
