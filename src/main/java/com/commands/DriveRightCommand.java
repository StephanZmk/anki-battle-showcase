package com.commands;

import de.adesso.anki.battle.world.bodies.Vehicle;

public class DriveRightCommand extends Command {
	public DriveRightCommand() {
	}
	
	@Override
	public void execute(Vehicle vehicle) {
		vehicle.setTrack(6);
	}
}