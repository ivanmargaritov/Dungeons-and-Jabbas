package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class ManaPotion implements Treasure {
	private int manaPoints;

	public ManaPotion(int manaPoints) {
		this.manaPoints = manaPoints;
	}

	public int heal() {
		return this.manaPoints;
	}

	public String collect(Hero hero) {

		if (this.manaPoints + hero.getMana() <= hero.getMaxMana()) {
			hero.takeMana(this.manaPoints);
			return "Mana potion found! " + this.manaPoints + " mana points added to your hero!";
		} else {
			int manaAdded = hero.getMaxMana() - hero.getMana();
			hero.takeMana(manaAdded);
			return "Mana potion found! " + manaAdded + " mana points added to your hero!";
		}
	}
}
