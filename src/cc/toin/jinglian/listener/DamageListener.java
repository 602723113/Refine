package cc.toin.jinglian.listener;

import cc.toin.jinglian.Upgrade;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class DamageListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow) {
            Arrow damager1 = (Arrow) e.getDamager();
            LivingEntity in_hand1 = (LivingEntity) damager1.getShooter();
            if (in_hand1 instanceof Player) {
                Player meta1 = (Player) in_hand1;
                ItemStack item1 = meta1.getItemInHand();
                if (item1.hasItemMeta()) {
                    ItemMeta res2 = item1.getItemMeta();
                    if (res2.hasDisplayName()) {
                        String loc1 = res2.getDisplayName();
                        if (item1.getType().equals(Material.BOW)) {
                            if (Upgrade.hasUpgrade(loc1, "[1]")) {
                                e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-1-Damge"));
                            } else if (Upgrade.hasUpgrade(loc1, "[2]")) {
                                e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-2-Damge"));
                            } else if (Upgrade.hasUpgrade(loc1, "[3]")) {
                                e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-3-Damge"));
                            } else if (Upgrade.hasUpgrade(loc1, "[4]")) {
                                e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-4-Damge"));
                            } else if (Upgrade.hasUpgrade(loc1, "[5]")) {
                                e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-5-Damge"));
                            } else if (Upgrade.hasUpgrade(loc1, "[6]")) {
                                e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-6-Damge"));
                            } else if (Upgrade.hasUpgrade(loc1, "[7]")) {
                                e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-7-Damge"));
                                if (e.getEntity() instanceof Player) {
                                    int sj = (int) (Math.random() * 100.0D);
                                    if (sj <= 10) {
                                        Player g = (Player) e.getEntity();

                                        ClaimedResidence res1 = Residence.getInstance().getResidenceManager().getByLoc(e.getEntity().getLocation());
                                        if (res1 != null) {
                                            return;
                                        }

                                        g.setHealth(0.0D);
                                        g.sendMessage("§f[§b§l青玄弓§f]§c" + meta1.getPlayer().getName() + " 触发致命一击将你射穿...");
                                        meta1.sendMessage("§f[§b§l青玄弓§f]§a你触发了致命一击将 " + g.getPlayer().getName() + " 射穿...");
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        if (e.getDamager() instanceof Player) {
            Player damager = (Player) e.getDamager();
            ItemStack in_hand = damager.getItemInHand();
            if (in_hand.hasItemMeta()) {
                ItemMeta meta = in_hand.getItemMeta();
                if (meta.hasDisplayName()) {
                    String item = meta.getDisplayName();
                    if (in_hand.getType().equals(Material.DIAMOND_SWORD)) {
                        if (Upgrade.hasUpgrade(item, "[1]")) {
                            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-1-Damge"));
                        } else if (Upgrade.hasUpgrade(item, "[2]")) {
                            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-2-Damge"));
                        } else if (Upgrade.hasUpgrade(item, "[3]")) {
                            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-3-Damge"));
                        } else if (Upgrade.hasUpgrade(item, "[4]")) {
                            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-4-Damge"));
                        } else if (Upgrade.hasUpgrade(item, "[5]")) {
                            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-5-Damge"));
                        } else if (Upgrade.hasUpgrade(item, "[6]")) {
                            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-6-Damge"));
                        } else if (Upgrade.hasUpgrade(item, "[7]")) {
                            e.setDamage(e.getDamage(EntityDamageEvent.DamageModifier.BASE) + Upgrade.getInstance().getConfig().getDouble("Plus-7-Damge"));
                            ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(e.getEntity().getLocation());
                            if (res == null) {
                                Location loc = e.getEntity().getLocation();
                                loc.setY(loc.getY() + 1.0D);
                                damager.getWorld().createExplosion(loc, 0.0F);
//                                loc.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, 0,0,0,1, 0,0,0,0,);
                            }
                        }
                    }
                }
            }
        }
        // 护甲防御
        if (e.getEntity() instanceof Player) {
            Player entity = (Player) e.getEntity();
//            ItemStack helmet = entity.getEquipment().getHelmet();
//            ItemStack chestPlate = entity.getEquipment().getChestplate();
//            ItemStack legging = entity.getEquipment().getLeggings();
//            ItemStack boots = entity.getEquipment().getBoots();
            boolean has7Level = false;
            // 这个是减免的伤害
            double resistence = 0D;
            // 获取所有防具
            for (ItemStack armorStack : entity.getEquipment().getArmorContents()) {
                if (armorStack.hasItemMeta()) {
                    ItemMeta meta = armorStack.getItemMeta();
                    if (meta.hasDisplayName()) {
                        String item = meta.getDisplayName();
                        if (armorStack.getType().equals(Material.DIAMOND_HELMET) ||
                        		armorStack.getType().equals(Material.DIAMOND_CHESTPLATE) ||
                        		armorStack.getType().equals(Material.DIAMOND_LEGGINGS) ||
                        		armorStack.getType().equals(Material.DIAMOND_BOOTS)) {
                            if (Upgrade.hasUpgrade(item, "[1]")) {
                                resistence += Upgrade.getInstance().getConfig().getDouble("Plus-1-Armor");
                            } else if (Upgrade.hasUpgrade(item, "[2]")) {
                                resistence += Upgrade.getInstance().getConfig().getDouble("Plus-2-Armor");
                            } else if (Upgrade.hasUpgrade(item, "[3]")) {
                                resistence += Upgrade.getInstance().getConfig().getDouble("Plus-3-Armor");
                            } else if (Upgrade.hasUpgrade(item, "[4]")) {
                                resistence += Upgrade.getInstance().getConfig().getDouble("Plus-4-Armor");
                            } else if (Upgrade.hasUpgrade(item, "[5]")) {
                                resistence += Upgrade.getInstance().getConfig().getDouble("Plus-5-Armor");
                            } else if (Upgrade.hasUpgrade(item, "[6]")) {
                                resistence += Upgrade.getInstance().getConfig().getDouble("Plus-6-Armor");
                            } else if (Upgrade.hasUpgrade(item, "[7]")) {
                                resistence += Upgrade.getInstance().getConfig().getDouble("Plus-7-Armor");
                                has7Level = true;
                            }
                        }
                    }
                }
            }

            if (has7Level) {
                ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(e.getEntity().getLocation());
                if (res == null) {
                    // 3%的几率使对方死亡
                    if (Upgrade.getInstance().getChance(3)) {
                        Player damager = null;
                        if (e.getDamager() instanceof Arrow) {
                            Arrow arrow = (Arrow) e.getDamager();
                            if (arrow.getShooter() instanceof Player) {
                                damager = (Player) arrow.getShooter();
                            }
                        }
                        if (e.getDamager() instanceof Player) {
                            damager = (Player) e.getDamager();
                        }

                        if (damager == null) {
                            e.setCancelled(true);
                            return;
                        }
                        damager.setHealth(0);
                        damager.damage(233333);
                        damager.sendMessage("§8[§6精炼§8] §e>> §c对方身上的+7帝王套触发被动效果, 将你反伤至死了!");
                        entity.sendMessage("§8[§6精炼§8] §e>> §c你身上的+7帝王套触发被动效果, 将对方反伤至死了!");
                        e.setCancelled(true);
                        return;
                    }
                }
            }

            double result = e.getDamage(EntityDamageEvent.DamageModifier.BASE) - resistence;
            if (result <= 0) {
                if (e.getDamager() instanceof Player) {
                    Player damager = (Player) e.getDamager();
                    damager.sendMessage("§7你未能击穿对方的护甲!");
                    e.setCancelled(true);
                }
            } else {
                e.setDamage(result);
            }
        }
    }

	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			ItemStack in_hand = e.getBow();
			ItemMeta meta = in_hand.getItemMeta();
			if (meta.hasDisplayName()) {
				String item = meta.getDisplayName();
				if (Upgrade.hasUpgrade(item, "[1]")) {
					e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(1.1D));
				} else {
					Player player;
					Vector velocity;
					double speed;
					double spray;
					Vector direction;
					Arrow arrow1;
					if (Upgrade.hasUpgrade(item, "[2]")) {
						e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(1.3D));
						player = (Player) e.getEntity();
						if (e.getForce() < 1.0F) {
							player.sendMessage("§f[§b§l青玄弓§f]§f蓄力:" + e.getForce() + ",未满,无多重射.");
						} else {
							velocity = e.getProjectile().getVelocity();
							speed = velocity.length();
							spray = 2.0D;
							direction = new Vector(velocity.getX() / speed, velocity.getY() / speed,
									velocity.getZ() / speed);
							arrow1 = player.launchProjectile(Arrow.class);
							arrow1.setVelocity((new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
									direction.getY() + (Math.random() - 0.5D) / spray,
									direction.getZ() + (Math.random() - 0.5D) / spray)).normalize().multiply(speed));
						}
					} else {
						Arrow arrow2;
						if (Upgrade.hasUpgrade(item, "[3]")) {
							e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(1.5D));
							player = (Player) e.getEntity();
							if (e.getForce() < 1.0F) {
								player.sendMessage("§f[§b§l青玄弓§f]§f蓄力:" + e.getForce() + ",未满,无多重射.");
							} else {
								velocity = e.getProjectile().getVelocity();
								speed = velocity.length();
								spray = 2.0D;
								direction = new Vector(velocity.getX() / speed, velocity.getY() / speed,
										velocity.getZ() / speed);
								arrow1 = player.launchProjectile(Arrow.class);
								arrow2 = player.launchProjectile(Arrow.class);
								arrow1.setVelocity((new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
										direction.getY() + (Math.random() - 0.5D) / spray,
										direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
												.multiply(speed));
								arrow2.setVelocity((new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
										direction.getY() + (Math.random() - 0.5D) / spray,
										direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
												.multiply(speed));
							}
						} else {
							Arrow arrow3;
							if (Upgrade.hasUpgrade(item, "[4]")) {
								e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(1.7D));
								player = (Player) e.getEntity();
								if (e.getForce() < 1.0F) {
									player.sendMessage("§f[§b§l青玄弓§f]§f蓄力:" + e.getForce() + ",未满,无多重射.");
								} else {
									velocity = e.getProjectile().getVelocity();
									speed = velocity.length();
									spray = 2.0D;
									direction = new Vector(velocity.getX() / speed, velocity.getY() / speed,
											velocity.getZ() / speed);
									arrow1 = player.launchProjectile(Arrow.class);
									arrow2 = player.launchProjectile(Arrow.class);
									arrow3 = player.launchProjectile(Arrow.class);
									arrow1.setVelocity((new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
											direction.getY() + (Math.random() - 0.5D) / spray,
											direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
													.multiply(speed));
									arrow2.setVelocity((new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
											direction.getY() + (Math.random() - 0.5D) / spray,
											direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
													.multiply(speed));
									arrow3.setVelocity((new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
											direction.getY() + (Math.random() - 0.5D) / spray,
											direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
													.multiply(speed));
								}
							} else {
								Arrow arrow4;
								if (Upgrade.hasUpgrade(item, "[5]")) {
									e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(2.0D));
									player = (Player) e.getEntity();
									if (e.getForce() < 1.0F) {
										player.sendMessage("§f[§b§l青玄弓§f]§f蓄力:" + e.getForce() + ",未满,无多重射.");
									} else {
										velocity = e.getProjectile().getVelocity();
										speed = velocity.length();
										spray = 2.0D;
										direction = new Vector(velocity.getX() / speed, velocity.getY() / speed,
												velocity.getZ() / speed);
										arrow1 = player.launchProjectile(Arrow.class);
										arrow2 = player.launchProjectile(Arrow.class);
										arrow3 = player.launchProjectile(Arrow.class);
										arrow4 = player.launchProjectile(Arrow.class);
										arrow1.setVelocity(
												(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
														direction.getY() + (Math.random() - 0.5D) / spray,
														direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
																.multiply(speed));
										arrow2.setVelocity(
												(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
														direction.getY() + (Math.random() - 0.5D) / spray,
														direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
																.multiply(speed));
										arrow3.setVelocity(
												(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
														direction.getY() + (Math.random() - 0.5D) / spray,
														direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
																.multiply(speed));
										arrow4.setVelocity(
												(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
														direction.getY() + (Math.random() - 0.5D) / spray,
														direction.getZ() + (Math.random() - 0.5D) / spray)).normalize()
																.multiply(speed));
									}
								} else {
									Arrow arrow5;
									if (Upgrade.hasUpgrade(item, "[6]")) {
										e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(2.5D));
										player = (Player) e.getEntity();
										if (e.getForce() < 1.0F) {
											player.sendMessage("§f[§b§l青玄弓§f]§f蓄力:" + e.getForce() + ",未满,无多重射.");
										} else {
											velocity = e.getProjectile().getVelocity();
											speed = velocity.length();
											spray = 2.0D;
											direction = new Vector(velocity.getX() / speed, velocity.getY() / speed,
													velocity.getZ() / speed);
											arrow1 = player.launchProjectile(Arrow.class);
											arrow2 = player.launchProjectile(Arrow.class);
											arrow3 = player.launchProjectile(Arrow.class);
											arrow4 = player.launchProjectile(Arrow.class);
											arrow5 = player.launchProjectile(Arrow.class);
											arrow1.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow2.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow3.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow4.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow5.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
										}
									} else if (Upgrade.hasUpgrade(item, "[7]")) {
										player = (Player) e.getEntity();
										if (e.getForce() < 1.0F) {
											player.sendMessage("§f[§b§l青玄弓§f]§f蓄力:" + e.getForce() + ",未满,无多重射.");
										} else {
											velocity = e.getProjectile().getVelocity();
											speed = velocity.length();
											spray = 2.0D;
											direction = new Vector(velocity.getX() / speed, velocity.getY() / speed,
													velocity.getZ() / speed);
											arrow1 = player.launchProjectile(Arrow.class);
											arrow2 = player.launchProjectile(Arrow.class);
											arrow3 = player.launchProjectile(Arrow.class);
											arrow4 = player.launchProjectile(Arrow.class);
											arrow5 = player.launchProjectile(Arrow.class);
											Arrow arrow6 = player.launchProjectile(Arrow.class);
											arrow1.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow2.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow3.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow4.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow5.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
											arrow6.setVelocity(
													(new Vector(direction.getX() + (Math.random() - 0.5D) / spray,
															direction.getY() + (Math.random() - 0.5D) / spray,
															direction.getZ() + (Math.random() - 0.5D) / spray))
																	.normalize().multiply(speed));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		Projectile proj = event.getEntity();
		if (proj instanceof Arrow) {
			Arrow arrow = (Arrow) proj;
			LivingEntity shooter = (LivingEntity) arrow.getShooter();
			if (shooter instanceof Player) {
				Player player = (Player) shooter;
				ItemStack item = player.getItemInHand();
				if (!item.hasItemMeta()) {
					return;
				}

				ItemMeta meta = item.getItemMeta();
				if (!meta.hasDisplayName()) {
					return;
				}

				String namestr = item.getItemMeta().getDisplayName();
				if (Upgrade.hasUpgrade(namestr, "[7]")) {
					ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(arrow.getLocation());
					if (res != null) {
						return;
					}

					arrow.getWorld().createExplosion(arrow.getLocation(), 0.0F);
					return;
				}
			}
		}

	}

}
