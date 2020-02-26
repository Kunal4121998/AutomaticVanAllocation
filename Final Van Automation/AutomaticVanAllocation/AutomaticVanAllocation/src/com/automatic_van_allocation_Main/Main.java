package com.automatic_van_allocation_Main;

import com.automatic_van_allocation_Automation.Automation;

import java.io.IOException;
import java.text.ParseException;

public class Main {
	
	public static void main(String[] args) throws IOException, ParseException {
		Automation automation=new Automation();
               
		automation.getData("done");
		automation.getResult();
		automation.displayData();
                automation.setData();
               //CsvWrite csvWrite=new CsvWrite();
               
        }

}