/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_scheduling_daily;

import java.util.Objects;

/**
 *
 * @author fast
 */
public class dailyConsumptionModel implements Comparable<dailyConsumptionModel>{
    private final String plant;
	private final String date;
	private final String material;
	//private final int sum;
	private  double daily;
	private final int id;
        private double daily_consumption;

    public double getDaily_consumption() {
        return daily_consumption;
    }

    public void setDaily_consumption(double daily_consumption) {
        this.daily_consumption = daily_consumption;
    }

        
	 public dailyConsumptionModel(String plant, String date, String material, double daily,int id) {
		this.plant = plant;
		this.date = date;
		this.material = material;
		this.daily = daily;
		this.id=id;
	}

    public void setDaily(double sum) {
        this.daily = daily;
    }

           

	public String getPlant() {
		return plant;
	}
	
	public String getDate() {
		return date;
	}

    public String getMaterial() {
		return material;
	}

    public double getDaily() {
		return daily;
	}

//    public int getSum() {
//		return sum;
//	}

      @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        dailyConsumptionModel that = (dailyConsumptionModel) o;
	        return Objects.equals(date, that.date) &&
	                Objects.equals(plant, that.plant);
	    }
	
	@Override
    public int hashCode() {
        return Objects.hash(plant, date);
    }

    public dailyConsumptionModel merge(dailyConsumptionModel other) {
        assert(this.equals(other));
        return new dailyConsumptionModel(this.plant, this.date, this.material,this.daily +other.daily,id) ;
    }



	public int getId() {
		return id;
	}



	@Override
	public int compareTo(dailyConsumptionModel o) {
		// TODO Auto-generated method stub
		return o.id-id;
}
}
