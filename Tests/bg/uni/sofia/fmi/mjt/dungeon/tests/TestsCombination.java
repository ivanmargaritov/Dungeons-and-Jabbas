package bg.uni.sofia.fmi.mjt.dungeon.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import bg.uni.sofia.fmi.mjt.dungeon.tests.actor.EnemyTest;
import bg.uni.sofia.fmi.mjt.dungeon.tests.actor.HeroTest;
import fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure.HealthPotionTest;
import fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure.ManaPotionTest;
import fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure.SpellTest;
import fmi.uni.sofia.fmi.mjt.dungeon.tests.treasure.WeaponTest;

@RunWith(Suite.class)
@SuiteClasses({HeroTest.class,
	EnemyTest.class,
	HealthPotionTest.class,
	ManaPotionTest.class,
	WeaponTest.class,
	SpellTest.class,
	GameEngineTest.class})
public class TestsCombination {
}
