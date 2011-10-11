package com.elsewhere_games.lib.entity.component;

// Entity Imports
import com.elsewhere_games.lib.entity.Component;

//Math Imports
import com.elsewhere_games.lib.math.Vertex2d;
import com.elsewhere_games.lib.math.boundingvolume.BoundingVolume;

public class Bounds implements Component {

	/*
	 * Components
	 */
	
	private BoundingVolume bounds;
		
	/*
	 * Constructors
	 */
	
	/**
	 * <p>Class constructor.</p>
	 */
	public Bounds(BoundingVolume bounds) {
		this.bounds = bounds;
	}
	
	/*
	 * Bounds Operations
	 */
	
	/**
	 * <p>Check to see if the specified <code>point</code> lies inside of these
	 * bounds.</p>
	 * 
	 * @param point The point to check for containment in these bounds.
	 * @return <code>true</code> if the specified point lies inside of these
	 * bounds, <code>false</code> otherwise. 
	 */
	public boolean contains(Vertex2d point) {
		return this.bounds.contains(point);
	}
	
	/*
	 * Accessors and Modifiers
	 */
	
	/**
	 * <p>Gets the structure used to provide the underlying implementation of
	 * these bounds.</p>
	 * 
	 * @return The specification used to implement these bounds.
	 */
	public BoundingVolume getImplementation() {
		return this.bounds;
	}
}
