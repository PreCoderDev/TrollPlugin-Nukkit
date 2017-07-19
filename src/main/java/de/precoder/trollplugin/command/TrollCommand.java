package de.precoder.trollplugin.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import de.precoder.trollplugin.Main;
import de.precoder.trollplugin.listener.TrollListener;

public class TrollCommand extends MainCommand {
	
	private String helppage = "§7-------[§cTroll Help§7]-------\n"
			+ "§e/troll help §7-> §6Zeigt diese Seite\n"
			+ "§e/troll freeze <spieler> §7-> §6Lässt einen Spieler erstarren";
	
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
			
			if(args[0].equals("freeze")) {
				if(args.length > 2) {
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
