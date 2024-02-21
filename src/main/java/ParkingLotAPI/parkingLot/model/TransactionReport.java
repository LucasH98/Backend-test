package ParkingLotAPI.parkingLot.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Document
@Component
public class TransactionReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Schema(description = "ID do relatório de transações")
    private String id;

    @Schema(description = "Número total de entradas")
    private Integer totalEntries = 0;

    @Schema(description = "Número total de saídas")
    private Integer totalExits = 0;

    @JsonIgnore
    private Company company;

    public TransactionReport() {

    }

    public TransactionReport(String id, Integer totalEntries, Integer totalExits) {
        this.id = id;
        this.totalEntries = totalEntries;
        this.totalExits = totalExits;
    }

    // Métodos de acesso e modificação dos atributos

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(Integer totalEntries) {
        this.totalEntries = totalEntries;
    }

    public Integer getTotalExits() {
        return totalExits;
    }

    public void setTotalExits(Integer totalExits) {
        this.totalExits = totalExits;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void incrementTotalEntry() {
        this.totalEntries++;
    }

    public void incrementTotalExit() {
        this.totalExits++;
    }

    public void resetCounts() {
        this.totalEntries = 0;
        this.totalExits = 0;
    }
}
