package com.automatic_van_allocation_Automation;

public class AutomationDataModel implements Comparable<AutomationDataModel>{

	private String indent_id;
	private String indent_date;
	private String customer_id;
	private int plant;
	private int quantity;
	private String van_no;

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

	    public int getPlant() {
	        return plant;
	    }

	    public void setPlant(int plant) {
	        this.plant = plant;
	    }

	    

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public String getVan_no() {
	        return van_no;
	    }

	    public void setVan_no(String van_no) {
	        this.van_no = van_no;
	    }

	    @Override
	    public int compareTo(AutomationDataModel o) {
	        return plant-o.getPlant();
	    }
}