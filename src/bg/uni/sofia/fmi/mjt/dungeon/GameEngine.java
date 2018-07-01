package bg.uni.sofia.fmi.mjt.dungeon;

import bg.uni.sofia.fmi.mjt.dungeon.actor.Enemy;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Treasure;

public class GameEngine {
	private char[][] map;
	private Hero hero;
	private Enemy[] enemies;
	private int enemyToFight;
	private Treasure[] treasures;
	private int treasuresTaken;

	public GameEngine(char[][] map, Hero hero, Enemy[] enemies, Treasure[] treasures) {
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

	public String makeMove(int command) {
		if (command == 0 || command == 1 || command == 2 || command == 3) {
			int futurePositionX = hero.getPosition().getX();
			int futurePositionY = hero.getPosition().getY();

			switch (command) {
			case 0:
				futurePositionY -= 1;
				break;
			case 1:
				futurePositionX -= 1;
				break;
			case 2:
				futurePositionY += 1;
				break;
			case 3:
				futurePositionX += 1;
				break;
			}

			if (this.map[futurePositionX][futurePositionY] == '.') {

				map[hero.getPosition().getX()][hero.getPosition().getY()] = '.';
				hero.setPosition(futurePositionX, futurePositionY);
				map[futurePositionX][futurePositionY] = 'H';

				return "You moved successfully to the next position.";
			}

			else if (this.map[futurePositionX][futurePositionY] == '#') {
				return "Wrong move. There is an obstacle and you cannot bypass it.";
			}

			else if (this.map[futurePositionX][futurePositionY] == 'T') {
				map[hero.getPosition().getX()][hero.getPosition().getY()] = '.';
				hero.setPosition(futurePositionX, futurePositionY);
				map[futurePositionX][futurePositionY] = 'H';
				treasuresTaken++;
				return this.treasures[treasuresTaken - 1].collect(hero);
			}

			else if (this.map[futurePositionX][futurePositionY] == 'E') {

				while (enemies[enemyToFight].isAlive() && hero.isAlive()) {
					enemies[enemyToFight].takeDamage(hero.attack());
					hero.takeDamage(enemies[enemyToFight].attack());
				}
				if (!enemies[enemyToFight].isAlive()) {
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

			else if (this.map[futurePositionX][futurePositionY] == 'G') {
				return "You have successfully passed through the dungeon. Congrats!";
			} else {
				return "";
			}
		}

		else {
			return "Unknown command entered.";
		}
	}
}
