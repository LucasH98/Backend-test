package ParkingLotAPI.parkingLot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ParkingLotAPI.parkingLot.model.Company;
import ParkingLotAPI.parkingLot.model.TransactionReport;
import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.model.DTO.CompanyDTO;
import ParkingLotAPI.parkingLot.repositories.CompanyRepository;
import ParkingLotAPI.parkingLot.repositories.TransactionReportRepository;
import ParkingLotAPI.parkingLot.service.exceptions.ResourceNotFoundException;
import ParkingLotAPI.parkingLot.service.exceptions.VehicleNotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	@Autowired
	private TransactionReportRepository transactionReportRepository;

	public Company insert(Company company) {
		Company c = findByCnpj(company.getCnpj());

		if (c != null) {
			throw new RuntimeException(" Estabelecimento  ja registrado");
		}

		TransactionReport tr = transactionReportRepository.save(new TransactionReport());
		company.setTransactionReport(tr);
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

		Company company = repository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException(companyId));
		return company.getVehicles().stream().filter(x -> x.getId().equals(vehicleId)).findFirst()
				.orElseThrow(() -> new VehicleNotFoundException(vehicleId));

	}

	public void deleteById(String id) {

		Company company = repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id));

		if (!company.getVehicles().isEmpty()) {

			throw new RuntimeException("Não é possivel apagar pois existem veículos vinculados  neste estabelecimento!");
		}

		transactionReportRepository.delete(company.getTransactionReport());
		repository.delete(company);
	}

	public TransactionReport report(String companyId) {
		Company c = repository.findByCnpj(companyId);
		TransactionReport tr = c.getTransactionReport();
		return tr;

	}

	public Company fromDto(CompanyDTO dto) {
		return new Company(dto.getId(), dto.getName(), dto.getCnpj(), dto.getEndereco(), dto.getTelefone(),
				dto.getQntVagaCarro(), dto.getQntVagaMoto());
	}

}
