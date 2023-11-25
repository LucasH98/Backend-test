package ParkingLotAPI.parkingLot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ParkingLotAPI.parkingLot.model.Vehicle;

public interface VehicleRepository  extends MongoRepository<Vehicle, String>{
	
public Vehicle findByPlaca(String placa);


}
