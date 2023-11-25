package ParkingLotAPI.parkingLot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ParkingLotAPI.parkingLot.model.Company;
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.VehicleDTO;
import ParkingLotAPI.parkingLot.repositories.CompanyRepository;
import ParkingLotAPI.parkingLot.repositories.MovementRecorderRepository;
import ParkingLotAPI.parkingLot.repositories.VehicleRepository;
import ParkingLotAPI.parkingLot.service.exceptions.ResourceNotFoundException;
import ParkingLotAPI.parkingLot.service.exceptions.VehicleNotFoundException;
import ParkingLotAPI.parkingLot.util.MovementRecord;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Autowired
	private CompanyRepository CompRep;
	
	@Autowired
	private MovementRecorderRepository movementRep;
	

	public Vehicle insert(Vehicle vehicle, String idCompany) {

		Company comp = CompRep.findById(idCompany).orElseThrow(()-> new ResourceNotFoundException(idCompany));

		if (comp != null) {
			
			if(comp.getVehicles().contains(vehicle)) {
				
				throw new RuntimeException("Veiculo ja registrado!");
			}
			
			else {
				
			vehicle = repository.save(vehicle);
			comp.getVehicles().add(vehicle);
			CompRep.save(comp);
			return vehicle;
			
			}
			
		}

		throw new ResourceNotFoundException("Empresa não encontrada com o ID: " + idCompany);
	}

	public Vehicle findById(String id) {
		Vehicle vehicle = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
		System.out.print(vehicle);
		return vehicle;
	};
	
	
	public Vehicle findByPlaca(String placa) {
		Vehicle v =  repository.findByPlaca(placa);
		
		if(v  == null) {
			 throw new VehicleNotFoundException("Vehicle not found");

		}
		
		return v;
	}

	public List<Vehicle> findAll() {
		return repository.findAll();
	}

	public void deleteById(String id) {
		Vehicle vehicle = repository.findById(id).orElseThrow();
		MovementRecord mr = vehicle.getMovementRecord();
		movementRep.delete(mr);
		repository.delete(vehicle);
	}

	public Vehicle fromDto(VehicleDTO objdto) {
		Vehicle vehicle = new Vehicle(objdto.getId(), objdto.getPlaca(), objdto.getMarca(), objdto.getModelo(),
				objdto.getCor(), objdto.getTipo());
		return vehicle;
	}

}
