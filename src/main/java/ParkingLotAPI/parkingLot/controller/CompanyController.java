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
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.CompanyDTO;
import ParkingLotAPI.parkingLot.service.CompanyService;

@RestController
@RequestMapping(value ="/company")
public class CompanyController{

@Autowired
private CompanyService service ;
	
@GetMapping(value = "/{id}")
public ResponseEntity<CompanyDTO> findById(@PathVariable String id){	
Company company = service.findById(id);
return ResponseEntity.ok().body(new CompanyDTO(company));
}

@GetMapping
public ResponseEntity<List<CompanyDTO>> findAll(){
List<Company> list = service.findAll();	
List<CompanyDTO> listDTO = list.stream().map(x-> new CompanyDTO(x)).collect(Collectors.toList());
return ResponseEntity.ok().body(listDTO);

}

@GetMapping(value = "/{id}/vehicles")
public ResponseEntity<List<Vehicle>> findAllVehicles(@PathVariable String id){	
Company company = service.findById(id);
return ResponseEntity.ok().body(company.getVehicles());
}


@GetMapping(value = "/{companyId}/vehicles/{vehicleId}")
public ResponseEntity<Vehicle> findVehicleById(@PathVariable String companyId, @PathVariable String vehicleId) {	
    Vehicle vehicle = service.findVehicleById(companyId, vehicleId);
    return ResponseEntity.ok().body(vehicle);
}


@PostMapping
public ResponseEntity<Company> insert(@RequestBody CompanyDTO companydto){
Company company = service.fromDto(companydto);
service.insert(company);
return ResponseEntity.ok().body(company);

}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteById(@PathVariable String  id){
	service.deleteById(id);
	return ResponseEntity.noContent().build();
	
}


}
