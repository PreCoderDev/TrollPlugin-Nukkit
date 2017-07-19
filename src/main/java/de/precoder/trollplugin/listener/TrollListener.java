package de.precoder.trollplugin.listener;

import java.util.ArrayList;

import cn.nukkit.Player;

public class TrollListener {
	
	public static ArrayList<Player> freezelist = new ArrayList<>();
	
	
	public static ArrayList<Player> getFreezedPayers() {
		return freezelist;
	}
	
	
	public static Boolean isPlayerFreezed(Player p) {
		if(getFreezedPayers().contains(p)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void setPlayerFreezed(Player p, Boolean status) {
		if(status) {
			freezelist.add(p);
		} else {
			freezelist.remove(p);
		}
	}

}
