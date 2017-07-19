package de.precoder.trollplugin.listener;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.entity.projectile.EntityEgg;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.ProjectileHitEvent;
import cn.nukkit.level.Explosion;

public class ProjectileHit implements Listener {
	
	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		Entity en = e.getEntity();
		
		if(en instanceof EntityEgg) {
			Player p = (Player) ((EntityEgg) en).shootingEntity;
			
			if(TrollListener.isPlayerVerified(p)) {
				Explosion ex = new Explosion(en.getPosition(), 5, en);
				ex.explode();
			}
		}
	}

}
