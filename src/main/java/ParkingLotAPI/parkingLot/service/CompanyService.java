package ParkingLotAPI.parkingLot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ParkingLotAPI.parkingLot.model.Company;
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.CompanyDTO;
import ParkingLotAPI.parkingLot.repositories.CompanyRepository;
import ParkingLotAPI.parkingLot.service.exceptions.ResourceNotFoundException;
import ParkingLotAPI.parkingLot.service.exceptions.VehicleNotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	public Company insert(Company company) {
		Company c  = findByCnpj(company.getCnpj());
		
		if(c!=null) {
			
			throw new RuntimeException(" Estabelecimento  ja registrado");
		}
		

		repository.insert(company);
	
		return company;
	}
	

	public Company findById(String id) {
		Company company = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return company;
	}
	

	public Company findByCnpj(String cnpj) {
		Company company = repository.findByCnpj(cnpj);
		return company;
	}

	public List<Company> findAll() {
		return repository.findAll();
	}

	public Vehicle findVehicleById(String companyId, String vehicleId) {
		
		Company company = repository.findById(companyId)
				.orElseThrow(() -> new ResourceNotFoundException(companyId));
		return company.getVehicles().stream().filter(x -> x.getId().equals(vehicleId)).findFirst()
				.orElseThrow(() -> new VehicleNotFoundException(vehicleId));

	}
	
	public void deleteById(String id) {
		
		Company company = repository.findById(id).orElseThrow();
		
		if(company!=null) {
			
			
			if(company.getVehicles().isEmpty()) {
				
				throw new ResourceNotFoundException("Não é possivel apagar pois existem veículos vinculados  neste estabelecimento!");
			}
			
			else {
				repository.delete(company);
			}
			
		}
		
		else {
			throw new ResourceNotFoundException(id+" not found");
		}
		
		
	}

	
	public Company fromDto(CompanyDTO dto) {
		return new Company(dto.getId(), dto.getName(), dto.getCnpj(), dto.getEndereco(), dto.getTelefone(),
				dto.getQntVagaCarro(), dto.getQntVagaMoto());
	}
	
}
