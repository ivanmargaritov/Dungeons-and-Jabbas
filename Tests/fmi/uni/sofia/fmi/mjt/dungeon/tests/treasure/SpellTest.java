package fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;

public class SpellTest {

	private static final int CONST_10 = 10;
	private static final int CONST_20 = 20;
	private static final int CONST_15 = 15;
	private static final int CONST_0 = 0;
	private static final int HERO_MANA = 700;
	private static final int HERO_HEALTH = 400;
	private static final int SPELL_MANA_COST = 40;
	private static final int SPELL_DAMAGE = 90;
	private Spell speed;
	private Hero hanzo;

	@Before
	public void initializeSpellAndHero() {
		speed = new Spell("Speed", CONST_15, CONST_20);
		hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodCollectShouldThrowExceptionWhenGivenNullHero() {
		speed.collect(null);
	}

	@Test
	public void methodCollectShouldAddSpellToHeroIfHeDoesNotPossessOne() {
		assertEquals("collect method does not add spell to hero if he does not have one!",
				"Spell found! Damage points: 15, Mana cost: 20", speed.collect(hanzo));
		assertEquals(speed, hanzo.getSpell());
	}

	@Test
	public void methodCollectShouldAddSpellToHeroIfCurrentHeroSpellDoesLessDamage() {
		speed.collect(hanzo);
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		assertEquals(
				"collect method does not add new spell to hero if it does more damage than the one the hero possesses!",
				"Spell found! Damage points: 90, Mana cost: 40", rage.collect(hanzo));
		assertEquals(rage, hanzo.getSpell());
	}

	@Test
	public void methodCollectShouldNotAddNewSpellToHeroIfCurrentHeroSpellDoesMoreDamage() {
		speed.collect(hanzo);
		Spell earthquake = new Spell("Earthquake", CONST_10, CONST_10);
		assertEquals("collect method changes hero spell when one doing less damage is applied!",
				"Spell found! Damage points: 10, Mana cost: 10", earthquake.collect(hanzo));
		assertEquals(speed, hanzo.getSpell());
	}

}
