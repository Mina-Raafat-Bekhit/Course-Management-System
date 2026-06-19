/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Mega Store
 */
public class Final extends Application {
    
            private ObservableList <finalc> data ;
            TableView<finalc>  t ;
            Connection con = null ;
            PreparedStatement pst = null ;
            ResultSet rs = null ;
    
    
    @Override
    public void start(Stage primaryStage) {

        Label l1 = new Label("Add new course");
        Label l2 = new Label("ID");
        Label l3 = new Label("Course Name");
        Label l4 = new Label("Course Credit hour");    
        Label l5 = new Label("Professor Name");
        Label l6 = new Label("Number of Students");       
        
        TextField t2 = new TextField();
        TextField t3 = new TextField();
        TextField t4 = new TextField();
        TextField t5 = new TextField();
        TextField t6 = new TextField();
        
        Button add = new Button("Add");
        
        GridPane g1 = new GridPane();
        g1.add(l1, 0, 0, 2, 1);
        g1.add(l2, 0, 1);
        g1.add(l3, 0, 2);
        g1.add(l4, 0, 3);
        g1.add(l5, 0, 4);
        g1.add(l6, 0, 5);
        g1.add(t2, 1, 1);
        g1.add(t3, 1, 2);
        g1.add(t4, 1, 3);
        g1.add(t5, 1, 4);
        g1.add(t6, 1, 5);        
        g1.add(add, 2, 5);
        
        g1.setAlignment(Pos.CENTER);
        g1.setHgap(10);
        g1.setVgap(10);
        l1.getStyleClass().add("title");
        
        Label l21 = new Label("Update Course Number of students by ID");
        Label l22 = new Label("ID");
        Label l23 = new Label("No.of Students");
        
        TextField t22 = new TextField();
        TextField t23 = new TextField();
        
        Button update = new Button("Update");
        
        GridPane g2 = new GridPane();
        g2.add(l21, 0, 0, 2, 1);
        g2.add(l22, 0, 1);
        g2.add(l23, 0, 2);
        g2.add(t22, 1, 1);
        g2.add(t23, 1, 2);        
        g2.add(update, 2, 2);
        
        g2.setAlignment(Pos.CENTER);
        g2.setHgap(10);
        g2.setVgap(10);
        l21.getStyleClass().add("title");
        
        VBox v1 = new VBox(g1 , g2);
        v1.setSpacing(60);
        
        Label l31 = new Label("Delete Course by ID :");
        Label l32 = new Label("ID");
        
        TextField t32 = new TextField();
        
        Button delete = new Button("Delete");
        
        GridPane g3 = new GridPane();
        g3.add(l31, 0, 0, 2, 1);
        g3.add(l32, 0, 1);
        g3.add(t32, 1, 1);        
        g3.add(delete, 2, 1);
        
        g3.setHgap(10);
        g3.setVgap(10);
        l31.getStyleClass().add("title");
     
        
        
        Button total = new Button("Calculate Total of Students in all Course");
        Label l41 = new Label("0");
        
        GridPane g4 = new GridPane();
        g4.add(l41, 1, 0);      
        g4.add(total, 0, 0);
        
        g4.setHgap(10);
        g4.setVgap(10);
        
        
        
        Button maxh = new Button("Maximum Course Hour");
        Label l52 = new Label("0");
        
        GridPane g5 = new GridPane();
        g5.add(l52, 1, 1);      
        g5.add(maxh, 0, 1);
        
        g5.setHgap(10);
        g5.setVgap(10);
        
        VBox v2 = new VBox(g3 , g4 ,g5);
        v2.setSpacing(60);    
        
        t = new TableView<>();
        
        TableColumn<finalc , Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<finalc , String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<finalc , Integer> hours = new TableColumn<>("Hours");
        hours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        TableColumn<finalc , String> profn = new TableColumn<>("Prof Name");
        profn.setCellValueFactory(new PropertyValueFactory<>("profname"));
        TableColumn<finalc , Integer> noofst = new TableColumn<>("No of Students");
        noofst.setCellValueFactory(new PropertyValueFactory<>("noofstudent"));
        
        t.getColumns().addAll(id,name,hours,profn,noofst);
        
        show();
        
        HBox h = new HBox(v1 , v2 ,t);
        h.setAlignment(Pos.CENTER);
        h.setSpacing(20);
        
        add.setOnAction((value)->{
        
            int x = Integer.parseInt(t2.getText());
            int y = Integer.parseInt(t4.getText());
            int z = Integer.parseInt(t6.getText());
            String a = t3.getText();
            String b = t5.getText();
            
            t.getItems().addAll(new finalc(x, a, y, b, z));
            
            con = dbcon.DBConnection() ;
            
            String insert = "Insert into courses (id , name , hours , prof_name , no_of_student) Values(? , ? , ? , ? , ?)";
            
            try {
                pst = con.prepareStatement(insert);
                pst.setString(1, t2.getText()); 
                pst.setString(2, t3.getText()); 
                pst.setString(3, t4.getText());
                pst.setString(4, t5.getText());
                pst.setString(5, t6.getText());

               int i = pst.executeUpdate(); 
               
                if(i == 1){
                  
                  Alert a1 = new Alert(Alert.AlertType.INFORMATION,"Sucess");
                  a1.show();
                
                }
               con.close();
               pst.close();
              
            } catch (SQLException ex) {
                Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
            }

            show();
            
            
        });
          update.setOnAction((value)->{
        
           con = dbcon.DBConnection();
           String updte = "Update courses set no_of_student = ? where id = ?";
    
            try {
                pst = con.prepareStatement(updte);
                pst.setString(1, t23.getText()); 
                pst.setString(2, t22.getText()); 
                pst.executeUpdate(); 
                
                con.close();
                pst.close();
                
                show();
            } catch (SQLException ex) {
                Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
            }

 
        });
        delete.setOnAction((value)->{
        
          con = dbcon.DBConnection();
          String delet = "Delete from courses where id = ?" ;
          
            try {
                pst = con.prepareStatement(delet);
                pst.setString(1,t32.getText()); 
                pst.executeUpdate(); 
                pst.close(); 
                con.close();
                show();
              
            } catch (SQLException ex) {
                Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        total.setOnAction(e -> { 
            
            con = dbcon.DBConnection();
            String sql = "select sum (no_of_student) from courses";     
            
            try { 
                pst = con.prepareStatement(sql); 
                rs = pst.executeQuery(); 
                if (rs.next()) { 
                    l41.setText(Integer.toString(rs.getInt(1))); 
                } 
                pst.close(); 
                con.close(); 
 
            } catch (Exception ex) { 
                System.out.println(ex.toString()); 
            } 
 
        });
        maxh.setOnAction(e -> { 
            
            con = dbcon.DBConnection();
            String sql = "select max (HOURS) from courses";     
            
            try { 
                pst = con.prepareStatement(sql); 
                rs = pst.executeQuery(); 
                if (rs.next()) { 
                    l52.setText(Integer.toString(rs.getInt(1))); 
                } 
                pst.close(); 
                con.close(); 
 
            } catch (Exception ex) { 
                System.out.println(ex.toString()); 
            } 
 
        });
        
        Scene s1 = new Scene(h , 1200 , 500);
        s1.getStylesheets().add(this.getClass().getResource("css.css").toExternalForm());
        primaryStage.setScene(s1);
        primaryStage.show();
        
        
        
        
        
        
    }
    public void show(){
    
                
                    data = FXCollections.observableArrayList();
                    
                    con = dbcon.DBConnection() ;
                    
                    String select = "select * from courses ";
                    
                  try {
                    pst = con.prepareStatement(select);
                    
                      rs = pst.executeQuery(); 
                 while (rs.next()){
                 
                  data.add(new finalc(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
                 
                 }
                t.setItems(data);
                con.close();
                pst.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
                }
         
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
