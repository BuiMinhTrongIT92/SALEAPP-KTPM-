/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.LoaiHH;
import com.ktpm.services.ChiNhanhService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DSHangHoaController implements Initializable {
    @FXML
    private Button btnTatCa;
    /**
     * @return the btnTatCa
     */
    public Button getBtnTatCa() {
        return btnTatCa;
    }

    /**
     * @param btnTatCa the btnTatCa to set
     */
    public void setBtnTatCa(Button btnTatCa) {
        this.btnTatCa = btnTatCa;
    }

    /**
     * @return the tbDSHangHoa
     */
    public TableView getTbDSHangHoa() {
        return tbDSHangHoa;
    }

    /**
     * @param tbDSHangHoa the tbDSHangHoa to set
     */
    public void setTbDSHangHoa(TableView tbDSHangHoa) {
        this.tbDSHangHoa = tbDSHangHoa;
    }

    /**
     * @return the btnCloseDSHH
     */
    public Button getBtnCloseDSHH() {
        return btnCloseDSHH;
    }

    /**
     * @param btnCloseDSHH the btnCloseDSHH to set
     */
    public void setBtnCloseDSHH(Button btnCloseDSHH) {
        this.btnCloseDSHH = btnCloseDSHH;
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
    private TableView tbDSHangHoa;

    @FXML
    private Button btnCloseDSHH;
    
    @FXML
    private Button btnTatCa;

    private Stage stage;
    
    @FXML
    private ComboBox<LoaiHH> cbLoaiHH;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoaiHHService lhhs = new LoaiHHService();
        // TODO
        loadTableHangHoa();
        
        try {
            loadHangHoa(null, true);
        } catch (SQLException ex) {
            Logger.getLogger(DSHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.cbLoaiHH.setItems(FXCollections.observableArrayList(lhhs.getLoaiHH(null, true)));
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void closeDSHH(ActionEvent event) {
        setStage((Stage) ((Button)event.getSource()).getScene().getWindow());
        getStage().close();
    }
    
    public void loadTableHangHoa() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("idHangHoa"));
        // Ten trong pojo
        colId.setPrefWidth(tbDSHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colTenHangHoa = new TableColumn("Tên tên hàng hóa");
        colTenHangHoa.setCellValueFactory(new PropertyValueFactory("TenHangHoa"));
        colTenHangHoa.setPrefWidth(tbDSHangHoa.getPrefWidth() * 30 / 100);
        
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("Gia"));
        colGia.setPrefWidth(tbDSHangHoa.getPrefWidth() * 20 / 100);
        
        TableColumn colSoLuong = new TableColumn("Số lượng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("SL"));
        colSoLuong.setPrefWidth(tbDSHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colKhoiLuong = new TableColumn("Khối lượng");
        colKhoiLuong.setCellValueFactory(new PropertyValueFactory("KG"));
        colKhoiLuong.setPrefWidth(tbDSHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colLoaiHH = new TableColumn("Loại hàng hóa");
        colLoaiHH.setCellValueFactory(new PropertyValueFactory("IDLoaiHH"));
        colLoaiHH.setPrefWidth(tbDSHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colXuatXu = new TableColumn("Xuất xứ");
        colXuatXu.setCellValueFactory(new PropertyValueFactory("XuatXu"));
        colXuatXu.setPrefWidth(tbDSHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colAnh = new TableColumn("Ảnh");
        colAnh.setCellValueFactory(new PropertyValueFactory("AnhHH"));
        colAnh.setPrefWidth(tbDSHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colActive = new TableColumn("Trạng thái");
        colActive.setCellValueFactory(new PropertyValueFactory("Active"));
        colActive.setPrefWidth(tbDSHangHoa.getPrefWidth() * 10 / 100);

        this.tbDSHangHoa.getColumns().addAll(colId, colTenHangHoa, colGia, 
                colSoLuong, colKhoiLuong, colLoaiHH, colXuatXu, colAnh, colActive);
    }
    
    public void loadHangHoa(String kwHH, Boolean Active) throws SQLException {
        HangHoaService hhs = new HangHoaService();
        this.tbDSHangHoa.setItems(FXCollections.observableList(hhs.getHangHoa(kwHH, Active)));     
    }
    public void loadHHTheoLoai(String idHangHoa) throws SQLException {
        HangHoaService hhs = new HangHoaService();
        this.tbDSHangHoa.setItems(FXCollections.observableList(hhs.getHHTHeoLoai(idHangHoa)));     
    }
    
<<<<<<< HEAD
=======
    public void loadHHTheoLoai(String idHangHoa) throws SQLException {
        HangHoaService hhs = new HangHoaService();
        this.tbDSHangHoa.setItems(FXCollections.observableList(hhs.getHHTHeoLoai(idHangHoa)));     
    }
    
>>>>>>> e8967775d65aafbc1e619615a5047e12d0665059
    public void loadHHTheoLoai(ActionEvent event) throws SQLException {
        HangHoaService hhs = new HangHoaService();   
        String s = cbLoaiHH.getSelectionModel().getSelectedItem().getIDloaiHH();     
        loadHHTheoLoai(s);
    }
    
    public void loadTatCaHH(ActionEvent event) throws SQLException {
<<<<<<< HEAD
        
        loadHangHoa(null, true);    
    }

    /**
     * @return the btnTatCa
     */
    public Button getBtnTatCa() {
        return btnTatCa;
    }

    /**
     * @param btnTatCa the btnTatCa to set
     */
    public void setBtnTatCa(Button btnTatCa) {
        this.btnTatCa = btnTatCa;
    }
=======
        loadTableHangHoa();
        loadHangHoa(null, true);    
    }
>>>>>>> e8967775d65aafbc1e619615a5047e12d0665059
}
