package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class Spell extends Attribute {
	private int manaCost;

	public Spell(String name, int damage, int manaCost) {
		super(name, damage);
		this.manaCost = manaCost;
	}

	public int getManaCost() {
		return this.manaCost;
	}

	public String collect(Hero hero) {
		if (hero.getSpell() == null) {
			hero.learn(this);
		}
		if (this.getDamage() > hero.getSpell().getDamage()) {
			hero.learn(this);
		}

		return "Spell found! Damage points: " + hero.getSpell().getDamage() + ", Mana cost: "
				+ hero.getSpell().manaCost;
	}
}
