package cc.toin.jinglian;

import cc.toin.jinglian.core.UpgradeCore;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RefineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("getcore")) {
            if (args.length == 0) {
                sender.sendMessage("§8[§6精炼§8] §e>> §c正确用法: /getcore send <hujia/gong/jian> <数量> <玩家名> §7获取对应的精炼石头");
                sender.sendMessage("§8[§6精炼§8] §e>> §c正确用法: /getcore pingce §7对身上的护甲装进行评测");
                sender.sendMessage("§8[§6精炼§8] §e>> §c正确用法: /getcore level <等级> §7将手上的装备进行精炼直升操作");
                return true;
            }
            if (args[0].equalsIgnoreCase("pingce")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§c你必须是一名玩家!");
                    return true;
                }
                Player p = (Player) sender;
                p.sendMessage("§8[§6精炼§8] §e>> §a正在对你身上的装备进行评测...");
//                    boolean is4Plus7Armor = false;
                List<Boolean> check = Lists.newArrayList();
                for (ItemStack itemStack : p.getEquipment().getArmorContents()) {
                    if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
                        if (itemStack.getType().equals(Material.DIAMOND_HELMET) ||
                        		itemStack.getType().equals(Material.DIAMOND_CHESTPLATE) ||
                        		itemStack.getType().equals(Material.DIAMOND_LEGGINGS) ||
                        		itemStack.getType().equals(Material.DIAMOND_BOOTS)) {
                            if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
                                if (Upgrade.hasUpgrade(itemStack.getItemMeta().getDisplayName(), "[7]")) {
                                    check.add(true);
                                }
                            }
                        }
                    }
                }
                Bukkit.getScheduler().runTaskLater(Upgrade.getInstance(), () -> {
                    if (check.size() == 4) {
                        p.sendMessage("§8[§6精炼§8] §e>> §f本次检测到您身上共有 §a§l4 §f件+7护甲, 授予你称号: §6不动如山");
                        Upgrade.getInstance().getConfig().getStringList("PingceCommands").forEach(s ->
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("&", "§")
                                        .replace("%player%", p.getName())));
                    } else {
                        p.sendMessage("§8[§6精炼§8] §e>> §f本次检测到您身上共有 §a§l" + check.size() + " §f件+7护甲 暂未获得对应称号!");
                    }
                }, 20 * 3L);
                return true;
            }

            if (args[0].equalsIgnoreCase("level")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§c你必须是一名玩家!");
                    return true;
                }
                if (!sender.isOp()) {
                	sender.sendMessage("§c权限不足!");
                	return true;
				}
                Player p = (Player) sender;
                if (p.getEquipment().getItemInMainHand() == null || p.getEquipment().getItemInMainHand().getType().equals(Material.AIR)) {
                	p.sendMessage("§c你必须得手持武器才能使用!");
                	return true;
				}
                Upgrade.getInstance().updateMethod(p, p.getItemInHand(), p.getItemInHand().getItemMeta(), Integer.parseInt(args[1]));
                return true;
            }

            if (args[0].equalsIgnoreCase("send")) {
            	if (!sender.isOp()) {
                	sender.sendMessage("§c权限不足!");
                	return true;
				}
                if (args.length >= 4) {
                    String playerName = args[3];
                    String amount = args[2];
                    String stoneName = args[1];
                    Player receiver = Bukkit.getPlayerExact(playerName);
                    if (receiver == null || !receiver.isOnline()) {
                        sender.sendMessage("§c该玩家不在线!");
                        return true;
                    }
                    receiver.getInventory().addItem(UpgradeCore.getByPinyi(stoneName).toItemStack(Integer.parseInt(amount)));
                } else {
                    if (args.length < 3) {
                        sender.sendMessage("§c使用方法: /getcore send <hujia/gong/jian> <数量> <玩家名> §7获取对应的精炼石头");
                        return true;
                    }
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§c你必须是一名玩家!");
                        return true;
                    }
                    Player p = (Player) sender;
                    String stoneName = args[1];
                    String amount = args[2];
                    p.getInventory().addItem(UpgradeCore.getByPinyi(stoneName).toItemStack(Integer.parseInt(amount)));
                }
                return true;
            }
        }
        return true;
    }
}
