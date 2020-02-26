package com.automatic_van_allocation_vanMaster;

public class VanMasterModel implements Comparable<VanMasterModel>{


	private String plancode;
	private String vehno;
        private String material;
        private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

  
        public String getMaterial() {
        return material;
    }

        public void setMaterial(String material) {
              this.material = material;
        }
	
	public String getPlancode() {
		return plancode;
	}
	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}
	public String getVehno() {
		return vehno;
	}
	public void setVehno(String vehno) {
		this.vehno = vehno;
	}

    @Override
    public int compareTo(VanMasterModel o) {
        return capacity-o.capacity;//To change body of generated methods, choose Tools | Templates.
    }
	
}