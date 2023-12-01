package ParkingLotAPI.parkingLot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ParkingLotAPI.parkingLot.model.Company;
import ParkingLotAPI.parkingLot.model.TransactionReport;
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.CompanyDTO;
import ParkingLotAPI.parkingLot.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Api do Estabelecimento")
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @Operation(description = "Retorna uma determinada empresa com base em um ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable String id) {
        Company company = service.findById(id);
        return ResponseEntity.ok().body(new CompanyDTO(company));
    }

    @Operation(description = "Retorna todos os estabelecimentos criados")
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAll() {
        List<Company> list = service.findAll();
        List<CompanyDTO> listDTO = list.stream().map(x -> new CompanyDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @Operation(description = "Retorna todos os veículos do estacionamento")
    @GetMapping(value = "/{id}/vehicles")
    public ResponseEntity<List<Vehicle>> findAllVehicles(@PathVariable String id) {
        Company company = service.findById(id);
        return ResponseEntity.ok().body(company.getVehicles());
    }

    @Operation(description = "Retorna um veículo com base em um determinado ID")
    @GetMapping(value = "/{companyId}/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable String companyId, @PathVariable String vehicleId) {
        Vehicle vehicle = service.findVehicleById(companyId, vehicleId);
        return ResponseEntity.ok().body(vehicle);
    }

    @Operation(description = "Cria um novo estabelecimento")
    @PostMapping
    public ResponseEntity<Company> insert(@RequestBody CompanyDTO companydto) {
        Company company = service.fromDto(companydto);
        service.insert(company);
        return ResponseEntity.ok().body(company);
    }

    @Operation(description = "Apaga determinado estabelecimento com base em um ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Retorna o relatório de transações de um estabelecimento com base em um ID")
    @GetMapping("/report/{id}")
    public ResponseEntity<TransactionReport> report(@PathVariable String id) {
        Company c = service.findById(id);
        TransactionReport tr = c.getTransactionReport();
        return ResponseEntity.ok().body(tr);
    }
}
