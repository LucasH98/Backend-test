package ParkingLotAPI.parkingLot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ParkingLotAPI.parkingLot.model.TransactionReport;
import ParkingLotAPI.parkingLot.repositories.TransactionReportRepository;

@Service
public class TransactionReportService {

	@Autowired
	private TransactionReportRepository transactionReportRepository;

	@Autowired

	public TransactionReport insert(TransactionReport transactionReport) {

		return transactionReportRepository.save(transactionReport);
	}

	public void deleteTransactionReport(String id) {
		transactionReportRepository.deleteById(id);
	}

}
