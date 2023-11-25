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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.VehicleDTO;
import ParkingLotAPI.parkingLot.service.VehicleService;

@RestController
@RequestMapping(value ="/vehicle")
public class VehicleController {

@Autowired
private VehicleService service ;
	
@GetMapping(value = "/{id}")
public ResponseEntity<VehicleDTO> findById(@PathVariable String id){
Vehicle vehicle = service.findById(id);
return ResponseEntity.ok().body(new VehicleDTO(vehicle));
}

@GetMapping
public ResponseEntity<List<VehicleDTO>> findAll(){
List<Vehicle> list = service.findAll();
List<VehicleDTO> getListDTO = list.stream().map(x->new VehicleDTO(x)).collect(Collectors.toList());
return ResponseEntity.ok().body(getListDTO);
}


@GetMapping("/get")
public ResponseEntity<Vehicle> findByPlaca(@RequestParam String placa){
Vehicle v = service.findByPlaca(placa);
return ResponseEntity.ok().body(v);

}

@PostMapping(value = "/add/{companyId}/")
public ResponseEntity<Vehicle>insert(@RequestBody VehicleDTO vehicleDTO,@PathVariable String companyId){
Vehicle vehicle = service.fromDto(vehicleDTO);
vehicle = service.insert(vehicle,companyId);
return ResponseEntity.ok().body(vehicle);
}


@DeleteMapping(value = "/{id}" )
public ResponseEntity<Void>delete(@PathVariable String id){	
service.deleteById(id);
return ResponseEntity.noContent().build();
}

}
