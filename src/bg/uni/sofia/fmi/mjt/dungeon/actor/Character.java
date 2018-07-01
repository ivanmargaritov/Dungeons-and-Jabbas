package bg.uni.sofia.fmi.mjt.dungeon.actor;

import bg.uni.sofia.fmi.mjt.dungeon.treasure.Spell;
import bg.uni.sofia.fmi.mjt.dungeon.treasure.Weapon;

abstract class Character implements Actor{
	private String name;
	private int health;
	private int mana;
	private Weapon weapon;
	private Spell spell;
	private int minHealth;
	
	public Character(String name, int health, int mana) {
		this.name=name;
		this.setHealth(health);
		this.setMana(mana);
		this.minHealth=0;
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
		return this.getHealth() > 0;
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	public Spell getSpell() {
		return this.spell;
	}
	
	public void takeDamage(int damagePoints) {
		if (this.getHealth() - damagePoints >= this.minHealth) {
			this.setHealth(this.getHealth() - damagePoints);
		} else {
			this.setHealth(this.minHealth);
		}
	}
	
	public int attack() {
		if (this.getWeapon() == null && this.getSpell() == null) {
			return 0;
		} else if (this.getWeapon() == null && this.getSpell() != null) {
			if (this.getSpell().getManaCost() <= this.getMana()) {
				this.setMana(this.getMana() - this.getSpell().getManaCost());
				return this.getSpell().getDamage();
			} else {
				return 0;
			}
		}

		else if (this.getSpell() == null && this.getWeapon() != null) {
			return this.getWeapon().getDamage();
		}

		else if (this.getWeapon().getDamage() >= this.getSpell().getDamage()) {
			return this.getWeapon().getDamage();
		}

		else if (this.getWeapon().getDamage() < this.getSpell().getDamage()) {
			if (this.getSpell().getManaCost() <= this.getMana()) {
				this.setMana(this.getMana() - this.getSpell().getManaCost());
				return this.getSpell().getDamage();
			}
			else {
				return this.getWeapon().getDamage();
			}
		}
		return 0;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setSpell(Spell spell) {
		this.spell = spell;
	}
}
