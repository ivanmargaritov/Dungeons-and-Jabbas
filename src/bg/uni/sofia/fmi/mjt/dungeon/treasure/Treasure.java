package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public interface Treasure {
	/**
	 * Adds the treasure to the hero. If hero is null, throws exception.
	 * If the treasure is a weapon, adds the weapon
	 * to the hero if it is more powerful than the one the hero possesses.  
	 * If the treasure is a spell, adds the spell
	 * to the hero if it is more powerful than the one the hero possesses.
	 * If the treasure is a health potion and the hero is alive, adds
	 * healing points to hero so that healing points plus current hero health
	 * does not overpass maximum hero health.
	 * If the treasure is a mana potion, adds mana points to hero so that
	 * mana points plus current hero mana points does not overpass
	 * maximum hero mana points.
	 * 
	 * @param hero
	 *            The hero we want to add specific treasure to.
	 * @return specified message over the success of collecting the treasure from
	 *         the hero
	 */
	String collect(Hero hero);
}
