package bg.uni.sofia.fmi.mjt.dungeon.tests.actor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class HeroTest {

	private static final int CONST_25 = 25;
	private static final int CONST_15 = 15;
	private static final int CONST_500 = 500;
	private static final int SPELL_MANA_COST = 40;
	private static final int SPELL_DAMAGE = 90;
	private static final int CONST_0 = 0;
	private static final int HERO_MANA = 700;
	private static final int HERO_HEALTH = 400;
	private static final int CONST_30 = 30;
	private static final int CONST_20 = 20;
	private static final int CONST_90 = 90;
	private static final int CONST_310 = 310;
	private static final int MINUS_20 = -20;
	private Hero hanzo;

	@Before
	public void initializeHero() {
		hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));
	}

	@Test
	public void methodTakeDamageShouldReturnAccurateResultWhenTheTakenDamageIsLessThanHerosHealth() {
		hanzo.takeDamage(CONST_90);
		assertEquals("takeDamage method for hero returns wrong result!", CONST_310, hanzo.getHealth());
	}

	@Test
	public void methodTakeDamegeShouldReturnAccurateResultWhenTheTakenDamageIsGreaterThanHerosHealth() {
		hanzo.takeDamage(CONST_500);
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodTakeHealingShouldThrowExceptionWhenTryingToAddNegativeHealingPoints() {
		hanzo.takeHealing(MINUS_20);
	}

	@Test
	public void methodTakeHealingShouldReturnAccurateResultWhenHealthIsMaxedOut() {
		hanzo.takeHealing(CONST_20);
		assertEquals("takeHealing method for hero returns wrong result when the hero has maximum health!", 400,
				hanzo.getHealth());
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodTakeDamageShouldThrowExceptionWhenGivenNegativeDamage() {
		hanzo.takeDamage(MINUS_20);
	}

	@Test
	public void methodTakeHealingShouldReturnAccurateResultWhenHealthIsNotMaxedOut() {
		hanzo.takeDamage(CONST_90);
		hanzo.takeHealing(CONST_20);
		assertEquals("takeHealing method for hero returns wrong result when the hero does not have maximum health", 330,
				hanzo.getHealth());
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodTakeManaShouldThrowExceptionWhenTryingToAddNegativeManaPoints() {
		hanzo.takeMana(MINUS_20);
	}

	@Test
	public void methodTakeManaShouldReturnAccurateResultWhenManaIsMaxedOut() {
		hanzo.takeMana(CONST_30);
		assertEquals("takeMana method for hero returns wrong result when the hero has maxed out mana!", 700,
				hanzo.getMana());
	}

	@Test
	public void methodTakeManaShouldReturnAccurateResultWhenManaIsNotMaxedOut() {
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		hanzo.attack();
		hanzo.takeMana(30);
		assertEquals("takeMana method for hero returns wrong result when the hero does not have maximum mana!", 690,
				hanzo.getMana());
	}

	@Test
	public void methodLearnSpellShouldAddTheSpellAsHerosAttributeWhenHeDoesNotHavaOtherSpell() {
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		assertEquals("learnSpell method for hero returns wrong result when the hero does not have other spell!", rage,
				hanzo.getSpell());
	}

	@Test
	public void methodLearnSpellShouldAddNewSpellToHeroOverTheOldOneIfTheNewOneHasGreatherDamage() {
		Spell speed = new Spell("Speed", CONST_15, CONST_20);
		hanzo.learnSpell(speed);
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		assertEquals(
				"learnSpell method for hero returns wrong result when the hero has another spell with less damage than the new one!",
				rage, hanzo.getSpell());
	}

	@Test
	public void methodLeardSpellShouldNotAddNewSpellToHeroWhenTheNewSpellHasLessDamageThanTheOldOne() {
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		Spell speed = new Spell("Speed", CONST_15, CONST_20);
		hanzo.learnSpell(speed);
		assertEquals(
				"learnSpell method for hero returns wrong result when the hero has another spell with greater damage than the new one!",
				rage, hanzo.getSpell());
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodEquipWeaponShouldThrowExceptionWhenGivenNullWeapon() {
		hanzo.equipWeapon(null);
	}

	@Test
	public void methodEquipWeaponShouldAddNewWeaponToHeroIfHeDoesNotHaveOne() {
		Weapon dagger = new Weapon("Dagger", CONST_90);
		hanzo.equipWeapon(dagger);
		assertEquals("equipWeapon method for hero returns wrong result when the hero does not have weapon!", dagger,
				hanzo.getWeapon());
	}

	@Test
	public void methodEquipWeaponShouldAddNewWeaponOverOldOneToHeroIfTheNewOneDoesGreaterDamage() {
		Weapon knife = new Weapon("Knife", CONST_25);
		hanzo.equipWeapon(knife);
		Weapon dagger = new Weapon("Dagger", CONST_90);
		hanzo.equipWeapon(dagger);
		assertEquals(
				"equipWeapon method for hero returns wrong result when the hero has another weapon with less damage than the new one!",
				dagger, hanzo.getWeapon());
	}

	@Test
	public void methodEquipWeaponShouldNotAddNewWeaponToHeroWhenHeroHasWeaponThatDoesGreaterDamageThanTheNewOne() {
		Weapon dagger = new Weapon("Dagger", CONST_90);
		hanzo.equipWeapon(dagger);
		Weapon knife = new Weapon("Knife", CONST_25);
		hanzo.equipWeapon(knife);
		assertEquals(
				"equipWeapon method for hero returns wrong result when the hero has another weapon with greater damage than the new one!",
				dagger, hanzo.getWeapon());
	}

	@Test
	public void methodAttackShouldReturnZeroWhenHeroDoesNotHaveNeitherWeaponNorSpell() {
		assertEquals("attack method should return zero when the hero does not possess neither weapon, nor spell!",
				CONST_0, hanzo.attack());
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodLearnSpellShouldThrowExceptionWhenGivenNullSpell() {
		hanzo.learnSpell(null);
	}

	@Test
	public void methodAttackShouldReturnZeroWhenHeroHasSpellButDoesNotHaveEnoughMana() {
		hanzo.setMana(CONST_30);
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		assertEquals("attack method should return zero when the hero possesses a spell, but does not have enough mana!",
				CONST_0, hanzo.attack());
	}

	@Test
	public void methodAttackShouldReturnWeaponDamageWhenHeroOnlyHasWeapon() {
		Weapon dagger = new Weapon("Dagger", CONST_90);
		hanzo.equipWeapon(dagger);
		assertEquals("attack method should return weapon damage for hero when he posseses only weapon!", CONST_90,
				hanzo.attack());
	}

	@Test
	public void methodAttackShouldReturnWeaponDamageWhenTheWeaponDamageIsGreaterThanTheSpellDamage() {
		Weapon dagger = new Weapon("Dagger", CONST_90);
		hanzo.equipWeapon(dagger);
		Spell speed = new Spell("Speed", CONST_15, CONST_20);
		hanzo.learnSpell(speed);
		assertEquals(
				"attack method should return weapon damage when the weapon damage is greater than the spell damage!",
				CONST_90, hanzo.attack());
	}

	@Test
	public void methodAttackShouldReturnSpellDamageWhenSpellDamageIsGreaterThanWeaponDamageAndHeroHasEnoughMana() {
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		Weapon knife = new Weapon("Knife", CONST_25);
		hanzo.equipWeapon(knife);
		assertEquals(
				"attack method should return spell damage when spell damage is greater than weapon damage and hero has enough mana!",
				SPELL_DAMAGE, hanzo.attack());
	}

	@Test
	public void methodAttackShouldReturnWeaponDamageWhenSpellDamageIsGreaterThanWeaponDamageButHeroDoesNotHaveEnoughMana() {
		hanzo.setMana(CONST_20);
		Spell rage = new Spell("Rage", SPELL_DAMAGE, SPELL_MANA_COST);
		hanzo.learnSpell(rage);
		Weapon knife = new Weapon("Knife", CONST_25);
		hanzo.equipWeapon(knife);
		assertEquals(
				"attack method should return weapon damage when spell damage is greater than weapon damage but hero does not have enough mana!",
				CONST_25, hanzo.attack());
	}

}
