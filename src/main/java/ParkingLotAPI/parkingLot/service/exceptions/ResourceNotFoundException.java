package ParkingLotAPI.parkingLot.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
private static final long serialVersionUID = 1L;


public ResourceNotFoundException(String id) {
	super("Resource Not Found. "+id);
}


}
