package de.precoder.trollplugin;

import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.TextFormat;
import de.precoder.trollplugin.command.TrollCommand;
import de.precoder.trollplugin.listener.PlayerInteract;
import de.precoder.trollplugin.listener.PlayerMove;
import de.precoder.trollplugin.listener.ProjectileHit;
import de.precoder.trollplugin.task.VanishTask;

public class Main extends PluginBase {
	
	public final static String prefix = TextFormat.GRAY + "[" + TextFormat.RED + "TrollPlugin" + TextFormat.GRAY + "] ";
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		this.registerCommands();
		this.registerListener();
		
		this.getServer().getScheduler().scheduleRepeatingTask(new VanishTask(this), 20);
		
		this.getLogger().info(prefix + TextFormat.GREEN + "TrollPlugin wurde gestartet!");
	}
	
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		this.getLogger().info(prefix + TextFormat.RED + "TrollPlugin wurde beendet!");
	}
	
	
	private void registerCommands() {
		CommandMap map = getServer().getCommandMap();
		
		map.register("troll", new TrollCommand(this));
	}
	
	
	private void registerListener() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new ProjectileHit(), this);
	}

}
