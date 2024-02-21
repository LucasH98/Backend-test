package ParkingLotAPI.parkingLot.resource.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ParkingLotAPI.parkingLot.service.exceptions.InvalidEntryException;
import ParkingLotAPI.parkingLot.service.exceptions.InvalidExitException;
import ParkingLotAPI.parkingLot.service.exceptions.ResourceNotFoundException;
import ParkingLotAPI.parkingLot.service.exceptions.VehicleNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)	//qualquer exception desse tipo vai ser interceptada e lançar a minha personalizada!
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		
		String error = "Resource Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error,e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
		
	
	}
	
	
	@ExceptionHandler(VehicleNotFoundException.class)
	public ResponseEntity<StandardError> vehicleNotFound(VehicleNotFoundException e , HttpServletRequest request){
		
		String error = "Vehicle Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error,e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(InvalidEntryException.class)
	public ResponseEntity<StandardError> InvalidEntry(InvalidEntryException e , HttpServletRequest request){
		
		String error = "Invalid Entry";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error,e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
	
	
	@ExceptionHandler(InvalidExitException.class)
	public ResponseEntity<StandardError> InvalidExit(InvalidExitException e , HttpServletRequest request){
		
		String error = "Invalid Exit";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error,e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
}
