/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import utils.utills;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class TrangQuanLy_ChinhController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void openDanhMucNhanVien(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DanhMucNhanVien.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDanhMucChiNhanh(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DanhMucChiNhanh.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDanhMucLoaiHH(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DanhMucLoaiHH.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDanhMucHangHoa(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DanhMucHangHoa.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
}
