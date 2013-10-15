package com.elsewhere_games.lib.entity;

//Utility Imports
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>An entity is a container for components. Any entity can hold a mix of 
 * components, which makes the model very flexible.</p>
 * 
 * <p>Note that an entity cannot be directly constructed, it has to be created
 * by an entity manager.</p>
 * 
 * @author Hans Pragt
 *
 * @see EntityManager
 */

public class Entity {

	/*
	 * Constructors
	 */
	
	/**
	 * <p>Class constructor. Creates a new entity with a random id. This class 
	 * has been made protected so that only an entity manager can create new
	 * entities.</p>
	 */
	protected Entity() {
		this.id = UUID.randomUUID();
		this.components = new HashMap<Class<?>, Component>();
	}
	
	/*
	 * Identification
	 * 
	 * Each entity contains a unique identifier by which they can be accurately 
	 * identifier. Note that UUIDs are 128 bits, so they may be a bit too heavy-
	 * weight.
	 */
	
	private UUID id;
	
	/**
	 * <p>Gets the unique identifier of this object.</p>
	 * 
	 * @return The unique identifier of this object.
	 */
	public UUID getId() {
		return this.id;
	}
	
	/*
	 * Components
	 */
	
	public Map<Class<?>, Component> components;
	
	/**
	 * <p>Check to see if this entity contains a component of the specified
	 * <code>signature</code> class.</p>
	 * 
	 * @param signature The class signature of the component to check for.
	 * @return <code>true</code> if this entity has a component of the specified
	 * signature, <code>false</code> otherwise.
	 */
	public boolean hasComponent(Class<?> signature) {
		return this.components.containsKey(signature);
	}
	
	/**
	 * <p>Check to see if this entity contains all components of the specified
	 * <code>signatures</code> classes.</p>
	 * 
	 * @param signatures The class signatures of the components to check for.
	 * @return <code>true</code> if this entity contains all the components of
	 * the specified <code>signatures</code>, <code>false</code> otherwise.
	 */
	public boolean hasComponents(Class<?>... signatures) {
		boolean hasAll = true;
		
		// Check for absence of any of the signatures:
		for (Class<?> signature : signatures) {
			// Fast fail:
			if (!this.components.containsKey(signature)) {
				hasAll = false;
				break;
			}
		}
		
		return hasAll;
	}
	
	/**
	 * <p>Gets a component of this entity specified <code>signature</code> if
	 * any is present.</p>
	 *  
	 * @param signature The class signature of the component to retrieve.
	 * @return The component, if this entity has one matching the specified
	 * <code>signature</code>, or <code>null</code> otherwise.
	 */
	@SuppressWarnings("unchecked")
	public <C extends Component> C getComponent(Class<C> signature) {
		C component = (C)this.components.get(signature);
		
		return component;
	}
	
	/**
	 * <p>Adds a new <code>component</code> to this entity.</p>
	 * 
	 * @param component The component to add to this class.
	 * 
	 * @throws IllegalArgumentException If this entity already contains a
	 * component of the type specified. Note that the <code>hasComponent</code>
	 * function can be used to check in advance.
	 */
	public void addComponent(Component component) {
		if (this.hasComponent(component.getClass())) {
			throw new IllegalArgumentException("This entitiy already contains a component of the type specified.");
		}
		
		this.components.put(component.getClass(), component);
	}
	
	/**
	 * <p>Removes any component with the specified <code>singature</code> from
	 * this entity.</p>
	 * 
	 * @param signature The class signature of the component to remove.
	 */
	public void removeComponent(Class<?> signature) {
		this.components.remove(signature);
	}
	
	/*
	 * Java Object Overrides
	 */
	
	/**
	 * <p>Returns a <code>String</code> representation of the unique identifier
	 * of this entity.</p>
	 * 
	 * @return The <code>String</code> version of this object's identifier.
	 */
	@Override public String toString() {
		return this.id.toString();
	}
}

