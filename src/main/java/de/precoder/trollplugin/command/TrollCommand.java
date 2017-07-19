package de.precoder.trollplugin.command;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import de.precoder.trollplugin.Main;

public class TrollCommand extends MainCommand {
	
	private String helppage = "§7-------[§cTroll Help§7]-------\n"
			+ "§e/help §7-> §6Zeigt diese Seite";
	
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
		}
		
		
		if(args.length > 0) {
			if(args[0].equals("help")) {
				sender.sendMessage(this.helppage);
			}
		}
		
		
		return false;
	}

}
