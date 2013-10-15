package com.elsewhere_games.lib.entity.component;

import com.elsewhere_games.lib.entity.Component;

public class Name implements Component {

	public Name(String name) {
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return this.name;
	}
	
}
