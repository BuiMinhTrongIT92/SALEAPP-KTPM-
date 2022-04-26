/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.services.ChiNhanhService;
import com.ktpm.services.NhanVienService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DanhMucChiNhanhController implements Initializable {

    /**
     * @return the btnXoaONhapLieu
     */
    public Button getBtnXoaONhapLieu() {
        return btnXoaONhapLieu;
    }

    /**
     * @param btnXoaONhapLieu the btnXoaONhapLieu to set
     */
    public void setBtnXoaONhapLieu(Button btnXoaONhapLieu) {
        this.btnXoaONhapLieu = btnXoaONhapLieu;
    }

    /**
     * @return the btnDeleteChiNhanh
     */
    public Button getBtnDeleteChiNhanh() {
        return btnDeleteChiNhanh;
    }

    /**
     * @param btnDeleteChiNhanh the btnDeleteChiNhanh to set
     */
    public void setBtnDeleteChiNhanh(Button btnDeleteChiNhanh) {
        this.btnDeleteChiNhanh = btnDeleteChiNhanh;
    }

    /**
     * @return the txtIDChiNhanh
     */
    public TextField getTxtIDChiNhanh() {
        return txtIDChiNhanh;
    }

    /**
     * @param txtIDChiNhanh the txtIDChiNhanh to set
     */
    public void setTxtIDChiNhanh(TextField txtIDChiNhanh) {
        this.txtIDChiNhanh = txtIDChiNhanh;
    }

    /**
     * @return the txtTenChiNhanh
     */
    public TextField getTxtTenChiNhanh() {
        return txtTenChiNhanh;
    }

    /**
     * @param txtTenChiNhanh the txtTenChiNhanh to set
     */
    public void setTxtTenChiNhanh(TextField txtTenChiNhanh) {
        this.txtTenChiNhanh = txtTenChiNhanh;
    }

    /**
     * @return the txtDiaChi
     */
    public TextField getTxtDiaChi() {
        return txtDiaChi;
    }

    /**
     * @param txtDiaChi the txtDiaChi to set
     */
    public void setTxtDiaChi(TextField txtDiaChi) {
        this.txtDiaChi = txtDiaChi;
    }

    /**
     * @return the btnCloseDMCN
     */
    public Button getBtnCloseDMCN() {
        return btnCloseDMCN;
    }

    /**
     * @param btnCloseDMCN the btnCloseDMCN to set
     */
    public void setBtnCloseDMCN(Button btnCloseDMCN) {
        this.btnCloseDMCN = btnCloseDMCN;
    }

    /**
     * @return the cbIDNguoiDung
     */
    public ComboBox getCbIDNguoiDung() {
        return cbIDNguoiDung;
    }

    /**
     * @param cbIDNguoiDung the cbIDNguoiDung to set
     */
    public void setCbIDNguoiDung(ComboBox cbIDNguoiDung) {
        this.cbIDNguoiDung = cbIDNguoiDung;
    }

    /**
     * @return the txtkwCN
     */
    public TextField getTxtkwCN() {
        return txtkwCN;
    }

    /**
     * @param txtkwCN the txtkwCN to set
     */
    public void setTxtkwCN(TextField txtkwCN) {
        this.txtkwCN = txtkwCN;
    }
    @FXML
    private TableView<ChiNhanh> tbChiNhanh;
    
    @FXML
    private TextField txtkwCN;
    // Nhap tu khoa de tim kiem
    
    @FXML
    private ComboBox cbIDNguoiDung;
    
    @FXML
    private Button btnCloseDMCN;
    
    Stage stage;
    
    @FXML
    private TextField txtIDChiNhanh;

    @FXML
    private TextField txtTenChiNhanh;

    @FXML
    private TextField txtDiaChi;
    
    @FXML
    private Button btnDeleteChiNhanh;
    
    @FXML
    private Button btnXoaONhapLieu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NhanVienService ns = new NhanVienService();
        loadTableChiNhanh();
        // Load cac cot trong TableView
        try {
            loadDSCN(null, Boolean.TRUE);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtkwCN.textProperty().addListener((evt) -> {
            try {
                loadDSCN(this.txtkwCN.getText(), true);
            } catch (SQLException ex) {
                Logger.getLogger(DanhMucChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        try {
            this.cbIDNguoiDung.setItems(FXCollections.observableList(ns.getNhanVien(null, true)));
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadDataToTableView(); 

        
    }

    public void closeDMCN(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void openDSCN(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSChiNhanh.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDSCN_DaXoa(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSChiNhanh_DaXoa.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void loadTableChiNhanh() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("IDChiNhanh"));
        // Ten trong pojo
        colId.setPrefWidth(tbChiNhanh.getPrefWidth() * 20 / 100);
        
        TableColumn colTenChiNhanh = new TableColumn("Tên chi nhánh");
        colTenChiNhanh.setCellValueFactory(new PropertyValueFactory("TenChiNhanh"));
        colTenChiNhanh.setPrefWidth(tbChiNhanh.getPrefWidth() * 30 / 100);
        
        TableColumn colDiaChi = new TableColumn("Địa chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory("DiaChi"));
        colDiaChi.setPrefWidth(tbChiNhanh.getPrefWidth() * 30 / 100);
        
        TableColumn colIDNguoiDung = new TableColumn("ID Người dùng");
        colIDNguoiDung.setCellValueFactory(new PropertyValueFactory("IDNguoiDung"));
        colIDNguoiDung.setPrefWidth(tbChiNhanh.getPrefWidth() * 20 / 100);
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("Active"));
        colTrangThai.setPrefWidth(tbChiNhanh.getPrefWidth() * 20 / 100);
        
        this.tbChiNhanh.getColumns().addAll(colId, colTenChiNhanh, colDiaChi, colIDNguoiDung, colTrangThai);
    }
    
    public void loadDSCN(String kwCN, Boolean active) throws SQLException {
        ChiNhanhService ns = new ChiNhanhService();
        this.tbChiNhanh.setItems(FXCollections.observableList(ns.getChiNhanh(kwCN, active)));       
    }
    
    public void themChiNhanhHandler(ActionEvent event) throws SQLException {
        if (txtIDChiNhanh.getText().length() != 0 && txtTenChiNhanh.getText().length() != 0 &&
                    txtDiaChi.getText().length() != 0 && cbIDNguoiDung.getSelectionModel().getSelectedItem() != null) {
                ChiNhanh c = new ChiNhanh(txtIDChiNhanh.getText(), 
                txtTenChiNhanh.getText(), txtDiaChi.getText(), 
                cbIDNguoiDung.getSelectionModel().getSelectedItem().toString(), 
                true);
            
            ChiNhanhService cns = new ChiNhanhService();
        
            try {
                    cns.themChiNhanh(c);
                    utils.utills.showBox("Thêm chi nhánh thành công!", Alert.AlertType.INFORMATION).show();
                    xoaTextField();
                    loadDSCN(null, true);
            } catch (SQLException ex) {
                utils.utills.showBox("Thêm không thành công!", Alert.AlertType.WARNING).show();
            }
        } else {
            utils.utills.showBox("Cần nhập đủ các trường thông tin!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void xoaTextField() {
        txtIDChiNhanh.clear();
        txtTenChiNhanh.clear();
        txtDiaChi.clear();
        cbIDNguoiDung.setValue(null);
    }
    
    public void xoaChiNhanhHandler(ActionEvent event) throws SQLException {
        if (txtIDChiNhanh.getText().length() != 0) {}
        ChiNhanhService cns = new ChiNhanhService();
        
        if (cns.xoaChiNhanh(txtIDChiNhanh.getText()) > 0) {
            utils.utills.showBox("Xóa chi nhánh thành công!", Alert.AlertType.INFORMATION).show();
            xoaTextField();
            loadDSCN(null, true);   
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void xoaChiNhanh_TamThoi_Handler(ActionEvent event) throws SQLException {
        if (txtIDChiNhanh.getText().length() != 0 && txtTenChiNhanh.getText().length() != 0 &&
                    txtDiaChi.getText().length() != 0 && cbIDNguoiDung.getSelectionModel().getSelectedItem() != null) {
            ChiNhanhService cns = new ChiNhanhService();
        
            if (cns.xoaChiNhanh_TamThoi(txtIDChiNhanh.getText()) > 0) {
                utils.utills.showBox("Xóa chi nhánh thành công!", Alert.AlertType.INFORMATION).show();
                xoaTextField();
                loadDSCN(null, true);      
            } else {
                utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
            }  
        } else {
            utils.utills.showBox("Cần chọn chi nhánh cần xóa!", Alert.AlertType.WARNING).show();
        }   
    }
    
    public void capnhatChiNhanhHandler(ActionEvent event) throws SQLException {
        if (txtIDChiNhanh.getText().length() != 0 && txtTenChiNhanh.getText().length() != 0 &&
                    txtDiaChi.getText().length() != 0 && cbIDNguoiDung.getSelectionModel().getSelectedItem() != null) {
            ChiNhanh c = new ChiNhanh(txtIDChiNhanh.getText(), 
                txtTenChiNhanh.getText(), txtDiaChi.getText(), 
                cbIDNguoiDung.getSelectionModel().getSelectedItem().toString(), 
                true);
            ChiNhanhService cns = new ChiNhanhService();

            if (cns.capNhatChiNhanh(c) > 0) {
                utils.utills.showBox("Cập nhật chi nhánh thành công!", Alert.AlertType.INFORMATION).show();
                xoaTextField();
                loadDSCN(null, true);  
            } else {
                utils.utills.showBox("Cập nhật không thành công!", Alert.AlertType.WARNING).show();
            }   
        } else {
            utils.utills.showBox("Cần chọn chi nhánh cần cập nhật!", Alert.AlertType.WARNING).show();
        }   
    }
    
    public void loadDataToTableView() {
        tbChiNhanh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ChiNhanh cn = tbChiNhanh.getItems().get(tbChiNhanh.getSelectionModel().getSelectedIndex());
                txtIDChiNhanh.setText(cn.getIDChiNhanh());
                txtTenChiNhanh.setText(cn.getTenChiNhanh());
                txtDiaChi.setText(cn.getDiaChi());
                cbIDNguoiDung.setValue(cn.getIDNguoiDung());
            }
        });
    }
    
    public void xoaONhapLieu(ActionEvent event) {
        if (txtIDChiNhanh.getText().length() == 0 && txtTenChiNhanh.getText().length() == 0 &&
                    txtDiaChi.getText().length() == 0 && cbIDNguoiDung.getSelectionModel().getSelectedItem() == null) {
            utils.utills.showBox("Các ô nhập liệu hiện đang trống!", Alert.AlertType.INFORMATION).show();
        } else {
            xoaTextField();
        }     
    }
}
