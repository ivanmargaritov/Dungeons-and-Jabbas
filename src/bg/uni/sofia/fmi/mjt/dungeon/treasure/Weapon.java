package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class Weapon extends Attribute {

	public Weapon(String name, int damage) {
		super(name, damage);
	}

	public String collect(Hero hero) {
		if (hero == null) {
			throw new IllegalArgumentException();
		}
		if (hero.getWeapon() == null) {
			hero.equipWeapon(this);
		}

		if (this.getDamage() > hero.getWeapon().getDamage()) {
			hero.equipWeapon(this);
		}

		return "Weapon found! Damage points: " + this.getDamage();
	}
}
