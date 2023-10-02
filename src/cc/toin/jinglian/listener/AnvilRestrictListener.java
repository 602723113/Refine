package cc.toin.jinglian.listener;

import cc.toin.jinglian.Upgrade;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class AnvilRestrictListener implements Listener {
    private Upgrade upgrade;

    public AnvilRestrictListener(Upgrade upgrade) {
        this.upgrade = upgrade;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.isCancelled()) {
            Player p = (Player) e.getWhoClicked();
            Inventory inv = e.getInventory();
            if (inv instanceof AnvilInventory) {
                InventoryView view = e.getView();
                int rawSlot = e.getRawSlot();
                if (rawSlot == view.convertSlot(rawSlot) && rawSlot == 2) {
                    ItemStack item = e.getCurrentItem();
                    String item_name = item.getItemMeta().getDisplayName();
                    if (Upgrade.hasUpgrade(item_name, "[1]") || Upgrade.hasUpgrade(item_name, "[2]") || Upgrade.hasUpgrade(item_name, "[3]") || Upgrade.hasUpgrade(item_name, "[4]") || Upgrade.hasUpgrade(item_name, "[5]") || Upgrade.hasUpgrade(item_name, "[6]") || Upgrade.hasUpgrade(item_name, "[7]") || Upgrade.hasUpgrade(item_name, "[8]") || Upgrade.hasUpgrade(item_name, "[9]")) {
                        String tempname = "";
                        if (item.getItemMeta().hasDisplayName()) {
                            tempname = item.getItemMeta().getDisplayName();
                        }

                        if (!tempname.equals(e.getInventory().getItem(0).getItemMeta().getDisplayName())) {
                            e.setCancelled(true);
                            p.closeInventory();
                            p.sendMessage(ChatColor.RED + "精炼过的装备不能改名咯!而且不要试图冒充红莲装~");
                        }
                    }
                }
            }

        }
    }

    public Upgrade getUpgrade() {
        return this.upgrade;
    }
}
