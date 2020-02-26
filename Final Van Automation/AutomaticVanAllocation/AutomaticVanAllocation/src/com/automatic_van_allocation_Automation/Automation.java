package com.automatic_van_allocation_Automation;

import com.automatic_van_allocation_Jdbc.JDBCSingleton;
import java.util.ArrayList;
import java.util.Collections;
import com.automatic_van_allocation_crm.CRMData;
import com.automatic_van_allocation_crm.CRMDataModel;
import com.automatic_van_allocation_interface.Interface;
import com.automatic_van_allocation_vanMaster.VanMasterData;
import com.automatic_van_allocation_vanMaster.VanMasterModel;
import com.automatic_van_allocation_vanStatus.VanStatusData;
import com.automatic_van_allocation_vanStatus.VanStatusModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automation implements Interface<AutomationDataModel> {

	CRMData crmData;
	VanMasterData vanMasterData;
	VanStatusData vanStatusData;
	ArrayList<AutomationDataModel> automationList;
	ArrayList<CRMDataModel> crmList;
	ArrayList<VanMasterModel> masterList;
	ArrayList<VanStatusModel> statusList;
	   JDBCSingleton jDBCSingleton;
	 public Automation() {
		 crmData=new CRMData();
                 vanMasterData=new VanMasterData();
                 vanStatusData =new VanStatusData();
                 automationList =new ArrayList<>();
                 String excelPath1="C:\\Users\\KUNAL\\Desktop\\Van\\CRMData.csv";
		 String excelPath2="C:\\Users\\KUNAL\\Desktop\\Van\\VanMaster.csv";
		 String excelPath3="C:\\Users\\KUNAL\\Desktop\\Van\\Vanstatus.csv";
                 crmList=crmData.getData(excelPath1);
                 masterList=vanMasterData.getData(excelPath2);
                 statusList=vanStatusData.getData(excelPath3);
                  jDBCSingleton= JDBCSingleton.getInstance();
	}
	
	@Override
	public ArrayList<AutomationDataModel> getData(String ExcelPath) {
            try {
                // TODO Auto-generated method stub

                automationList =getResult();
            } catch (ParseException ex) {
                Logger.getLogger(Automation.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		return automationList;
	}
	 public void setData() {
  	   for (CRMDataModel listcrm : crmList.subList(1, crmList.size())) {
			System.out.println(listcrm.getIndent_id());
		}
  	   for (VanMasterModel listmaster : masterList.subList(1, masterList.size())) {
			System.out.println(listmaster.getMaterial()+"  "+listmaster.getVehno()+" "+listmaster.getCapacity());
		}
       for (VanStatusModel liststatus : statusList.subList(1, statusList.size())) {
		System.out.println(liststatus.getStatus()+" "+liststatus.getVehicle_no()+" "+liststatus.getCapacity());
     	}
     }
	
	public ArrayList<AutomationDataModel> getResult() throws ParseException {
		   for (CRMDataModel listcrm : crmList) {
			  // System.out.println(listcrm.getIndent_id());
                          SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat output=new SimpleDateFormat("M/dd/yyyy");
			//System.out.println(java.time.LocalDate.now());

			                    LocalDate da=LocalDate.now();
			String ab=da.toString();
			Date date=simpleDateFormat.parse(ab);
			String rd=output.format(date);
//			System.out.println(rd);
			String a=listcrm.getIndent_date();
			
		      if(a.equals(rd))
	  	   for (VanMasterModel listmaster : masterList) {
				if(listcrm.getPlant_id().equals(listmaster.getPlancode())&&listmaster.getMaterial().equalsIgnoreCase("Total Capacity (MT)")) {
			       for (VanStatusModel liststatus : statusList) {
					  
						if(listmaster.getVehno().equals(liststatus.getVehicle_no()) && liststatus.getStatus().equalsIgnoreCase("ok for dispatch")) {
	                                            //System.out.println(listcrm.getIndent_id()+"  "+liststatus.getVehicle_no());
	                                         int crm =listcrm.getQuantity();
	                                         int sts=liststatus.getCapacity();
	                                         if(!listcrm.isOrderDone())
	                                         if(sts>=crm){
	                                             System.out.println(liststatus.getCapacity());
	                                             liststatus.setStatus("Dispatched");
	                                             listcrm.setOrderDone(true);
                                                     
                                                 
//                                                      try {  
//                                int i= jDBCSingleton.update(listcrm.getIndent_id(), liststatus.getVehicle_no());  
//                                 if (i>0) {  
//                                System.out.println( " Data has been updated successfully");  
//                                    }  
//                          
//                        } catch (Exception e) {  
//                          System.out.println(e);  
//                        }  
                                                     
                                                     
                                                     
	                                             AutomationDataModel vanAutomaticAllocation=new AutomationDataModel();
	                                             vanAutomaticAllocation.setIndent_id(listcrm.getIndent_id());
	                                             vanAutomaticAllocation.setIndent_date(listcrm.getIndent_date());
	                                             vanAutomaticAllocation.setCustomer_id(listcrm.getCustomer_id());
	                                             vanAutomaticAllocation.setPlant(Integer.parseInt(listcrm.getPlant_id()));
	                                             vanAutomaticAllocation.setQuantity(listcrm.getQuantity());
	                                             vanAutomaticAllocation.setVan_no(liststatus.getVehicle_no());
	                                             automationList.add(vanAutomaticAllocation);
	                                         }
	                                        }
					  
			     	}
				}
			}
			}
		   return automationList;
		}

	  public void displayData(){
		  
                  Collections.sort(automationList);
		  
                  for (AutomationDataModel automationDataModel : automationList) {
			
                        String a=automationDataModel.getIndent_id();
                        String b=automationDataModel.getIndent_date();
                        String c=(automationDataModel.getCustomer_id());
                        int d=automationDataModel.getPlant();
                        int e=automationDataModel.getQuantity();
                        String f=automationDataModel.getVan_no();
              
                        System.out.println(a+"         "+b+"         "+d+"         "+e+"         "+f+"         "+c+"         ");
      } }
	
}
