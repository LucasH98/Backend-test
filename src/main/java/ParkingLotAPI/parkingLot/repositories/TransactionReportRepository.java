package ParkingLotAPI.parkingLot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ParkingLotAPI.parkingLot.util.TransactionReport;

public interface TransactionReportRepository extends MongoRepository<TransactionReport, String>{

}
