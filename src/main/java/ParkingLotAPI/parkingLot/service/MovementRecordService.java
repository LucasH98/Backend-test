package ParkingLotAPI.parkingLot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.repositories.MovementRecorderRepository;
import ParkingLotAPI.parkingLot.repositories.VehicleRepository;
import ParkingLotAPI.parkingLot.service.exceptions.InvalidEntryException;
import ParkingLotAPI.parkingLot.service.exceptions.InvalidExitException;
import ParkingLotAPI.parkingLot.service.exceptions.ResourceNotFoundException;
import ParkingLotAPI.parkingLot.service.exceptions.VehicleNotFoundException;
import ParkingLotAPI.parkingLot.util.MovementRecord;
import ParkingLotAPI.parkingLot.util.TransactionReport;

@Service
public class MovementRecordService {

	@Autowired
	private MovementRecorderRepository repository;

	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	private TransactionReport transactionReport ; 
	

	public MovementRecord generateRecord(String idvehicle) {

		Vehicle vehicle = vehicleRepository.findById(idvehicle)
				.orElseThrow(() -> new VehicleNotFoundException(idvehicle));

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

		if (!v.getMovementRecord().isParking()) {

			if (repository.findRecordByVehicleId(v.getId()) != null) {
				v.getMovementRecord().entrou();
				transactionReport.setTotalEntries(transactionReport.getTotalEntries()+1);//***
				vehicleRepository.save(v);
				repository.save(v.getMovementRecord());
			}

			else {

				throw new InvalidEntryException("Invalid entry");

			}
		}

		else {
			throw new InvalidEntryException("Invalid entry - Veículo já entrou !");
		}

	}

	public void exityValidator(String id, String placa) {

		Vehicle v = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));

		boolean isFinded = false;

		if (v.getMovementRecord().isParking()) {

			if (v.getPlaca().equals(placa)) {
				v.getMovementRecord().saiu();
				transactionReport.setTotalEntries(transactionReport.getTotalEntries()+1); //***
				repository.save(v.getMovementRecord());
				vehicleRepository.save(v);
				isFinded = true;
			}

			else {
				throw new InvalidExitException("Placa Inválida");
			}

		} else if (v.getMovementRecord().getEntryTime() == null) {
			throw new InvalidExitException("Invalid Exit , Horario de entrada nao registrado !");

		}

		else {
			throw new InvalidExitException("Invalid Exit --> Veículo já saiu do estabelecimento , Horario da saída : "
					+ v.getMovementRecord().getEntryTime());
		}

		repository.save(v.getMovementRecord());

		if (!isFinded) {
			throw new InvalidExitException("Invalid Exit");

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
