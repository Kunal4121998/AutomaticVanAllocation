/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_scheduling_vendor;

/**
 *
 * @author fast
 */
public class Vendors implements Comparable<Vendors>{
    private String plant;
    private String material;
    private String name;
    private int lead_time;
    private double lot_size;
    private double per_day_capacity;
    private int po_quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getLead_time() {
        return lead_time;
    }

    public void setLead_time(int lead_time) {
        this.lead_time = lead_time;
    }

    public double getLot_size() {
        return lot_size;
    }

    public void setLot_size(double lot_size) {
        this.lot_size = lot_size;
    }

    public double getPer_day_capacity() {
        return per_day_capacity;
    }

    public void setPer_day_capacity(double per_day_capacity) {
        this.per_day_capacity = per_day_capacity;
    }

    public int getPo_quantity() {
        return po_quantity;
    }

    public void setPo_quantity(int po_quantity) {
        this.po_quantity = po_quantity;
    }

    @Override
    public int compareTo(Vendors o) {
        return po_quantity-o.getPo_quantity();
    }
    
    
}
