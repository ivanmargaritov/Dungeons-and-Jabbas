/*
import java.util.ArrayList;
import java.util.List;

import bg.uni.sofia.fmi.mjt.dungeon.Direction;
import bg.uni.sofia.fmi.mjt.dungeon.GameEngine;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Enemy;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Hero;
import bg.uni.sofia.fmi.mjt.dungeon.actor.Position;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.HealthPotion;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.ManaPotion;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Treasure;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class Main {

	public static void print(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		Hero hanzo = new Hero("Hanzo", 400, 700, new Position(0, 0));
		System.out.println(hanzo.getName());
		System.out.println(hanzo.getHealth());
		System.out.println(hanzo.getMana());
		System.out.println(hanzo.getPosition().getX());
		System.out.println(hanzo.getPosition().getY());
		System.out.println(hanzo.isAlive());
		hanzo.takeDamage(90);
		System.out.println(hanzo.getHealth());
		hanzo.takeHealing(20);
		System.out.println(hanzo.getHealth());
		hanzo.takeMana(10);
		System.out.println(hanzo.getMana());

		Weapon dagger = new Weapon("Dagger", 70);
		hanzo.equipWeapon(dagger);

		System.out.println(hanzo.getWeapon().getName());

		Spell rage = new Spell("Rage", 90, 40);
		hanzo.learnSpell(rage);

		System.out.println(hanzo.getSpell().getName());
		System.out.println(hanzo.attack());
		System.out.println(hanzo.getMana());

		Weapon knife = new Weapon("Knife", 25);
		Spell flash = new Spell("Flash", 35, 15);

		Enemy dart = new Enemy("Dart", 90, 85, knife, flash);
		System.out.println(dart.getName());
		System.out.println(dart.getHealth());
		System.out.println(dart.getMana());
		System.out.println(dart.isAlive());
		System.out.println(dart.getWeapon().getName());
		System.out.println(dart.getWeapon().getDamage());
		System.out.println(dart.getSpell().getName());
		System.out.println(dart.getSpell().getDamage());
		System.out.println(dart.getSpell().getManaCost());
		dart.takeDamage(60);
		System.out.println(dart.getHealth());
		System.out.println(dart.attack());
		System.out.println(dart.getMana());
		dart.takeDamage(30);
		System.out.println(dart.isAlive());
		System.out.println(dart.getHealth());
		System.out.println(dart.getMana());

		Weapon lightsaber = new Weapon("Light saber", 40);
		Spell darkForce = new Spell("Dark force", 35, 30);

		Enemy vader = new Enemy("Vader", 95, 90, lightsaber, darkForce);

		Weapon sword = new Weapon("Sword", 20);
		Spell speed = new Spell("Speed", 15, 20);

		Enemy knight = new Enemy("Knight", 70, 55, sword, speed);

		HealthPotion heal = new HealthPotion(40);
		System.out.println(heal.getHealingPoints());
		System.out.println(heal.collect(hanzo));
		System.out.println(hanzo.getHealth());
		System.out.println(heal.collect(hanzo));
		System.out.println(hanzo.getHealth());
		System.out.println(heal.collect(hanzo));
		System.out.println(hanzo.getHealth());

		hanzo.takeDamage(100);
		System.out.println(heal.collect(hanzo));
		System.out.println(hanzo.getHealth());

		ManaPotion mana = new ManaPotion(30);
		System.out.println("mana potion" + "\n" + mana.getManaPoints());
		System.out.println(hanzo.getMana());
		System.out.println(mana.collect(hanzo));
		System.out.println(hanzo.getMana());
		System.out.println(mana.collect(hanzo));
		System.out.println(hanzo.getMana());
		System.out.println(mana.collect(hanzo));
		System.out.println(hanzo.getMana());

		HealthPotion heal1 = new HealthPotion(25);

		char[][] map = { { 'S', '.', '#', '#', '.', '.', '.', '.', '.', 'T' },
				{ '#', 'T', '#', '#', '.', '.', '#', '#', '#', '.' },
				{ '#', '.', '#', '#', '#', 'E', '#', '#', '#', 'E' },
				{ '#', '.', 'E', '.', '.', '.', '#', '#', '#', '.' },
				{ '#', '#', '#', 'T', '#', '#', '#', '#', '#', 'G' } };

		print(map);

		List<Enemy> enemies = new ArrayList<>();
		enemies.add(dart);
		enemies.add(vader);
		enemies.add(knight);
		List<Treasure> treasures = new ArrayList<>();
		treasures.add(heal);
		treasures.add(mana);
		treasures.add(heal1);

		GameEngine game = new GameEngine(map, hanzo, enemies, treasures);
		System.out.println(game.makeMove(Direction.RIGHT));
		System.out.println();

		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.LEFT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.LEFT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.UP));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.UP));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.UP));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.LEFT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.UP));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.RIGHT));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
		System.out.println(game.makeMove(Direction.DOWN));
		print(game.getMap());
		System.out.println();
	}
}
*/