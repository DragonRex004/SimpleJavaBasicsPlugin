package de.dragonrexx.simplejavabasicsplugin.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class JoinListener implements Listener {

    @EventHandler
    public  void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String message = "§3[SimpleJavaBasicsPlugin] §6Hallo wie gehts?";
        event.setJoinMessage(message);
        player.sendTitle("§3[SimpleJavaBasicsPlugin]", player.getName(), 20, 100, 20);
        player.sendMessage(player.getUniqueId().toString());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().contains(Material.COMMAND_BLOCK)) {
            player.getLocation().getBlock().getRelative(0, -1, 0).setType(Material.DIAMOND_ORE);
        }
    }
}
