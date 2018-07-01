package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class Spell implements Treasure {
	private String name;
	private int damage;
	private int manaCost;

	public Spell(String name, int damage, int manaCost) {
		this.name = name;
		this.damage = damage;
		this.manaCost = manaCost;
	}

	public String getName() {
		return this.name;
	}

	public int getDamage() {
		return this.damage;
	}

	public int getManaCost() {
		return this.manaCost;
	}

	public String collect(Hero hero) {
		if (hero.getSpell() == null) {
			hero.learn(this);
		}
		if (this.damage > hero.getSpell().damage) {
			hero.learn(this);
		}

		return "Spell found! Damage points: " + hero.getSpell().damage + ", Mana cost: " + hero.getSpell().manaCost;
	}
}
