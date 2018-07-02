package bg.uni.sofia.fmi.mjt.dungeon;

import java.util.List;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Enemy;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Treasure;

public class GameEngine {
	private char[][] map;
	private Hero hero;
	private List<Enemy> enemies;
	private int enemyToFight;
	private List<Treasure> treasures;
	private int treasuresTaken;

	public GameEngine(char[][] map, Hero hero, List<Enemy> enemies, List<Treasure> treasures) {
		if (map == null || hero == null || enemies == null || treasures == null) {
			throw new IllegalArgumentException();
		}
		this.map = map;
		this.hero = hero;
		this.enemies = enemies;
		this.enemyToFight = 0;
		this.treasures = treasures;
		this.treasuresTaken = 0;
	}

	public char[][] getMap() {
		return map;
	}

	public String makeMove(Direction command) {
		if (command == null) {
			throw new IllegalArgumentException();
		}
		if (command.equals(Direction.UP) || command.equals(Direction.DOWN) || command.equals(Direction.LEFT)
				|| command.equals(Direction.RIGHT)) {
			int futurePositionX = findPositionX(command);
			int futurePositionY = findPositionY(command);

			return moveThroughMap(futurePositionX, futurePositionY);
		} else {
			return "Unknown command!";
		}
	}

	private int findPositionX(Direction command) {
		int futurePositionX = hero.getPosition().getX();
		if (command.equals(Direction.UP)) {
			futurePositionX -= 1;
		}
		if (command.equals(Direction.DOWN)) {
			futurePositionX += 1;
		}
		return futurePositionX;
	}

	private int findPositionY(Direction command) {
		int futurePositionY = hero.getPosition().getY();
		if (command.equals(Direction.LEFT)) {
			futurePositionY -= 1;
		}
		if (command.equals(Direction.RIGHT)) {
			futurePositionY += 1;
		}
		return futurePositionY;
	}

	private String moveThroughMap(int futurePositionX, int futurePositionY) {
		switch (this.map[futurePositionX][futurePositionY]) {
		case '.':
			return passThroughEmptySpot(futurePositionX, futurePositionY);
		case '#':
			return passThroughObstacle();
		case 'T':
			return findTreasure(futurePositionX, futurePositionY);
		case 'E':
			return fightEnemy(futurePositionX, futurePositionY);
		case 'G':
			return finishSuccessfully();
		default:
			return "Unknown symbol on map!";
		}
	}

	private String passThroughEmptySpot(int futurePositionX, int futurePositionY) {
		map[hero.getPosition().getX()][hero.getPosition().getY()] = '.';
		hero.setPosition(futurePositionX, futurePositionY);
		map[futurePositionX][futurePositionY] = 'H';
		return "You moved successfully to the next position.";
	}

	private String passThroughObstacle() {
		return "Wrong move. There is an obstacle and you cannot bypass it.";
	}

	private String findTreasure(int futurePositionX, int futurePositionY) {
		map[hero.getPosition().getX()][hero.getPosition().getY()] = '.';
		hero.setPosition(futurePositionX, futurePositionY);
		map[futurePositionX][futurePositionY] = 'H';
		treasuresTaken++;
		return this.treasures.get(treasuresTaken - 1).collect(hero);
	}

	private String fightEnemy(int futurePositionX, int futurePositionY) {
		while (enemies.get(enemyToFight).isAlive() && hero.isAlive()) {
			enemies.get(enemyToFight).takeDamage(hero.attack());
			hero.takeDamage(enemies.get(enemyToFight).attack());
		}
		if (!enemies.get(enemyToFight).isAlive()) {
			map[hero.getPosition().getX()][hero.getPosition().getY()] = '.';
			hero.setPosition(futurePositionX, futurePositionY);
			map[futurePositionX][futurePositionY] = 'H';
			enemyToFight++;
			return "Enemy died.";
		} else {
			enemyToFight++;
			return "Hero is dead! Game over!";
		}
	}

	private String finishSuccessfully() {
		return "You have successfully passed through the dungeon. Congrats!";
	}
}
