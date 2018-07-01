package bg.uni.sofia.fmi.mjt.dungeon.treasure;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;

public class HealthPotion implements Treasure {
	private int healingPoints;

	public HealthPotion(int healingPoints) {
		this.healingPoints = healingPoints;
	}

	public int heal() {
		return this.healingPoints;
	}

	public String collect(Hero hero) {

		if (hero.getHealth() == 0) {
			this.healingPoints = 0;
			return "Health potion found! " + this.healingPoints + " health points added to your hero!";
		} else if (this.healingPoints + hero.getHealth() <= hero.getMaxHealth()) {
			hero.takeHealing(this.healingPoints);
			return "Health potion found! " + this.healingPoints + " health points added to your hero!";
		} else {
			int healthAdded = hero.getMaxHealth() - hero.getHealth();
			hero.takeHealing(healthAdded);
			return "Health potion found! " + healthAdded + " health points added to your hero!";
		}

	}
}
