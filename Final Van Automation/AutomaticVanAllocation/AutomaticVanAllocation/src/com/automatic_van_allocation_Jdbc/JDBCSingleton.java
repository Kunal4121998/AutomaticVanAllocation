/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatic_van_allocation_Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSingleton {

	  private static JDBCSingleton jdbc;  
      
	     //JDBCSingleton prevents the instantiation from any other class.  
	       private JDBCSingleton() {  }  
	          
	         public static JDBCSingleton getInstance() {    
	                                     if (jdbc==null)  
	                                   {  
	                                            jdbc=new  JDBCSingleton();  
	                                   }  
	                         return jdbc;  
	             }  
	            
	   // to get the connection from methods like insert, view etc.   
	          private static Connection getConnection()throws ClassNotFoundException, SQLException  
	          {  
	                
	              Connection con=null;  
	              Class.forName("org.postgresql.Driver");  
	              con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Solar","postgres", "solar123");  
	              return con;  
}
	          
	          
	          public int update(String Indent_id,String Van_no) throws SQLException  {  
	              Connection c=null;  
	              PreparedStatement ps=null;  
	                String st="update \"CRM Data\" set \"VAN_NO\"=? where \"INDENT_ID\"=?";
	             int recordCounter = 0;
	              try {  
	                      c=this.getConnection();  
	                      ps=c.prepareStatement(st);  
	                      ps.setString(1,Van_no);
                              ps.setString(2, Indent_id);
	                      recordCounter=ps.executeUpdate();
                              if (recordCounter > 0) {
                              System.out.println("An existing user was updated successfully!");
                              }

	              } catch (Exception e) {  e.printStackTrace(); } finally{  
	                      
	                  if (ps!=null){  
	                      ps.close();  
	                  }if(c!=null){  
	                      c.close();  
	                  }   
	               }  
	             return recordCounter;  
	          }  
	          
	          
	          public  void view(int name) throws SQLException  
	          {  
	                    Connection con = null;  
	            PreparedStatement ps = null;  
	            ResultSet rs = null;
                     String st="update \"CRM Data\" set \"VAN_NO\"=? where \"INDENT_ID\"=?";
	                     // select * from \"VanStatus\" where \"SNo.\"=?
	                    try {  
	                          
                                   String tb="CRM Data";
	                            con=this.getConnection();  
	                            ps=con.prepareStatement(st);  
	                            ps.setInt(1, name);  
	                            rs=ps.executeQuery();  
	                            while (rs.next()) {  
	                                            
	                                       System.out.println(rs.getInt(1));
	                                        
	                            }  
	                    
	              } catch (Exception e) { System.out.println(e);}  
	              finally{  
	                        if(rs!=null){  
	                            rs.close();  
	                        }if (ps!=null){  
	                          ps.close();  
	                      }if(con!=null){  
	                          con.close();  
	                      }   
	                    }  
	          }  
	          
                  
                  
	          }

