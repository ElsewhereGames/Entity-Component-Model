package com.elsewhere_games.lib.entity.component;

public class UIWidget {

	/*
	 * Constructors
	 */
	
	/**
	 * <p>Class constructor.</p>
	 */
	public UIWidget() {
		this.selected = false;
	}
	
	/*
	 * Components
	 */
	
	private boolean enabled;
	
	/**
	 * <p>To determine whether or not this widget is enabled.</p>
	 * 
	 * @return <code>true</code> if this widget is enabled, <code>false</code>
	 * otherwise.
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	
	/**
	 * <p>To set wheter or not this widget is enabled.</p>
	 * 
	 * @param enabled <code>true</code> to enable this widget, <code>false</code>
	 * otherwise.
	 */
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}
	
	private boolean selected;
	
	/**
	 * <p>To determine whether or not this widget is selected.</p>
	 * 
	 * @return <code>true</code> if this widget is selected, <code>false</code>
	 * otherwise.
	 */
	public boolean isSelected() {
		return this.selected;
	}
	
	/**
	 * <p>Sets whether or not this widget is selected.</p>
	 * 
	 * @param selected set to <code>true</code> if this widget is selected, 
	 * <code>false</code> otherwise.
	 */
	public void setSelected(final boolean selected) {
		this.selected = selected;
	}
	
}
