package net.skaerf.PaperWarp.cmds;

import com.earth2me.essentials.Essentials;
import net.skaerf.PaperWarp.PaperWarp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class PaperWarpCommand implements CommandExecutor {

    Essentials es = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("The console cannot interact with this command!");
        }
        else {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lPaper&f&lWarp\n&aCopyrighted to skaerf © 2022"));
            }
            else {
                if (args[0].equalsIgnoreCase("get")) {
                    if (!player.hasPermission("paperwarp.get")) {
                        player.sendMessage(ChatColor.RED+"You don't have permission to do that!");
                    }
                    else {
                        if (args.length == 1) {
                            sender.sendMessage(ChatColor.RED+"Please state a warp name! /paperwarp get <warp>");
                        }
                        else {
                            String warp = args[1];
                            if (!(es.getWarps().getList().contains(warp))) {
                                sender.sendMessage(ChatColor.RED+"That warp doesn't exist! Please state a warp that already exists. /paperwarp get <warp>");
                            }
                            else {
                                ItemStack paperStack = new ItemStack(Material.PAPER, 1);
                                ItemMeta paperMeta = paperStack.getItemMeta();
                                paperMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', PaperWarp.getPlugin(PaperWarp.class).getConfig().getString("paper-name")));
                                ArrayList<String> paperMetaLore = new ArrayList<>();
                                char[] charArray = warp.toCharArray();
                                charArray[0] = Character.toUpperCase(charArray[0]);
                                paperMetaLore.add(ChatColor.translateAlternateColorCodes('&', "&eWarp to "+new String(charArray)));
                                paperMeta.setLore(paperMetaLore);
                                paperStack.setItemMeta(paperMeta);
                                player.getInventory().addItem(paperStack);
                                player.sendMessage(ChatColor.GREEN+"You have been given a paper to warp to "+new String(charArray)+"!");
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
