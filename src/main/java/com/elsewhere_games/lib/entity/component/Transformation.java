package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;
import com.elsewhere_games.lib.math.Vertex2d;

public class Transformation implements Component {

	/*
	 * Constants
	 */
	
	protected static final String TAG				= "Transformation";
	
	/*
	 * Constructors
	 */
	
	public Transformation(Vertex2d translation, float angle, float xScale, float yScale) {
		this.translation = translation;
		this.rotation = angle;
		this.xScale = xScale;
		this.yScale = yScale;
	}
	
	/**
	 * <p>Class constructor.</p>
	 */
	public Transformation() {
		this.translation = new Vertex2d();
		this.rotation = 0.0f;
		this.xScale = 1.0f;
		this.yScale = 1.0f;
	}
	
	/*
	 * Transformation
	 */
	
	/**
	 * <p>Transforms the specified <code>point</code> by the inverse of this
	 * transformation.</p>
	 * 
	 * @param point The point to transform.
	 * @return The resulting point.
	 */
	public Vertex2d applyInverse(Vertex2d point) {
		float x = point.getX() - this.translation.getX();
		x = x * (1 / this.xScale);
		
		float y = point.getY() - this.translation.getY();
		y = y * (1 / this.yScale);
		
		return new Vertex2d(x, y);
	}
	
	/*
	 * Translation
	 */
	
	private Vertex2d translation;
	
	/**
	 * <p>Gets the translation component of this transformation.</p>
	 * 
	 * @return The translation component of this transformation.
	 */
	public Vertex2d getTranslation() {
		return this.translation;
	}
	
	/*
	 * Rotation
	 */
	
	private float rotation;
	
	/**
	 * <p>Gets the rotation component of this transformation.</p>
	 * 
	 * @return The rotation of this component, specified in degrees.
	 */
	public float getRotation() {
		return this.rotation;
	}
	
	/**
	 * <p>Sets the rotation component of this transformation.</p>
	 * 
	 * @param rotation The new value for the transformation component.
	 */
	public void setRotation(final float rotation) {
		this.rotation = rotation;
	}
	
	/*
	 * Scale
	 */
	
	private float xScale;
	
	/**
	 * <p>Gets the horizontal scale of this transformation.</p>
	 * 
	 * @return The horizontal scale of this transformation.
	 */
	public float getXScale() {
		return this.xScale;
	}
	
	private float yScale;
	
	public float getYScale() {
		return this.yScale;
	}	
}
