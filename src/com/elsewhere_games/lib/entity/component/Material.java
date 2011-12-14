package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;
import com.elsewhere_games.lib.graphics.Color;
import com.elsewhere_games.lib.resources.Texture;

public class Material implements Component {

	/*
	 * Contructors
	 */
	
	/**
	 * <p>Class constructor. Initializes the color of this material to black.</p>
	 */
	public Material() {
		this(Color.Black);
	}
	
	/**
	 * <p>Class constructor. Allows the <code>color</code> of this material to be
	 * specified.</p>
	 * 
	 * @param color The color of this material.
	 */
	public Material(final Color color) {
		this.color = color;
	}
	
	/**
	 * <p>Class constructor. Allows the <code>texture</code> of this material to
	 * be specified.</p>
	 * 
	 * @param texture The texture of this material.
	 */
	public Material(final Texture texture) {
		this.texture = texture;
		this.color = Color.Black;
	}
	
	/*
	 * Accessors and Modifiers
	 */
	
	private Color color;
	
	/**
	 * <p>Gets the color of this material.</p>
	 * 
	 * @return The color of this material.
	 */
	public final Color getColor() {
		return this.color;
	}
	
	/**
	 * <p>Sets the color of this material.</p>
	 * 
	 * @param color The new color of this material.
	 */
	public void setColor(final Color color) {
		this.color = color;
	}
	
	private Texture texture;
	
	/**
	 * <p>Gets the texture of this material.</p>
	 * 
	 * @return The texture of this material.
	 */
	public final Texture getTexture() {
		return this.texture;
	}
	
	/**
	 * <p>Sets the texture of this material.</p>
	 * 
	 * @param texture The new texture of this material.
	 */
	public void setTexture(final Texture texture) {
		this.texture = texture;
	}
	
}
