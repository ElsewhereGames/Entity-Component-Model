package com.elsewhere_games.lib.entity;

// Utility Containers
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Events Elsewhere
import com.elsewhere_games.lib.entity.event.ComponentChangeListener;
import com.elsewhere_games.lib.entity.event.ComponentChangeType;

/**
 * <p>An entity is a container for components. Any entity can hold a mix of 
 * components, which makes the model very flexible.</p>
 * 
 * <p>Note that an entity cannot be directly constructed, it has to be created
 * by an entity manager.</p>
 * 
 * @author Hans Pragt
 *
 * @see {@link EntityManager}
 */

public class Entity {

	//// Life-Cycle ////

	/**
	 * <p>Class constructor. Creates a new entity with a random id. This class 
	 * has been made protected so that only an entity manager can create new
	 * entities.</p>
	 */
	Entity() {
		this.id = UUID.randomUUID();
		this.components = new HashMap<Class<?>, Component>();
		this.listeners = new ArrayList<ComponentChangeListener>();
	}

	//// Identity ////

	/*
	 * Each entity contains a unique identifier by which they can be accurately
	 * identifier. Note that UUIDs are 128 bits, so they may be a bit too heavy-
	 * weight.
	 */
	
	private final UUID id;
	
	/**
	 * <p>Gets the unique identifier of this object.</p>
	 * 
	 * @return The unique identifier of this object.
	 */
	public UUID getId() {
		return this.id;
	}
	
	//// Components ////
	
	private final Map<Class<?>, Component> components;
	
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
	 *
	 * @return <code>true</code> if this entity contains all the components of
	 * the specified <code>signatures</code>, <code>false</code> otherwise.
	 */
	public boolean hasComponents(Class<?>... signatures) {
		return this.hasComponents(Arrays.asList(signatures));
	}

	/**
	 * <p>Check to see if this entity contains all components of the specified
	 * <code>signatures</code> classes.</p>
	 *
	 * @param signatures The class signatures of the components to check for.
	 *
	 * @return <code>true</code> if this entity contains all the components of
	 * the specified <code>signatures</code>, <code>false</code> otherwise.
	 */
	public boolean hasComponents(List<Class<?>> signatures) {
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
		return (C)this.components.get(signature);
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
			throw new IllegalArgumentException("This entity already contains a component of the type specified.");
		}
		
		this.components.put(component.getClass(), component);

		/*
		 * If we get past putting the component into the map, it should be
		 * safe to fire a change event.
		 */
		this.fireComponentChange(ComponentChangeType.COMPONENT_ADDED, component);
	}
	
	/**
	 * <p>Removes any component with the specified <code>signature</code> from
	 * this entity.</p>
	 * 
	 * @param signature The class signature of the component to remove.
	 */
	public void removeComponent(Class<?> signature) {
		Component removed = this.components.remove(signature);
		if (removed != null) {
			this.fireComponentChange(ComponentChangeType.COMPONENT_REMOVED, removed);
		}
	}

	//// Component Change Listeners ////

	private final List<ComponentChangeListener> listeners;	// Notified on a change to the component list.

	/**
	 * <p>Adds a component change listener to this entity. When there is a
	 * change to the collection of components of this entity, the listener
	 * will be notified.</p>
	 *
	 * <p>Notifications are sent out in the order in which change listeners
	 * are registered with this entity, in the same thread context as in
	 * which the change to the component collection occurs.</p>
	 *
	 * @param listener Will receive notification of changes to the component set.
	 */
	public void addComponentChangeListener(final ComponentChangeListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * <p>Removes a component change from this entity. The listener will
	 * no longer receive notification about changes to the component set
	 * of this entity.</p>
	 *
	 * @param listener Will no longer receive notifications about the component set.
	 */
	public void removeComponentChangeListener(final ComponentChangeListener listener) {
		this.listeners.remove(listener);
	}

	// Trigger the call-back of all listeners registered with this class.
	private void fireComponentChange(ComponentChangeType type, Component context) {
		for (ComponentChangeListener listener : this.listeners) {
			listener.onComponentChange(type, context);
		}
	}
	
	//// Object Overrides ////
	
	/**
	 * <p>Returns a <code>String</code> representation of the unique identifier
	 * of this entity.</p>
	 * 
	 * @return The <code>String</code> version of this object's identifier.
	 */
	@Override
	public String toString() {
		return this.id.toString();
	}
}

