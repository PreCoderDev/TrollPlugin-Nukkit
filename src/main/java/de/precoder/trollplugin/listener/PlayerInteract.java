package de.precoder.trollplugin.listener;

import cn.nukkit.Player;
import cn.nukkit.entity.projectile.EntityEgg;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.TextFormat;
import de.precoder.trollplugin.Main;

public class PlayerInteract implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getItem().getCustomName().contains("GRANADE-LAUNCHER")) {
			if(TrollListener.isPlayerVerified(p)) {
				CompoundTag nbt = new CompoundTag()
		                .putList(new ListTag<DoubleTag>("Pos")
		                        .add(new DoubleTag("", p.x))
		                        .add(new DoubleTag("", p.y + p.getEyeHeight()))
		                        .add(new DoubleTag("", p.z)))
		                .putList(new ListTag<DoubleTag>("Motion")
		                        .add(new DoubleTag("", -Math.sin(p.yaw / 180 * Math.PI) * Math.cos(p.pitch / 180 * Math.PI)))
		                        .add(new DoubleTag("", -Math.sin(p.pitch / 180 * Math.PI)))
		                        .add(new DoubleTag("", Math.cos(p.yaw / 180 * Math.PI) * Math.cos(p.pitch / 180 * Math.PI))))
		                .putList(new ListTag<FloatTag>("Rotation")
		                        .add(new FloatTag("", (float) p.yaw))
		                        .add(new FloatTag("", (float) p.pitch)));

		        float f = 1.5f;
		        EntityEgg en = new EntityEgg(p.chunk, nbt, p);
		        en.spawnToAll();
		        en.setMotion(en.getMotion().multiply(f));
			}
		}
		
		
		if(e.getItem().getCustomName().contains("MINIGUN")) {
			p.sendMessage(Main.prefix + TextFormat.RED + "SOON");
		}
		
		
	}

}
