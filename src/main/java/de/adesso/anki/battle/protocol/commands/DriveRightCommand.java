package de.adesso.anki.battle.protocol.commands;

import de.adesso.anki.battle.world.bodies.Vehicle;

public class DriveRightCommand extends Command {
	
	@Override
	public void execute(Vehicle vehicle) {
		vehicle.setTargetOffset(67.5);
	}

}
