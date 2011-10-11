package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;

public class Dimension implements Component {

	/*
	 * Constructors
	 */
	
	public Dimension(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	/*
	 * Accessors and Modifiers
	 */
	
	private float width;
	
	public float getWidth() {
		return this.width;
	}
	
	private float height;
	
	public float getHeight() {
		return this.height;
	}
}
