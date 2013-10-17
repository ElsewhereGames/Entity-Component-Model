package com.elsewhere_games.lib.entity;

// JUnit
import org.junit.Test;
import org.junit.Assert;

/**
 * <p>This is the test case of the Entity class.</p>
 */
public class EntityTestCase {

	@Test
	public void createEntity() {
		Entity entity = new Entity();
		Assert.assertNotNull(entity);
	}

}
