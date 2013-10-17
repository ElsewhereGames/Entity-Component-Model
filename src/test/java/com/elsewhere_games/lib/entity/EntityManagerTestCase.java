package com.elsewhere_games.lib.entity;

// JUnit
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Test case for the entity manager.</p>
 */
public class EntityManagerTestCase {

	@Test
	public void entityManagerCanBeCreated() {
		EntityManager manager = new EntityManager();
		Assert.assertNotNull(manager);
	}

}
