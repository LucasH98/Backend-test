package ParkingLotAPI.parkingLot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ParkingLotAPI.parkingLot.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {
	
public Company findByCnpj(String cnpj);	

}
