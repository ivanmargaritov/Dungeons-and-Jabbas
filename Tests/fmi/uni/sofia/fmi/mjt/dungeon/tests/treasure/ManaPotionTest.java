package fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.ManaPotion;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;

public class ManaPotionTest {
	private static final int CONST_20 = 20;
	private static final int CONST_30 = 30;
	private static final int HERO_MANA = 700;
	private static final int HERO_HEALTH = 400;
	private static final int CONST_0 = 0;
	private static final int SPELL_MANA_COST = 40;
	private static final int SPELL_DAMAGE = 90;
	private ManaPotion manaPotion;

	@Before
	public void initializeManaPotion() {
		manaPotion = new ManaPotion(CONST_30);
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodCollectShouldThrowExceptionWhenGivenNullHero() {
		manaPotion.collect(null);
	}

	@Test
	public void methodCollectShouldAddManaPointsToHeroIfManaPointPlusCurrentManaPointAreBelowMaxManaPoints() {
		Hero hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		hanzo.attack();
		assertEquals(
				"collect method does not add mana points accurately when mana points plus current mana points are below max mana points",
				"Mana potion found! 30 mana points added to your hero!", manaPotion.collect(hanzo));
	}

	@Test
	public void methodCollectShouldAddDifferenceBetweenMaxManaPointAndCurrentManaPointsIfManaPotionPointsPlusCurrentManaPointsOverpassMaxManaPoints() {
		Hero hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
		Spell rage = new Spell("Rage", SPELL_DAMAGE, CONST_20);
		hanzo.learnSpell(rage);
		hanzo.attack();
		assertEquals("collect method does not add add difference between max mana points and "
				+ "current mana points if potion mana points plus current mana points overpass max mana points!",
				"Mana potion found! 20 mana points added to your hero!", manaPotion.collect(hanzo));
	}

	@Test
	public void methodGetManaPointsShouldReturnManaPoints() {
		assertEquals("getManaPoints method does not return accurate mana point!", CONST_30, manaPotion.getManaPoints());
	}

}
