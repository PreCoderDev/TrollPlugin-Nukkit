package de.precoder.trollplugin.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.network.protocol.AddEntityPacket;
import cn.nukkit.utils.TextFormat;
import de.precoder.trollplugin.Main;
import de.precoder.trollplugin.listener.TrollListener;

public class TrollCommand extends MainCommand {
	
	private String helppage = "§7-------[§cTroll Help§7]-------\n"
			+ "§e/troll help §7-> §6Zeigt diese Seite\n"
			+ "§e/troll vanish §7-> §6Macht dich unsichtbar\n"
			+ "§e/troll freeze <spieler> §7-> §6Lässt einen Spieler erstarren\n"
			+ "§e/troll fire <spieler> <seconds> §7-> §6Zündet einen Spieler an\n"
			+ "§e/troll crash <spieler> §7-> §6Zündet einen Spieler an\n"
			+ "§e/troll strike <spieler> [<anzahl>] §7-> §6Schießt einen Blitz auf den Spieler\n"
			+ "§cSOON §e/troll fireball §7-> §6Gibt dir das Feuerball Item\n"
			+ "§cSOON §e/troll minigun §7-> §6Gibt dir eine MiniGun\n";
	
	public TrollCommand(Main plugin) {
		super(plugin, "troll", "troll Command", null, new String[]{});
		setPermission("troll.cmd");
		commandParameters.clear();
		commandParameters.put("default", new CommandParameter[] {
				new CommandParameter("help", true)
		});
	}
	
	
	@Override
	public boolean execute(CommandSender sender, String cmd, String[] args) {
		
		if(!testPermission(sender)) {
			sender.sendMessage(Main.prefix + TextFormat.RED + "Du hast keine Berechtigung dazu!");
			return true;
		}
		
		
		if(args.length == 0) {
			sender.sendMessage(this.helppage);
			return true;
		}
		
		
		if(args.length > 0) {
			if(args[0].equals("help")) {
				sender.sendMessage(this.helppage);
				return true;
			}
			
			if(args[0].equals("vanish")) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					if(TrollListener.isPlayerVanished(p)) {
						TrollListener.setPlayerVanished(p, false);
						for (Player hideplayer : Server.getInstance().getOnlinePlayers().values()) {
							if(TrollListener.isPlayerVanished(hideplayer)) {
								for (Player all : Server.getInstance().getOnlinePlayers().values()) {
									all.showPlayer(hideplayer);
								}
							}
						}
						p.sendMessage(Main.prefix + TextFormat.RED + "Du bist nicht länger unsichtbar!");
					} else {
						TrollListener.setPlayerVanished(p, true);
						p.sendMessage(Main.prefix + TextFormat.GREEN + "Du bist jetzt unsichtbar!");
					}
				} else {
					sender.sendMessage(Main.prefix + TextFormat.RED + "Der Command kann nur ingame ausgeführt werden!");
				}
				return true;
			}
			
			if(args[0].equals("strike")) {
				if(args.length > 1) {
					if(Server.getInstance().getPlayer(args[1]) != null) {
						Player fp = Server.getInstance().getPlayer(args[1]);
						int count = 1;
						if(args.length > 2) {
							count = Integer.parseInt(args[2]);
						}
						
						for (int i = 0; i < count; i++) {
							AddEntityPacket pk = new AddEntityPacket();
							pk.type = 93;
							pk.entityUniqueId = Entity.entityCount++;
							pk.metadata = new EntityMetadata();
							pk.speedX = 0;
							pk.speedY = 0;
							pk.speedZ = 0;
							pk.yaw = (float) fp.getYaw();
							pk.pitch = (float) fp.getPitch();
							pk.x = (float) fp.x;
							pk.y = (float) fp.y;
							pk.z = (float) fp.z;
							for (Player pl : fp.level.getPlayers().values()) {
								pl.dataPacket(pk);
							}
						}
						
						sender.sendMessage(Main.prefix + TextFormat.BLUE + fp.getName() + TextFormat.GOLD + " vom Blitz getroffen!");
					} else {
						sender.sendMessage(Main.prefix + TextFormat.RED + "Der Spieler wurde nicht gefunden!");
					}
				} else {
					sender.sendMessage(Main.prefix + TextFormat.RED + "Benutzung: /troll strike <spieler> [<anzahl>]");
					return true;
				}
			}
			
			if(args[0].equals("crash")) {
				if(args.length > 1) {
					if(Server.getInstance().getPlayer(args[1]) != null) {
						Player fp = Server.getInstance().getPlayer(args[1]);
						
						fp.close("", "timeout");
						
						sender.sendMessage(Main.prefix + TextFormat.BLUE + fp.getName() + TextFormat.GOLD + " wurde gecrasht!");
					} else {
						sender.sendMessage(Main.prefix + TextFormat.RED + "Der Spieler wurde nicht gefunden!");
					}
				} else {
					sender.sendMessage(Main.prefix + TextFormat.RED + "Benutzung: /troll crash <spieler>");
					return true;
				}
			}
			
			if(args[0].equals("fire")) {
				if(args.length > 2) {
					if(Server.getInstance().getPlayer(args[1]) != null) {
						Player fp = Server.getInstance().getPlayer(args[1]);
						int seconds = Integer.parseInt(args[2]);
						
						fp.setOnFire(seconds);
						
						sender.sendMessage(Main.prefix + TextFormat.BLUE + fp.getName() + TextFormat.GOLD + " brennt jetzt für "+TextFormat.BLUE+seconds+" Sekunden!");
					} else {
						sender.sendMessage(Main.prefix + TextFormat.RED + "Der Spieler wurde nicht gefunden!");
					}
				} else {
					sender.sendMessage(Main.prefix + TextFormat.RED + "Benutzung: /troll fire <spieler> <sekunden>");
					return true;
				}
			}
			
			
			if(args[0].equals("freeze")) {
				if(args.length > 1) {
					if(Server.getInstance().getPlayer(args[1]) != null) {
						Player fp = Server.getInstance().getPlayer(args[1]);
						if(TrollListener.isPlayerFreezed(fp)) {
							TrollListener.setPlayerFreezed(fp, false);
							sender.sendMessage(Main.prefix + TextFormat.BLUE + fp.getName() + TextFormat.GREEN + " kann sich wieder bewegen!");
							return true;
						} else {
							TrollListener.setPlayerFreezed(fp, true);
							sender.sendMessage(Main.prefix + TextFormat.BLUE + fp.getName() + TextFormat.GOLD + " kann sich jetzt nicht mehr bewegen!");
							sender.sendMessage(Main.prefix + TextFormat.BLUE + "Benutze diesen Command erneut, um ihn wieder frei zu lassen.");
							return true;
						}
					} else {
						sender.sendMessage(Main.prefix + TextFormat.RED + "Der Spieler wurde nicht gefunden!");
					}
				} else {
					sender.sendMessage(Main.prefix + TextFormat.RED + "Benutzung: /troll freeze <spieler>");
					return true;
				}
			}
		}
		
		
		return false;
	}

}
