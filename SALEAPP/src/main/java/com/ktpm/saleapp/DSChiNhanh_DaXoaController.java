/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.services.ChiNhanhService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DSChiNhanh_DaXoaController implements Initializable {

    /**
     * @param txtIDChiNhanh the txtIDChiNhanh to set
     */
    public void setTxtIDChiNhanh(Text txtIDChiNhanh) {
        this.txtIDChiNhanh = txtIDChiNhanh;
    }

    /**
     * @param txtID the txtID to set
     */
    public void setTxtID(Text txtID) {
        this.txtID = txtID;
    }

    /**
     * @param txtIDChiNhanh the txtIDChiNhanh to set
     */
    public void setTxtIDChiNhanh(TextField txtIDChiNhanh) {
        this.setTxtIDChiNhanh(txtIDChiNhanh);
    }

    /**
     * @param txtID the txtID to set
     */
    public void setTxtID(TextField txtID) {
        this.setTxtID(txtID);
    }

    /**
     * @return the tbDSChiNhanh_DaXoa
     */
    public TableView getTbDSChiNhanh_DaXoa() {
        return tbDSChiNhanh_DaXoa;
    }

    /**
     * @param tbDSChiNhanh_DaXoa the tbDSChiNhanh_DaXoa to set
     */
    public void setTbDSChiNhanh_DaXoa(TableView tbDSChiNhanh_DaXoa) {
        this.tbDSChiNhanh_DaXoa = tbDSChiNhanh_DaXoa;
    }

    /**
     * @return the btnCloseDSChiNhanh_DaXoa
     */
    public Button getBtnCloseDSChiNhanh_DaXoa() {
        return btnCloseDSChiNhanh_DaXoa;
    }

    /**
     * @param btnCloseDSChiNhanh_DaXoa the btnCloseDSChiNhanh_DaXoa to set
     */
    public void setBtnCloseDSChiNhanh_DaXoa(Button btnCloseDSChiNhanh_DaXoa) {
        this.btnCloseDSChiNhanh_DaXoa = btnCloseDSChiNhanh_DaXoa;
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
    private TableView tbDSChiNhanh_DaXoa;

    @FXML
    private Button btnCloseDSChiNhanh_DaXoa;

    private Stage stage;
    
    @FXML private Text txtIDChiNhanh;
    
    @FXML private Text txtID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableChiNhanh();
        
        try {
            loadDSCN(null, false);
        } catch (SQLException ex) {
            Logger.getLogger(DSChiNhanh_DaXoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadDataFromTableView();
    }    
    
    public void closeDSChiNhanh_DaXoa(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void loadTableChiNhanh() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("IDChiNhanh"));
        // Ten trong pojo
        colId.setPrefWidth(tbDSChiNhanh_DaXoa.getPrefWidth() * 20 / 100);
        
        TableColumn colTenChiNhanh = new TableColumn("Tên chi nhánh");
        colTenChiNhanh.setCellValueFactory(new PropertyValueFactory("TenChiNhanh"));
        colTenChiNhanh.setPrefWidth(tbDSChiNhanh_DaXoa.getPrefWidth() * 30 / 100);
        
        TableColumn colDiaChi = new TableColumn("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("DiaChi"));
        colDiaChi.setPrefWidth(tbDSChiNhanh_DaXoa.getPrefWidth() * 30 / 100);
        
        TableColumn colIDNguoiDung = new TableColumn("ID Người dùng");
        colIDNguoiDung.setCellValueFactory(new PropertyValueFactory("IDNguoiDung"));
        colIDNguoiDung.setPrefWidth(tbDSChiNhanh_DaXoa.getPrefWidth() * 20 / 100);
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("Active"));
        colTrangThai.setPrefWidth(tbDSChiNhanh_DaXoa.getPrefWidth() * 20 / 100);
        
        this.tbDSChiNhanh_DaXoa.getColumns().addAll(colId, colTenChiNhanh, colDiaChi, colIDNguoiDung, colTrangThai);
    }
    
    public void loadDSCN(String kwCN, Boolean active) throws SQLException {
        ChiNhanhService ns = new ChiNhanhService();
        this.tbDSChiNhanh_DaXoa.setItems(FXCollections.observableList(ns.getChiNhanh(kwCN, active)));       
    }
    
    public void xoaTextField() {
        txtIDChiNhanh.setText("");
        txtID.setText("");  
    }
    
    public void xoaChiNhanhHandler(ActionEvent event) throws SQLException {
        ChiNhanhService cns = new ChiNhanhService();
        
        if (cns.xoaChiNhanh(txtID.getText()) > 0) {
            utils.utills.showBox("Xóa chi nhánh thành công!", Alert.AlertType.INFORMATION).show();
            xoaTextField();
            loadDSCN(null, false);   
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void loadDataFromTableView() {
        tbDSChiNhanh_DaXoa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ChiNhanh cn = (ChiNhanh) tbDSChiNhanh_DaXoa.getItems().get(tbDSChiNhanh_DaXoa.getSelectionModel().getSelectedIndex());
                txtIDChiNhanh.setText("ID Chi nhánh: ");
                txtID.setText(cn.getIDChiNhanh());  
            }
        });
    }
}
