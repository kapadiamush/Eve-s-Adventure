package models.gridobjects.items;

import java.io.Serializable;

import models.Coordinate;
import exceptions.IllegalValueException;

/**
 * The Wall object extends Item and acts as a pure obstruction in the grid
 * world. No EObject can interact with a Wall beyond being blocked by it.
 * 
 * @author Carmine Iannaccone
 * 
 */
public class Wall extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	/*
	 * If a neighboring wall segment exists in a particular direction then it is
	 * stored within these variables.
	 */
	// private Wall north, south, east, west;
	/**
	 * Stores the side of the grid box that the wall exists on ("north",
	 * "south", "east", "west").
	 */
	private int side;

	public Wall(int iD, int side) {
		super(iD);
		if(side < Coordinate.UP || side > Coordinate.RIGHT) throw new IllegalValueException("Must be Coordinate.(UP|DOWN|LEFT|RIGHT");
		this.side = side;
	}
	
	public int getSide(){
		return side;
	}
	
	/**
	 * Determines what type the object is and returns the first letter of that
	 * types name.
	 * 
	 * @return type Returns the first letter of the type name. (Example: "S" =
	 *         Shrub)
	 */
	public String toString() {
		return "W";

	}
	
	public Wall copy(){
		return new Wall(this.getId(), this.side);
	}
}
