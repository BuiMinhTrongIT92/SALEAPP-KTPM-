/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.services.HangHoaService;
import com.ktpm.services.LoaiHHService;
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
public class DSLoaiHHController implements Initializable {

    /**
     * @return the tbLoaiHH
     */
    public TableView getTbLoaiHH() {
        return tbLoaiHH;
    }

    /**
     * @param tbLoaiHH the tbLoaiHH to set
     */
    public void setTbLoaiHH(TableView tbLoaiHH) {
        this.tbLoaiHH = tbLoaiHH;
    }

    /**
     * @return the btnCloseDSLoaiHH
     */
    public Button getBtnCloseDSLoaiHH() {
        return btnCloseDSLoaiHH;
    }

    /**
     * @param btnCloseDSLoaiHH the btnCloseDSLoaiHH to set
     */
    public void setBtnCloseDSLoaiHH(Button btnCloseDSLoaiHH) {
        this.btnCloseDSLoaiHH = btnCloseDSLoaiHH;
    }
    
    @FXML
    private TableView tbLoaiHH;
    
    @FXML
    private Button btnCloseDSLoaiHH;
    
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableLoaiHH();
        
        try {
            loadLoaiHH(null, true);
        } catch (SQLException ex) {
            Logger.getLogger(DSLoaiHHController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }    
    
    public void closeDSLoaiHH(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void loadTableLoaiHH() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("IDloaiHH"));
        // Ten trong pojo
        colId.setPrefWidth(tbLoaiHH.getPrefWidth() * 20 / 100);
        
        TableColumn colTenLoaiHH = new TableColumn("Tên loại hàng hóa");
        colTenLoaiHH.setCellValueFactory(new PropertyValueFactory("TenLoaiHH"));
        colTenLoaiHH.setPrefWidth(tbLoaiHH.getPrefWidth() * 50 / 100);
        
        TableColumn colDonVi = new TableColumn("Đơn vị");
        colDonVi.setCellValueFactory(new PropertyValueFactory("DonVi"));
        colDonVi.setPrefWidth(tbLoaiHH.getPrefWidth() * 30 / 100);
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("Active"));
        colTrangThai.setPrefWidth(tbLoaiHH.getPrefWidth() * 30 / 100);
        
        this.tbLoaiHH.getColumns().addAll(colId, colTenLoaiHH, colDonVi, colTrangThai);
    }
    
    public void loadLoaiHH(String kwLoaiHH, Boolean active) throws SQLException {
        LoaiHHService lhhs = new LoaiHHService();
        this.tbLoaiHH.setItems(FXCollections.observableList(lhhs.getLoaiHH()));
    }
}
