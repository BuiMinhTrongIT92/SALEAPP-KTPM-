/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.NhanVien;
import com.ktpm.services.NhanVienService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DSNVBHController implements Initializable {

    /**
     * @return the btnCloseNVBH
     */
    public Button getBtnCloseNVBH() {
        return btnCloseNVBH;
    }

    /**
     * @param btnCloseNVBH the btnCloseNVBH to set
     */
    public void setBtnCloseNVBH(Button btnCloseNVBH) {
        this.btnCloseNVBH = btnCloseNVBH;
    }

    /**
     * @return the txtkwDSNVBH
     */
    public TextField getTxtkwDSNVBH() {
        return txtkwDSNVBH;
    }

    /**
     * @param txtkwDSNVBH the txtkwDSNVBH to set
     */
    public void setTxtkwDSNVBH(TextField txtkwDSNVBH) {
        this.txtkwDSNVBH = txtkwDSNVBH;
    }
    
    @FXML
    private TableView<NhanVien> tbNVBH;
    
    @FXML
    ComboBox cbVaiTro;
    
    @FXML
    private TextField txtkwDSNVBH;
    
    @FXML
    private Button btnCloseNVBH;
    
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadTableNVBH();
        try {
            loadDataNVBH("NhanVien", null);
        } catch (SQLException ex) {
            Logger.getLogger(DSNVBHController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeDSNVBH(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void loadTableNVBH() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("IDNguoiDung"));
        colId.setPrefWidth(50);
        
        TableColumn colTenNhanVien = new TableColumn("Tên nhân viên");
        colTenNhanVien.setCellValueFactory(new PropertyValueFactory("TenNguoiDung"));
        colTenNhanVien.setPrefWidth(90);
        
        TableColumn colGioiTinh = new TableColumn("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory("GioiTinh"));
        colGioiTinh.setPrefWidth(60);
        
        TableColumn colTaiKhoan = new TableColumn("Tài khoản");
        colTaiKhoan.setCellValueFactory(new PropertyValueFactory("TaiKhoan"));
        colTaiKhoan.setPrefWidth(90);
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("Active"));
        colTrangThai.setPrefWidth(90);
        
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("Email"));
        colEmail.setPrefWidth(90);
        
        TableColumn colNgayTao = new TableColumn("Ngày tạo");
        colNgayTao.setCellValueFactory(new PropertyValueFactory("NgayTao"));
        colNgayTao.setPrefWidth(90);
        
        TableColumn colSDT = new TableColumn("Số điện thoại");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(90);
        
        TableColumn colRole = new TableColumn("Chức vụ");
        colRole.setCellValueFactory(new PropertyValueFactory("Role"));
        colRole.setPrefWidth(90);
        
        this.tbNVBH.getColumns().addAll(colId, colTenNhanVien, colGioiTinh, 
                colTaiKhoan, colTrangThai, colEmail, colNgayTao, colSDT, colRole); 
    }
    
    public void loadDataNVBH(String role, Boolean active) throws SQLException {
        NhanVienService ns = new NhanVienService();
        this.tbNVBH.setItems(FXCollections.observableList(ns.getDSNhanVien(role, active)));
    }
}
