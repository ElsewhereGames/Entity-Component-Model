package com.elsewhere_games.lib.entity.component;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Mesh {

	/*
	 * Constructors
	 */
	
	public Mesh() {
		// Vertices
		this.vertices = this.allocateFloatBuffer(6);
		this.vertices.put(-0.5f);
		this.vertices.put(-0.5f);
		this.vertices.put(0.5f);
		this.vertices.put(-0.5f);
		this.vertices.put(0.0f);
		this.vertices.put(0.0f);
		
		// Indices
		this.indices = this.allocateShortBuffer(3);
		this.indices.put((short)0);
		this.indices.put((short)1);
		this.indices.put((short)2);
	}
	
	/*
	 * Accessors and Modifiers
	 */
	
	private FloatBuffer vertices;
	
	/**
	 * <p>Gets the vertices of this mesh to render.</p>
	 *
	 * @return The vertices of this mesh to render.
	 */
	public FloatBuffer getVertices() {
		return this.vertices;
	}
	
	private ShortBuffer indices;
	
	/**
	 * <p>Gets the indices of this mesh to render.</p>
	 * 
	 * @return The indices of this mesh to render.
	 */
	public ShortBuffer getIndices() {
		return this.indices;
	}
	
	/*
	 * Buffer Utilities	
	 */
	
	/**
	 * <p>Directly allocates a float buffer able to hold <code>capacity</code>
	 * amount of floats.</p>
	 * 
	 * @param capacity The amount of floats the buffer will hold.
	 * @return A directly allocated float buffer.
	 */
	private FloatBuffer allocateFloatBuffer(final int capacity) {
		ByteBuffer backingBuffer = ByteBuffer.allocateDirect(capacity * 4);
		backingBuffer.order(ByteOrder.nativeOrder());
		
		return backingBuffer.asFloatBuffer();
	}
	
	/**
	 * <p>Directly allocates a short buffer able to hold <code>capacity</code>
	 * amount of shorts (ha!).</p>
	 * 
	 * @param capacity The amount of shorts the buffer will hold.
	 * @return A directly allocated short buffer.
	 */
	private ShortBuffer allocateShortBuffer(final int capacity) {
		ByteBuffer backingBuffer = ByteBuffer.allocateDirect(capacity * 2);
		backingBuffer.order(ByteOrder.nativeOrder());
		
		return backingBuffer.asShortBuffer();
	}
	
}
