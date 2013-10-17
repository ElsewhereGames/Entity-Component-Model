package com.elsewhere_games.lib.entity;

// Java Util
import java.util.UUID;

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

	@Test
	public void entityManagerCanCreateEntities() {
		EntityManager manager = new EntityManager();
		UUID entityId = manager.createEntity();

		Assert.assertNotNull(entityId);
	}

}
