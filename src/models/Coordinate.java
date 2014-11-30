package models;

import models.campaign.IllegalValueException;

/**
 * Coordinate class to represent coordinates in the world
 *
 */
public class Coordinate {
	/**
	 * Represents the x-coordinate in a Cartesian product representation of the world
	 */
	private int x;
	/**
	 * Represents the y-coordinate in a Cartesian product representation of the world
	 */
	private int y;
	
	/**
	 * Constructor method. Creates a representation of the x and y coordinates
	 * 
	 * @param x The x-coordinate in a Cartesian product representation of the world
	 * @param y The y-coordinate in a Cartesian product representation of the world
	 */
	public Coordinate(int x, int y){
		if(x < 0 || y < 0) throw new IllegalValueException("Given coordinates must be >= 0.");
		this.x = x; 
		this.y = y;
	}
	/**
	 * Gets the x-coordinate
	 * 
	 * @return The x-coordinate
	 */
	public int getX(){
		return this.x; 
	}
	
	/**
	 * Gets the y-coordinate
	 * 
	 * @return The y-coordinate
	 */
	public int getY(){ 
		return this.y; 
	}
	/**
	 * Sets a new value for the x coordinate. Assumes the new x coordinate is valid 
	 * 
	 * @param x The new x coordinate
	 * 
	 */
	public void setX(int x){ 
		if(x < 0) throw new IllegalValueException("Value for X must be >= 0.");
		this.x = x;
	}
	
	/**
	 * Sets a new value for the y coordinate. Assumes the new y coordinate is valid 
	 * 
	 * @param y The new y coordinate
	 */
	public void setY(int y){
		if(y < 0) throw new IllegalValueException("Value for Y must be >= 0.");
		 this.y = y; 
	}
	
	/**
	 * Moves the coordinate forward one
	 */
	public void moveNorth(){ 
		this.y--; 
	}
	
	/**
	 * Moves the coordinate back one
	 */
	public void moveSouth(){ 
		this.y++; 
	}
	
	/**
	 * Moves the coordinate to the right one
	 */
	public void moveEast(){ 
		this.x++; 
	}
	
	/**
	 * Moves the coordinate to the left one
	 */
	public void moveWest(){ 
		this.x--; 
	}
}

