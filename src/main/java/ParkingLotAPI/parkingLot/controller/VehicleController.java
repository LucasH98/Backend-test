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

import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.VehicleDTO;
import ParkingLotAPI.parkingLot.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Api de veículos")
@RestController
@RequestMapping(value ="/vehicle")
public class VehicleController {

@Autowired
private VehicleService service ;
	
@Operation(description = "retorna o veículos de determinado id")
@GetMapping(value = "/id/{id}")

public ResponseEntity<VehicleDTO> findById(@PathVariable String id){
Vehicle vehicle = service.findById(id);
return ResponseEntity.ok().body(new VehicleDTO(vehicle));
}

@Operation(description = "retorna todos os veículos")
@GetMapping(value="/")
public ResponseEntity<List<VehicleDTO>> findAll(){
List<Vehicle> list = service.findAll();
List<VehicleDTO> getListDTO = list.stream().map(x->new VehicleDTO(x)).collect(Collectors.toList());
return ResponseEntity.ok().body(getListDTO);
}


@GetMapping("/placa/{placa}")
@Operation(description = "retorna um veículo com determinada placa")
public ResponseEntity<Vehicle> findByPlaca(@PathVariable String placa){
Vehicle v = service.findByPlaca(placa);
return ResponseEntity.ok().body(v);
}

@PostMapping(value = "/add/{companyId}/")
@Operation(description = "adiciona um novo veículo")
public ResponseEntity<Vehicle>insert(@RequestBody VehicleDTO vehicleDTO,@PathVariable String companyId){
Vehicle vehicle = service.fromDto(vehicleDTO); 
vehicle = service.insert(vehicle,companyId);
return ResponseEntity.ok().body(vehicle);
}


@DeleteMapping(value = "/{id}" )
@Operation(description = "delete um veículo por id")
public ResponseEntity<Void>delete(@PathVariable String id){	
service.deleteById(id);
return ResponseEntity.noContent().build();
}

}
