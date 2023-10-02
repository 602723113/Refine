package cc.toin.jinglian.core;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;

public enum UpgradeCore {

    HUJIA("护甲"),
    GONG("弓"),
    JIAN("剑");

    private String chineseName;

    UpgradeCore(String chineseName) {
        this.chineseName = chineseName;
    }

    @Override
    public String toString() {
        return this.chineseName;
    }

    public ItemStack toItemStack(int shuliang) {
        if (this.chineseName.equalsIgnoreCase("护甲")) {
            return this.UC_hujia(shuliang);
        } else if (this.chineseName.equalsIgnoreCase("弓")) {
            return this.UC_gong(shuliang);
        } else {
            return this.UC_jian(shuliang);
        }
    }

    public static UpgradeCore getByPinyi(String name) {
        if (name.equalsIgnoreCase("gong")) {
            return GONG;
        } else if (name.equalsIgnoreCase("hujia")) {
            return HUJIA;
        } else {
            return JIAN;
        }
    }

    private ItemStack UC_hujia(int shuliang) {
        ItemStack i = new ItemStack(Material.YELLOW_DYE, shuliang);
        ItemMeta id = i.getItemMeta();
        id.setDisplayName("§6§l帝王黄玉");
        List<String> lore = Lists.newArrayList();
        lore.add("§e用法：§a背包内点击鼠标右键使用");
        lore.add("§e作用：§a精炼§a§l护甲§7(最高+7)");
        lore.add("§e说明：");
        lore.add("§7  -§a物品叠加后右键无效,需要拆分成1个");
        lore.add("§7  -§a精炼等级5上6,7失败,精炼等级会下降1");
        lore.add("§7  -§a精炼护甲可增加护甲值");
        id.setLore(lore);
        i.setItemMeta(id);
        return i;
    }

    private ItemStack UC_gong(int shuliang) {
        ItemStack i = new ItemStack(Material.LAPIS_LAZULI, shuliang);
        ItemMeta id = i.getItemMeta();
        id.setDisplayName("§b§l青玄陨铁");
        List<String> lore = Lists.newArrayList();
        lore.add("§e用法：§a背包内点击鼠标右键使用");
        lore.add("§e作用：§a精炼§a§l弓§7(最高+7)");
        lore.add("§e说明：");
        lore.add("§7  -§a物品叠加后右键无效,需要拆分成1个");
        lore.add("§7  -§a精炼等级5上6,7失败,精炼等级会下降1");
        lore.add("§7  -§a精炼弓可提升箭速、伤害、多重射");
        lore.add("§7  -§a满级弓箭头爆炸、附带致命一击");
        id.setLore(lore);
        i.setItemMeta(id);
        return i;
    }

    private ItemStack UC_jian(int shuliang) {
        ItemStack i = new ItemStack(Material.RED_DYE, shuliang);
        ItemMeta id = i.getItemMeta();
        id.setDisplayName("§4§l红莲陨铁");
        List<String> lore = Lists.newArrayList();
        lore.add("§e用法：§a背包内点击鼠标右键使用");
        lore.add("§e作用：§a精炼§a§l钻石剑§7(最高+7)");
        lore.add("§e说明：");
        lore.add("§7  -§a物品叠加后右键无效,需要拆分成1个");
        lore.add("§7  -§a精炼等级5上6,7失败,精炼等级会下降1");
        id.setLore(lore);
        i.setItemMeta(id);
        return i;
    }

}
