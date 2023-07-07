package Core.Entities;

public abstract class Neumatico {
		private int desgaste;
		private int durabilidad;
		private int consumo;
		private int velocidad;
		private int agarre;
		
		
		
		public Neumatico(int desgaste, int durabilidad, int consumo, int velocidad, int agarre) {
			super();
			this.desgaste = desgaste;
			this.durabilidad = durabilidad;
			this.consumo = consumo;
			this.velocidad = velocidad;
			this.agarre = agarre;
		}

		public int getDesgaste() {
			return desgaste;
		}

		public void setDesgaste(int desgaste) {
			this.desgaste = desgaste;
		}

		public int getDurabilidad() {
			return durabilidad;
		}

		public void setDurabilidad(int durabilidad) {
			this.durabilidad = durabilidad;
		}

		public int getConsumo() {
			return consumo;
	    }

		public int getVelocidad() {
			return velocidad;
		}

		public void setVelocidad(int velocidad) {
			this.velocidad = velocidad;
		}

		public int getAgarre() {
			return agarre;
		}

		public void setAgarre(int agarre) {
			this.agarre = agarre;
		}

		public void setConsumo(int consumo) {
			this.consumo = consumo;
		}
		
}
