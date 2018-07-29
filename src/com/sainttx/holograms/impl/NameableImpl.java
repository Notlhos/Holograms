package com.sainttx.holograms.impl;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import com.sainttx.holograms.api.entity.Nameable;
import com.sainttx.holograms.api.line.HologramLine;

public class NameableImpl implements Nameable {

	protected final HologramLine line;
	protected final ArmorStand armorstand;

	public NameableImpl(HologramLine line, ArmorStand armorstand) {
		this.line = line;
		this.armorstand = armorstand;
		armorstand.setInvulnerable(true);
		armorstand.setGravity(false);
		armorstand.setCanPickupItems(false);
		armorstand.setVisible(false);
		armorstand.setMarker(true);
		armorstand.setCustomNameVisible(true);
	}

	@Override
	public HologramLine getHologramLine() {
		return line;
	}

	@Override
	public Entity getBukkitEntity() {
		return armorstand;
	}

	@Override
	public void setName(String text) {
		armorstand.setCustomName(text);
	}

	@Override
	public String getName() {
		return armorstand.getCustomName();
	}

	@Override
	public void remove() {
		armorstand.remove();
	}

}
