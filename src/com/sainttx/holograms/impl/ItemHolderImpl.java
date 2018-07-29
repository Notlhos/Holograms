package com.sainttx.holograms.impl;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import com.sainttx.holograms.api.entity.HologramEntity;
import com.sainttx.holograms.api.entity.ItemHolder;
import com.sainttx.holograms.api.line.HologramLine;

public class ItemHolderImpl implements ItemHolder {

	protected final HologramLine line;
	protected final Item item;

	public ItemHolderImpl(HologramLine line, Item item) {
		this.line = line;
		this.item = item;
		item.setInvulnerable(true);
		item.setGravity(false);
	}

	@Override
	public HologramLine getHologramLine() {
		return line;
	}

	@Override
	public Entity getBukkitEntity() {
		return item;
	}

	@Override
	public void remove() {
		item.remove();
	}

	protected HologramEntity mount;

	@Override
	public HologramEntity getMount() {
		return mount;
	}

	@Override
	public void setMount(HologramEntity entity) {
		if (mount != null) {
			mount.getBukkitEntity().removePassenger(getBukkitEntity());
			mount = null;
		}
		if (entity != null) {
			mount = entity;
			mount.getBukkitEntity().addPassenger(getBukkitEntity());
		}
	}

	@Override
	public void setItem(ItemStack itemstack) {
		item.setItemStack(itemstack);
	}

	@Override
	public ItemStack getItem() {
		return item.getItemStack();
	}

}
