package bg.uni.sofia.fmi.mjt.dungeon.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bg.uni.sofia.fmi.mjt.dungeon.Direction;
import bg.uni.sofia.fmi.mjt.dungeon.GameEngine;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Enemy;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.HealthPotion;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Treasure;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class GameEngineTest {

	private static final int CONST_25 = 25;
	private static final int CONST_55 = 55;
	private static final int CONST_70 = 70;
	private static final int CONST_15 = 15;
	private static final int CONST_20 = 20;
	private static final int CONST_90 = 90;
	private static final int CONST_95 = 95;
	private static final int CONST_30 = 30;
	private static final int CONST_35 = 35;
	private static final int CONST_40 = 40;
	private static final int CONST_0 = 0;
	private static final int CONST_1 = 1;
	private static final int CONST_2 = 2;
	private static final int HERO_MANA = 700;
	private static final int HERO_HEALTH = 400;
	private Hero hanzo;
	private GameEngine game;
	private List<Enemy> enemies;
	private List<Treasure> treasures;

	@Before
	public void initializeEnemiesAndTreasures() {
		hanzo = new Hero("Hanzo", HERO_HEALTH, HERO_MANA, new Position(CONST_0, CONST_0));

		Weapon lightsaber = new Weapon("Light saber", CONST_40);
		Spell darkForce = new Spell("Dark force", CONST_35, CONST_30);
		Enemy vader = new Enemy("Vader", CONST_95, CONST_90, lightsaber, darkForce);
		enemies = new ArrayList<>();
		enemies.add(vader);

		Weapon sword = new Weapon("Sword", CONST_20);
		Spell speed = new Spell("Speed", CONST_15, CONST_20);
		Enemy knight = new Enemy("Knight", CONST_70, CONST_55, sword, speed);
		enemies.add(knight);

		Spell rage = new Spell("Rage", CONST_90, CONST_40);
		treasures = new ArrayList<>();
		treasures.add(rage);
		Weapon knife = new Weapon("Knife", CONST_25);
		treasures.add(knife);
		HealthPotion healthPotion = new HealthPotion(CONST_25);
		treasures.add(healthPotion);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorShouldThrowExceptionWhenGivenNullMap() {
		game = new GameEngine(null, hanzo, enemies, treasures);
	}

	@Test(expected = IllegalArgumentException.class)
	public void methodMakeMoveShouldThrowExceptionWhenGivenNullCommand() {
		char[][] map = { { 'S', '.' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		game.makeMove(null);
	}

	@Test
	public void methodMakeMoveShouldMoveToTheRightSuccessfully() {
		char[][] map = { { 'S', '.' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		assertEquals("makeMove method does not move to the left accurately!",
				"You moved successfully to the next position.", game.makeMove(Direction.RIGHT));
		Direction.values();
		assertEquals("makeMove does not put '.' to hero's old position when he has moved to another!", '.',
				map[CONST_0][CONST_0]);
		assertEquals("makeMove does not change hero's position accurately!", 'H', map[CONST_0][CONST_1]);
	}

	@Test
	public void methodMakeMoveShouldNotMoveIfThereIsAnObstacle() {
		char[][] map = { { 'S', '#' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		assertEquals("makeMove method does not work accurately when moving to a place with an obstacle!",
				"Wrong move. There is an obstacle and you cannot bypass it.", game.makeMove(Direction.RIGHT));
		assertEquals("makeMove method changes hero's position when trying to move through an obstacle!", 'S',
				map[CONST_0][CONST_0]);
		assertEquals("makeMove method removes an obstacle from the map when trying to move through one!", '#',
				map[CONST_0][CONST_1]);
	}

	@Test
	public void methodMakeMoveShouldAddTreasureToHero() {
		char[][] map = { { 'S', '#' }, { 'T', '.' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		assertEquals("makeMove method does not work accurately when finding treasure!",
				"Spell found! Damage points: 90, Mana cost: 40", game.makeMove(Direction.DOWN));
		assertEquals('.', map[CONST_0][CONST_0]);
		assertEquals('H', map[CONST_1][CONST_0]);
	}

	@Test
	public void methodMakeMoveShouldDoNothingIfUnknownPositionOccurredOnMap() {
		char[][] map = { { 'S', 'Q' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		assertEquals("makeMove method does not work accurately when unknown symbol found on map!",
				"Unknown symbol on map!", game.makeMove(Direction.RIGHT));
	}

	@Test
	public void methodMakeMoveShouldMoveHeroUpSuccessfully() {
		char[][] map = { { 'S', '#' }, { 'T', '.' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		game.makeMove(Direction.DOWN);
		assertEquals("makeMove method does not move hero up accurately!",
				"You moved successfully to the next position.", game.makeMove(Direction.UP));
		assertEquals("makeMove method does not change hero's position when moving up!", 'H', map[CONST_0][CONST_0]);
		assertEquals("makeMove method does not change treasure symbol on map to '.' when taken!", '.',
				map[CONST_1][CONST_0]);
	}

	@Test
	public void methodMakeMoveShouldMoveHeroToTheLeftSuccessfully() {
		char[][] map = { { 'S', '.' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		game.makeMove(Direction.RIGHT);
		assertEquals("makeMove method does not move hero to the left accurately!",
				"You moved successfully to the next position.", game.makeMove(Direction.LEFT));
		assertEquals("makeMove method does not change hero's position when moving to the left!", 'H',
				map[CONST_0][CONST_0]);
		assertEquals("makeMove method does not change hero's position when moving to the left!", '.',
				map[CONST_0][CONST_1]);
	}

	@Test
	public void methodMakeMoveShouldMoveHeroAfterFightingEnemy() {
		char[][] map = { { 'S', 'T', 'E' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		game.makeMove(Direction.RIGHT);
		assertEquals("makeMove method does not move hero accurately when he wins over enemy!", "Enemy died.",
				game.makeMove(Direction.RIGHT));
		assertEquals("makeMove method does not move hero accurately to the right!", '.', map[CONST_0][CONST_0]);
		assertEquals("makeMove method does not move hero accurately when taking treasury!", '.', map[CONST_0][CONST_1]);
		assertEquals("makeMove method does not move hero when defeating an enemy!", 'H', map[CONST_0][CONST_2]);
	}

	@Test
	public void methodMakeMoveShouldNotMoveHeroIfDefeatedByEnemy() {
		char[][] map = { { 'S', 'E' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		assertEquals("makeMove method does not work accurately when hero has been defeated by enemy!",
				"Hero is dead! Game over!", game.makeMove(Direction.RIGHT));
	}

	@Test
	public void methodMakeMoveShouldNotMoveHeroIfHeIsDefeatedAfterBattleWithAnEnemy() {
		char[][] map = { { 'S', 'G' } };
		game = new GameEngine(map, hanzo, enemies, treasures);
		assertEquals("makeMove method does not finish the game accurately!",
				"You have successfully passed through the dungeon. Congrats!", game.makeMove(Direction.RIGHT));
	}

}
