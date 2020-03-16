/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_scheduling_vendor;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import java.util.ArrayList;

/**
 *
 * @author fast
 */
public class DataVendor {
    ArrayList<Vendors> vendors;
    public DataVendor(){
        vendors=new ArrayList<>();
    }
    public ArrayList<Vendors> getvendors(String plant,String material) throws FilloException{
          Fillo fillo=new Fillo();
          Connection connection=fillo.getConnection("C:\\Users\\p.charde\\Downloads\\OneDrive_1_3-16-2020\\ds\\Vendor_Master_File.xlsx");
          String strQuery="Select * from sheet1 where Plant='"+plant+"' and Material='"+material+"' ";
	  Recordset recordset=connection.executeQuery(strQuery);  
          while (recordset.next()) {            
           Vendors ven =new Vendors();
           if(plant.equals(recordset.getField("Plant"))&&material.equals(recordset.getField("Material")))
           {
              ven.setPlant(plant);
              ven.setMaterial(material);
              ven.setLead_time(Integer.parseInt(recordset.getField("Lead Time")));
              ven.setLot_size(Integer.parseInt(recordset.getField("Lot Size")));
              ven.setPo_quantity(300);
              ven.setName(recordset.getField("Supplier/Supplying Plant"));
              ven.setPer_day_capacity(Integer.parseInt(recordset.getField("Production Capacity Vendor")));
           }
            
           
           
           vendors.add(ven);
        }
        return vendors;
    }
}
