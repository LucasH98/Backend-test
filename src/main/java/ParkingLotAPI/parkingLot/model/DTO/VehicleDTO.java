package ParkingLotAPI.parkingLot.model.DTO;

import java.io.Serializable;
import java.util.Objects;

import ParkingLotAPI.parkingLot.model.Vehicle;

public class VehicleDTO implements Serializable{
	private static final long serialVersionUID = 1L; 
	
		private String id ;
		private String marca;
		private String modelo;
		private String cor;
		private String placa;
		private String tipo;

		public VehicleDTO() {
			
		}

		public VehicleDTO(Vehicle obj) {
			this.id = obj.getId();
			this.placa = obj.getPlaca();
			this.marca = obj.getMarca();
			this.modelo = obj.getModelo();
			this.cor = obj.getCor();
			this.tipo = obj.getTipo();
		}

		
		
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getCor() {
			return cor;
		}

		public void setCor(String cor) {
			this.cor = cor;
		}

		public String getPlaca() {
			return placa;
		}

		public void setPlaca(String placa) {
			this.placa = placa;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
		
		@Override
		public int hashCode() {
			return Objects.hash(modelo, placa);
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			VehicleDTO other = (VehicleDTO) obj;
			return Objects.equals(modelo, other.modelo) && Objects.equals(placa, other.placa);
		}

		@Override
		public String toString() {
			return "VehicleDTO [marca=" + marca + ", modelo=" + modelo + ", cor=" + cor + ", placa=" + placa + ", tipo="
					+ tipo + "]";
		}
		
		
}
