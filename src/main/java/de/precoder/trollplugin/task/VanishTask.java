package de.precoder.trollplugin.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.PluginTask;
import de.precoder.trollplugin.listener.TrollListener;

public class VanishTask extends PluginTask<Plugin> {

	public VanishTask(Plugin owner) {
		super(owner);
	}
	
	
	@Override
	public void onRun(int arg0) {
		
		for (Player hideplayer : Server.getInstance().getOnlinePlayers().values()) {
			if(TrollListener.isPlayerVanished(hideplayer)) {
				for (Player all : Server.getInstance().getOnlinePlayers().values()) {
					all.hidePlayer(hideplayer);
				}
			} else {
				for (Player all : Server.getInstance().getOnlinePlayers().values()) {
					all.showPlayer(hideplayer);
				}
			}
		}
		
	}

}
