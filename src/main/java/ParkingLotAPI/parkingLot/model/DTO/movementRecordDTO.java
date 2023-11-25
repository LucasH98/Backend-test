package ParkingLotAPI.parkingLot.model.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import ParkingLotAPI.parkingLot.model.Vehicle;
import ParkingLotAPI.parkingLot.util.MovementRecord;

public class movementRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@Id
	private String id;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private Vehicle vehicle;
	
	public movementRecordDTO() {

	}

	public movementRecordDTO(MovementRecord obj) {
		this.vehicle = obj.getVehicle();
		this.entryTime = obj.getEntryTime();
		this.exitTime = obj.getExitTime();
		this.id = obj.getId();

	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}
	
	public boolean entrou() {
	    return (entryTime != null);
	}
	
	public boolean saiu() {
	    return (exitTime != null);
	}

}

