package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public interface Actor {
	/**
	 * Gets actor's name
	 * 
	 * @return actor's name
	 */
	String getName();

	/**
	 * Gets actor's health
	 * 
	 * @return actor's health
	 */
	int getHealth();

	/**
	 * Gets actor's mana
	 * 
	 * @return actor's mana
	 */
	int getMana();

	/**
	 * Returns true if the actor has health over zero, false - otherwise
	 * 
	 * @return true if the actor has health over zero, false - otherwise
	 */
	boolean isAlive();

	/**
	 * Subtracts damage points from actor's health. An actor cannot have negative
	 * health.
	 * 
	 * @param damagePoints
	 *            The damage points we want to subtract from actor's health
	 */
	void takeDamage(int damagePoints);

	/**
	 * Gets actor's weapon. Throws exception if given negative number
	 * 
	 * @return actor's weapon
	 */
	Weapon getWeapon();

	/**
	 * Gets actor's spell
	 * 
	 * @return actor's spell
	 */
	Spell getSpell();

	/**
	 * Return actor's attacking points - the one between actor's weapon and spell
	 * that does more damage (spell can be used if actor has enough mana to use it.
	 * When using a spell, spell's mana cost is being subtracted from actor's mana).
	 * It is possible that an actor can have only weapon or spell or none of them.
	 * 
	 * @return actor's attacking poins
	 */
	int attack();
}
