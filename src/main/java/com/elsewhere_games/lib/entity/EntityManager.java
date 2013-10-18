package com.elsewhere_games.lib.entity;

// Java Containers
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

// Java Utilities
import java.util.UUID;

/**
 *<p>The entity manager can maintain a collection of entities. More-over, a set
 * of queries to those entities can be created, making finding the right set of
 * entities for a particular task quick and easy.</p>
 *
 * <p>The backing collections used in this manager are not thread-safe, and
 * therefore any access must be synchronized externally.</p>
 */
public class EntityManager {

	//// Life-Cycle ////
	
	/**
	 * <p>Class constructor.</p>
	 */
	public EntityManager() {
		this.entities = new HashSet<Entity>();
		
		// Query system:
		this.queries = new HashMap<UUID, Class<?>[]>();
		this.cachedQueryResults = new HashMap<UUID, List<Entity>>();
	}
	
	//// Entities ////
	
	private Set<Entity> entities;
	
	/**
	 * <p>Creates a new entity.</p>
	 * 
	 * @return The newly created entity.
	 */
	public Entity createEntity() {
		Entity entity = new Entity();
		this.entities.add(entity);
		
		// Mark all queries as dirty by clearing the cache:
		for (UUID queryId : this.queries.keySet()) {
			this.cachedQueryResults.remove(queryId);
		}
		
		return entity;
	}

	/**
	 * <p>Check to see if this manager contains a particular entity.</p>
	 *
	 * @param entity The entity to check for.
	 *
	 * @return True if the specified entity is managed by this manager, false
	 * otherwise.
	 */
	public boolean hasEntity(Entity entity) {
		return this.entities.contains(entity);
	}
	
	/**
	 * <p>Destroys an existing entity.</p>
	 * 
	 * @param entity The entity to destroy.
	 */
	public void destroyEntity(Entity entity) {

		// Mark queries which should return the specified entity as dirty:
		if (this.entities.remove(entity)) {

			// Compare the contents of every query against the entity:
			for (UUID queryId : this.queries.keySet()) {
				if (entity.hasComponents(this.queries.get(queryId))) {
					this.cachedQueryResults.remove(queryId);
				}
			}
		}
	}
	
	/**
	 * <p>Gets the entity which has the specified <code>id</code>.</p>
	 * 
	 * @param id The identifier of the identity to get.
	 * @return An entity with the specified <code>id</code>, or <code>null</code>
	 * if no entity with that identifier exists.
	 */
	public Entity getEntity(UUID id) {
		for (Entity entity : this.entities) {
			if (entity.getId() == id) {
				return entity;
			}
		}

		return null;
	}

	//// Queries ////

	/*
	 * Since most queries for entities will be repeated quite often,
	 * the query system allows for common queries to be cached. This
	 * will provide a great speed in accessing the desired entities.
	 */
	
	private Map<UUID, Class<?>[]> queries;
	
	/**
	 * <p>Creates a query into the entity system which, when executed, will
	 * return all entities which hold all components with the specified
	 * <code>signatures</code>. To use generated query, call the executeQuery
	 * method of this manager.</p>
	 * 
	 * @param signatures When the query is executed, all included entities will
	 * all components specified here.
	 * 
	 * @return An identifier with which the query can be executed against this
	 * manager.
	 */
	public UUID createQuery(Class<?>... signatures) {
		UUID queryId = UUID.randomUUID();
				
		this.queries.put(queryId, signatures);
		
		return queryId;
	}
	
	/**
	 * <p>Updates a query by replacing the original list of components is uses
	 * to query the entities with the list of <code>signatures</code>.</p>
	 * 
	 * @param queryId The id of the query to update.
	 * @param signatures The new components to query the entity list for.
	 */
	public void updateQuery(UUID queryId, Class<?>... signatures) {
		this.queries.put(queryId, signatures);
		this.cachedQueryResults.remove(queryId);
		
	}
	
	private Map<UUID, List<Entity>> cachedQueryResults;
	
	/**
	 * <p>Executes the query with <code>queryId</code> generated from this
	 * manager using the createQuery method to generate a collection of
	 * entities with components specified at the time the query was created.</p>
	 * 
	 * @param queryId The identifier of the query to execute.
	 * 
	 * @return A list of all entities which meets the specifications of the
	 * query provided when it was created.
	 */
	public List<Entity> executeQuery(UUID queryId) {
		// Check to see if a query has been made:
		boolean isQueryCached = this.cachedQueryResults.containsKey(queryId);
		
		if (!isQueryCached) {
			this.cachedQueryResults.put(queryId, this.findQueryMatches(queryId));
		}
		
		return this.cachedQueryResults.get(queryId);
	}

	// Build a list of all entities matching the query:
	private List<Entity> findQueryMatches(UUID queryId) {
		List<Entity> matchingEntities = new ArrayList<Entity>();
		Class<?>[] signatures = this.queries.get(queryId);
 		
		for (Entity entity : this.entities) {
			if (entity.hasComponents(signatures)) {
				matchingEntities.add(entity);
			}
		}
		
		return matchingEntities;
	}
}
