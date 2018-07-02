package fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.HealthPotion;

public class HealthPotionTest {

	private static final int CONST_240 = 240;
	private static final int CONST_200 = 200;
	private static final int CONST_30 = 30;
	private static final int CONST_40 = 40;
	private static final int HERO_MANA = 700;
	private static final int HERO_HEALTH = 400;
	private static final int CONST_0 = 0;
	private HealthPotion healthPotion;

	@Before
	public void initializeHealthPotion() {
		healthPotion = new HealthPotion(CONST_40);
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodCollectShouldThrowExceptionIfGivenNullHero() {
		healthPotion.collect(null);
	}

	@Test
	public void methodCollectShouldAddZeroHealthToHeroIfHeIsNotAlive() {
		Hero hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
		hanzo.takeDamage(HERO_HEALTH);
		assertEquals("collect method should not add health to hero if he is not alive!",
				"Health potion found! 0 health points added to your hero!", healthPotion.collect(hanzo));
		assertEquals(CONST_0, hanzo.getHealth());
	}

	@Test
	public void methodCollectShouldAddHealthIfCurrentHealthPlusPotionHealthIsBelowMaxHelathForHero() {
		Hero hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
		hanzo.takeDamage(CONST_200);
		assertEquals(
				"collect method should add health potion healing points to hero if current health plus healing points does not overpass maximum health!",
				"Health potion found! 40 health points added to your hero!", healthPotion.collect(hanzo));
		assertEquals(CONST_240, hanzo.getHealth());
	}

	@Test
	public void methodCollectShouldAddDifferenceBetweenMaxHealthAndCurrentHealthIfHealingPointsPlusCurrentHealthOverPassMaxHealth() {
		Hero hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
		hanzo.takeDamage(CONST_30);
		assertEquals(
				"collect method should add difference between max health and current health if healing point plus current health overpass maximum health!",
				"Health potion found! 30 health points added to your hero!", healthPotion.collect(hanzo));
		assertEquals(HERO_HEALTH, hanzo.getHealth());
	}

	@Test
	public void methodHealShouldReturnHealingPoints() {
		assertEquals("heal method does not return accurate healing points!", CONST_40, healthPotion.getHealingPoints());
	}
}
