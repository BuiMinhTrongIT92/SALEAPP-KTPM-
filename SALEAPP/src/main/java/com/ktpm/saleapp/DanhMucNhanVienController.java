/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.pojo.NhanVien;
import com.ktpm.services.ChiNhanhService;
import com.ktpm.services.NhanVienService;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.DateCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author NhatTien
 */
public class DanhMucNhanVienController implements Initializable {

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
     * @return the vBox
     */
    public VBox getvBox() {
        return vBox;
    }

    /**
     * @param vBox the vBox to set
     */
    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    /**
     * @return the txtNV
     */
    public Text getTxtNV() {
        return txtNV;
    }

    /**
     * @param txtNV the txtNV to set
     */
    public void setTxtNV(Text txtNV) {
        this.txtNV = txtNV;
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
     * @return the dpNgaySinh
     */
    public DatePicker getDpNgaySinh() {
        return dpNgaySinh;
    }

    /**
     * @param dpNgaySinh the dpNgaySinh to set
     */
    public void setDpNgaySinh(DatePicker dpNgaySinh) {
        this.dpNgaySinh = dpNgaySinh;
    }

    /**
     * @return the txtIDNguoiDung
     */
    public TextField getTxtIDNguoiDung() {
        return txtIDNguoiDung;
    }

    /**
     * @param txtIDNguoiDung the txtIDNguoiDung to set
     */
    public void setTxtIDNguoiDung(TextField txtIDNguoiDung) {
        this.txtIDNguoiDung = txtIDNguoiDung;
    }

    /**
     * @return the btnCloseDMNV
     */
    public Button getBtnCloseDMNV() {
        return btnCloseDMNV;
    }

    /**
     * @param btnCloseDMNV the btnCloseDMNV to set
     */
    public void setBtnCloseDMNV(Button btnCloseDMNV) {
        this.btnCloseDMNV = btnCloseDMNV;
    }

    /**
     * @return the txtkwNV
     */
    public TextField getTxtkwNV() {
        return txtkwNV;
    }

    /**
     * @param txtkwNV the txtkwNV to set
     */
    public void setTxtkwNV(TextField txtkwNV) {
        this.txtkwNV = txtkwNV;
    }

    /**
     * @return the txtTenNhanVien
     */
    public TextField getTxtTenNhanVien() {
        return txtTenNhanVien;
    }

    /**
     * @param txtTenNhanVien the txtTenNhanVien to set
     */
    public void setTxtTenNhanVien(TextField txtTenNhanVien) {
        this.txtTenNhanVien = txtTenNhanVien;
    }

    /**
     * @return the txtEmail
     */
    public TextField getTxtEmail() {
        return txtEmail;
    }

    /**
     * @param txtEmail the txtEmail to set
     */
    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @return the txtSDT
     */
    public TextField getTxtSDT() {
        return txtSDT;
    }

    /**
     * @param txtSDT the txtSDT to set
     */
    public void setTxtSDT(TextField txtSDT) {
        this.txtSDT = txtSDT;
    }

    /**
     * @return the cbVaiTro
     */
    public ComboBox getCbVaiTro() {
        return cbVaiTro;
    }

    /**
     * @param cbVaiTro the cbVaiTro to set
     */
    public void setCbVaiTro(ComboBox cbVaiTro) {
        this.cbVaiTro = cbVaiTro;
    }

    /**
     * @return the txtTaiKhoan
     */
    public TextField getTxtTaiKhoan() {
        return txtTaiKhoan;
    }

    /**
     * @param txtTaiKhoan the txtTaiKhoan to set
     */
    public void setTxtTaiKhoan(TextField txtTaiKhoan) {
        this.txtTaiKhoan = txtTaiKhoan;
    }

    /**
     * @return the txtMatKhau
     */
    public TextField getTxtMatKhau() {
        return txtMatKhau;
    }

    /**
     * @param txtMatKhau the txtMatKhau to set
     */
    public void setTxtMatKhau(TextField txtMatKhau) {
        this.txtMatKhau = txtMatKhau;
    }

    /**
     * @return the cbTrangThai
     */
    public CheckBox getCbTrangThai() {
        return cbTrangThai;
    }

    /**
     * @param cbTrangThai the cbTrangThai to set
     */
    public void setCbTrangThai(CheckBox cbTrangThai) {
        this.cbTrangThai = cbTrangThai;
    }

    /**
     * @return the dpNgayTao
     */
    public DatePicker getDpNgayTao() {
        return dpNgayTao;
    }

    /**
     * @param dpNgayTao the dpNgayTao to set
     */
    public void setDpNgayTao(DatePicker dpNgayTao) {
        this.dpNgayTao = dpNgayTao;
    }

    /**
     * @return the cbGioiTinh
     */
    public ComboBox getCbGioiTinh() {
        return cbGioiTinh;
    }

    /**
     * @param cbGioiTinh the cbGioiTinh to set
     */
    public void setCbGioiTinh(ComboBox cbGioiTinh) {
        this.cbGioiTinh = cbGioiTinh;
    }
    
    @FXML
    private TableView<NhanVien> tbNhanVien;
    
    private static final NhanVienService ns = new NhanVienService();
    
    @FXML
    private TextField txtTenNhanVien;
    
    @FXML
    private ComboBox cbGioiTinh;
    
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSDT;

    @FXML
    private ComboBox cbVaiTro;

    @FXML
    private TextField txtTaiKhoan;

    @FXML
    private TextField txtMatKhau;

    @FXML
    private CheckBox cbTrangThai;

    @FXML
    private DatePicker dpNgayTao;
    
    @FXML
    private DatePicker dpNgaySinh;
    
    @FXML
    private TextField txtkwNV;
    
    @FXML
    private TextField txtIDNguoiDung;
    
    @FXML
    private Text txtNV;
    
    @FXML
    private Text txtID;
    
    @FXML
    private Button btnCloseDMNV;
    
    @FXML
    private Button btnXoaONhapLieu;
    
    @FXML
    private VBox vBox;
     
    Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbGioiTinh.setItems(FXCollections.observableList(this.setGioiTinh()));
        cbVaiTro.setItems(FXCollections.observableList(this.setRole()));
        
        try {
            loadDataNhanVien(null, true);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTableNhanVien();
        
        this.txtkwNV.textProperty().addListener((evt) -> {
            try {
                this.loadDataNhanVien(this.txtkwNV.getText(), true);
            } catch (SQLException ex) {
                Logger.getLogger(DanhMucNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        txtSDT.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d*")) {
                    txtSDT.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
       loadDataToNhanVien();
    }
    
    private List<String> setGioiTinh() {
        List<String> listGioiTinh = new ArrayList<>();
        listGioiTinh.add("Nam");
        listGioiTinh.add("Nữ");
        listGioiTinh.add("Khác");
        
        return listGioiTinh;
    }
    
    private List<String> setRole() {
        List<String> listRole = new ArrayList<>();
        listRole.add("NhanVien");
        listRole.add("QuanLy");
        
        return listRole;
    }
    
    public void closeDMNV(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void openDSNVBH(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSNVBH.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDSNVQL(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSNVQL.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }
    
    public void openDSNV_DaXoa(ActionEvent event) throws SQLException, IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DSNhanVien_DaXoa.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
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
        
        this.tbNhanVien.getColumns().addAll(colId, colTenNhanVien, colGioiTinh, colNgaySinh, 
                colTaiKhoan, colTrangThai, colEmail, colNgayTao, colSDT, colRole);  
    }
    
    public void loadDataNhanVien(String kwNV, Boolean active) throws SQLException {
        NhanVienService ns = new NhanVienService();
        this.tbNhanVien.setItems(FXCollections.observableList(ns.getNhanVien(kwNV, active)));
        // Lay danh sach cau hoi do vao bang TableView
    }
    
    public static Date getDateFromDatePicket(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        if (localDate != null) {
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId
                    .systemDefault()));
            Date date = Date.from(instant);
            return date;
        } else {
            return null;
        }
    }
    
    public void xoa() {
        txtNV.setText("");
        txtID.setText("");
        txtTenNhanVien.clear();
        cbGioiTinh.setValue(null);
        dpNgaySinh.setValue(null);
        txtEmail.clear();
        txtSDT.clear();
        cbVaiTro.setValue(null);
        txtTaiKhoan.clear();
        txtMatKhau.clear();
        dpNgayTao.setValue(null);  
    }
    
    public String MD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        while (hashtext.length() < 32)
            hashtext = "0" + hashtext;
        
        return hashtext;
    }
    
    public void themNhanVienHandler(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        int SDT = Integer.parseInt(txtSDT.getText());  
        NhanVien n = new NhanVien(UUID.randomUUID().toString(), txtTenNhanVien.getText(), 
                txtTaiKhoan.getText(), MD5(txtMatKhau.getText()), cbGioiTinh.getSelectionModel().getSelectedItem().toString(), 
                true, txtEmail.getText(), getDateFromDatePicket(dpNgayTao),
                SDT, cbVaiTro.getSelectionModel().getSelectedItem().toString(), getDateFromDatePicket(dpNgaySinh));
        
        NhanVienService nvs = new NhanVienService();
        try {
            nvs.themNhanVien(n);
            utils.utills.showBox("Thêm nhân viên thành công!", Alert.AlertType.INFORMATION).show();
            loadDataNhanVien(null, true);
            xoa();
        } catch (SQLException ex) {
            utils.utills.showBox("Thêm không thành công!" + ex, Alert.AlertType.WARNING).show();
        }
    }
    
    public void capNhatNhanVienHandler(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
        int SDT = Integer.parseInt(txtSDT.getText());
        
        NhanVien n = new NhanVien(txtID.getText(), txtTenNhanVien.getText(), 
                null, null, cbGioiTinh.getSelectionModel().getSelectedItem().toString(), 
                true, txtEmail.getText(), null,
                SDT, cbVaiTro.getSelectionModel().getSelectedItem().toString(), getDateFromDatePicket(dpNgaySinh));
        
        NhanVienService nvs = new NhanVienService();
        
        if (nvs.capNhatNhanVien(n) > 0) {
            utils.utills.showBox("Cập nhật nhân viên thành công!", Alert.AlertType.INFORMATION).show();
            loadDataNhanVien(null, true);        
        } else {
            utils.utills.showBox("Cập nhật không thành công!", Alert.AlertType.WARNING).show();
        } 
    }
    
    public void xoaNhanVien_TamThoi_Handler(ActionEvent event) throws SQLException {
        NhanVienService cns = new NhanVienService();
        
        if (cns.xoaNhanVien_TamThoi(txtID.getText()) > 0) {
            utils.utills.showBox("Xóa nhân viên thành công!", Alert.AlertType.INFORMATION).show();
            xoa();
            loadDataNhanVien(null, true);
        } else {
            utils.utills.showBox("Xóa không thành công!", Alert.AlertType.WARNING).show();
        }  
    }
    
    public Date LocalDateToDate(LocalDate lcd) {
        LocalDate localDate = lcd;
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
    
    public LocalDate DateToLocalDate(Date date) {
        Date d = date;
        Instant current = d.toInstant(); 
        LocalDate ldt = LocalDate.ofInstant(current, ZoneId.systemDefault());    
        return ldt;
    }
    
    public void loadDataToNhanVien() {
        tbNhanVien.setOnMouseClicked(new EventHandler<MouseEvent>() {        
            @Override
            public void handle(MouseEvent t) {
                NhanVien nv = tbNhanVien.getItems().get(tbNhanVien.getSelectionModel().getSelectedIndex());
                txtNV.setText("ID Nhân viên: ");
                txtID.setText(nv.getIDNguoiDung());
                txtTenNhanVien.setText(nv.getTenNguoiDung());
                cbGioiTinh.setValue(nv.getGioiTinh());
                dpNgaySinh.setValue(LocalDate.parse(nv.getNgaySinh().toString()));
                txtEmail.setText(nv.getEmail());
                txtSDT.setText(String.valueOf(nv.getSDT()));
                cbVaiTro.setValue(nv.getRole());
                dpNgayTao.setValue(LocalDate.parse(nv.getNgayTao().toString()));         
            }
        });
    }
    
    public void xoaONhapLieu(ActionEvent event) {
        xoa();
    }
}
