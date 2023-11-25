package ParkingLotAPI.parkingLot.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ParkingLotAPI.parkingLot.util.MovementRecord;

public interface MovementRecorderRepository extends MongoRepository<MovementRecord,String> {
	
	@Query("{ 'vehicle.$id': ?0 }")
public  MovementRecord generateRecord(String vehicleId);	
public  MovementRecord findRecordByVehicleId(String id);	
@Query("{ 'vehicle.$placa': ?0 }")
public 	MovementRecord findRecordByPlaca(String placa);
	
}
