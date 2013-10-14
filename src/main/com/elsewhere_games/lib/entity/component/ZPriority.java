package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;

public class ZPriority implements Component {

	public ZPriority(int priority) {
		this.priority = priority;
	}
	
	private int priority;
	
	public int getPriority() {
		return this.priority;
	}
	
}
