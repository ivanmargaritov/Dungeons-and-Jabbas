package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class HealthPotion extends Potion {

	public HealthPotion(int healingPoints) {
		super(healingPoints);
	}

	public int heal() {
		return super.getPotionPoints();
	}

	public String collect(Hero hero) {

		if (hero.getHealth() == 0) {
			this.setPotionPoints(0);
			return "Health potion found! " + this.getPotionPoints() + " health points added to your hero!";
		} else if (this.getPotionPoints() + hero.getHealth() <= hero.getMaxHealth()) {
			hero.takeHealing(this.getPotionPoints());
			return "Health potion found! " + this.getPotionPoints() + " health points added to your hero!";
		} else {
			int healthAdded = hero.getMaxHealth() - hero.getHealth();
			hero.takeHealing(healthAdded);
			return "Health potion found! " + healthAdded + " health points added to your hero!";
		}

	}
}
