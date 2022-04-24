/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.pojo.NhanVien;
import com.ktpm.services.ChiNhanhService;
import com.ktpm.services.NhanVienService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DSNhanVien_DaXoaController implements Initializable {

    /**
     * @return the tbDSNV_DaXoa
     */
    public TableView getTbDSNV_DaXoa() {
        return tbDSNV_DaXoa;
    }

    /**
     * @param tbDSNV_DaXoa the tbDSNV_DaXoa to set
     */
    public void setTbDSNV_DaXoa(TableView tbDSNV_DaXoa) {
        this.tbDSNV_DaXoa = tbDSNV_DaXoa;
    }

    /**
     * @return the btnCloseDSNV_DaXoa
     */
    public Button getBtnCloseDSNV_DaXoa() {
        return btnCloseDSNV_DaXoa;
    }

    /**
     * @param btnCloseDSNV_DaXoa the btnCloseDSNV_DaXoa to set
     */
    public void setBtnCloseDSNV_DaXoa(Button btnCloseDSNV_DaXoa) {
        this.btnCloseDSNV_DaXoa = btnCloseDSNV_DaXoa;
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

    /**
     * @return the txtIDNhanVien
     */
    public Text getTxtIDNhanVien() {
        return txtIDNhanVien;
    }

    /**
     * @param txtIDNhanVien the txtIDNhanVien to set
     */
    public void setTxtIDNhanVien(Text txtIDNhanVien) {
        this.txtIDNhanVien = txtIDNhanVien;
    }

    /**
     * @return the txtID
     */
    public Text getTxtID() {
        return txtID;
    }

    /**
     * @param txtID the txtID to set
     */
    public void setTxtID(Text txtID) {
        this.txtID = txtID;
    }
    
    @FXML
    private TableView tbDSNV_DaXoa;

    @FXML
    private Button btnCloseDSNV_DaXoa;

    private Stage stage;

    @FXML
    private Text txtIDNhanVien;

    @FXML
    private Text txtID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableNhanVien();
        
        try {
            loadDataNhanVien(null, false);
        } catch (SQLException ex) {
            Logger.getLogger(DSNhanVien_DaXoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadDataFromTableView();
    }    
    
    public void loadTableNhanVien() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("IDNguoiDung"));
        colId.setPrefWidth(50);
        
        TableColumn colTenNhanVien = new TableColumn("Tên nhân viên");
        colTenNhanVien.setCellValueFactory(new PropertyValueFactory("TenNguoiDung"));
        colTenNhanVien.setPrefWidth(90);
        
        TableColumn colGioiTinh = new TableColumn("Giới tính");
        colGioiTinh.setCellValueFactory(new PropertyValueFactory("GioiTinh"));
        colGioiTinh.setPrefWidth(60);
        
        TableColumn colNgaySinh = new TableColumn("Ngày sinh");
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("NgaySinh"));
        colNgaySinh.setPrefWidth(90);
        
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
        
        this.tbDSNV_DaXoa.getColumns().addAll(colId, colTenNhanVien, colGioiTinh, colNgaySinh, 
                colTaiKhoan, colTrangThai, colEmail, colNgayTao, colSDT, colRole);  
    }
    
    public void loadDataNhanVien(String kwNV, Boolean active) throws SQLException {
        NhanVienService ns = new NhanVienService();
        this.tbDSNV_DaXoa.setItems(FXCollections.observableList(ns.getNhanVien(kwNV, active)));
    }
    
    public void xoaText() {
        txtIDNhanVien.setText("");
        txtID.setText("");  
    }
    
    public void closeDSNV_DaXoa(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void xoaNhanVienHandler(ActionEvent event) throws SQLException {
        NhanVienService nvs = new NhanVienService();
         
        if (nvs.xoaNhanVien(txtID.getText()) > 0) {
            utils.utills.showBox("Xóa nhân viên thành công!", Alert.AlertType.INFORMATION).show();
            xoaText();
            loadDataNhanVien(null, false);
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void loadDataFromTableView() {
        tbDSNV_DaXoa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                NhanVien nv = (NhanVien) tbDSNV_DaXoa.getItems().get(tbDSNV_DaXoa.getSelectionModel().getSelectedIndex());
                txtIDNhanVien.setText("ID Nhân viên: ");
                txtID.setText(nv.getIDNguoiDung());  
            }
        });
    }
    
}
