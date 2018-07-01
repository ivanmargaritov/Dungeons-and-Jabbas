package bg.uni.sofia.fmi.mjt.dungeon.treasure;

public abstract class Attribute implements Treasure {
	private String name;
	private int damage;

	public Attribute(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public String getName() {
		return this.name;
	}

	public int getDamage() {
		return this.damage;
	}
}
