package com.automatic_van_allocation_vanStatus;

public class VanStatusModel implements Comparable<VanStatusModel> {

	private String status;
	private String vehicle_no;
	private int capacity;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
        
        
        @Override
        public int compareTo(VanStatusModel o) {
             return capacity-o.capacity; //To change body of generated methods, choose Tools | Templates.
    }
        
        
}