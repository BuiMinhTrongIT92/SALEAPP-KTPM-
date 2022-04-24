/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.pojo.HangHoa;
import com.ktpm.pojo.LoaiHH;
import com.ktpm.pojo.NhanVien;
import com.ktpm.services.ChiNhanhService;
import com.ktpm.services.HangHoaService;
import com.ktpm.services.LoaiHHService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DanhMucHangHoaController implements Initializable {

    /**
     * @return the txtkwHH
     */
    public TextField getTxtkwHH() {
        return txtkwHH;
    }

    /**
     * @param txtkwHH the txtkwHH to set
     */
    public void setTxtkwHH(TextField txtkwHH) {
        this.txtkwHH = txtkwHH;
    }

    /**
     * @return the btnCloseDMHH
     */
    public Button getBtnCloseDMHH() {
        return btnCloseDMHH;
    }

    /**
     * @param btnCloseDMHH the btnCloseDMHH to set
     */
    public void setBtnCloseDMHH(Button btnCloseDMHH) {
        this.btnCloseDMHH = btnCloseDMHH;
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

    /**
     * @return the cbDonViTinh
     */
    public ComboBox getCbDonViTinh() {
        return cbDonViTinh;
    }

    /**
     * @param cbDonViTinh the cbDonViTinh to set
     */
    public void setCbDonViTinh(ComboBox cbDonViTinh) {
        this.cbDonViTinh = cbDonViTinh;
    }

    /**
     * @return the vbox
     */
    public VBox getVbox() {
        return vbox;
    }

    /**
     * @param vbox the vbox to set
     */
    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    /**
     * @return the fis
     */
    public FileInputStream getFis() {
        return fis;
    }

    /**
     * @param fis the fis to set
     */
    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    /**
     * @return the txtPathAnh
     */
    public TextField getTxtPathAnh() {
        return txtPathAnh;
    }

    /**
     * @param txtPathAnh the txtPathAnh to set
     */
    public void setTxtPathAnh(TextField txtPathAnh) {
        this.txtPathAnh = txtPathAnh;
    }

    /**
     * @return the txtAnh
     */
    public TextField getTxtAnh() {
        return txtAnh;
    }

    /**
     * @param txtAnh the txtAnh to set
     */
    public void setTxtAnh(TextField txtAnh) {
        this.txtAnh = txtAnh;
    }

    /**
     * @return the txtIDHangHoa
     */
    public TextField getTxtIDHangHoa() {
        return txtIDHangHoa;
    }

    /**
     * @param txtIDHangHoa the txtIDHangHoa to set
     */
    public void setTxtIDHangHoa(TextField txtIDHangHoa) {
        this.txtIDHangHoa = txtIDHangHoa;
    }

    /**
     * @return the txtTenHangHoa
     */
    public TextField getTxtTenHangHoa() {
        return txtTenHangHoa;
    }

    /**
     * @param txtTenHangHoa the txtTenHangHoa to set
     */
    public void setTxtTenHangHoa(TextField txtTenHangHoa) {
        this.txtTenHangHoa = txtTenHangHoa;
    }

    /**
     * @return the txtGia
     */
    public TextField getTxtGia() {
        return txtGia;
    }

    /**
     * @param txtGia the txtGia to set
     */
    public void setTxtGia(TextField txtGia) {
        this.txtGia = txtGia;
    }

    /**
     * @return the txtXuatXu
     */
    public TextField getTxtXuatXu() {
        return txtXuatXu;
    }

    /**
     * @param txtXuatXu the txtXuatXu to set
     */
    public void setTxtXuatXu(TextField txtXuatXu) {
        this.txtXuatXu = txtXuatXu;
    }

    /**
     * @return the txtSoLuong
     */
    public TextField getTxtSoLuong() {
        return txtSoLuong;
    }

    /**
     * @param txtSoLuong the txtSoLuong to set
     */
    public void setTxtSoLuong(TextField txtSoLuong) {
        this.txtSoLuong = txtSoLuong;
    }

    /**
     * @return the txtKhoiLuong
     */
    public TextField getTxtKhoiLuong() {
        return txtKhoiLuong;
    }

    /**
     * @param txtKhoiLuong the txtKhoiLuong to set
     */
    public void setTxtKhoiLuong(TextField txtKhoiLuong) {
        this.txtKhoiLuong = txtKhoiLuong;
    }

    /**
     * @return the themAnh
     */
    public Button getThemAnh() {
        return themAnh;
    }

    /**
     * @param themAnh the themAnh to set
     */
    public void setThemAnh(Button themAnh) {
        this.themAnh = themAnh;
    }

    /**
     * @return the imgAnh
     */
    public ImageView getImgAnh() {
        return imgAnh;
    }

    /**
     * @param imgAnh the imgAnh to set
     */
    public void setImgAnh(ImageView imgAnh) {
        this.imgAnh = imgAnh;
    }

    /**
     * @return the tbHangHoa
     */
    public TableView<HangHoa> getTbHangHoa() {
        return tbHangHoa;
    }

    /**
     * @param tbHangHoa the tbHangHoa to set
     */
    public void setTbHangHoa(TableView<HangHoa> tbHangHoa) {
        this.tbHangHoa = tbHangHoa;
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
    private ComboBox<LoaiHH> cbLoaiHH;
    
    @FXML
    private TableView<HangHoa> tbHangHoa;
    
    @FXML
    private TextField txtIDHangHoa;

    @FXML
    private TextField txtTenHangHoa;

    @FXML
    private TextField txtGia;

    @FXML
    private TextField txtXuatXu;

    @FXML
    private TextField txtSoLuong;

    @FXML
    private TextField txtKhoiLuong;
    
    @FXML
    private TextField txtAnh;

    @FXML
    private Button themAnh;

    @FXML
    private ImageView imgAnh;
    
    private Image img;
    
    @FXML
    private TextField txtPathAnh;
    
    private FileInputStream fis;
    
    @FXML
    private VBox vbox;
    
    @FXML private ComboBox cbDonViTinh;
    
    @FXML
    private Text txtIDLoaiHH;

    @FXML
    private Text txtID;
    
    
    @FXML
    private Button btnCloseDMHH;
    
    @FXML
    private TextField txtkwHH;

    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoaiHHService lhhs = new LoaiHHService();
        
        loadTableHangHoa();
        // TODO
        try {
            loadHangHoa(null, true);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.cbLoaiHH.setItems(FXCollections.observableArrayList(lhhs.getLoaiHH(null, true)));
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtkwHH.textProperty().addListener((evt) -> {
            try {
                loadHangHoa(this.txtkwHH.getText(), true);
            } catch (SQLException ex) {
                Logger.getLogger(DanhMucHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("Images", "*.png*", "*.jpg*", "*.gif*"),
                new FileChooser.ExtensionFilter("Text File", "*.txt*"));
        
        
        txtGia.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                    txtGia.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
//        txtSoLuong.textProperty().addListener(new ChangeListener<String>() {
//        @Override
//        public void changed(ObservableValue<? extends String> observable, String oldValue, 
//            String newValue) {
//            if (!newValue.matches("\\d*")) {
//                    txtSoLuong.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });
        
//        txtKhoiLuong.textProperty().addListener(new ChangeListener<String>() {
//        @Override
//        public void changed(ObservableValue<? extends String> observable, String oldValue, 
//            String newValue) {
//            if (!newValue.matches("\\d*")) {
//                    txtKhoiLuong.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });
//        
        loadDataFromTableView();     
    }
    
    public void openDSHH(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSHangHoa.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDSHH_DaXoa(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSHangHoa_DaXoa.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void loadTableHangHoa() {
        TableColumn colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("idHangHoa"));
        // Ten trong pojo
        colId.setPrefWidth(tbHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colTenHangHoa = new TableColumn("Tên tên hàng hóa");
        colTenHangHoa.setCellValueFactory(new PropertyValueFactory("TenHangHoa"));
        colTenHangHoa.setPrefWidth(tbHangHoa.getPrefWidth() * 30 / 100);
        
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("Gia"));
        colGia.setPrefWidth(tbHangHoa.getPrefWidth() * 20 / 100);
        
        TableColumn colSoLuong = new TableColumn("Số lượng");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("SL"));
        colSoLuong.setPrefWidth(tbHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colKhoiLuong = new TableColumn("Khối lượng");
        colKhoiLuong.setCellValueFactory(new PropertyValueFactory("KG"));
        colKhoiLuong.setPrefWidth(tbHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colLoaiHH = new TableColumn("Loại hàng hóa");
        colLoaiHH.setCellValueFactory(new PropertyValueFactory("IDLoaiHH"));
        colLoaiHH.setPrefWidth(tbHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colXuatXu = new TableColumn("Xuất xứ");
        colXuatXu.setCellValueFactory(new PropertyValueFactory("XuatXu"));
        colXuatXu.setPrefWidth(tbHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colAnh = new TableColumn("Ảnh");
        colAnh.setCellValueFactory(new PropertyValueFactory("AnhHH"));
        colAnh.setPrefWidth(tbHangHoa.getPrefWidth() * 10 / 100);
        
        TableColumn colActive = new TableColumn("Trạng thái");
        colActive.setCellValueFactory(new PropertyValueFactory("Active"));
        colActive.setPrefWidth(tbHangHoa.getPrefWidth() * 10 / 100);

        this.tbHangHoa.getColumns().addAll(colId, colTenHangHoa, colGia, 
                colSoLuong, colKhoiLuong, colLoaiHH, colXuatXu, colAnh, colActive);
    }
    
    public void loadHangHoa(String kwHH, Boolean Active) throws SQLException {
        HangHoaService hhs = new HangHoaService();
        this.tbHangHoa.setItems(FXCollections.observableList(hhs.getHangHoa(kwHH, Active)));     
    }
    
    public void xoa() {
        txtIDHangHoa.clear();
        txtTenHangHoa.clear();
        txtGia.clear();
        txtXuatXu.clear();
        txtSoLuong.clear();
        txtKhoiLuong.clear();
        cbLoaiHH.setPromptText("-- Lựa chọn --");
        txtPathAnh.clear();
        imgAnh.setImage(null);
    }
    
    public void luuAnhHH(ImageView imgAnh) {
        
        Image imgSave = imgAnh.getImage();
       // File outputFile = new File("/souresImage/" + txtPathAnh.getText().substring(0, txtPathAnh.getLength() - 4) + ".png");
         File outputFile = new File("D:/" + txtPathAnh.getText().substring(0, txtPathAnh.getLength() - 4) + ".png");
        
        try {
            ImageIO.write((SwingFXUtils.fromFXImage(imgSave, null)), ".png", outputFile);
        } catch (IOException ex) {
            Logger.getLogger(DanhMucHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public void closeDMHH(ActionEvent event) {
        setStage((Stage) ((Button)event.getSource()).getScene().getWindow());
        getStage().close();
    }
    
//    public void ganGiaTri() {
//        String s = cbDonViTinh.getSelectionModel().getSelectedItem().toString();
//        if (s.equals("Số lượng")) {
//            txtKhoiLuong.setText("0");
//            txtKhoiLuong.setEditable(false);
//        } else if (s.equals("Khối lượng")){
//            txtSoLuong.setText("0");
//            txtSoLuong.setEditable(false);
//        }
//    }
     
    public void themHangHoaHandler(ActionEvent event) {
        double gia = Double.parseDouble(txtGia.getText());
        double soLuong = Double.parseDouble(txtSoLuong.getText());
        double khoiLuong = Double.parseDouble(txtKhoiLuong.getText());
        
        HangHoa h = new HangHoa(txtIDHangHoa.getText(), txtTenHangHoa.getText(), 
                gia, txtXuatXu.getText(), 
                cbLoaiHH.getSelectionModel().getSelectedItem().getIDloaiHH(), 
                txtPathAnh.getText(), soLuong, khoiLuong, true);
        
        HangHoaService hhs = new HangHoaService();
        try {
            hhs.themHangHoa(h);
            luuAnhHH(imgAnh);
            utils.utills.showBox("Thêm hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            loadHangHoa(null, true);
            xoa();

        } catch (SQLException ex) {
            Logger.getLogger(DanhMucHangHoaController.class.getName()).log(Level.SEVERE, null, ex);
            utils.utills.showBox("Thêm không thành công!", Alert.AlertType.WARNING).show();
        }
    }
    
    public void capnhatHangHoaHandler(ActionEvent event) throws SQLException {
        double gia = Double.parseDouble(txtGia.getText());
        double soLuong = Double.parseDouble(txtSoLuong.getText());
        double khoiLuong = Double.parseDouble(txtKhoiLuong.getText());
        
        HangHoa h = new HangHoa(txtIDHangHoa.getText(), txtTenHangHoa.getText(), 
                gia, txtXuatXu.getText(), 
                cbLoaiHH.getSelectionModel().getSelectedItem().getIDloaiHH().toString(), 
                txtPathAnh.getText(), soLuong, khoiLuong, true);
        
        HangHoaService hhs = new HangHoaService();
        
        if (hhs.capNhatHangHoa(h) > 0) {
            utils.utills.showBox("Cập nhật hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            xoa();
            loadHangHoa(null, true);
        } else {
            utils.utills.showBox("Cập nhật không thành công!", Alert.AlertType.WARNING).show();
        }   
    }
    
    public void xoaHangHoa_TamThoi_Handler(ActionEvent event) throws SQLException {
        HangHoaService hhs = new HangHoaService();
        
        if (hhs.xoaHangHoa_TamThoi(txtIDHangHoa.getText()) > 0) {
            utils.utills.showBox("Xóa hàng hóa thành công!", Alert.AlertType.INFORMATION).show();
            xoa();
            loadHangHoa(null, true);
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }  
    }
    
    public void themAnhHandler(ActionEvent event) {
        Stage stage;
        stage = (Stage)vbox.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(stage);
        
        if (f != null) {
            img = new Image(f.getAbsoluteFile().toURI().toString());
            imgAnh.setImage(img);
            txtPathAnh.setText(f.getName().substring(0, f.getName().length() - 4));
        }   
    }
    
    public void loadDataFromTableView() {
        tbHangHoa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                HangHoa hh = tbHangHoa.getItems().get(tbHangHoa.getSelectionModel().getSelectedIndex());
                txtIDHangHoa.setText(hh.getIdHangHoa());
                txtTenHangHoa.setText(hh.getTenHangHoa());
                txtGia.setText(hh.getGia().toString());
                txtSoLuong.setText(hh.getSL().toString());
                txtKhoiLuong.setText(hh.getKG().toString());
                txtXuatXu.setText(hh.getXuatXu());
            }
        });
    }
    
    public void xoaONhapLieu(ActionEvent event) {
        xoa();
    }
}
