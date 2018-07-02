package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class Hero extends Character {
	private Position position;
	private int maxHealth;
	private int maxMana;

	public Hero(String name, int health, int mana, Position position) {
		super(name, health, mana);
		this.position = position;
		this.maxHealth = super.getHealth();
		this.maxMana = super.getMana();
	}

	public int getMaxHealth() {
		return this.maxHealth;
	}

	public int getMaxMana() {
		return this.maxMana;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(int x, int y) {
		this.position.setX(x);
		this.position.setY(y);
	}

	public void takeHealing(int healingPoints) {
		if (healingPoints < 0) {
			throw new IllegalArgumentException();
		}

		if (isAlive()) {
			if (this.getHealth() + healingPoints >= this.maxHealth) {
				this.setHealth(this.maxHealth);
			} else {
				this.setHealth(this.getHealth() + healingPoints);
			}
		}
	}

	public void takeMana(int manaPoints) {
		if (manaPoints < 0) {
			throw new IllegalArgumentException();
		}
		if (this.getMana() + manaPoints >= this.maxMana) {
			this.setMana(this.maxMana);
		} else {
			this.setMana(this.getMana() + manaPoints);
		}
	}

	public void equipWeapon(Weapon weapon) {
		if (weapon == null) {
			throw new IllegalArgumentException();
		}
		if (this.getWeapon() == null) {
			this.setWeapon(weapon);
			return;
		}
		if (this.getWeapon().getDamage() < weapon.getDamage()) {
			this.setWeapon(weapon);
		}
	}

	public void learnSpell(Spell spell) {
		if (spell == null) {
			throw new IllegalArgumentException();
		}
		if (this.getSpell() == null) {
			this.setSpell(spell);
			return;
		}
		if (spell.getDamage() > this.getSpell().getDamage()) {
			this.setSpell(spell);
		}
	}

}
