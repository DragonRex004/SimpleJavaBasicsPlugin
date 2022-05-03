package de.dragonrexx.simplejavabasicsplugin.commands;

import de.dragonrexx.simplejavabasicsplugin.SimpleJavaBasicsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))return false;
        Player player = (Player) sender;

        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null){
                target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 5, true, false, false));
                player.sendMessage("§3[SimpleJavaBasicsPlugin] §6Du hast dem Spieler: " + target.getName() + " Heilung gegeben!");
            } else {
                player.sendMessage("§3[SimpleJavaBasicsPlugin] §6Der angegebene Spieler ist nicht online!");
            }
        } else {
            player.sendMessage("§3[SimpleJavaBasicsPlugin] §6Bitte benutze /heal <playername>");
        }
       // /heal <playername>

        return false;
    }
}
