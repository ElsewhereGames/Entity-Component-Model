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

	@Test
	public void entityManagerCanCreateEntities() {
		EntityManager manager = new EntityManager();
		Entity entity = manager.createEntity();

		Assert.assertNotNull(entity);
		Assert.assertTrue(manager.hasEntity(entity));
	}

	@Test
	public void entitiesCanBeRemoved() {
		EntityManager manager = new EntityManager();
		Entity entity = manager.createEntity();

		Assert.assertNotNull(entity);

		manager.destroyEntity(entity);

		Assert.assertFalse(manager.hasEntity(entity));
	}

}
