package com.elsewhere_games.lib.entity;

// JUnit
import org.junit.Test;
import org.junit.Assert;

// Events Elsewhere
import com.elsewhere_games.lib.entity.event.ComponentChangeType;

// Mocking Elsewhere
import com.elsewhere_games.lib.entity.mock.MockComponent;
import com.elsewhere_games.lib.entity.mock.AnotherMockComponent;
import com.elsewhere_games.lib.entity.mock.MockComponentListener;


/**
 * <p>This is the test case of the Entity class.</p>
 */
public class EntityTestCase {

	@Test
	public void entityCanBeCreated() {
		Entity entity = new Entity();
		Assert.assertNotNull(entity);
	}

	@Test
	public void oneComponentCanBeAdded() {
		Entity entity = new Entity();
		MockComponent mockComponent = new MockComponent();

		entity.addComponent(mockComponent);
		Assert.assertTrue(entity.hasComponent(MockComponent.class));
	}

	@Test
	public void multipleComponentsCanBeAdded() {
		Entity entity = new Entity();

		MockComponent mockComponent = new MockComponent();
		entity.addComponent(mockComponent);

		Assert.assertTrue(entity.hasComponent(MockComponent.class));

		AnotherMockComponent anotherMockComponent = new AnotherMockComponent();
		entity.addComponent(anotherMockComponent);

		Assert.assertTrue(entity.hasComponent(AnotherMockComponent.class));

		Assert.assertTrue(entity.hasComponents(MockComponent.class, AnotherMockComponent.class));
	}

	@Test
	public void aComponentCanBeAddedOnlyOnce() {
		Entity entity = new Entity();
		MockComponent mockComponent = new MockComponent();

		entity.addComponent(mockComponent);

		try {
			entity.addComponent(mockComponent);
			Assert.fail();
		}

		catch (IllegalArgumentException argumentException) {
			// Expected condition.
		}
	}

	@Test
	public void componentsCanBeRetrieved() {
		Entity entity = new Entity();
		MockComponent mockComponent = new MockComponent();

		entity.addComponent(mockComponent);

		MockComponent retrievedComponent = entity.getComponent(MockComponent.class);
		Assert.assertEquals(mockComponent, retrievedComponent);
	}

	@Test
	public void componentsCanBeRemoved() {
		Entity entity = new Entity();
		MockComponent mockComponent = new MockComponent();

		entity.addComponent(mockComponent);

		Assert.assertTrue(entity.hasComponent(MockComponent.class));

		entity.removeComponent(MockComponent.class);

		Assert.assertFalse(entity.hasComponent(MockComponent.class));
	}

	@Test
	public void componentSetChangesCanBeObserved() {
		Entity entity = new Entity();

		MockComponentListener listener = new MockComponentListener();
		entity.addComponentChangeListener(listener);

		Assert.assertNull(listener.getLastTypeReceived());
		Assert.assertNull(listener.getLastComponentReceived());

		MockComponent mockComponent = new MockComponent();
		entity.addComponent(mockComponent);

		Assert.assertEquals(ComponentChangeType.COMPONENT_ADDED, listener.getLastTypeReceived());
		Assert.assertEquals(mockComponent, listener.getLastComponentReceived());

		listener.reset();

		Assert.assertNull(listener.getLastTypeReceived());
		Assert.assertNull(listener.getLastComponentReceived());

		entity.removeComponent(MockComponent.class);

		Assert.assertEquals(ComponentChangeType.COMPONENT_REMOVED, listener.getLastTypeReceived());
		Assert.assertEquals(mockComponent, listener.getLastComponentReceived());
	}

}
