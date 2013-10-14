package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;

public class Mass implements Component {

	/*
	 * Components
	 */
	
	private float mass;
	
	public Mass(float mass) {
		this.mass = mass;
	}
	
	/*
	 * Accessors
	 */
	
	public float getMass() {
		return this.mass;
	}
	
}
