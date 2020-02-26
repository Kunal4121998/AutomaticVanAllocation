package com.automatic_van_allocation_vanStatus;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.automatic_van_allocation_interface.Interface;
import java.util.Collections;

public class VanStatusData implements Interface<VanStatusModel>{

	@Override
	public ArrayList<VanStatusModel> getData(String excelPath3) {
		ArrayList<VanStatusModel> statusList=new ArrayList<>();
		
		File statusfile = new File(excelPath3); 
		List<String> lines2;
		try {
			lines2 = Files.readAllLines(statusfile.toPath(), StandardCharsets.UTF_8);
			for (String line : lines2.subList(1, lines2.size())) { 
				   String[] array = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				   VanStatusModel vanStatus=new VanStatusModel();
				   vanStatus.setStatus(array[1]);
				   vanStatus.setVehicle_no(array[2]);
                                    String s=array[4];
					 //System.out.println(s);
					 Double d=Double.parseDouble(s);
					 int x=(int) Math.floor(d);
					 //System.out.println(x);
				   vanStatus.setCapacity(x);
				   statusList.add(vanStatus);
		}
                        Collections.sort(statusList);
		}catch (IOException e) {
			System.out.println("VanStatus Error");
		} 
		
		
		return statusList;
	}
	

}