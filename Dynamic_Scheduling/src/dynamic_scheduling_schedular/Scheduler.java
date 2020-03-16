/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_scheduling_schedular;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import dynamic_scheduling_daily.DailyConsumption;
import dynamic_scheduling_daily.dailyConsumptionModel;
import dynamic_scheduling_order.orderDetails;
import dynamic_scheduling_vendor.Vendors;
import dynamic_scheduling_vendor.DataVendor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author fast
 */
public class Scheduler {
    DailyConsumption dailyConsumption;
    ArrayList<dailyConsumptionModel> dailyArrayList;
    String date[];
    double consumption[];
    double maxStock;
    double minStock;
    double openingStock[];
    double closingStock[];
    double orderQuantity[];
    double lotSize=20;
    int orderIndex=0;
    int storeOrderIndex[];
    double Po[];
    ArrayList<orderDetails> orderList;
     ArrayList<orderDetails> orderListOutput;
    int x;
    String currentDate;
    ArrayList<Vendors> vendorList;
    DataVendor dataVendor;
    int daysBetween[];
    
    public Scheduler(){
        dataVendor =new DataVendor();
        dailyConsumption=new DailyConsumption();
        date =new String[32];
        consumption=new double[32];
        openingStock=new double[50];
        closingStock=new double[50];
        orderQuantity=new double[50];
        storeOrderIndex=new int[100];
        daysBetween=new int[30];
        orderList =new ArrayList<>();
        orderListOutput =new ArrayList<>();
       
        Po=new double[30];
    }
    
    public void Schedules() throws NumberFormatException, FilloException{
        dailyConsumption.getData();
        dailyArrayList=dailyConsumption.display();
        DataInitialization();
        maxStock_and_minStock(dailyArrayList.get(0).getPlant(),dailyArrayList.get(0).getMaterial());
        openingStock[0]=getOpeningStock(dailyArrayList.get(0).getPlant(),dailyArrayList.get(0).getMaterial());
        for(int i=0; i<dailyArrayList.size(); i++){
            closingStock[i]=openingStock[i]-consumption[i];
            if(closingStock[i]<minStock){
            orderQuantity[i]=maxStock-closingStock[i];
            double k=orderQuantity[i]/lotSize;
            int l=(int) Math.round(k);
            orderQuantity[i]=l*lotSize;
            closingStock[i]=orderQuantity[i]+closingStock[i];
            storeOrderIndex[orderIndex]=i;
            x=orderIndex++;
        }
        openingStock[i+1]=closingStock[i];
    }   
        displaySchedules();
        index();
        getVendors(dailyArrayList.get(0).getPlant(),dailyArrayList.get(0).getMaterial());
        
        displayOutput();
    }
    
    private void displaySchedules(){
         for(int i=0; i<dailyArrayList.size(); i++){
             System.out.println(date[i]+" "+openingStock[i]+" "+consumption[i]+" "+closingStock[i]+" "+orderQuantity[i] );
    }     
    }

    private void maxStock_and_minStock(String plant, String material) throws FilloException {
         
          Fillo fillo=new Fillo();
          Connection connection=fillo.getConnection("C:\\Users\\p.charde\\Desktop\\ds1\\Storage_Capacity.xlsx");
  
          String strQuery="Select * from sheet1 where Plant='"+plant+"' and Material='"+material+"' ";
	  Recordset recordset=connection.executeQuery(strQuery);  
          while (recordset.next()) {            
             maxStock=Double.parseDouble(recordset.getField("Maximum Storage Capacity"));
              minStock=Double.parseDouble(recordset.getField("Minimum Storage Capacity"));
        }
             
        
    }

    private void DataInitialization() {
         for(int i=0; i<dailyArrayList.size(); i++){
            consumption[i]=dailyArrayList.get(i).getDaily();
            date[i]=dailyArrayList.get(i).getDate();
             //System.out.println(date[i]);
        }
    }

    private double getOpeningStock(String plant, String material) throws FilloException {
          Fillo fillo=new Fillo();
          Connection connection=fillo.getConnection("C:\\Users\\p.charde\\Desktop\\ds1\\Opening_Stock.xlsx");
          double opening = 0;
          String strQuery="Select * from sheet1 where Plant='"+plant+"' and Material='"+material+"' ";
	  Recordset recordset=connection.executeQuery(strQuery);  
          while (recordset.next()) {            
           opening=Double.parseDouble(recordset.getField("Unrest Qty"));
        }
        return opening; //To change body of generated methods, choose Tools | Templates.
    }

    private void index() {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        currentDate=date[0];
        //System.out.println(currentDate);
        for(int i=0; i<orderIndex; i++){
            //System.out.println(date[storeOrderIndex[i]]+" "+orderIndex);
            String date1=date[storeOrderIndex[i]];
            try {
	       Date dateBefore = myFormat.parse(currentDate);
	       Date dateAfter = myFormat.parse(date1);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	       daysBetween[i] = (int) (difference / (1000*60*60*24));               
	 } catch (Exception e) {
	 }
         }
        
    }

    private ArrayList<orderDetails> getVendors(String plant, String material) throws FilloException {
        vendorList=dataVendor.getvendors(plant, material);
        for(int i=0; i<vendorList.size(); i++){
            Po[i]=vendorList.get(i).getPo_quantity();
        }
        for (int i=0; i<orderIndex;i++)
        {
            if(daysBetween[i]==storeOrderIndex[i]){
          //  System.out.println(daysBetween[i]+"  "+storeOrderIndex[i]+" "+orderQuantity[daysBetween[i]]);
            Collections.sort(vendorList);
            for(int z=0; z<vendorList.size(); z++){
                if(vendorList.get(z).getLead_time()<=daysBetween[i]){
                    if(Po[z]!=0 && orderQuantity[daysBetween[i]]!=0){
                    if(vendorList.get(z).getPer_day_capacity()<=orderQuantity[daysBetween[i]]){ 
                             orderDetails order =new orderDetails();
                             order.setDate(date[daysBetween[i]-vendorList.get(z).getLead_time()]);
                             order.setName(vendorList.get(z).getName());
                             order.setOrder_quantity(vendorList.get(z).getPer_day_capacity());
                             Po[z]-=vendorList.get(z).getPer_day_capacity(); 
                             orderQuantity[daysBetween[i]]-=vendorList.get(z).getPer_day_capacity();
                             orderList.add(order);
                    }else{
                             orderDetails order =new orderDetails();
                             order.setDate(date[daysBetween[i]-vendorList.get(z).getLead_time()]);
                             order.setName(vendorList.get(z).getName());
                             order.setOrder_quantity(orderQuantity[daysBetween[i]]);
                             Po[z]-=orderQuantity[daysBetween[i]];
                             orderQuantity[daysBetween[i]]-=orderQuantity[daysBetween[i]];
                             orderList.add(order);
                    }
                    }
                }
            }
            }
        }
        return orderList;
    }
    public void displayOutput(){
        for(orderDetails orderDetails1: orderList){
            System.out.println(orderDetails1.getDate()+" "+orderDetails1.getOrder_quantity()+" "+orderDetails1.getName());
        }
    }
       
}
