package ParkingLotAPI.parkingLot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ParkingLotAPI.parkingLot.model.Company;
import ParkingLotAPI.parkingLot.model.MovementRecord;
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.VehicleDTO;
import ParkingLotAPI.parkingLot.repositories.CompanyRepository;
import ParkingLotAPI.parkingLot.repositories.MovementRecorderRepository;
import ParkingLotAPI.parkingLot.repositories.VehicleRepository;
import ParkingLotAPI.parkingLot.service.exceptions.ResourceNotFoundException;
import ParkingLotAPI.parkingLot.service.exceptions.VehicleNotFoundException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Autowired
	private CompanyRepository CompRep;

	@Autowired
	private MovementRecorderRepository movementRep;

	public Vehicle insert(Vehicle vehicle, String idCompany) {

		Company comp = CompRep.findById(idCompany).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com o ID: " + idCompany));

		if (comp.getVehicles().contains(vehicle)) {

			throw new RuntimeException("Veiculo ja registrado!");
		}

		comp.getVehicles().add(vehicle);
		vehicle.setCompany(comp);
		vehicle = repository.save(vehicle);
		CompRep.save(comp);
		return vehicle;

	}

	public Vehicle findById(String id) {
		Vehicle vehicle = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
		System.out.print(vehicle);
		return vehicle;
	};

	public Vehicle findByPlaca(String placa) {
		Vehicle v = repository.findByPlaca(placa);

		if (v == null) {
			throw new VehicleNotFoundException("Vehicle not found");

		}

		return v;
	}

	public List<Vehicle> findAll() {
		return repository.findAll();
	}

	public void deleteById(String id) {
		
		Vehicle vehicle = repository.findById(id).orElseThrow(()->  new VehicleNotFoundException(id));
		MovementRecord mr = vehicle.getMovementRecord();
		
		if(mr==null) {
			throw new ResourceNotFoundException("Associated Movement Record not found");
		}
		
		movementRep.delete(mr);
		repository.delete(vehicle);
		CompRep.save(vehicle.getCompany());
	}

	public Vehicle fromDto(VehicleDTO objdto) {
		Vehicle vehicle = new Vehicle(objdto.getId(), objdto.getPlaca(), objdto.getMarca(), objdto.getModelo(),
				objdto.getCor(), objdto.getTipo());
		return vehicle;
	}

}
