package ParkingLotAPI.parkingLot.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "Vehicle")
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Schema(description = "ID do veículo")
    private String id;

    @Schema(description = "Placa do veículo")
    private String placa;

    @Schema(description = "Marca do veículo")
    private String marca;

    @Schema(description = "Modelo do veículo")
    private String modelo;

    @Schema(description = "Cor do veículo")
    private String cor;

    @Schema(description = "Tipo do veículo")
    private String tipo;

    @DBRef
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

    // Métodos de acesso e modificação dos atributos

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

    // Métodos de acesso e modificação dos atributos

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public MovementRecord getMovementRecord() {
        return movementRecord;
    }

    public void setMovementRecord(MovementRecord movementRecord) {
        this.movementRecord = movementRecord;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", cor=" + cor
                + ", tipo=" + tipo + "]";
    }
}
