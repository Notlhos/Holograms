package com.sainttx.holograms.api.line;

import java.util.Iterator;

import org.bukkit.inventory.ItemStack;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.animation.Animation;

public class AnimatedItemLine extends ItemLine implements UpdatingHologramLine {

	private final Animation<ItemStack> animation;
	private final long delay;
	private long lastUpdate;

	public AnimatedItemLine(Hologram parent, Animation<ItemStack> animation) {
		this(parent, animation, 5000L);
	}

	public AnimatedItemLine(Hologram parent, Animation<ItemStack> animation, long delay) {
		super(parent, "animated_item(" + delay + "):" + animationToRaw(animation), animation.firstSlide());
		this.animation = animation;
		this.delay = delay;
	}

	// Converts an animation to raw format
	private static String animationToRaw(Animation<ItemStack> animation) {
		StringBuilder sb = new StringBuilder();
		Iterator<ItemStack> iterator = animation.getSlides().iterator();
		while (iterator.hasNext()) {
			sb.append(itemstackToRaw(iterator.next()));
			if (iterator.hasNext()) {
				sb.append("||");
			}
		}
		return sb.toString();
	}

	@Override
	public void update() {
		setItem(animation.nextSlide());
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public long getLastUpdateTime() {
		return lastUpdate;
	}
}
