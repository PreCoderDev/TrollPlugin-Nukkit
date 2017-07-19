package de.precoder.trollplugin.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.PluginIdentifiableCommand;
import de.precoder.trollplugin.Main;

public abstract class MainCommand extends Command implements PluginIdentifiableCommand {
	
	public Main plugin;
	
	public MainCommand(Main plugin, String name, String desc) {
		super(name, desc);
		
		this.plugin = plugin;
	}
	
	public MainCommand(Main plugin, String name, String desc, String usage) {
		super(name, desc, usage);
		
		this.plugin = plugin;
	}
	
	public MainCommand(Main plugin, String name, String desc, String usage, String[] aliases) {
		super(name, desc, usage, aliases);
		
		this.plugin = plugin;
	}
	
	public MainCommand(Main plugin, Boolean override, String name, String desc, String usage, String[] aliases) {
		super(name, desc, usage, aliases);
		
		this.plugin = plugin;
		
		CommandMap map = plugin.getServer().getCommandMap();
		Command cmd = map.getCommand(name);
		cmd.setLabel(name + "_disabled");
		cmd.unregister(map);
	}
	
	public Main getPlugin() {
		return plugin;
	}

}
