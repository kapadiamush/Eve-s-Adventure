package models.gridobjects.items;

import java.io.Serializable;

/**
 * The Bamboo object extends Item and is an object that can be stored within Eve's inventory or other Item objects.
 * @author Carmine Iannaccone
 *
 */
public class Bamboo extends Item implements Serializable{

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 7L;
	
	public Bamboo(int iD){ 
		super(iD); 
	}
	
	/**
	 * Determines what type the object is and returns the first letter of that
	 * types name.
	 * 
	 * @return type Returns the first letter of the type name. (Example: "S" =
	 *         Shrub)
	 */
	public String toString() {
		return "B";

	}
	
	public Item copy(){
		return new Bamboo(this.getId());
	}
}
