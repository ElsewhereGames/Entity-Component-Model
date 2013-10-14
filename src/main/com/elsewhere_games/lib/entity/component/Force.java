package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;
import com.elsewhere_games.lib.math.Vector2d;

public class Force implements Component {

	/*
	 * Components
	 */
	
	private Vector2d direction;			// Direction in which this force is exerted.
	private float magnitude;			// Expressed in newtons.
	
	/*
	 * Constructors
	 */
	
	public Force(Vector2d direction, float magnitude) {
		this.direction = direction;
		this.magnitude = magnitude;
	}
	
	/*
	 * Accessors
	 */
	
	public Vector2d getDirection() {
		return this.direction;
	}
	
	public float getMagnitude() {
		return this.magnitude;
	}
	
}
