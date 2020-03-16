/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_scheduling_daily;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author fast
 */
public class DailyConsumption {
    ArrayList<dailyConsumptionModel> list;
	 ArrayList<dailyConsumptionModel> mergedList;

         public DailyConsumption(){
             list = new ArrayList<dailyConsumptionModel>();
             mergedList = new ArrayList<dailyConsumptionModel>();
         }
	public void getData() throws NumberFormatException, FilloException{
		int z=0; 
		
	           Fillo fillo=new Fillo();
	           Connection connection=fillo.getConnection("C:\\Users\\p.charde\\Desktop\\ds1\\Consumption_File.xlsx");
		   int i1=1826;
		   int y=40000001;
		   //Scanner scanner =new Scanner(System.in);
		   //System.out.println("Enter Plant:");
		   //i1=scanner.nextInt();
		   //System.out.println("Enter Material:");
		   //y=scanner.nextInt();
		   String strQuery="Select * from sheet1 where Plant='"+i1+"' and Material='"+y+"' ";
	           Recordset recordset=connection.executeQuery(strQuery);
		   //System.out.println(recordset.getCount());
                   
		while(recordset.next()){
			String inp=recordset.getField("Quantity");
			inp=inp.replaceAll("[-]","" );
			inp=inp.replaceAll("[,]", "");
			double b=Double.parseDouble(inp)/1000;
			dailyConsumptionModel model2=new dailyConsumptionModel(recordset.getField("Plant"),recordset.getField("Posting Date"),recordset.getField("Material"),b,z++);
                    list.add(model2);
		//System.out.println(recordset.getField("Plant")+"  "+recordset.getField("Posting") +" "+recordset.getField("Material")+" "+inp);
		}
	        Collections.sort(list);
		recordset.close();
		connection.close();
		
		
         for(dailyConsumptionModel p : list) {
              int index = mergedList.indexOf(p);
              if(index != -1) {
              mergedList.set(index, mergedList.get(index).merge(p));
           } else {
            mergedList.add(p);
       }
             
     }}
          public ArrayList<dailyConsumptionModel> display(){
              for (dailyConsumptionModel model2 : mergedList) {
                  double x=Math.ceil(model2.getDaily());
                  model2.setDaily_consumption(x);
                  //System.out.println(model2.getDaily_consumption());
                 // System.out.println(model2.getId());
             }
              
             return mergedList;
}
}
