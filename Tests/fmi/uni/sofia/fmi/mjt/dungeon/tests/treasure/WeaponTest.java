package fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class WeaponTest {
	private static final int CONST_20 = 20;
	private static final int CONST_150 = 150;
	private static final int CONST_90 = 90;
	private static final int CONST_0 = 0;
	private static final int HERO_MANA = 700;
	private static final int HERO_HEALTH = 400;
	private Weapon dagger;
	private Hero hanzo;

	@Before
	public void initializerWeaponAndHero() {
		dagger = new Weapon("Dagger", CONST_90);
		hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodCollectShouldThrowExceptionWhenGivenNullHero() {
		dagger.collect(null);
	}

	@Test
	public void methodCollectShouldAddWeaponToHeroIfHeDoesNotPossessOne() {
		assertEquals("collect method does not add weapon to hero if the does not posses one!",
				"Weapon found! Damage points: 90", dagger.collect(hanzo));
		assertEquals(dagger, hanzo.getWeapon());
	}

	@Test
	public void methodCollectShouldAddWeaponToHeroIfCurrentHeroWeaponDoesLessDamage() {
		dagger.collect(hanzo);
		Weapon lightsaber = new Weapon("Lightsaber", CONST_150);
		assertEquals("collect method does not add weapon to hero if he possesses one doing less damage!",
				"Weapon found! Damage points: 150", lightsaber.collect(hanzo));
		assertEquals(lightsaber, hanzo.getWeapon());
	}

	@Test
	public void methodCollectShouldDoNothingIfCurrentHeroWeaponDoesGreaterDamage() {
		dagger.collect(hanzo);
		Weapon knife = new Weapon("Knife", CONST_20);
		assertEquals("collect method adds new weapon to hero, but shouln't!", "Weapon found! Damage points: 20",
				knife.collect(hanzo));
		assertEquals(dagger, hanzo.getWeapon());
	}

}
