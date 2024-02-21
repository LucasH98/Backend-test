package ParkingLotAPI.parkingLot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ParkingLotAPI.parkingLot.model.MovementRecord;
import ParkingLotAPI.parkingLot.service.MovementRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name="Api do registro de movimento dos veículos")
@RestController
@RequestMapping("/record")
public class MovementRecordController {

    @Autowired
    private MovementRecordService service;

    @Operation(description = "Gera um registro de movimento para um veículo")
    @PostMapping("/generate")
    public ResponseEntity<MovementRecord> generateMovementRecord(@RequestParam String vehicleId) {
        MovementRecord mr = service.generateRecord(vehicleId);
        return ResponseEntity.ok().body(mr);
    }

    @Operation(description = "Valida a entrada de um veículo no estacionamento")
    @PostMapping("/in")
    public ResponseEntity<Void> entryValidator(@RequestParam String id) {
        service.entryValidator(id);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Valida a saída de um veículo do estacionamento")
    @PostMapping("/out")
    public ResponseEntity<Void> exitValidator(@RequestParam String id, @RequestParam String placa) {
        service.exityValidator(id, placa);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Retorna o registro de movimento de um veículo com base na placa")
    @GetMapping("/get")
    public ResponseEntity<MovementRecord> findRecordByPlaca(@RequestParam String placa){
        MovementRecord mr = service.findRecordByPlaca(placa);
        return ResponseEntity.ok().body(mr);
    }
}
