/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.services.ChiNhanhService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DSChiNhanhController implements Initializable {

    /**
     * @return the tbDSChiNhanh
     */
    public TableView getTbDSChiNhanh() {
        return tbDSChiNhanh;
    }

    /**
     * @param tbDSChiNhanh the tbDSChiNhanh to set
     */
    public void setTbDSChiNhanh(TableView tbDSChiNhanh) {
        this.tbDSChiNhanh = tbDSChiNhanh;
    }

    /**
     * @return the btnCloseDSChiNhanh
     */
    public Button getBtnCloseDSChiNhanh() {
        return btnCloseDSChiNhanh;
    }

    /**
     * @param btnCloseDSChiNhanh the btnCloseDSChiNhanh to set
     */
    public void setBtnCloseDSChiNhanh(Button btnCloseDSChiNhanh) {
        this.btnCloseDSChiNhanh = btnCloseDSChiNhanh;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private TableView tbDSChiNhanh;
    
    @FXML
    private Button btnCloseDSChiNhanh;
    
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableChiNhanh();
        
        try {
            loadDSCN(null, true);
        } catch (SQLException ex) {
            Logger.getLogger(DSChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void closeDSChiNhanh(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void loadTableChiNhanh() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("IDChiNhanh"));
        // Ten trong pojo
        colId.setPrefWidth(tbDSChiNhanh.getPrefWidth() * 20 / 100);
        
        TableColumn colTenChiNhanh = new TableColumn("Tên chi nhánh");
        colTenChiNhanh.setCellValueFactory(new PropertyValueFactory("TenChiNhanh"));
        colTenChiNhanh.setPrefWidth(tbDSChiNhanh.getPrefWidth() * 30 / 100);
        
        TableColumn colDiaChi = new TableColumn("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("DiaChi"));
        colDiaChi.setPrefWidth(tbDSChiNhanh.getPrefWidth() * 30 / 100);
        
        TableColumn colIDNguoiDung = new TableColumn("ID Người dùng");
        colIDNguoiDung.setCellValueFactory(new PropertyValueFactory("IDNguoiDung"));
        colIDNguoiDung.setPrefWidth(tbDSChiNhanh.getPrefWidth() * 20 / 100);
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("Active"));
        colTrangThai.setPrefWidth(tbDSChiNhanh.getPrefWidth() * 20 / 100);
        
        this.tbDSChiNhanh.getColumns().addAll(colId, colTenChiNhanh, colDiaChi, colIDNguoiDung, colTrangThai);
    }
    
    public void loadDSCN(String kwCN, Boolean active) throws SQLException {
        ChiNhanhService ns = new ChiNhanhService();
        this.tbDSChiNhanh.setItems(FXCollections.observableList(ns.getChiNhanh(kwCN, active)));       
    }
}
