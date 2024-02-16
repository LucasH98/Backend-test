package ParkingLotAPI.parkingLot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.VehicleDTO;
import ParkingLotAPI.parkingLot.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Api de veículos")
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value ="/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service ;

    @Operation(description = "Retorna um veículo com base em um ID")
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable String id){
        Vehicle vehicle = service.findById(id);
        return ResponseEntity.ok().body(new VehicleDTO(vehicle));
    }

    @Operation(description = "Retorna todos os veículos")
    @GetMapping(value="/")
    public ResponseEntity<List<VehicleDTO>> findAll(){
        List<Vehicle> list = service.findAll();
        List<VehicleDTO> getListDTO = list.stream().map(x->new VehicleDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(getListDTO);
    }

    @Operation(description = "Retorna um veículo com determinada placa")
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehicle> findByPlaca(@PathVariable String placa){
        Vehicle v = service.findByPlaca(placa);
        return ResponseEntity.ok().body(v);
    }

    @Operation(description = "Adiciona um novo veículo")
    @PostMapping(value = "/add/{companyId}/")
    public ResponseEntity<Vehicle> insert(@RequestBody VehicleDTO vehicleDTO,@PathVariable String companyId){
        Vehicle vehicle = service.fromDto(vehicleDTO); 
        vehicle = service.insert(vehicle,companyId);
        return ResponseEntity.ok().body(vehicle);
    }

    @Operation(description = "Exclui um veículo por ID")
    @DeleteMapping(value = "/{id}" )
    public ResponseEntity<Void> delete(@PathVariable String id){    
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
