package ParkingLotAPI.parkingLot.service.exceptions;

public class VehicleNotFoundException extends ResourceNotFoundException {
private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(String id) {
		super("Vehicle not found. Id: "+ id);
		
	}

}
