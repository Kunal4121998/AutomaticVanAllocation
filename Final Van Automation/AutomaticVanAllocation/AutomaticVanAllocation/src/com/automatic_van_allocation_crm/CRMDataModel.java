package com.automatic_van_allocation_crm;

public class CRMDataModel implements Comparable<CRMDataModel> {

	private String indent_id;
	private String indent_date;
	private String customer_id;
	private String plant_id;
	private int quantity;
        private boolean orderDone;

    public boolean isOrderDone() {
        return orderDone;
    }

    public void setOrderDone(boolean orderDone) {
        this.orderDone = orderDone;
    }
	
	public String getIndent_id() {
		return indent_id;
	}
	public void setIndent_id(String indent_id) {
		this.indent_id = indent_id;
	}
	public String getIndent_date() {
		return indent_date;
	}
	public void setIndent_date(String indent_date) {
		this.indent_date = indent_date;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getPlant_id() {
		return plant_id;
	}
	public void setPlant_id(String plant_id) {
		this.plant_id = plant_id;
	}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

        

    @Override
    public int compareTo(CRMDataModel o){
         return o.getQuantity()-quantity;
    }

}