package ParkingLotAPI.parkingLot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "Company")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Schema(description = "ID da empresa")
	private String id;

	@Schema(description = "CNPJ da empresa")
	private String cnpj;

	@Schema(description = "Nome da empresa")
	private String name;

	@Schema(description = "Endereço da empresa")
	private String endereco;

	@Schema(description = "Número de telefone da empresa")
	private String telefone;

	@Schema(description = "Quantidade de vagas para carros")
	private Integer qntVagaCarro;
	@Schema(description = "Quantidade de vagas para motos")
	private Integer qntVagaMoto;

	@DBRef(lazy = true)
	@Schema(description = "Lista de veículos associados à empresa")
	private List<Vehicle> vehicles = new ArrayList<>();

	@DBRef
	@JsonIgnore
	private TransactionReport transactionReport;

	public Company(String id, String cnpj, String name, String endereco, String telefone, Integer qntVagaCarro,
			Integer qntVagaMoto) {

		this.id = id;
		this.cnpj = cnpj;
		this.name = name;
		this.endereco = endereco;
		this.telefone = telefone;
		this.qntVagaCarro = qntVagaCarro;
		this.qntVagaMoto = qntVagaMoto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getQntVagaCarro() {
		return qntVagaCarro;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public TransactionReport getTransactionReport() {
		return transactionReport;
	}

	public void setTransactionReport(TransactionReport transactionReport) {
		this.transactionReport = transactionReport;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(cnpj, other.cnpj);
	}

	public void setQntVagaCarro(Integer qntVagaCarro) {
		this.qntVagaCarro = qntVagaCarro;
	}

	public Integer getQntVagaMoto() {
		return qntVagaMoto;
	}

	public void setQntVagaMoto(Integer qntVagaMoto) {
		this.qntVagaMoto = qntVagaMoto;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", cnpj=" + cnpj + ", name=" + name + ", endereco=" + endereco + ", telefone="
				+ telefone + ", qntVagaCarro=" + qntVagaCarro + ", qntVagaMoto=" + qntVagaMoto + ", vehicles="
				+ vehicles + "]";
	}

}
