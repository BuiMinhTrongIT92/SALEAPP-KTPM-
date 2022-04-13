/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.HangHoa;
import com.ktpm.pojo.LoaiHH;
import com.ktpm.services.LoaiHHService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import utils.utills;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class BanHangController implements Initializable {
    @FXML private ComboBox<LoaiHH> CbLoaiHH;
    @FXML private GridPane GridItems;
    @FXML private TextField count;
    private static final LoaiHHService loaihhSV = new LoaiHHService();
    private static List<HangHoa> hanghoa = new ArrayList<>();
    int column = 0;
    int row = 0;
    private static List<Button> removeHH = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoaiHHService loaiHHSV = new LoaiHHService();
        try {
            this.CbLoaiHH.setItems(FXCollections.observableArrayList(loaiHHSV.getLoaiHH()));
            GridItems.setAlignment(Pos.CENTER);
            GridItems.setPadding(new Insets(10,10,10,10));
            
            //width
            
            GridItems.setMinWidth(Region.USE_COMPUTED_SIZE);
            GridItems.setPrefWidth(Region.USE_COMPUTED_SIZE);
            GridItems.setMaxWidth(Region.USE_PREF_SIZE);
            //height
            GridItems.setMinHeight(Region.USE_COMPUTED_SIZE);
            GridItems.setPrefHeight(Region.USE_COMPUTED_SIZE);
            GridItems.setMaxHeight(Region.USE_PREF_SIZE);
            this.CbLoaiHH.setOnAction(event -> {
                column =0;
                row = 0;
                GridItems.setVgap(20);
                GridItems.setHgap(130);
                this.GridItems.getChildren().clear();
                String loaihh = CbLoaiHH.getSelectionModel().getSelectedItem().toString();
                this.GridItems.getAlignment();
                try {
                    hanghoa = loaiHHSV.getLoaiHHByLoai(loaihh);
                    count.setText(String.valueOf(hanghoa.size()));
                    for(int i =0;i < hanghoa.size();i++){
                        Button itemss = this.createItem(hanghoa.get(i).getTenHangHoa(), hanghoa.get(i).getAnhHH(),String.valueOf(loaihhSV.getSL(hanghoa.get(i).getTenHangHoa())));
                        if(column == 4){
                            column = 0;
                            row++;
                        }
                        GridItems.add(itemss, column++, row);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Button createItem(String id,String img,String SL ){
       
       Button item = new Button();
       item.setId(id);
       ImageView imgv = new ImageView("/souresImage/" + img + ".jpg");
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
