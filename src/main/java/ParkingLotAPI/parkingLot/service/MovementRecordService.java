package ParkingLotAPI.parkingLot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ParkingLotAPI.parkingLot.model.MovementRecord;
import ParkingLotAPI.parkingLot.model.TransactionReport;
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.repositories.MovementRecorderRepository;
import ParkingLotAPI.parkingLot.repositories.TransactionReportRepository;
import ParkingLotAPI.parkingLot.repositories.VehicleRepository;
import ParkingLotAPI.parkingLot.service.exceptions.InvalidEntryException;
import ParkingLotAPI.parkingLot.service.exceptions.InvalidExitException;
import ParkingLotAPI.parkingLot.service.exceptions.ResourceNotFoundException;
import ParkingLotAPI.parkingLot.service.exceptions.VehicleNotFoundException;

@Service
public class MovementRecordService {

	@Autowired
	private MovementRecorderRepository repository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private TransactionReportRepository traReportRepository;

	public MovementRecord generateRecord(String idvehicle) {

		Vehicle vehicle = vehicleRepository.findById(idvehicle).orElseThrow(() -> new VehicleNotFoundException(idvehicle));

		if (vehicle.getMovementRecord() != null) {
			throw new RuntimeException("Veículo Já registrado ! ");
		}

		else {

			MovementRecord movementRecord = new MovementRecord(vehicle);
			repository.save(movementRecord);
			vehicle.setMovementRecord(movementRecord);
			vehicleRepository.save(vehicle);
			return movementRecord;

		}

	}

	public void entryValidator(String idVehicle) {

		Vehicle v = vehicleRepository.findById(idVehicle).orElseThrow(() -> new VehicleNotFoundException(idVehicle));
		TransactionReport tr = v.getCompany().getTransactionReport();
		MovementRecord mr = v.getMovementRecord();

		if (mr == null) {
			throw new InvalidEntryException("Invalid entry , Movement Record cannot be null");
		}

		if (mr.isParking()) {
			throw new InvalidEntryException("Invalid entry ,  Entrada do veículo já registrada !");
		}

		v.getMovementRecord().entrou();
		tr.setTotalEntries(tr.getTotalEntries() + 1);
		traReportRepository.save(tr);
		vehicleRepository.save(v);
		repository.save(v.getMovementRecord());

	}

	public void exityValidator(String id, String placa) {

		Vehicle v = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
		TransactionReport tr = v.getCompany().getTransactionReport();
		MovementRecord mr = v.getMovementRecord();

		if (mr.isParking()) {

			if (v.getPlaca().equals(placa)) {

				mr.saiu();

				tr.setTotalExits(tr.getTotalExits() + 1);
				traReportRepository.save(tr);
				repository.save(mr);
				vehicleRepository.save(v);
			}

			else {
				throw new InvalidExitException("Placa Inválida");
			}

		}

		else if (mr.getEntryTime() == null) {
			throw new InvalidExitException("Invalid Exit , Horario de entrada nao registrado !");

		}

		else {
			throw new InvalidExitException("Invalid Exit --> Veículo já saiu do estabelecimento , Horario da saída : " + mr.getEntryTime());
		}
	}

	public MovementRecord findRecordByPlaca(String placa) {

		Vehicle v = vehicleRepository.findByPlaca(placa);

		if (v == null) {
			throw new ResourceNotFoundException("Vehicle not found");
		}

		MovementRecord mr = v.getMovementRecord();

		if (mr == null) {
			throw new ResourceNotFoundException("MovementRecord not found");
		}

		return mr;
	}

}
