/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.services.NhanVienService;
import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.utills;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class LoginController implements Initializable {
    @FXML TextField username;
    @FXML TextField password;
    NhanVienController NVController = new NhanVienController();
    QuanLyController QLController = new QuanLyController();
    private static NhanVienService nvSV = new NhanVienService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
  
    public void openManage(ActionEvent event) throws IOException{
       if(username.getText() !=null & password.getText() !=null){
            try{
                if(QLController.LoginQL(username.getText(), password.getText()) == true){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuanLy.fxml"));
                Parent root = (Parent)fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setMaximized(true);
                stage.show();
                }else{
                    throw new Exception();
                }
            }catch(Exception ex){
                utills.showBox("Sai tên đăng nhập hoặc mật khẩu", Alert.AlertType.WARNING).show();  
            }
        }
    }
    public void openSale(ActionEvent event) throws SQLException, IOException{
            if(username.getText() !=null & password.getText() !=null){
                try{
                    if(NVController.LoginNVBH(username.getText(), password.getText()) == true){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BanHang.fxml"));
                    Parent root = (Parent)fxmlLoader.load();
                    BanHangController banhangcontrol = fxmlLoader.getController();
                    banhangcontrol.setIDNV(nvSV.findNVByUSPS(username.getText(), password.getText()).getIDNguoiDung());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setMaximized(true);
                    stage.show();
                    }else{
                        throw new Exception();
                    }
                }catch(Exception ex){
                    utills.showBox("Sai tên đăng nhập hoặc mật khẩu", Alert.AlertType.WARNING).show();  
             }
          }
        
    }
}
