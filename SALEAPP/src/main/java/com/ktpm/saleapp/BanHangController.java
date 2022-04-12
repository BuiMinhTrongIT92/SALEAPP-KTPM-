/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.LoaiHH;
import com.ktpm.services.LoaiHHService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class BanHangController implements Initializable {
    @FXML private ComboBox<LoaiHH> CbLoaiHH;
    @FXML private GridPane GridItems;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoaiHHService loaiHHSV = new LoaiHHService();
        try {
            this.CbLoaiHH.setItems(FXCollections.observableArrayList(loaiHHSV.getLoaiHH()));
            GridItems.setAlignment(Pos.CENTER);
            GridItems.setVgap(20);
            GridItems.setHgap(130);
            GridItems.setPadding(new Insets(10,10,10,0));
            GridItems.setMinWidth(Region.USE_COMPUTED_SIZE);
            GridItems.setPrefWidth(Region.USE_COMPUTED_SIZE);
            GridItems.setMaxWidth(Region.USE_PREF_SIZE);
            
            GridItems.setMinHeight(Region.USE_COMPUTED_SIZE);
            GridItems.setPrefHeight(Region.USE_COMPUTED_SIZE);
            GridItems.setMaxHeight(Region.USE_PREF_SIZE);
            
            GridItems.setPadding(new Insets(10,10,10,10));
            GridItems.add(this.createItem(), 0, 0);
            GridItems.add(this.createItem(), 0, 1);
            GridItems.add(this.createItem(), 0, 2);
            GridItems.add(this.createItem(), 1, 0);
            GridItems.add(this.createItem(), 1, 1);
            GridItems.add(this.createItem(), 1, 2);
            GridItems.add(this.createItem(), 2, 0);
            GridItems.add(this.createItem(), 2, 1);
            GridItems.add(this.createItem(), 2, 2);
            GridItems.add(this.createItem(), 3, 0);
            GridItems.add(this.createItem(), 3, 1);
            
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Button createItem(){
       int SL = 20;
       Button item = new Button();
       ImageView imgv = new ImageView("/souresImage/locsua.jpg");
       Label lbslconlai = new Label("SL: " + SL);
       VBox info = new VBox();
       info.setAlignment(Pos.CENTER);
       info.getChildren().add(imgv);
       info.getChildren().add(lbslconlai);
       imgv.setFitHeight(80);
       imgv.setFitWidth(120);
       item.setGraphic(info);
       item.setPrefHeight(110);
       item.setPrefWidth(120);
       item.setLineSpacing(40);
       return item;
    }
    
    
}
