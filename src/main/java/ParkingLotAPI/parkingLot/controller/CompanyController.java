package ParkingLotAPI.parkingLot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	
	@Operation(description = "retorna uma determinada empresa baseada em um  id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CompanyDTO> findById(@PathVariable String id) {
		Company company = service.findById(id);
		return ResponseEntity.ok().body(new CompanyDTO(company));
	}

	@GetMapping
	@Operation(description = "retorna todos os estabelecimentos criados")
	public ResponseEntity<List<CompanyDTO>> findAll() {
		List<Company> list = service.findAll();
		List<CompanyDTO> listDTO = list.stream().map(x -> new CompanyDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@Operation(description = "retorna todos os veículos do estacionamento")
	@GetMapping(value = "/{id}/vehicles")
	public ResponseEntity<List<Vehicle>> findAllVehicles(@PathVariable String id) {
		Company company = service.findById(id);
		return ResponseEntity.ok().body(company.getVehicles());
	}

	@GetMapping(value = "/{companyId}/vehicles/{vehicleId}")
	@Operation(description = "retorna um veículo por baseado em um determinado id")
	public ResponseEntity<Vehicle> findVehicleById(@PathVariable String companyId, @PathVariable String vehicleId) {
		Vehicle vehicle = service.findVehicleById(companyId, vehicleId);
		return ResponseEntity.ok().body(vehicle);
	}

	@PostMapping
	@Operation(description = "Cria um novo estabelecimento")
	public ResponseEntity<Company> insert(@RequestBody CompanyDTO companydto) {
		Company company = service.fromDto(companydto);
		service.insert(company);
		return ResponseEntity.ok().body(company);

	}

	@DeleteMapping("/{id}")
	@Operation(description = "Apaga determinado estabelecimento por  id")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();

	}
	

	@GetMapping("/report/{id}")
	public ResponseEntity<TransactionReport> report(@PathVariable String id) {
		Company c = service.findById(id);
		TransactionReport tr = c.getTransactionReport();
		return ResponseEntity.ok().body(tr);

	}

}
