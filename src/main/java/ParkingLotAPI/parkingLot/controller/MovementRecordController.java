package ParkingLotAPI.parkingLot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.service.MovementRecordService;
import ParkingLotAPI.parkingLot.util.MovementRecord;

@RestController
@RequestMapping("/record")
public class MovementRecordController {

	@Autowired
	private MovementRecordService service;

	@PostMapping("/generate")
	public ResponseEntity<MovementRecord> generateMovementRecord( @RequestParam String vehicleId) {
		
		MovementRecord mr = service.generateRecord(vehicleId);
		return ResponseEntity.ok().body(mr);

	}
		
	@PostMapping("/in")
	public ResponseEntity<Void> entryValidator(@RequestParam String id) {
		service.entryValidator(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/out")
	public ResponseEntity<Void> exitValidator(@RequestParam String id, @RequestParam String placa) {
		
		service.exityValidator(id, placa);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/get")
	public ResponseEntity<MovementRecord> findRecordByPlaca(@RequestParam String placa){
		MovementRecord mr = service.findRecordByPlaca(placa);
		return ResponseEntity.ok().body(mr);

		
	}
}