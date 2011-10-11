package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;
import com.elsewhere_games.lib.graphics.Color;

public class Material implements Component {

	/*
	 * Components
	 */
	
	private Color color;
	
	/*
	 * Contructors
	 */
	
	/**
	 * <p>Class constructor. Initializes the color of this material to black.</p>
	 */
	public Material() {
		this.color = Color.Black;
	}
	
	/**
	 * <p>Class constructor. Allows the <code>color</code> of this material to be
	 * specified.</p>
	 * 
	 * @param color The color of this material.
	 */
	public Material(Color color) {
		this.color = color;
	}
	
	/*
	 * Accessors and Modifiers
	 */
	
	/**
	 * <p>Gets the color of this material.</p>
	 * 
	 * @return The color of this material.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * <p>Sets the color of this material.</p>
	 * 
	 * @param color The new color of this material.
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
