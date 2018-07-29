package com.sainttx.holograms.impl;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.sainttx.holograms.HologramPlugin;
import com.sainttx.holograms.api.HologramEntityController;
import com.sainttx.holograms.api.entity.HologramEntity;
import com.sainttx.holograms.api.entity.ItemHolder;
import com.sainttx.holograms.api.entity.Nameable;
import com.sainttx.holograms.api.line.HologramLine;

public class HologramEntityControllerImpl implements HologramEntityController {

	protected final HologramPlugin plugin;

	public HologramEntityControllerImpl(HologramPlugin plugin) {
		this.plugin = plugin;
	}

	protected static final String METADATA_KEY = "hologram";

	@Override
	public Nameable spawnNameable(HologramLine line, Location location) {
		location.getChunk().load();
		ArmorStand stand = location.getWorld().spawn(location, ArmorStand.class);
		NameableImpl nameable = new NameableImpl(line, stand);
		stand.setMetadata(METADATA_KEY, new FixedMetadataValue(plugin, nameable));
		return nameable;
	}

	@Override
	public ItemHolder spawnItemHolder(HologramLine line, Location location) {
		location.getChunk().load();
		Item item = location.getWorld().spawn(location, Item.class);
		ItemHolderImpl itemholder = new ItemHolderImpl(line, item);
		item.setMetadata(METADATA_KEY, new FixedMetadataValue(plugin, itemholder));
		return itemholder;
	}

	@Override
	public HologramEntity getHologramEntity(Entity bukkitEntity) {
		Optional<MetadataValue> metadatavalue = bukkitEntity.getMetadata(METADATA_KEY).stream().filter(value -> value.getOwningPlugin().equals(plugin)).findAny();
		return metadatavalue.isPresent() ? (HologramEntity) metadatavalue.get().value() : null;
	}

}
