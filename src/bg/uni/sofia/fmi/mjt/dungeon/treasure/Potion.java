package bg.uni.sofia.fmi.mjt.dungeon.treasure;

public abstract class Potion implements Treasure {
	private int potionPoints;

	public Potion(int potionPoints) {
		this.potionPoints = potionPoints;
	}

	public int getPotionPoints() {
		return potionPoints;
	}

	public void setPotionPoints(int potionPoints) {
		this.potionPoints = potionPoints;
	}

}
