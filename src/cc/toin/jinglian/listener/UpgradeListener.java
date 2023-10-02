package cc.toin.jinglian.listener;

import cc.toin.jinglian.Upgrade;
import cc.toin.jinglian.core.UpgradeCore;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class UpgradeListener implements Listener {

    public static Map inUpgrade = Maps.newHashMap();

    public boolean inUpgradeStatus(String name, UpgradeCore core) {
        return inUpgrade.containsKey(name) && inUpgrade.containsValue(core);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if (inUpgrade.containsKey(e.getPlayer().getName())) {
            inUpgrade.remove(e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.isRightClick()) {
            if (e.getCurrentItem().equals(UpgradeCore.GONG.toItemStack(1))) {
                if (e.getInventory().getType() != InventoryType.CRAFTING) {
                    p.sendMessage("§8[§6精炼§8] §e>> §c请在背包内右键");
                    return;
                }

                if (inUpgradeStatus(p.getName(), UpgradeCore.GONG)) {
                    return;
                }

                e.setCancelled(true);
                p.updateInventory();
                p.closeInventory();
                p.sendMessage("§8[§6精炼§8] §e>> §a你正在使用§b§l青玄陨铁");
                p.sendMessage("  §f- 按E打开背包，鼠标右键点击一把 §b§l弓 §f来精炼它");
                p.sendMessage("  §f- 或者右键点击背包空位来取消本次精炼");
                inUpgrade.put(p.getName(), UpgradeCore.GONG); // 表示正在精炼弓
            }

            if (e.getCurrentItem().equals(UpgradeCore.JIAN.toItemStack(1))) {
                if (e.getInventory().getType() != InventoryType.CRAFTING) {
                    p.sendMessage("§8[§6精炼§8] §e>> §c请在背包内右键");
                    return;
                }

                if (inUpgradeStatus(p.getName(), UpgradeCore.JIAN)) {
                    return;
                }

                e.setCancelled(true);
                p.updateInventory();
                p.closeInventory();
                p.sendMessage("§8[§6精炼§8] §e>> §a你正在使用§4§l红莲陨铁");
                p.sendMessage("  §f- 按E打开背包，鼠标右键点击一把 §b§l钻石剑 §f来精炼它");
                p.sendMessage("  §f- 或者右键点击背包空位来取消本次精炼");
                inUpgrade.put(p.getName(), UpgradeCore.JIAN);
            }

            if (e.getCurrentItem().equals(UpgradeCore.HUJIA.toItemStack(1))) {
                if (e.getInventory().getType() != InventoryType.CRAFTING) {
                    p.sendMessage("§8[§6精炼§8] §e>> §c请在背包内右键");
                    return;
                }

                if (inUpgradeStatus(p.getName(), UpgradeCore.HUJIA)) {
                    return;
                }

                e.setCancelled(true);
                p.updateInventory();
                p.closeInventory();
                p.sendMessage("§8[§6精炼§8] §e>> §a你正在使用§6§l帝王黄玉");
                p.sendMessage("  §f- 按E打开背包，鼠标右键点击一件装备 §b§l(钻石头盔/钻石胸甲/钻石护腿/钻石靴子) §f来精炼它");
                p.sendMessage("  §f- 或者右键点击背包空位来取消本次精炼");
                inUpgrade.put(p.getName(), UpgradeCore.HUJIA);
            }

            // 此处正在判断是否点击的是武器
            ItemStack item;
            Material type = e.getCurrentItem().getType();
            if (inUpgradeStatus(p.getName(), UpgradeCore.HUJIA)) {
                if (type.equals(Material.DIAMOND_HELMET) || type.equals(Material.DIAMOND_CHESTPLATE) || type.equals(Material.DIAMOND_LEGGINGS) || type.equals(Material.DIAMOND_BOOTS)) {
                    item = e.getCurrentItem();
                    Upgrade.getInstance().upgradeItem(p, UpgradeCore.HUJIA, item);
                    e.setCancelled(true);
                    e.setCurrentItem(item);
                }
            } else if (inUpgradeStatus(p.getName(), UpgradeCore.GONG)) {
                if (type.equals(Material.BOW)) {
                    item = e.getCurrentItem();
                    p.closeInventory();
                    Upgrade.getInstance().upgradeItem(p, UpgradeCore.GONG, item);
                    e.setCancelled(true);
                    e.setCurrentItem(item);
                }
            } else if (inUpgradeStatus(p.getName(), UpgradeCore.JIAN) && type.equals(Material.DIAMOND_SWORD)) {
                item = e.getCurrentItem();
                p.closeInventory();
                Upgrade.getInstance().upgradeItem(p, UpgradeCore.JIAN, item);
                e.setCancelled(true);
                e.setCurrentItem(item);
            }

            if (inUpgradeStatus(p.getName(), UpgradeCore.HUJIA)) {
                if (e.getCurrentItem().getType().equals(Material.AIR)) {
                    e.setCancelled(true);
                    p.closeInventory();
                    p.sendMessage("§8[§6精炼§8] §e>> §a你取消了本次精炼!");
                    inUpgrade.remove(p.getName());
                }
            } else if (inUpgradeStatus(p.getName(), UpgradeCore.GONG)) {
                if (e.getCurrentItem().getType().equals(Material.AIR)) {
                    e.setCancelled(true);
                    p.closeInventory();
                    p.sendMessage("§8[§6精炼§8] §e>> §a你取消了本次精炼!");
                    inUpgrade.remove(p.getName());
                }
            } else if (inUpgradeStatus(p.getName(), UpgradeCore.JIAN) && (e.getCurrentItem().getType().equals(Material.AIR))) {
                e.setCancelled(true);
                p.closeInventory();
                p.sendMessage("§8[§6精炼§8] §e>> §a你取消了本次精炼!");
                inUpgrade.remove(p.getName());
            }

        }
    }
}
