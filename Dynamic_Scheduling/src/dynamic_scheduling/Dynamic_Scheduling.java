/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_scheduling;

import com.codoid.products.exception.FilloException;
import dynamic_scheduling_schedular.Scheduler;

/**
 *
 * @author fast
 */
public class Dynamic_Scheduling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NumberFormatException, FilloException {
        // TODO code application logic here
        Scheduler scheduler =new Scheduler();
        scheduler.Schedules();
    }
    
}
