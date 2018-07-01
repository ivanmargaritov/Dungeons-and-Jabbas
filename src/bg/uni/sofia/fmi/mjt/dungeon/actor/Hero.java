package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

public class Hero implements Actor {
	private String name;
	private int health;
	private int mana;
	private Position position;
	private Weapon weapon;
	private Spell spell;
	private int maxHealth;
	private int minHealth;
	private int maxMana;

	public Hero(String name, int health, int mana, Position position) {
		this.name = name;
		this.health = health;
		this.mana = mana;
		this.position = position;
		this.maxHealth = health;
		this.minHealth = 0;
		this.maxMana = mana;
		this.weapon = null;
		this.spell = null;
	}

	public String getName() {
		return this.name;
	}

	public int getHealth() {
		return this.health;
	}

	public int getMaxHealth() {
		return this.maxHealth;
	}

	public int getMana() {
		return this.mana;
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

	public boolean isAlive() {
		return this.health > 0;
	}

	public void takeHealing(int healingPoints) {
		if (this.isAlive()) {
			if (this.health + healingPoints >= this.maxHealth) {
				this.health = this.maxHealth;
			} else {
				this.health += healingPoints;
			}
		}
	}

	public void takeDamage(int damagePoints) {
		if (this.health - damagePoints >= this.minHealth) {
			this.health -= damagePoints;
		} else {
			this.health = this.minHealth;
		}
	}

	public void takeMana(int manaPoints) {
		if (this.mana + manaPoints >= this.maxMana) {
			this.mana = this.maxMana;
		} else {
			this.mana += manaPoints;
		}
	}

	public void equip(Weapon weapon) {
		if (this.weapon == null) {
			this.weapon = weapon;
			return;
		}
		if (this.weapon.getDamage() < weapon.getDamage()) {
			this.weapon = weapon;
		}
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void learn(Spell spell) {
		if (this.spell == null) {
			this.spell = spell;
			return;
		}
		if (spell.getDamage() > this.spell.getDamage()) {
			this.spell = spell;
		}
	}

	public Spell getSpell() {
		return this.spell;
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
