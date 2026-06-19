/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import java.sql.*;

/**
 *
 * @author Mega Store
 */
public class dbcon {
    
    public static Connection DBConnection(){ 
       Connection con=null;  
    try{ 
       con= DriverManager.getConnection      
        ("jdbc:oracle:thin:DB207/123@localhost:1521/XE");  
       System.out.println("success"); 
    }catch(Exception ex){ 
        System.out.println(ex.toString()); 
        System.out.println("fail to connect"); 
    } 
    return con; 
} 
    
}
