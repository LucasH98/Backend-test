package ParkingLotAPI.parkingLot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ParkingLotAPI.parkingLot.repositories.CompanyRepository;
import ParkingLotAPI.parkingLot.repositories.MovementRecorderRepository;
import ParkingLotAPI.parkingLot.repositories.TransactionReportRepository;
import ParkingLotAPI.parkingLot.repositories.VehicleRepository;

@SpringBootApplication
public class ParkingLotApplication implements CommandLineRunner {

	@Autowired
	private MovementRecorderRepository mrRep;
	@Autowired
	private CompanyRepository compRep;
	@Autowired
	private VehicleRepository vehicleRep;
	@Autowired
	private TransactionReportRepository transactionRep;
	
	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
compRep.deleteAll();
vehicleRep.deleteAll();
mrRep.deleteAll();	
transactionRep.deleteAll();

		
	}

}
