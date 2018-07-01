package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class Enemy extends Character {

	public Enemy(String name, int health, int mana, Weapon weapon, Spell spell) {
		super(name, health, mana);
		super.setWeapon(weapon);
		super.setSpell(spell);
	}

}