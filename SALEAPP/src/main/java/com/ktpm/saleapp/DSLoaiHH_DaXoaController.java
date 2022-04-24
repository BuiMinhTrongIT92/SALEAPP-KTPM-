/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.pojo.LoaiHH;
import com.ktpm.services.HangHoaService;
import com.ktpm.services.LoaiHHService;
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
public class DSLoaiHH_DaXoaController implements Initializable {

    /**
     * @return the tbLoaiHH_DaXoa
     */
    public TableView getTbLoaiHH_DaXoa() {
        return tbLoaiHH_DaXoa;
    }

    /**
     * @param tbLoaiHH_DaXoa the tbLoaiHH_DaXoa to set
     */
    public void setTbLoaiHH_DaXoa(TableView tbLoaiHH_DaXoa) {
        this.tbLoaiHH_DaXoa = tbLoaiHH_DaXoa;
    }

    /**
     * @return the btnCloseLoaiHH_DaXoa
     */
    public Button getBtnCloseLoaiHH_DaXoa() {
        return btnCloseLoaiHH_DaXoa;
    }

    /**
     * @param btnCloseLoaiHH_DaXoa the btnCloseLoaiHH_DaXoa to set
     */
    public void setBtnCloseLoaiHH_DaXoa(Button btnCloseLoaiHH_DaXoa) {
        this.btnCloseLoaiHH_DaXoa = btnCloseLoaiHH_DaXoa;
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
     * @return the txtIDLoaiHH
     */
    public Text getTxtIDLoaiHH() {
        return txtIDLoaiHH;
    }

    /**
     * @param txtIDLoaiHH the txtIDLoaiHH to set
     */
    public void setTxtIDLoaiHH(Text txtIDLoaiHH) {
        this.txtIDLoaiHH = txtIDLoaiHH;
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
    private TableView tbLoaiHH_DaXoa;

    @FXML
    private Button btnCloseLoaiHH_DaXoa;

    private Stage stage;

    @FXML
    private Text txtIDLoaiHH;

    @FXML
    private Text txtID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableLoaiHH();
        
        try {
            loadLoaiHH(null, false);
        } catch (SQLException ex) {
            Logger.getLogger(DSLoaiHH_DaXoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadDataFromTableView();
    }    
    
    public void closeLoaiHH_DaXoa(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void loadTableLoaiHH() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("IDloaiHH"));
        // Ten trong pojo
        colId.setPrefWidth(tbLoaiHH_DaXoa.getPrefWidth() * 20 / 100);
        
        TableColumn colTenLoaiHH = new TableColumn("Tên loại hàng hóa");
        colTenLoaiHH.setCellValueFactory(new PropertyValueFactory("TenLoaiHH"));
        colTenLoaiHH.setPrefWidth(tbLoaiHH_DaXoa.getPrefWidth() * 50 / 100);
        
        TableColumn colDonVi = new TableColumn("Đơn vị");
        colDonVi.setCellValueFactory(new PropertyValueFactory("DonVi"));
        colDonVi.setPrefWidth(tbLoaiHH_DaXoa.getPrefWidth() * 30 / 100);
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("Active"));
        colTrangThai.setPrefWidth(tbLoaiHH_DaXoa.getPrefWidth() * 30 / 100);
        
        this.tbLoaiHH_DaXoa.getColumns().addAll(colId, colTenLoaiHH, colDonVi, colTrangThai);
    }
    
    public void loadLoaiHH(String kwLoaiHH, Boolean active) throws SQLException {
        LoaiHHService lhhs = new LoaiHHService();
        this.tbLoaiHH_DaXoa.setItems(FXCollections.observableList(lhhs.getLoaiHH(kwLoaiHH, active)));
    }
    
    public void xoaTextField() {
        txtIDLoaiHH.setText("");
        txtID.setText("");  
    }
    
    public void xoaLoaiHHHandler(ActionEvent event) throws SQLException {
        LoaiHHService lhhs = new LoaiHHService();
        if (lhhs.xoaLoaiHH(txtID.getText()) > 0) {
            utils.utills.showBox("Xóa loại hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            xoaTextField();
            loadLoaiHH(null, false);
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void loadDataFromTableView() {
        tbLoaiHH_DaXoa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                LoaiHH lhh = (LoaiHH) tbLoaiHH_DaXoa.getItems().get(tbLoaiHH_DaXoa.getSelectionModel().getSelectedIndex());
                txtIDLoaiHH.setText("ID Loại hàng hóa: ");
                txtID.setText(lhh.getIDloaiHH());  
            }
        });
    } 
}
