package cc.toin.jinglian;

import cc.toin.jinglian.core.UpgradeCore;
import cc.toin.jinglian.listener.AnvilRestrictListener;
import cc.toin.jinglian.listener.DamageListener;
import cc.toin.jinglian.listener.UpgradeListener;
import com.google.common.collect.Lists;
import me.may.bind.api.BindAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Upgrade extends JavaPlugin implements Listener {

	private static Upgrade instance;

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();

		Bukkit.getPluginCommand("getcore").setExecutor(new RefineCommand());
		Bukkit.getPluginManager().registerEvents(new UpgradeListener(), this);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new AnvilRestrictListener(this), this);

		Bukkit.getConsoleSender().sendMessage("§8[§6精炼§8] §e>> 粒子特效系统已加载!");
	}

	public static Upgrade getInstance() {
		return instance;
	}

	public void upgradeItem(Player p, UpgradeCore core, ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		p.updateInventory();
		String has_plus = "hehe";
		String names = "未知";
		String chenghao = "未知";
		if (item.getItemMeta().hasDisplayName()) {
			has_plus = meta.getDisplayName();
		}

		if (p.getInventory().contains(core.toItemStack(1))) {
			p.getInventory().removeItem(core.toItemStack(1));

			// 护甲
			if (core.equals(UpgradeCore.HUJIA)) {
				if (item.getType().equals(Material.DIAMOND_HELMET)) {
					names = "头盔";
				} else if (item.getType().equals(Material.DIAMOND_CHESTPLATE)) {
					names = "胸甲";
				} else if (item.getType().equals(Material.DIAMOND_LEGGINGS)) {
					names = "护腿";
				} else if (item.getType().equals(Material.DIAMOND_BOOTS)) {
					names = "靴子";
				}
				chenghao = "不动如山";
				// 弓
			} else if (core.equals(UpgradeCore.GONG)) {
				names = "弓";
				chenghao = "后羿射日";
				// 钻石剑
			} else if (core.equals(UpgradeCore.JIAN)) {
				names = "钻石剑";
				chenghao = "红莲天舞";
			}

			if (hasUpgrade(has_plus, "[1]")) {
				if (this.getChance(this.getConfig().getDouble("Upgrading.SuccessRate.Lv1-Lv2"))) {
					this.updateMethod(p, item, meta, 2);
					p.sendMessage("§8[§6精炼§8] §e>> §a将" + names + "精炼+2成功！");
				} else {
					p.sendMessage("§8[§6精炼§8] §e>> §c将" + names + "精炼至+2失败!");
					p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("Sound.upgradeFail")), 0.5F, 1.0F);
					p.closeInventory();
					UpgradeListener.inUpgrade.remove(p.getName());
				}
			} else if (hasUpgrade(has_plus, "[2]")) {
				if (this.getChance(this.getConfig().getDouble("Upgrading.SuccessRate.Lv2-Lv3"))) {
					this.updateMethod(p, item, meta, 3);
					p.sendMessage("§8[§6精炼§8] §e>> §a将" + names + "精炼+3成功！");
				} else {
					p.sendMessage("§8[§6精炼§8] §e>> §c将" + names + "精炼至+3失败!");
					p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("Sound.upgradeFail")), 0.5F, 1.0F);
					p.closeInventory();
					UpgradeListener.inUpgrade.remove(p.getName());
				}
			} else if (hasUpgrade(has_plus, "[3]")) {
				if (this.getChance(this.getConfig().getDouble("Upgrading.SuccessRate.Lv3-Lv4"))) {
					this.updateMethod(p, item, meta, 4);
					p.sendMessage("§8[§6精炼§8] §e>> §a将" + names + "精炼+4成功！");
				} else {
					p.sendMessage("§8[§6精炼§8] §e>> §c将" + names + "精炼至+4失败!");
					p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("Sound.upgradeFail")), 0.5F, 1.0F);
					p.closeInventory();
					UpgradeListener.inUpgrade.remove(p.getName());
				}
			} else if (hasUpgrade(has_plus, "[4]")) {
				if (this.getChance(this.getConfig().getDouble("Upgrading.SuccessRate.Lv4-Lv5"))) {
					this.updateMethod(p, item, meta, 5);
					p.sendMessage("§8[§6精炼§8] §e>> §a将" + names + "精炼+5成功！");
				} else {
					p.sendMessage("§8[§6精炼§8] §e>> §c将" + names + "精炼至+5失败!");
					p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("Sound.upgradeFail")), 0.5F, 1.0F);
					p.closeInventory();
					UpgradeListener.inUpgrade.remove(p.getName());
				}
			} else if (hasUpgrade(has_plus, "[5]")) {
				if (this.getChance(this.getConfig().getDouble("Upgrading.SuccessRate.Lv5-Lv6"))) {
					this.updateMethod(p, item, meta, 6);
					p.sendMessage("§8[§6精炼§8] §e>> §a将" + names + "精炼+6成功！");
					this.getClass();
					this.getServer().broadcastMessage("§8[§6精炼§8] §e>> §f天哪,玩家 §a§l" + p.getPlayer().getName()
							+ " §f精炼 " + names + " §f+6成功！战斗力飞跃！");
				} else {
					this.getClass();
					this.getServer().broadcastMessage("§8[§6精炼§8] §e>> §f玩家 §a§l" + p.getPlayer().getName() + " §f想要将 "
							+ names + " §f精炼至+6失败,精炼等级从5降至4~");
					p.sendMessage("§8[§6精炼§8] §e>> §c将" + names + "精炼至+6失败!" + names + "精炼-1");
					p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("Sound.upgradeFail")), 0.5F, 1.0F);
					this.updateMethod(p, item, meta, 4);
				}
			} else if (hasUpgrade(has_plus, "[6]")) {
				if (this.getChance(this.getConfig().getDouble("Upgrading.SuccessRate.Lv6-Lv7"))) {
					this.updateMethod(p, item, meta, 7);
					p.sendMessage("§a§l" + names + "精炼+7成功！");
					this.getClass();
					this.getServer().broadcastMessage("§8[§6精炼§8] §e>> §f天哪,玩家 §a§l" + p.getPlayer().getName()
							+ " §f精炼 " + names + " §f+7成功！超神！并获得称号: §6" + chenghao);
					// 装备+7后自动绑定
					BindAPI.bindItem(item);
//                    this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "ch add " + p.getPlayer().getName() + " &f[&6" + chenghao + "&f]");

					/* +7称号评测部分 */
					p.sendMessage("§8[§6精炼§8] §e>> §a正在对你身上的装备进行评测...");
//                    boolean is4Plus7Armor = false;
					List<Boolean> check = Lists.newArrayList();
					for (ItemStack itemStack : p.getInventory().getContents()) {
						if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
							if (itemStack.getType().equals(Material.DIAMOND_HELMET)
									|| itemStack.getType().equals(Material.DIAMOND_CHESTPLATE)
									|| itemStack.getType().equals(Material.DIAMOND_LEGGINGS)
									|| itemStack.getType().equals(Material.DIAMOND_BOOTS)) {
								if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
									if (hasUpgrade(itemStack.getItemMeta().getDisplayName(), "[7]")) {
										check.add(true);
									}
								}
							}
						}
					}
					Bukkit.getScheduler().runTaskLater(this, () -> {
						if (check.size() == 4) {
							p.sendMessage("§8[§6精炼§8] §e>> §f本次检测到您身上共有 §a§l4 §f件+7护甲, 授予你称号: §6不动如山");
							getConfig().getStringList("PingceCommands")
									.forEach(s -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											s.replace("&", "§").replace("%player%", p.getName())));
						} else {
							p.sendMessage("§8[§6精炼§8] §e>> §f本次检测到您身上共有 §a§l" + check.size() + " §f件+7护甲 暂未获得对应称号!");
						}
					}, 20 * 3L);

				} else {
					this.getClass();
					this.getServer().broadcastMessage("§8[§6精炼§8] §e>> §f玩家 §a§l" + p.getPlayer().getName() + " §f想要将 "
							+ names + " §f精炼至+6失败,精炼等级从6降至5~");
					p.sendMessage("§8[§6精炼§8] §e>> §c将" + names + "精炼至+7失败!" + names + "精炼-1");
					p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("Sound.upgradeFail")), 0.5F, 1.0F);
					this.updateMethod(p, item, meta, 5);
				}
			} else if (hasUpgrade(has_plus, "[7]")) {
				p.sendMessage(ChatColor.RED + "目前" + names + "最多只能精炼到+7,等待日后更新咯!");
				p.getInventory().addItem(core.toItemStack(1));
			} else {
				this.updateMethod(p, item, meta, 1);
			}
		} else {
			p.sendMessage("§8[§6精炼§8] §e>> §a你取消了本次精炼!");
			p.sendMessage("§8[§6精炼§8] §e>> §a少年你就得意吧,你差点被清空背包.帅的南青 2686968089太仁慈你造么？");
			p.updateInventory();
			p.closeInventory();
			UpgradeListener.inUpgrade.remove(p.getName());
		}
	}

	public void updateMethod(Player p, ItemStack item, ItemMeta meta, int plus) {
		if (item == null || item.getType().equals(Material.AIR)) {
			return;
		}
		String names = "未知";
//        boolean leixing = false;
		byte var9;
		if (item.getType().equals(Material.DIAMOND_HELMET)) {
			names = "头盔[帝王✡]";
			var9 = 1;
		} else if (item.getType().equals(Material.DIAMOND_CHESTPLATE)) {
			names = "胸甲[帝王✡]";
			var9 = 2;
		} else if (item.getType().equals(Material.DIAMOND_LEGGINGS)) {
			names = "护腿[帝王✡]";
			var9 = 3;
		} else if (item.getType().equals(Material.DIAMOND_BOOTS)) {
			names = "靴子[帝王✡]";
			var9 = 4;
		} else if (item.getType().equals(Material.BOW)) {
			names = "弓[青玄☸]";
			var9 = 5;
		} else if (item.getType().equals(Material.DIAMOND_SWORD)) {
			names = "钻石剑[红莲❀]";
			var9 = 6;
		} else {
			names = "未知";
			var9 = 0;
		}

		List<String> lore = meta.getLore();
		if (!meta.hasLore() || lore == null || lore.isEmpty()) {
			lore = Lists.newArrayList();
		}
		if (plus == 7) { // 判断是否为+7内容
			meta.setDisplayName("§4§l" + names + " + [7]");

			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).contains("精炼")) {
					lore.remove(i);
					--i;
				}
			}

			lore.add("§a---§e§l装备精炼§a---");
			if (var9 == 6) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-7-Damge"));
				lore.add("§a精炼属性：§b攻击爆炸,威力§61");
			} else if (var9 == 5) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-7-Damge"));
				lore.add("§a精炼属性：§b箭头爆炸,威力§62");
				lore.add("§a精炼属性：§b蓄满力后§6七重射");
				lore.add("§a精炼被动：§610%§b致命一击(玩家)");
			} else if (var9 == 1) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-7-Armor"));
				lore.add("§a精炼属性：§b有3%的概率免疫伤害并反杀对方");
			} else if (var9 == 2) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-7-Armor"));
				lore.add("§a精炼属性：§b有3%的概率免疫伤害并反杀对方");
			} else if (var9 == 3) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-7-Armor"));
				lore.add("§a精炼属性：§b有3%的概率免疫伤害并反杀对方");
			} else if (var9 == 4) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-7-Armor"));
				lore.add("§a精炼属性：§b有3%的概率免疫伤害并反杀对方");
			}

			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (plus == 6) {
			meta.setDisplayName("§c§l" + names + " + [6]");

			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).contains("精炼")) {
					lore.remove(i);
					--i;
				}
			}

			lore.add("§a---§e§l装备精炼§a---");
			if (var9 == 6) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-6-Damge"));
			} else if (var9 == 5) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-6-Damge"));
				lore.add("§a精炼属性：§b箭速§62.5倍");
				lore.add("§a精炼属性：§b蓄满力后§6六重射");
			} else if (var9 == 1) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-6-Armor"));
			} else if (var9 == 2) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-6-Armor"));
			} else if (var9 == 3) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-6-Armor"));
			} else if (var9 == 4) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-6-Armor"));
			}

			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (plus == 5) {
			meta.setDisplayName("§c" + names + " + [5]");

			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).contains("精炼")) {
					lore.remove(i);
					--i;
				}
			}

			lore.add("§a---§e§l装备精炼§a---");
			if (var9 == 6) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-5-Damge"));
			} else if (var9 == 5) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-5-Damge"));
				lore.add("§a精炼属性：§b箭速§62倍");
				lore.add("§a精炼属性：§b蓄满力后§6五重射");
			} else if (var9 == 1) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-5-Armor"));
			} else if (var9 == 2) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-5-Armor"));
			} else if (var9 == 3) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-5-Armor"));
			} else if (var9 == 4) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-5-Armor"));
			}

			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (plus == 4) {
			meta.setDisplayName("§6" + names + " + [4]");
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).contains("精炼")) {
					lore.remove(i);
					--i;
				}
			}

			lore.add("§a---§e§l装备精炼§a---");
			if (var9 == 6) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-4-Damge"));
			} else if (var9 == 5) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-4-Damge"));
				lore.add("§a精炼属性：§b箭速§61.7倍");
				lore.add("§a精炼属性：§b蓄满力后§6四重射");
			} else if (var9 == 1) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-4-Armor"));
			} else if (var9 == 2) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-4-Armor"));
			} else if (var9 == 3) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-4-Armor"));
			} else if (var9 == 4) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-4-Armor"));
			}

			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (plus == 3) {
			meta.setDisplayName("§5" + names + " + [3]");
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).contains("精炼")) {
					lore.remove(i);
					--i;
				}
			}

			lore.add("§a---§e§l装备精炼§a---");
			if (var9 == 6) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-3-Damge"));
			} else if (var9 == 5) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-3-Damge"));
				lore.add("§a精炼属性：§b箭速§61.5倍");
				lore.add("§a精炼属性：§b蓄满力后§6三重射");
			} else if (var9 == 1) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-3-Armor"));
			} else if (var9 == 2) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-3-Armor"));
			} else if (var9 == 3) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-3-Armor"));
			} else if (var9 == 4) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-3-Armor"));
			}

			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (plus == 2) {
			meta.setDisplayName("§3" + names + " + [2]");
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).contains("精炼")) {
					lore.remove(i);
					--i;
				}
			}

			lore.add("§a---§e§l装备精炼§a---");
			if (var9 == 6) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-2-Damge"));
			} else if (var9 == 5) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-2-Damge"));
				lore.add("§a精炼属性：§b箭速§61.3倍");
				lore.add("§a精炼属性：§b蓄满力后§6二重射");
			} else if (var9 == 1) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-2-Armor"));
			} else if (var9 == 2) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-2-Armor"));
			} else if (var9 == 3) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-2-Armor"));
			} else if (var9 == 4) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-2-Armor"));
			}

			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (plus == 1) {
			p.sendMessage("§a§l精炼+1成功！");
			meta.setDisplayName("§a" + names + " + [1]");

			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).contains("精炼")) {
					lore.remove(i);
					--i;
				}
			}

			lore.add("§a---§e§l装备精炼§a---");
			if (var9 == 6) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-1-Damge"));
			} else if (var9 == 5) {
				lore.add("§a精炼属性：§b额外伤害+§6" + this.getConfig().getDouble("Plus-1-Damge"));
				lore.add("§a精炼属性：§b箭速§61.1倍");
			} else if (var9 == 1) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-1-Armor"));
			} else if (var9 == 2) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-1-Armor"));
			} else if (var9 == 3) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-1-Armor"));
			} else if (var9 == 4) {
				lore.add("§a精炼属性：§b额外防御+§6" + this.getConfig().getDouble("Plus-1-Armor"));
			}

			meta.setLore(lore);
			item.setItemMeta(meta);
		}

		p.updateInventory();
		p.closeInventory();
		UpgradeListener.inUpgrade.remove(p.getName());
		p.playSound(p.getLocation(), Sound.valueOf(getConfig().getString("Sound.upgradeSuccess")), 0.5F, 1.0F);
	}

	public boolean getChance(double chance) {
		double r = Math.random() * 100.0D;
		return r <= chance;
	}

	public static boolean hasUpgrade(String item, String number) {
		return item != null && (item.length() >= 5 && item.substring(item.length() - 3).equalsIgnoreCase(number));
	}

	public static String capitalizeString(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;

		for (int i = 0; i < chars.length; ++i) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == 46 || chars[i] == 39) {
				found = false;
			}
		}

		return String.valueOf(chars);
	}
}
