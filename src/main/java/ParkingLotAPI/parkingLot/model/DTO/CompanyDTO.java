package ParkingLotAPI.parkingLot.model.DTO;

import java.io.Serializable;
import java.util.Objects;

import ParkingLotAPI.parkingLot.model.Company;

public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String cnpj;
	private String endereco;
	private String telefone;
	private Integer qntVagaCarro;
	private Integer qntVagaMoto;

	public CompanyDTO() {

	}

	public CompanyDTO(Company obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.cnpj = obj.getCnpj();
		this.endereco = obj.getEndereco();
		this.telefone =obj.getTelefone();
		this.qntVagaCarro = obj.getQntVagaCarro();
		this.qntVagaMoto = obj.getQntVagaMoto();
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
	public int hashCode() {
		return Objects.hash(cnpj, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(name, other.name);
	}
	
	

}
