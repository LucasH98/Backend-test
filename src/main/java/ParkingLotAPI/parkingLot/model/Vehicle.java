package ParkingLotAPI.parkingLot.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Vehicle")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String placa;
	private String marca;
	private String modelo;
	private String cor;
	private String tipo;

	@DBRef
	//@JsonBackReference
	@JsonIgnore
	private MovementRecord movementRecord;

	@DBRef
	@JsonIgnore
	private Company company;

	public Vehicle() {

	}

	public Vehicle(String id, String placa, String marca, String modelo, String cor, String tipo) {

		this.id = id;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.tipo = tipo;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
		Vehicle other = (Vehicle) obj;
		return Objects.equals(modelo, other.modelo) && Objects.equals(placa, other.placa);
	}

	public MovementRecord getMovementRecord() {
		return movementRecord;
	}

	public void setMovementRecord(MovementRecord movementRecord) {
		this.movementRecord = movementRecord;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", cor=" + cor
				+ ", tipo=" + tipo + "]";
	}

}
