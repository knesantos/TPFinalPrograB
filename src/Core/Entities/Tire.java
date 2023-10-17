package Core.Entities;

public abstract class Tire {
		private double wear;
		private int durability;
		private int consumption;
		private int speed;
		private int grip;

		public Tire(double wear, int durability, int consumption, int speed, int grip) {
		    super();
		    this.wear = wear;
		    this.durability = durability;
		    this.consumption = consumption;
		    this.speed = speed;
		    this.grip = grip;
		}

		public abstract boolean isSuitableFor(String weatherCondition);
		public abstract double getModificationFactorTime();

		public double getWear() {
		    return wear;
		}

		public void setWear(double wear) {
		    this.wear = wear;
		}

		public int getDurability() {
		    return durability;
		}

		public void setDurability(int durability) {
		    this.durability = durability;
		}

		public int getConsumption() {
		    return consumption;
		}

		public void setConsumption(int consumption) {
		    this.consumption = consumption;
		}

		public int getSpeed() {
		    return speed;
		}

		public void setSpeed(int speed) {
		    this.speed = speed;
		}

		public int getGrip() {
		    return grip;
		}

		public void setGrip(int grip) {
		    this.grip = grip;
		}
}