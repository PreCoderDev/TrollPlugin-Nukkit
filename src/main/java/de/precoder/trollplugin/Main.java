package de.precoder.trollplugin;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase {
	
	final static String prefix = TextFormat.GRAY + "[" + TextFormat.RED + "TrollPlugin" + TextFormat.GRAY + "] ";
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		
		this.registerCommands();
		this.registerListener();
		
		this.getLogger().info(prefix + TextFormat.GREEN + "TrollPlugin wurde gestartet!");
	}
	
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		this.getLogger().info(prefix + TextFormat.RED + "TrollPlugin wurde beendet!");
	}
	
	
	private void registerCommands() {
		//CommandMap map = getServer().getCommandMap();
	}
	
	
	private void registerListener() {
		//PluginManager pm = getServer().getPluginManager();
	}

}
