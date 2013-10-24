package com.elsewhere_games.lib.entity.event;

// Entities Elsewhere
import com.elsewhere_games.lib.entity.Component;

/**
 * <p>A call-back interface for objects who want to observe changes in the
 * component collection of an {@link com.elsewhere_games.lib.entity.Entity}
 * provided an instance of this listener is registered with the entity.</p>
 */
public interface ComponentChangeListener {

	/**
	 * <p>Called when there is a change to the set of components in an
	 * entity.</p>
	 *
	 * @param type The type of change that occurred.
	 * @param component The context of the change.
	 */
	public void onComponentChange(ComponentChangeType type, Component component);

}
