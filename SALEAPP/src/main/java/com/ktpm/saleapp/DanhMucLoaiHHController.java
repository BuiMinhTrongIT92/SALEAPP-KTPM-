/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.pojo.LoaiHH;
import com.ktpm.services.ChiNhanhService;
import com.ktpm.services.LoaiHHService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class DanhMucLoaiHHController implements Initializable {

    /**
     * @return the btnCloseLoaiHH
     */
    public Button getBtnCloseLoaiHH() {
        return btnCloseLoaiHH;
    }

    /**
     * @param btnCloseLoaiHH the btnCloseLoaiHH to set
     */
    public void setBtnCloseLoaiHH(Button btnCloseLoaiHH) {
        this.btnCloseLoaiHH = btnCloseLoaiHH;
    }

    /**
     * @return the txtkwLoaiHH
     */
    public TextField getTxtkwLoaiHH() {
        return txtkwLoaiHH;
    }

    /**
     * @param txtkwLoaiHH the txtkwLoaiHH to set
     */
    public void setTxtkwLoaiHH(TextField txtkwLoaiHH) {
        this.txtkwLoaiHH = txtkwLoaiHH;
    }

    /**
     * @return the txtIDLoaiHH
     */
    public TextField getTxtIDLoaiHH() {
        return txtIDLoaiHH;
    }

    /**
     * @param txtIDLoaiHH the txtIDLoaiHH to set
     */
    public void setTxtIDLoaiHH(TextField txtIDLoaiHH) {
        this.txtIDLoaiHH = txtIDLoaiHH;
    }

    /**
     * @return the txtTenLoaiHH
     */
    public TextField getTxtTenLoaiHH() {
        return txtTenLoaiHH;
    }

    /**
     * @param txtTenLoaiHH the txtTenLoaiHH to set
     */
    public void setTxtTenLoaiHH(TextField txtTenLoaiHH) {
        this.txtTenLoaiHH = txtTenLoaiHH;
    }

    /**
     * @return the txtDonVi
     */
    public TextField getTxtDonVi() {
        return txtDonVi;
    }

    /**
     * @param txtDonVi the txtDonVi to set
     */
    public void setTxtDonVi(TextField txtDonVi) {
        this.txtDonVi = txtDonVi;
    }

    /**
     * @return the tbLoaiHH
     */
    public TableView<LoaiHH> getTbLoaiHH() {
        return tbLoaiHH;
    }

    /**
     * @param tbLoaiHH the tbLoaiHH to set
     */
    public void setTbLoaiHH(TableView<LoaiHH> tbLoaiHH) {
        this.tbLoaiHH = tbLoaiHH;
    }
    @FXML
    private TableView<LoaiHH> tbLoaiHH;
    
    @FXML
    private TextField txtIDLoaiHH;

    @FXML
    private TextField txtTenLoaiHH;

    @FXML
    private TextField txtDonVi;
    
    @FXML
    private TextField txtkwLoaiHH;
    
    @FXML
    private Button btnCloseLoaiHH;
    
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
            Logger.getLogger(DanhMucLoaiHHController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtkwLoaiHH.textProperty().addListener((evt) -> {
            try {
                loadLoaiHH(this.txtkwLoaiHH.getText(), true);
            } catch (SQLException ex) {
                Logger.getLogger(DanhMucLoaiHHController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        loadDataToTextField();
    }
    
    public void openDSLoaiHH(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSLoaiHH.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDSLoaiHH_DaXoa(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSLoaiHH_DaXoa.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void closeLoaiHH(ActionEvent event) {
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
    
//    public void loadLoaiHH() throws SQLException {
//        LoaiHHService ls = new LoaiHHService();
//        this.tbLoaiHH.setItems(FXCollections.observableList(ls.getLoaiHH()));        
//    }
    
    public void themLoaiHHHandler(ActionEvent event) throws SQLException {
        LoaiHH lhh = new LoaiHH(txtIDLoaiHH.getText(), txtTenLoaiHH.getText(), txtDonVi.getText(), true);
        LoaiHHService lhhs = new LoaiHHService();
        
        try {
            lhhs.themLoaiHH(lhh);
            utils.utills.showBox("Thêm loại hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            loadLoaiHH(null, true);
        } catch (SQLException ex) {
            utils.utills.showBox("Thêm không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void xoaTextField() {
        txtIDLoaiHH.clear();
        txtTenLoaiHH.clear();
        txtDonVi.clear();
    }
    
    public void xoaLoaiHHHandler(ActionEvent event) throws SQLException {
        LoaiHHService lhhs = new LoaiHHService();
        
        if (lhhs.xoaLoaiHH(txtIDLoaiHH.getText()) > 0) {
            utils.utills.showBox("Xóa loại hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            xoaTextField();
            loadLoaiHH(null, true);
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void capNhatLoaiHHHandler(ActionEvent event) throws SQLException {
        LoaiHH lhh = new LoaiHH(txtIDLoaiHH.getText(), txtTenLoaiHH.getText(), txtDonVi.getText(), true);
        LoaiHHService lhhs = new LoaiHHService();
        
        if (lhhs.capNhatLoaiHH(lhh) > 0) {
            utils.utills.showBox("Cập nhật loại hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            xoaTextField();
            loadLoaiHH(null, true);
        } else {
            utils.utills.showBox("Cập nhật không thành công!", Alert.AlertType.WARNING).show();
        }   
    }
    
    public void xoaLoaiHH_TamThoi_Handler(ActionEvent event) throws SQLException {
        LoaiHHService lhhs = new LoaiHHService();
        
        if (lhhs.xoaLoaiHH_TamThoi(txtIDLoaiHH.getText()) > 0) {
            utils.utills.showBox("Xóa loại hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            xoaTextField();
            loadLoaiHH(null, true);
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }  
    }
    
    public void loadDataToTextField() {
        tbLoaiHH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                LoaiHH lhh = tbLoaiHH.getItems().get(tbLoaiHH.getSelectionModel().getSelectedIndex());
                txtIDLoaiHH.setText(lhh.getIDloaiHH());
                txtTenLoaiHH.setText(lhh.getTenLoaiHH());
                txtDonVi.setText(lhh.getDonVi());
            }
        });
    }
    
    public void xoaONhapLieu(ActionEvent event) {
        xoaTextField();
    }
 }
