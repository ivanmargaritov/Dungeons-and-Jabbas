package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class Enemy implements Actor {
	private String name;
	private int health;
	private int mana;
	private Weapon weapon;
	private Spell spell;
	private int minHealth;

	public Enemy(String name, int health, int mana, Weapon weapon, Spell spell) {
		this.name = name;
		this.health = health;
		this.mana = mana;
		this.weapon = weapon;
		this.spell = spell;
		this.minHealth = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getHealth() {
		return this.health;
	}

	public int getMana() {
		return this.mana;
	}

	public boolean isAlive() {
		return this.health > 0;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public Spell getSpell() {
		return this.spell;
	}

	public void takeDamage(int damagePoints) {
		if (this.health - damagePoints >= this.minHealth) {
			this.health -= damagePoints;
		} else {
			this.health = this.minHealth;
		}
	}

	public int attack() {
		if (this.weapon == null && this.spell == null) {
			return 0;
		} else if (this.weapon == null && this.spell != null) {
			if (this.spell.getManaCost() <= this.mana) {
				this.mana -= this.spell.getManaCost();
				return this.spell.getDamage();
			} else {
				return 0;
			}
		}

		else if (this.spell == null && this.weapon != null) {
			return this.weapon.getDamage();
		}

		else if (this.weapon.getDamage() >= this.spell.getDamage()) {
			return this.weapon.getDamage();
		}

		else if (this.weapon.getDamage() < this.spell.getDamage()) {
			if (this.spell.getManaCost() <= this.mana) {
				this.mana -= this.spell.getManaCost();
				return this.spell.getDamage();
			}
			else {
				return this.weapon.getDamage();
			}
		}
		return 0;
	}
}