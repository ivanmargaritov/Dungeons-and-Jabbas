package bg.uni.sofia.fmi.mjt.dungeon.tests.actor;

import static org.junit.Assert.*;

import org.junit.Test;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Enemy;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class EnemyTest {

	private static final int CONST_90 = 90;
	private static final int CONST_95 = 95;
	private static final int CONST_30 = 30;
	private static final int CONST_35 = 35;
	private static final int CONST_40 = 40;

	@Test
	public void initializeEnemy() {
		Weapon lightsaber = new Weapon("Light saber", CONST_40);
		Spell darkForce = new Spell("Dark force", CONST_35, CONST_30);
		Enemy vader = new Enemy("Vader", CONST_95, CONST_90, lightsaber, darkForce);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionWhenGivenNullWeapon() {
		Spell darkForce = new Spell("Dark force", CONST_35, CONST_30);
		Enemy vader = new Enemy("Vader", CONST_95, CONST_90, null, darkForce);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionWhenGivenNullSpell() {
		Weapon lightsaber = new Weapon("Light saber", CONST_40);
		Enemy vader = new Enemy("Vader", CONST_95, CONST_90, lightsaber, null);
	}
}