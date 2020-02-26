package com.automatic_van_allocation_crm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.automatic_van_allocation_interface.Interface;

public class CRMData implements Interface<CRMDataModel>{

	@Override
	public ArrayList<CRMDataModel> getData(String excelPath1) {
		ArrayList<CRMDataModel> crmList=new ArrayList<>();
		File crmfile = new File(excelPath1); 
		List<String> lines;
		try {
			lines = Files.readAllLines(crmfile.toPath(), StandardCharsets.UTF_8);
			for (String line : lines.subList(1, lines.size())) { 
				   String[] array = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				   CRMDataModel crmData=new CRMDataModel();
				   crmData.setIndent_id(array[0]);
				   crmData.setIndent_date(array[2]);
				   crmData.setCustomer_id(array[3]);
				   crmData.setPlant_id(array[14]);
                                   int a=Integer.parseInt(array[33]);
				   crmData.setQuantity(a);
                                   crmData.setOrderDone(false);
				   crmList.add(crmData); 
		}
                        Collections.sort(crmList);   
		}catch (IOException e) {
                   System.out.println("Error CrmData");
		} 
		
		
		return crmList;
	}

	
}