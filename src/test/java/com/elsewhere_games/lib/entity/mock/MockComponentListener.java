package com.elsewhere_games.lib.entity.mock;

import com.elsewhere_games.lib.entity.Component;
import com.elsewhere_games.lib.entity.event.ComponentChangeListener;
import com.elsewhere_games.lib.entity.event.ComponentChangeType;

/**
 * <p>This mock component listener will store the last change type and associated
 * component. These values can also be reset to null if the test requires it.</p>
 */
public class MockComponentListener implements ComponentChangeListener {

	//// Listening ////

	@Override
	public void onComponentChange(ComponentChangeType type, Component component) {
		this.type = type;
		this.component = component;
	}

	//// Last Received ////

	private ComponentChangeType type;

	/**
	 * @return The last change type received by this listener.
	 */
	public ComponentChangeType getLastTypeReceived() {
		return this.type;
	}

	private Component component;

	/**
	 * @return The last component received by this listener.
	 */
	public Component getLastComponentReceived() {
		return this.component;
	}

	/**
	 * <p>Resets the last received change type and component to null.</p>
	 */
	public void reset() {
		this.type = null;
		this.component = null;
	}

}
