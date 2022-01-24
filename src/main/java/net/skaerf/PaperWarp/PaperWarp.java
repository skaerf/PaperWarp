package net.skaerf.PaperWarp;

import net.skaerf.PaperWarp.cmds.PaperWarpCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class PaperWarp extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getLogger().info("PaperWarp initialised.");
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("paperwarp").setExecutor(new PaperWarpCommand());
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getEquipment().getItemInMainHand() != null) {
            if (player.getEquipment().getItemInMainHand().getType().equals(Material.PAPER)) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (player.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("paper-name")))) {
                        String lore = player.getEquipment().getItemInMainHand().getItemMeta().getLore().get(0).split(" ")[2];
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+event.getPlayer().getName()+" add essentials.warps."+lore);
                        player.getInventory().setItemInMainHand(null);
                        player.sendMessage(ChatColor.GREEN+"You have been given permission to access warp "+lore+"!");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
