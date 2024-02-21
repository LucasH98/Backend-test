package ParkingLotAPI.parkingLot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ParkingLotAPI.parkingLot.model.TransactionReport;

public interface TransactionReportRepository extends MongoRepository<TransactionReport, String>{

}
