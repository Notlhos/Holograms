package com.sainttx.holograms.api.entity;

import org.bukkit.entity.Entity;

import com.sainttx.holograms.api.line.HologramLine;

public interface HologramEntity {

	/**
	 * Returns the parenting {@link HologramLine} of this base.
	 *
	 * @return the base line
	 */
	HologramLine getHologramLine();

	/**
	 * Permanently removes this entity.
	 */
	void remove();

	/**
	 * Gets the Bukkit entity for this hologram line.
	 *
	 * @return the entity
	 */
	Entity getBukkitEntity();
}
