package ParkingLotAPI.parkingLot.configuration;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import ParkingLotAPI.parkingLot.repositories.CompanyRepository;
import ParkingLotAPI.parkingLot.repositories.MovementRecorderRepository;
import ParkingLotAPI.parkingLot.repositories.TransactionReportRepository;
import ParkingLotAPI.parkingLot.repositories.VehicleRepository;

@Configuration
public class instantiation implements CommandLineRunner {

	@Autowired
	private CompanyRepository companyRep;
	@Autowired
	private VehicleRepository vehicleRep;

	@Autowired
	private MovementRecorderRepository mrRep;

	@Autowired
	private TransactionReportRepository transactRep;
	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		companyRep.deleteAll();
		vehicleRep.deleteAll();
		mrRep.deleteAll();
		transactRep.deleteAll();
		/*
		 * Company company = new Company(null, "1234578900534", "companySa",
		 * "rua vtnc 23", "61-9878418878", 80, 50); companyRep.insert(company);
		 * 
		 * Vehicle v1 = new Vehicle(null, "XXYZ102030", "BMW", "cr-3828472", "preto",
		 * "carro"); Vehicle v2 = new Vehicle(null, "XADBYZ102030", "HONDA",
		 * "ab-3828472", "azul", "moto"); Vehicle v3 = new Vehicle(null, "AXYZ102030",
		 * "FIAT", "rg-3872", "verde", "carro");
		 * 
		 * vehicleRep.saveAll(Arrays.asList(v1, v2, v3));
		 * 
		 * company.getVehicles().addAll(Arrays.asList(v1, v2, v3));
		 * 
		 * companyRep.save(company);
		 * 
		 * //////////////////////////ATE AQUI OK !! CONTROLLERS E SERVICES FEITOS
		 * ///////////////////////////
		 * 
		 * MovementRecord mr = new MovementRecord(v1); MovementRecord mr2 = new
		 * MovementRecord(v2); MovementRecord mr3 = new MovementRecord(v3);
		 * 
		 * v1.setMovementRecord(mr); v2.setMovementRecord(mr2);
		 * v3.setMovementRecord(mr3);
		 * 
		 * 
		 * mr.entrou(); mr2.entrou(); mr3.entrou();
		 * 
		 * mrRep.saveAll(Arrays.asList(mr, mr2, mr3)); //salvando explicitamente a
		 * entrada no mongo
		 * 
		 * 
		 * System.out.println("Veículos e registros de movimentação:");
		 * 
		 * System.out.println("(********************************");
		 * System.out.println("Deseja sair ? (y/n)"); char choice = sc.next().charAt(0);
		 * 
		 * sc.nextLine();
		 * 
		 * if (choice == 'y') {
		 * 
		 * System.out.println("Entre com a placa do veículo que deseja sair: "); String
		 * placa = sc.nextLine();
		 * 
		 * 
		 * boolean isFinded = false;
		 * 
		 * for (Vehicle x : company.getVehicles()) {
		 * 
		 * if (placa.equals(x.getPlaca())) {
		 * 
		 * x.getMovementRecord().saiu(); System.out.println(x +
		 * " saída confirmada! ,  momento da saida : "
		 * +x.getMovementRecord().getExitTime()); isFinded = true ; }
		 * mrRep.save(x.getMovementRecord()); //salvando as alterações explicitamente no
		 * banco!;
		 * 
		 * }
		 * 
		 * if(!isFinded) {
		 * System.out.print("Veículo não encontrado ou placa inválida!"); } }
		 * 
		 * 
		 * sc.close();
		 */
	}
}
