package com.elsewhere_games.lib.entity.system;

import com.elsewhere_games.lib.entity.AbstractSystem;
import com.elsewhere_games.lib.entity.EntityManager;

public abstract class AbstractCyclicalSystem extends AbstractSystem {

	/*
	 * Constructors
	 */
	
	/**
	 * <p>Class constructor that allows the <code>entities</code> controlled by
	 * this system to be specified, as well as the delay between each cycle.</p>
	 * 
	 * @param entities The entities controlled by this system.
	 * @param cycleDuration How long between each cycle, in milliseconds.
	 */
	public AbstractCyclicalSystem(final EntityManager entities, final long cycleDuration) {
		super(entities);
		
		this.cyclingThread = new Cycler(cycleDuration);
	}
	
	/*
	 * Cycles
	 */
	
	private Cycler cyclingThread;
	
	/**
	 * <p>Starts cycling this system. Each cyclical system can only be started
	 * once.</p>
	 * 
	 * @throws IllegalStateException When this method is called after the system
	 * has already been started.
	 */
	public void start() {
		// Thread was already started:
		if (this.cyclingThread.isAlive()) {
			throw new IllegalStateException("Cyclical system was already started.");
		}
		
		this.cyclingThread.start();
	}
	
	/**
	 * <p>Pauses the cycling of this system. If a cycle is already in progress,
	 * it will be completed first.</p>
	 * 
	 * @throws IllegalStateException When this method is called when this system
	 * is not running.
	 */
	public void pause() {
		// Thread is not running:
		if (!this.cyclingThread.isAlive()) {
			throw new IllegalStateException("Cyclical system is not running.");
		}
		
		this.cyclingThread.pause();
	}
	
	/**
	 * <p>Stops the cycling of this system. A cyclical system cannot be restarted
	 * once stopped.</p>
	 * 
	 * @throws IllegalStateException When this method is called when this system
	 * is not running.
	 */
	public void stop() {
		// Thread is not running:
		if (!this.cyclingThread.isAlive()) {
			throw new IllegalStateException("Cyclical system is not running.");
		}
		
		this.cyclingThread.interrupt();
	}
	
	/**
	 * <p>Called each cycle.</p>
	 */
	protected abstract void update(long delaySinceLastUpdate);
	
	private class Cycler extends Thread {
		
		/*
		 * Constructors
		 */
		
		/**
		 * <p>Class constructor which allows the duration of each cycle to be 
		 * specified in milliseconds.</p>
		 * 
		 * @param cycleDuration The duration of each cycle, in milliseconds.
		 */
		public Cycler(final long cycleDuration) {
			this.cycleDuration = cycleDuration;
		}
		
		/*
		 * Thread Life Cycle
		 */
		
		private long cycleDuration;
		
		@Override
		public void run() {
			while (!this.isInterrupted()) {
				// Update the parent class:
				update(this.cycleDuration);
				
				// Sleep for the specified amount of time:
				// TODO Calculate sleep time based on how long the update takes
				try {
					Thread.sleep(this.cycleDuration);
				}
				
				// If the sleep is interrupted by someone interrupting this thread:
				catch (InterruptedException interruptedException) {
					// Do nothing, thread will stop running.
				}
				
			}
		}
		
		public void pause() {
			
		}
		
	}
	
}
