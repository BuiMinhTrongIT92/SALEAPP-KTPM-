/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.pojo.HangHoa;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class DSHangHoa_DaXoaController implements Initializable {

    /**
     * @return the txtIDHangHoa
     */
    public Text getTxtIDHangHoa() {
        return txtIDHangHoa;
    }

    /**
     * @param txtIDHangHoa the txtIDHangHoa to set
     */
    public void setTxtIDHangHoa(Text txtIDHangHoa) {
        this.txtIDHangHoa = txtIDHangHoa;
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

    /**
     * @return the tbDSHangHoa_DaXoa
     */
    public TableView getTbDSHangHoa_DaXoa() {
        return tbDSHangHoa_DaXoa;
    }

    /**
     * @param tbDSHangHoa_DaXoa the tbDSHangHoa_DaXoa to set
     */
    public void setTbDSHangHoa_DaXoa(TableView tbDSHangHoa_DaXoa) {
        this.tbDSHangHoa_DaXoa = tbDSHangHoa_DaXoa;
    }

    /**
     * @return the cbLoaiHH_DaXoa
     */
    public ComboBox<LoaiHH> getCbLoaiHH_DaXoa() {
        return cbLoaiHH_DaXoa;
    }

    /**
     * @param cbLoaiHH_DaXoa the cbLoaiHH_DaXoa to set
     */
    public void setCbLoaiHH_DaXoa(ComboBox<LoaiHH> cbLoaiHH_DaXoa) {
        this.cbLoaiHH_DaXoa = cbLoaiHH_DaXoa;
    }

    /**
     * @return the btnCloseDSHH_DaXoa
     */
    public Button getBtnCloseDSHH_DaXoa() {
        return btnCloseDSHH_DaXoa;
    }

    /**
     * @param btnCloseDSHH_DaXoa the btnCloseDSHH_DaXoa to set
     */
    public void setBtnCloseDSHH_DaXoa(Button btnCloseDSHH_DaXoa) {
        this.btnCloseDSHH_DaXoa = btnCloseDSHH_DaXoa;
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
     * @return the cbLoaiHH
     */
    public ComboBox<LoaiHH> getCbLoaiHH() {
        return cbLoaiHH;
    }

    /**
     * @param cbLoaiHH the cbLoaiHH to set
     */
    public void setCbLoaiHH(ComboBox<LoaiHH> cbLoaiHH) {
        this.cbLoaiHH = cbLoaiHH;
    }
    
    @FXML
    private ComboBox<LoaiHH> cbLoaiHH_DaXoa;

    @FXML
    private Button btnCloseDSHH_DaXoa;

    private Stage stage;
    
    @FXML
    private TableView tbDSHangHoa_DaXoa;
    
    @FXML
    private Text txtIDHangHoa;

    @FXML
    private Text txtID;

    @FXML
    private ComboBox<LoaiHH> cbLoaiHH;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoaiHHService lhhs = new LoaiHHService();
        loadTableHangHoa();
        
        try {
            loadHangHoa(null, false);
        } catch (SQLException ex) {
            Logger.getLogger(DSHangHoa_DaXoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.cbLoaiHH_DaXoa.setItems(FXCollections.observableArrayList(lhhs.getLoaiHH(null, true)));
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadDataFromTableView();
    } 
    
    public void closeDSHH_DaXoa(ActionEvent event) {
        setStage((Stage) ((Button)event.getSource()).getScene().getWindow());
        getStage().close();
    }
    
    public void loadTableHangHoa() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("idHangHoa"));
        // Ten trong pojo
        colId.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 10 / 100);
        
        TableColumn colTenHangHoa = new TableColumn("Tên tên hàng hóa");
        colTenHangHoa.setCellValueFactory(new PropertyValueFactory("TenHangHoa"));
        colTenHangHoa.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 30 / 100);
        
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("Gia"));
        colGia.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 20 / 100);
        
        TableColumn colSoLuong = new TableColumn("Số lượng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("SL"));
        colSoLuong.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 10 / 100);
        
        TableColumn colKhoiLuong = new TableColumn("Khối lượng");
        colKhoiLuong.setCellValueFactory(new PropertyValueFactory("KG"));
        colKhoiLuong.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 10 / 100);
        
        TableColumn colLoaiHH = new TableColumn("Loại hàng hóa");
        colLoaiHH.setCellValueFactory(new PropertyValueFactory("IDLoaiHH"));
        colLoaiHH.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 10 / 100);
        
        TableColumn colXuatXu = new TableColumn("Xuất xứ");
        colXuatXu.setCellValueFactory(new PropertyValueFactory("XuatXu"));
        colXuatXu.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 10 / 100);
        
        TableColumn colAnh = new TableColumn("Ảnh");
        colAnh.setCellValueFactory(new PropertyValueFactory("AnhHH"));
        colAnh.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 10 / 100);
        
        TableColumn colActive = new TableColumn("Trạng thái");
        colActive.setCellValueFactory(new PropertyValueFactory("Active"));
        colActive.setPrefWidth(tbDSHangHoa_DaXoa.getPrefWidth() * 10 / 100);

        this.tbDSHangHoa_DaXoa.getColumns().addAll(colId, colTenHangHoa, colGia, 
                colSoLuong, colKhoiLuong, colLoaiHH, colXuatXu, colAnh, colActive);
    }
    
    public void loadHangHoa(String kwHH, Boolean Active) throws SQLException {
        HangHoaService hhs = new HangHoaService();
        this.tbDSHangHoa_DaXoa.setItems(FXCollections.observableList(hhs.getHangHoa(kwHH, Active)));     
    }
    
    public void xoaTextField() {
        txtIDHangHoa.setText("");
        txtID.setText("");  
    }
    
    public void xoaHangHoaHandler(ActionEvent event) throws SQLException {
        HangHoaService hhs = new HangHoaService();
        
        if (hhs.xoaHangHoa(txtID.getText()) > 0) {
            utils.utills.showBox("Xóa hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            xoaTextField();
            loadHangHoa(null, false);
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void loadDataFromTableView() {
        tbDSHangHoa_DaXoa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                HangHoa hh = (HangHoa) tbDSHangHoa_DaXoa.getItems().get(tbDSHangHoa_DaXoa.getSelectionModel().getSelectedIndex());
                txtIDHangHoa.setText("ID Hàng hóa: ");
                txtID.setText(hh.getIdHangHoa());  
            }
        });
    }
}
