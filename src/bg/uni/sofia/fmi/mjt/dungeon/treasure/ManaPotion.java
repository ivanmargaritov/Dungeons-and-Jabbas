package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class ManaPotion extends Potion {

	public ManaPotion(int manaPoints) {
		super(manaPoints);
	}

	public int getManaPoints() {
		return super.getPotionPoints();
	}

	public String collect(Hero hero) {
		if (hero == null) {
			throw new IllegalArgumentException();
		}
		if (this.getPotionPoints() + hero.getMana() <= hero.getMaxMana()) {
			hero.takeMana(this.getPotionPoints());
			return "Mana potion found! " + this.getPotionPoints() + " mana points added to your hero!";
		} else {
			int manaAdded = hero.getMaxMana() - hero.getMana();
			hero.takeMana(manaAdded);
			return "Mana potion found! " + manaAdded + " mana points added to your hero!";
		}
	}
}
