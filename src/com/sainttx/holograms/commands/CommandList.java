package com.sainttx.holograms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.api.HologramPlugin;
import com.sainttx.holograms.util.TextUtil;

public class CommandList implements CommandExecutor {

	private final HologramPlugin plugin;

	public CommandList(HologramPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		HologramManager manager = plugin.getHologramManager();

		sender.sendMessage(ChatColor.GRAY + "All holograms:");
		for (Hologram hologram : manager.getActiveHolograms().values()) {
			int lines = hologram.getLines().size();
			sender.sendMessage(" - \"" + hologram.getId() + "\" at " + TextUtil.locationAsString(hologram.getLocation()) + " (" + lines + " lines)");
		}
		return true;
	}
}