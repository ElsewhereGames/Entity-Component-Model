package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;
import com.elsewhere_games.lib.math.Vector2d;

public class Velocity implements Component {

	/*
	 * Constructors
	 */
	
	/**
	 * <p>Class constructor that allows the <code>speed</code> as well as the
	 * <code>direction</code> of this velocity to be specified.</p>
	 * 
	 * @param speed The speed of this velocity.
	 * @param direction The direction of this velocity.
	 */
	public Velocity(final float speed, final Vector2d direction) {
		this.speed = speed;
		this.direction = direction;
	}
	
	/*
	 * Accessors and Modifiers
	 */
	
	private float speed;
	
	/**
	 * <p>Gets the speed of this velocity.</p>
	 * 
	 * @return The speed of this velocity.
	 */
	public final float getSpeed() {
		return this.speed;
	}
	
	private Vector2d direction;
	
	/**
	 * <p>Gets the direction of this velocity.</p>
	 * 
	 * @return The direction of this velocity.
	 */
	public final Vector2d getDirection() {
		return this.direction;
	}
	
	/**
	 * <p>Sets the direction of this velocity.</p>
	 * 
	 * @param direction The new direction of this velocity.
	 */
	public void setDirection(final Vector2d direction) {
		this.direction = direction;
	}
	
}
