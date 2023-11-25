	package ParkingLotAPI.parkingLot.util;
	
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

	@Document
	@Component
	public class TransactionReport implements Serializable {

		private static final long serialVersionUID = 1L;
		
		
		@Id
		private String id;
		private Integer totalEntries = 0;
		private Integer totalExits = 0;
		private Integer totalEntriesPerHour = 0;
		private Integer totalExitsPerHour = 0;
		
	
		public TransactionReport() {
	
		}
	
		
	
		public TransactionReport(String id ,Integer totalEntries, Integer totalExits, Integer totalEntriesPerHour,Integer totalExitsPerHour) {
			this.id = id ;
			this.totalEntries = totalEntries;
			this.totalExits = totalExits;
			this.totalEntriesPerHour = totalEntriesPerHour;
			this.totalExitsPerHour = totalExitsPerHour;
		}



		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
	
		public Integer getTotalEntries() {
			return totalEntries;
		}
	
		public void setTotalEntries(Integer totalEntries) {
			this.totalEntries = totalEntries;
		}
	
		public Integer getTotalExits() {
			return totalExits;
		}
	
		public void setTotalExits(Integer totalExits) {
			this.totalExits = totalExits;
		}
	
		public Integer getTotalEntriesPerHour() {
			return totalEntriesPerHour;
		}
	
		public void setTotalEntriesPerHour(Integer totalEntriesPerHour) {
			this.totalEntriesPerHour = totalEntriesPerHour;
		}
	
		public Integer getTotalExitsPerHour() {
			return totalExitsPerHour;
		}
	
		public void setTotalExitsPerHour(Integer totalExitsPerHour) {
			this.totalExitsPerHour = totalExitsPerHour;
		}
	
		
		
		public void incrementTotalEntry() {
			this.totalEntries++;
		}
	
		public void incrementTotalExit() {
			this.totalExits++;
		}
		
		
		 public void resetCounts() {
			 
		        this.totalEntries = 0;
		        this.totalExits = 0;
		        this.totalEntriesPerHour = 0;
		        this.totalExitsPerHour = 0;
		    }
	
	}
