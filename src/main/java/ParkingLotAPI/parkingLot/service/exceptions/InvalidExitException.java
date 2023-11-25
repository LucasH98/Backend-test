package ParkingLotAPI.parkingLot.service.exceptions;

public class InvalidExitException extends RuntimeException {
private static final long serialVersionUID = 1L;


public InvalidExitException(String message) {
	super(message);
}

}
