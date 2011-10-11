package com.elsewhere_games.lib.entity;

/**
 * 
 * @author Hans Pragt
 *
 */
public abstract class AbstractSystem {

	/*
	 * Constructors
	 */
	
	/**
	 * <p>Class constructor.</p>
	 * 
	 * @param entities The entities with which this system will interact.
	 */
	public AbstractSystem(EntityManager entities) {
		this.entities = entities;
	}
	
	/*
	 * Entities
	 */
	
	protected EntityManager entities;
	
}
