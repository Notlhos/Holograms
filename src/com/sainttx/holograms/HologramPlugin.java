package com.sainttx.holograms;

import com.sainttx.holograms.api.HologramEntityController;
import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.commands.HologramCommands;
import com.sainttx.holograms.impl.HologramEntityControllerImpl;
import com.sainttx.holograms.parser.AnimatedItemLineParser;
import com.sainttx.holograms.parser.AnimatedTextLineParser;
import com.sainttx.holograms.parser.ItemLineParser;
import com.sainttx.holograms.tasks.HologramSaveTask;
import com.sainttx.holograms.tasks.HologramUpdateTask;

public class HologramPlugin extends com.sainttx.holograms.api.HologramPlugin {

	private ManagerImpl manager = new ManagerImpl(this);
	private HologramEntityController controller = new HologramEntityControllerImpl(this);
	private final Runnable saveTask = new HologramSaveTask(this);
	private final Runnable updateTask = new HologramUpdateTask(this);

	@Override
	public void onEnable() {
		addLineParser(new ItemLineParser());
		addLineParser(new AnimatedItemLineParser());
		addLineParser(new AnimatedTextLineParser());

		getServer().getPluginManager().registerEvents(new HologramListener(this), this);
		getCommand("holograms").setExecutor(new HologramCommands(this));
		getServer().getScheduler().runTaskLater(this, () -> manager.load(), 1L);
		getServer().getScheduler().runTaskTimer(this, updateTask, 2L, 2L);
	}

	@Override
	public void onDisable() {
		saveTask.run();
		manager.clear();
		this.manager = null;
		this.controller = null;
	}

	@Override
	public HologramManager getHologramManager() {
		return manager;
	}

	@Override
	public HologramEntityController getEntityController() {
		return controller;
	}
}
