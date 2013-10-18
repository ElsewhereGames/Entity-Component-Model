package com.elsewhere_games.lib.entity;

// JUnit
import org.junit.Assert;
import org.junit.Test;

// Java Utilities
import java.util.List;
import java.util.UUID;

// Testing Elsewhere
import com.elsewhere_games.lib.entity.mock.MockComponent;
import com.elsewhere_games.lib.entity.mock.AnotherMockComponent;

/**
 * <p>Test case for the entity manager.</p>
 */
public class EntityManagerTestCase {

	//// Life-Cycle ////

	@Test
	public void entityManagerCanBeCreated() {
		EntityManager manager = new EntityManager();
		Assert.assertNotNull(manager);
	}

	//// Entities ////

	@Test
	public void entityManagerCanCreateEntities() {
		EntityManager manager = new EntityManager();
		Entity entity = manager.createEntity();

		Assert.assertNotNull(entity);
		Assert.assertTrue(manager.hasEntity(entity));
	}

	@Test
	public void entitiesCanBeAdded() {
		EntityManager manager = new EntityManager();
		Entity entity = new Entity();

		Assert.assertFalse(manager.hasEntity(entity));

		manager.addEntity(entity);

		Assert.assertTrue(manager.hasEntity(entity));
	}

	@Test
	public void entitiesCanBeAddedOnlyOnce() {
		EntityManager manager = new EntityManager();
		Entity entity = new Entity();

		manager.addEntity(entity);

		Assert.assertTrue(manager.hasEntity(entity));

		try {
			manager.addEntity(entity);
			Assert.fail();
		}

		catch (IllegalArgumentException argumentException) {
			// Do nothing, expected.
		}
	}

	@Test
	public void entitiesCanBeRemoved() {
		EntityManager manager = new EntityManager();
		Entity entity = manager.createEntity();

		Assert.assertNotNull(entity);

		manager.destroyEntity(entity);

		Assert.assertFalse(manager.hasEntity(entity));
	}

	@Test
	public void entitiesCannotBeRemovedIfNotPresent() {
		EntityManager manager = new EntityManager();
		Entity entity = new Entity();

		Assert.assertFalse(manager.hasEntity(entity));

		try {
			manager.destroyEntity(entity);
			Assert.fail();
		}

		catch (IllegalArgumentException argumentException) {
			// Do nothing, expected.
		}
	}

	@Test
	public void entityCanBeFoundById() {
		EntityManager manager = new EntityManager();
		Entity entity = manager.createEntity();

		Entity foundEntity = manager.getEntity(entity.getId());

		Assert.assertEquals(entity, foundEntity);
	}

	//// Queries ////

	@Test
	public void emptyQueriesCanBeCreated() {
		EntityManager manager = new EntityManager();

		UUID queryId = manager.createQuery();
		Assert.assertNotNull(queryId);
		Assert.assertTrue(manager.executeQuery(queryId).isEmpty());
	}

	@Test
	public void simpleQueriesCanBeCreated() {
		EntityManager manager = new EntityManager();

		Entity entity = manager.createEntity();
		entity.addComponent(new MockComponent());

		UUID queryId = manager.createQuery(MockComponent.class);
		List<Entity> queryResults = manager.executeQuery(queryId);

		Assert.assertTrue(queryResults.size() == 1);
		Assert.assertTrue(queryResults.contains(entity));
	}

	@Test
	public void complexQueriesCanBeCreated() {
		EntityManager manager = new EntityManager();

		Entity entityWithComponent = manager.createEntity();
		entityWithComponent.addComponent(new MockComponent());

		Entity entityWithAnotherComponent = manager.createEntity();
		entityWithAnotherComponent.addComponent(new AnotherMockComponent());

		UUID queryId = manager.createQuery(MockComponent.class);
		List<Entity> queryResults = manager.executeQuery(queryId);

		Assert.assertTrue(queryResults.size() == 1);
		Assert.assertTrue(queryResults.contains(entityWithComponent));
		Assert.assertFalse(queryResults.contains(entityWithAnotherComponent));
	}

	@Test
	public void queriesCanBeUpdated() {
		EntityManager manager = new EntityManager();

		Entity entity = manager.createEntity();
		entity.addComponent(new MockComponent());

		UUID queryId = manager.createQuery(AnotherMockComponent.class);

		List<Entity> initialQueryResults = manager.executeQuery(queryId);
		Assert.assertTrue(initialQueryResults.isEmpty());

		manager.updateQuery(queryId, MockComponent.class);

		List<Entity> updatedQueryResults = manager.executeQuery(queryId);
		Assert.assertFalse(updatedQueryResults.isEmpty());
		Assert.assertTrue(updatedQueryResults.contains(entity));
	}

	@Test
	public void queriesHaveToBeReRunWhenAnEntityIsAdded() {
		EntityManager manager = new EntityManager();

		Entity entity = new Entity();
		MockComponent component = new MockComponent();
		entity.addComponent(component);

		UUID queryId = manager.createQuery(MockComponent.class);
		List<Entity> initialQueryResults = manager.executeQuery(queryId);
		Assert.assertFalse(initialQueryResults.contains(entity));

		manager.addEntity(entity);
		Assert.assertFalse(initialQueryResults.contains(entity));

		List<Entity> updatedQueryResults = manager.executeQuery(queryId);
		Assert.assertTrue(updatedQueryResults.contains(entity));
	}

}
