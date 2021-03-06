/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;
import com.ktpm.pojo.HangHoa;
import com.ktpm.pojo.KhachHang;
import com.ktpm.pojo.LoaiHH;
import com.ktpm.services.HangHoaService;
import com.ktpm.services.HoaDonService;
import com.ktpm.services.KhachHangService;
import com.ktpm.services.KhuyenMaiService;
import com.ktpm.services.LoaiHHService;
import com.ktpm.services.NhanVienService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import utils.JDBCutils;
import utils.utills;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class BanHangController implements Initializable {
    @FXML private ComboBox<LoaiHH> CbLoaiHH;
    @FXML private GridPane GridItems;
    @FXML private TextField tfFindHH,tfTienKhachTra,tfFindKH,tfFindHHInLoai;
    @FXML private TableView tbvHangHoa;
    @FXML private Button loadAllHH;
    @FXML private Label lbNgayMua,lbSoluong,lbKhuyenMai,lbThanhTien,lbTienThoi,lbCheckKH;
    @FXML RadioButton rdSinhNhat;
    public static String idNhanVien;
    public String idKhachHang;
    private static final LoaiHHService loaihhSV = new LoaiHHService();
    private static final HangHoaService hanghoaSV = new HangHoaService();
    private static final KhachHangService khachhangSV = new KhachHangService();
    private static final HoaDonService hoadonSV = new HoaDonService();
    private static final NhanVienService nvSV = new NhanVienService();
    private static final KhuyenMaiService kmSV = new KhuyenMaiService();
    private static List<HangHoa> hanghoa = new ArrayList<>();
    int column = 0;
    int row = 0;
    String HHbyLoai = "";
    double tongTienCheckSN = 0;
    List<HangHoa> tbvHH = new ArrayList<>();
    double sl,thanhtien,khuyenmai,tienkh,tienthoi;
    private static List<Button> removeHH = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbvHH.clear();
        LoaiHHService loaiHHSV = new LoaiHHService();
        LoadHH();
        this.lbKhuyenMai.setText("Kh??ng c?? khuy???n m??i");
        this.tbvHangHoa.setItems(FXCollections.observableList(this.tbvHH));
        try {
            this.CbLoaiHH.setItems(FXCollections.observableArrayList(loaiHHSV.getLoaiHH()));
            GridItems.setAlignment(Pos.CENTER);
            //width
            GridItems.setMinWidth(Region.USE_COMPUTED_SIZE);
            GridItems.setPrefWidth(Region.USE_COMPUTED_SIZE);
            GridItems.setMaxWidth(Region.USE_PREF_SIZE);
            //height
            GridItems.setMinHeight(Region.USE_COMPUTED_SIZE);
            GridItems.setPrefHeight(Region.USE_COMPUTED_SIZE);
            GridItems.setMaxHeight(Region.USE_PREF_SIZE);
            showAllHH("");
            this.CbLoaiHH.setOnAction(event -> {
                this.GridItems.getChildren().clear();
                GridItems.setPadding(new Insets(10,10,10,20));
                column =0;
                row = 0;
                String aa = "";
                GridItems.setVgap(20);
                GridItems.setHgap(130);
                String loaihh;
                loaihh= CbLoaiHH.getSelectionModel().getSelectedItem().toString();
                HHbyLoai = loaihh;
                this.GridItems.getAlignment();
                try {
                    hanghoa = loaiHHSV.getLoaiHHByLoai(loaihh);
                    
                    for(int i = 0;i < hanghoa.size(); i++){
                        aa+= hanghoa.get(i).getAnhHH();
                    }
                    for(int i = 0;i < hanghoa.size(); i++){
                        Button itemss = this.createItem(hanghoa.get(i).getTenHangHoa(), hanghoa.get(i).getAnhHH().strip(),
                                String.valueOf(loaihhSV.getSL(hanghoa.get(i).getTenHangHoa())));
                        
                        if(column == 4){
                            column = 0;
                            row++;
                        }
                        this.GridItems.add(itemss, column++, row);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tfFindHH.textProperty().addListener((evt) ->{
            try {
                if(this.tfFindHH.getText() == null || this.tfFindHH.getText() == ""){
                    this.tfFindHHInLoai.setVisible(true);
                }else
                    this.tfFindHHInLoai.setVisible(false);
                this.GridItems.getChildren().clear();
                if(this.tfFindHH.getText().contains("%") == false && this.tfFindHH.getText().contains("_") == false){
                    showAllHH(this.tfFindHH.getText());
                }else
                {
                    utills.showBox("Coi ch???ng ???? l?? coi ch???ng ????", Alert.AlertType.WARNING).show();
                    this.tfFindHH.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.tfFindHHInLoai.textProperty().addListener((evt) ->{
            try {
                if(this.tfFindHHInLoai.getText() == null || this.tfFindHHInLoai.getText() == ""){
                    this.tfFindHH.setVisible(true);
                }else
                    this.tfFindHH.setVisible(false);
                this.GridItems.getChildren().clear();
                if(this.tfFindHHInLoai.getText().contains("%") == false && this.tfFindHHInLoai.getText().contains("_") == false){
                    showHHByLoai(HHbyLoai, this.tfFindHHInLoai.getText());
                }else{
                    utills.showBox("Coi ch???ng ???? l?? coi ch???ng ????", Alert.AlertType.WARNING).show();
                    this.tfFindHHInLoai.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.loadAllHH.setOnAction((event) -> {
            try {
                showAllHH(null);
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        checkSN();
    }
    public void showHHByLoai(String loaiHH, String kw) throws SQLException{
        String aa = "";
        GridItems.setPadding(new Insets(10,10,10,20));
        column =0;
        row = 0;
        GridItems.setVgap(20);
        GridItems.setHgap(130);
        if(loaiHH == "" || loaiHH ==null){
            kw = "";
        }
        hanghoa = hanghoaSV.getHHByLoai(loaiHH, kw);
        for(int i = 0;i < hanghoa.size(); i++){
            aa+= hanghoa.get(i).getAnhHH();
        }
        for(int i = 0;i < hanghoa.size(); i++){
            Button itemss = this.createItem(hanghoa.get(i).getTenHangHoa(), hanghoa.get(i).getAnhHH().strip(),String.valueOf(loaihhSV.getSL(hanghoa.get(i).getTenHangHoa())));

            if(column == 4){
                column = 0;
                row++;
            }
            this.GridItems.add(itemss, column++, row);
        }
    }
    public void showAllHH(String kw) throws SQLException{
        String aa = "";
        GridItems.setPadding(new Insets(10,10,10,20));
        column =0;
        row = 0;
        GridItems.setVgap(20);
        GridItems.setHgap(130);
        if(kw == null || kw == ""){
            kw = "";
        }
        hanghoa = hanghoaSV.getHHByKey(kw);
        for(int i = 0;i < hanghoa.size(); i++){
            aa+= hanghoa.get(i).getAnhHH();
        }
        for(int i = 0;i < hanghoa.size(); i++){
            Button itemss = this.createItem(hanghoa.get(i).getTenHangHoa(), hanghoa.get(i).getAnhHH().strip(),String.valueOf(loaihhSV.getSL(hanghoa.get(i).getTenHangHoa())));

            if(column == 4){
                column = 0;
                row++;
            }
            this.GridItems.add(itemss, column++, row);
        }
    } 
    public Button createItem(String tenHH,String img,String SL) throws SQLException{
        Button item = new Button();
        if( img.isEmpty() || (loaihhSV.getAllNameImg(img) == false) || img == null)
            img = "noimage";
        item.setId(tenHH);
        ImageView imgv = new ImageView("/souresImage/" + img + ".jpg");
        Label lbslconlai = new Label("SL: " + SL);
        VBox info = new VBox();
        info.setAlignment(Pos.CENTER);
        info.getChildren().add(imgv);
        info.getChildren().add(lbslconlai);
        imgv.setFitHeight(80);
        imgv.setFitWidth(120);
        item.setGraphic(info);
        item.setPrefHeight(110);
        item.setPrefWidth(120);
        item.setLineSpacing(40);
        String TenHH = tenHH;
        item.setOnAction((event) ->{
            try {
                String idHH = hanghoaSV.getIDHH(TenHH);
                HangHoa hh = hanghoaSV.getHangHoa(idHH);
                Button btnSL = new Button("Nh???p SL");
                setBtnNhap(btnSL);
                hh.setBtnSL(btnSL);
                Button btnCong = new Button("+");
                setBtnCong(btnCong);
                hh.setBtnCong(btnCong);
                Button btnTru = new Button("-");
                setBtnTru(btnTru);
                hh.setBtnTru(btnTru);
                Button btnKG = new Button("Nh???p KG");
                setBtnNhapKG(btnKG);
                hh.setBtnKG(btnKG);
                Button btnXoa = new Button("X??a");
                setBtnXoa(btnXoa);
                hh.setBtnXoa(btnXoa);
                setGiamGia(hh);
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                if(hanghoaSV.isHangHoa(tbvHH, item.getId()) == false && (hanghoaSV.getSLCheck(TenHH) != 0 || hanghoaSV.getKGCheck(TenHH) != 0))
                {
                    if(hanghoaSV.getSLCheck(TenHH) == 0){
                        hh.setKG(1.0);
                        hh.setSL(0.0);
                    }
                    else if(hanghoaSV.getKGCheck(TenHH) == 0){
                        hh.setKG(0.0);
                        hh.setSL(1.0);
                    }
                    this.tbvHH.add(hh);
                }
                else if(hanghoaSV.isHangHoa(tbvHH, item.getId()) == true)
                    for(int i =0;i<tbvHH.size();i++){
                        if(tbvHH.get(i).getTenHangHoa().contains(item.getId())){
                            double k =tbvHH.get(i).getSL();
                            k++;
                            if(k > hanghoaSV.getSLCheck(tbvHH.get(i).getTenHangHoa()))
                                utills.showBox("S??? l?????ng trong kho kh??ng ????? (n???u l?? KG h??y nh???p tay)", Alert.AlertType.WARNING).show();
                            else
                                tbvHH.get(i).setSL(k);
                            this.tbvHangHoa.refresh();
                            break;
                        }
                    }
                this.tbvHangHoa.setItems(FXCollections.observableList(this.tbvHH));
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tamTinh();
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return item;
    }
    public void LoadHH(){
        TableColumn col1 = new TableColumn("T??n H??ng H??a");
        col1.setCellValueFactory(new PropertyValueFactory("TenHangHoa"));
        col1.setPrefWidth(200);

        TableColumn col2 = new TableColumn("Gi??");
        col2.setCellValueFactory(new PropertyValueFactory("Gia"));
        col2.setPrefWidth(200);
        TableColumn col11 = new TableColumn("Gi?? ???? gi???m");
        col11.setCellValueFactory(new PropertyValueFactory("GiaGiam"));
        col11.setPrefWidth(200);

        TableColumn col3 = new TableColumn("Xu???t X???");
        col3.setCellValueFactory(new PropertyValueFactory("XuatXu"));
        col3.setPrefWidth(200);
        
        TableColumn col4 = new TableColumn("S??? L?????ng");
        col4.setCellValueFactory (new PropertyValueFactory("SL"));
        col4.setPrefWidth(100);
        
        TableColumn col5 = new TableColumn("Nh???p SL");
        col5.setCellValueFactory(new PropertyValueFactory("btnSL"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Th??m SL");
        col6.setCellValueFactory(new PropertyValueFactory("btnCong"));
        col6.setPrefWidth(100);
        
        TableColumn col7 = new TableColumn("Tr??? SL");
        col7.setCellValueFactory(new PropertyValueFactory("btnTru"));
        col7.setPrefWidth(100);
        
        TableColumn col8 = new TableColumn();
        col8.setCellValueFactory(new PropertyValueFactory("KG"));
        col8.setPrefWidth(100);
        
        TableColumn col9 = new TableColumn("Nh???p SL");
        col9.setCellValueFactory(new PropertyValueFactory("btnKG"));
        col9.setPrefWidth(100);
        
        TableColumn col10 = new TableColumn("X??a");
        col10.setCellValueFactory(new PropertyValueFactory("btnXoa"));
        col10.setPrefWidth(100);
        
        this.tbvHangHoa.getColumns().addAll(col1,col2,col11,col3,col4,col5,col6,col7,col8,col9,col10);
    }
    
    public void tamTinh() throws SQLException{
        double tongtien =0;
        KhachHang kh = new KhachHang();
        Date ngaydat = new Date();
        SimpleDateFormat f = new SimpleDateFormat("E dd/MM/yy hh:mm:ss");
        lbNgayMua.setText(String.valueOf(f.format(ngaydat)));
        lbSoluong.setText(String.valueOf(tbvHH.size()));
        sl = tbvHH.size();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        for(int i =0;i<tbvHH.size();i++){
            if(tbvHH.get(i).getGiaGiam() == null){
                if(tbvHH.get(i).getKG() == 0){
                    tongtien +=tbvHH.get(i).getGia() * tbvHH.get(i).getSL();
                }else if(tbvHH.get(i).getSL() == 0){
                    tongtien +=tbvHH.get(i).getGia() * tbvHH.get(i).getKG();
                }
            }
            else if(tbvHH.get(i).getGiaGiam() >0){
                 if(tbvHH.get(i).getKG() == 0){
                    tongtien +=tbvHH.get(i).getGiaGiam() * tbvHH.get(i).getSL();
                }else if(tbvHH.get(i).getSL() == 0){
                    tongtien +=tbvHH.get(i).getGiaGiam() * tbvHH.get(i).getKG();
                }
            }
        }
        if(tongtien < 1000000){
           this.rdSinhNhat.setSelected(false);
           khuyenmai = 0;
        }
        if(!rdSinhNhat.isSelected() && this.lbKhuyenMai.getText() == "0.1" ){
           this.lbKhuyenMai.setText("Kh??ng c?? khuy???n m??i");
           this.rdSinhNhat.setSelected(false);
        }
        double tt = tongtien;
        tongTienCheckSN = tongtien;
        if(this.lbKhuyenMai.getText() != "Kh??ng c?? khuy???n m??i"){
            khuyenmai = Double.parseDouble(this.lbKhuyenMai.getText());
            tt = tt*(1 - Double.parseDouble(this.lbKhuyenMai.getText()));
            thanhtien = tt;
            lbThanhTien.setText(currencyVN.format(tt));
        }
        else if(this.lbKhuyenMai.getText().contains("Kh??ng c?? khuy???n m??i") || this.lbKhuyenMai.getText().isEmpty())
        {
            thanhtien = tt;
            lbThanhTien.setText(currencyVN.format(tt));
        }
        if(Double.parseDouble(this.tfTienKhachTra.getText()) !=0 && Double.parseDouble(this.tfTienKhachTra.getText()) >= tt){
            tienkh = Double.parseDouble(this.tfTienKhachTra.getText());
            this.lbTienThoi.setText(currencyVN.format(Double.parseDouble(this.tfTienKhachTra.getText()) - tt));
            tienthoi = Double.parseDouble(this.tfTienKhachTra.getText()) - tt;
        }
        else
        {
            utills.showBox("S??? ti???n kh??ng h???p l???", Alert.AlertType.WARNING).show();
            tfTienKhachTra.setText("");
            lbTienThoi.setText("");
        }
        
    }
   
    public void thanhToan() throws SQLException{
        if(rdSinhNhat.isSelected() == false){
            findKH();
        }
        if(this.tfTienKhachTra.getText() =="" || this.tfTienKhachTra.getText() == null){
            utills.showBox("H??y nh???p s??? ti???n kh??ch", Alert.AlertType.INFORMATION).show();
        }else
        {
            tamTinh();
            if(this.tbvHH.size() > 0 && tienkh >= thanhtien && this.tfTienKhachTra.getText() !=""){
                Date n = new Date();
                Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
                Optional<ButtonType> option = utills.showBox("X??c nh???n thanh to??n", Alert.AlertType.CONFIRMATION).showAndWait();
                if(option.get() == ButtonType.OK){
                    String idHDD = UUID.randomUUID().toString();
                    if((hoadonSV.saveHoaDon(idHDD, ngaydat,this.nvSV.findNVByID(idNhanVien).getTenNguoiDung() , 
                            sl, thanhtien, khuyenmai, tienkh, tienthoi, String.valueOf(idNhanVien), String.valueOf(idKhachHang))) == true){
                        utills.showBox("L??u h??a ????n th??nh c??ng", Alert.AlertType.INFORMATION).show();
                        this.rdSinhNhat.setSelected(false);
                        for(int i = 0;i< this.tbvHH.size();i++){
                            hoadonSV.upDateHH_DH(idHDD, this.tbvHH.get(i).getIdHangHoa());
                        }
                        try {
                            printDH(idHDD);
                        } catch (IOException ex) {
                            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sl =0;
                        thanhtien = 0;
                        khuyenmai = 0;
                        tienkh = 0;
                        tienthoi = 0;
                        this.lbNgayMua.setText("");
                        this.lbSoluong.setText("");
                        this.lbKhuyenMai.setText("Kh??ng c?? khuy???n m??i");
                        this.lbThanhTien.setText("");
                        this.tfTienKhachTra.setText("");
                        this.lbTienThoi.setText("");
                        this.tfFindKH.setText("");
                        for(int i =0;i<this.tbvHH.size();i++){
                            hanghoaSV.UpdateHH(tbvHH.get(i));
                        }
                        this.tbvHH.clear();
                        this.tfFindKH.setText("");
                        findKH();
                    }else
                        utills.showBox("L??u h??a ????n th???t b???i", Alert.AlertType.WARNING).show();
                }
            }
            else{
                utills.showBox("L???i thanh to??n", Alert.AlertType.WARNING).show();
            }
        }
        this.tbvHangHoa.refresh();

    }
    public void setBtnNhap(Button btn){
        btn.setOnAction((evt) ->{
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nh???p s??? l?????ng: ");
        Optional<String> ans = dialog.showAndWait();
        TableCell tbc = (TableCell)((Button)evt.getSource()).getParent();
        HangHoa qs = (HangHoa)tbc.getTableRow().getItem();
        try {
            if(Double.parseDouble(ans.get()) >= 0.5){
                if(Double.parseDouble(ans.get()) <= hanghoaSV.getSLCheck(qs.getTenHangHoa())){
//                    qs.setSL(Double.parseDouble(ans.get()));  
                    double kq = Double.parseDouble(ans.get());
                    int IntValue = (int) Math.round(kq);
                    qs.setSL(Double.valueOf(IntValue));
                }
                else
                    utills.showBox("S??? l?????ng trong kho kh??ng ?????", Alert.AlertType.WARNING).show();
            }else
                utills.showBox("S??? l?????ng kh??ng ???????c nh??? h??n ho???c b???ng 0", Alert.AlertType.WARNING).show();
            
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tbvHangHoa.refresh();
        });
    }
    
    public void setBtnCong(Button btn) throws SQLException{
        btn.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 17px;");
        btn.setOnAction((evt) ->{
            TableCell tbc = (TableCell)((Button)evt.getSource()).getParent();
            HangHoa qs = (HangHoa)tbc.getTableRow().getItem();
            double sl = qs.getSL();
            sl++;
            try {
                if(sl > hanghoaSV.getSLCheck(qs.getTenHangHoa()))
                    utills.showBox("S??? l?????ng trong kho kh??ng ?????", Alert.AlertType.WARNING).show();
                else{
                    qs.setSL(sl);
                    this.tbvHangHoa.refresh();
                }
                    
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                tamTinh();
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
    public void setBtnTru(Button btn){
        btn.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 17px;");
        btn.setOnAction((evt) ->{
                TableCell tbc = (TableCell)((Button)evt.getSource()).getParent();
                HangHoa qs = (HangHoa)tbc.getTableRow().getItem();
                double sl = qs.getSL();
                sl--;
                if(sl <1)
                    utills.showBox("S??? l?????ng kh??ng ???????c nh??? h??n 1", Alert.AlertType.WARNING).show();
                else{
                    qs.setSL(sl);
                    this.tbvHangHoa.refresh();
                }
            try {
                tamTinh();
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            });
    }
    public void setBtnNhapKG(Button btn){
        btn.setOnAction((evt1) ->{
                TextInputDialog dialog1 = new TextInputDialog();
                dialog1.setHeaderText("Nh???p s??? l?????ng: ");
                Optional<String> ans1 = dialog1.showAndWait();
                TableCell tbc1 = (TableCell)((Button)evt1.getSource()).getParent();
                HangHoa qs1 = (HangHoa)tbc1.getTableRow().getItem();
                try {
                    double kq = Double.parseDouble(ans1.get());
                    if(Math.ceil(kq*10)/10 > 0){
                        if(Math.ceil(kq*10)/10 <= hanghoaSV.getKGCheck(qs1.getTenHangHoa())){
                            qs1.setKG(Math.ceil(kq*10)/10);
                        }
                        else
                            utills.showBox("S??? l?????ng trong kho kh??ng ?????", Alert.AlertType.WARNING).show();
                    }
                    else
                        utills.showBox("S??? l?????ng kh??ng ???????c b???ng 0", Alert.AlertType.WARNING).show();
                } catch (SQLException ex) {
                    Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tbvHangHoa.refresh();
                try {
                tamTinh();
                } catch (SQLException ex) {
                    Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
    }
    public void setBtnXoa(Button btn){
        btn.setOnAction((evt1) ->{
                TableCell tbc2 = (TableCell)((Button)evt1.getSource()).getParent();
                HangHoa qs2 = (HangHoa)tbc2.getTableRow().getItem();
                
                Optional<ButtonType> option = utills.showBox("X??c nh???n x??a", Alert.AlertType.CONFIRMATION).showAndWait();
                if(option.get() == ButtonType.OK){
                    
                    tbvHH.remove(qs2);
                }
                this.tbvHangHoa.refresh();
            try {
                if(tbvHH.size() == 0){
                    tienkh = 0;
                    tienthoi = 0;
                    this.tfTienKhachTra.setText("");
                    this.lbTienThoi.setText("");
                    try {
                        this.tfFindKH.setText("");
                        findKH();
                    } catch (SQLException ex) {
                        Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tamTinh();
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
    }
    
    public void setGiamGia(HangHoa hh) throws SQLException{
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        if(hanghoaSV.checkGiamGia(hh.getIdHangHoa(),String.valueOf(f.format(date))) !=0)
            hh.setGiaGiam(hh.getGia() - hanghoaSV.checkGiamGia(hh.getIdHangHoa(),String.valueOf(f.format(date))));
    }
    public void findKH() throws SQLException{
        KhachHang kh = new KhachHang();
        Date ngaydat = new Date();
        SimpleDateFormat f = new SimpleDateFormat("E dd/MM/yy hh:mm:ss");
        List<KhachHang> kq1 = khachhangSV.getKHByTen(tfFindKH.getText().strip());
        List<KhachHang> kq2 = khachhangSV.getKHBySDT(tfFindKH.getText().strip());
        if(kq1.size() >0){
            this.lbCheckKH.setText("T???n t???i");
            this.lbCheckKH.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 17px;");
            for(int i =0;i< kq1.size();i++)
            {
                kh = kq1.get(i);
                idKhachHang = kq1.get(i).getIDNguoiDung();
            }
            if(kh.getRole().contains("QuanLy")){
                this.lbKhuyenMai.setText(String.valueOf(kmSV.getKM("QuanLy")));
            }else
            if(kh.getRole().contains("NhanVien")){
                this.lbKhuyenMai.setText(String.valueOf(kmSV.getKM("NhanVien")));
            }
            else{
                this.lbKhuyenMai.setText("Kh??ng c?? khuy???n m??i");
                this.rdSinhNhat.setSelected(false);
            }
            this.rdSinhNhat.setSelected(false);
            tamTinh();
        }
        else if(kq2.size() >0){
            this.lbCheckKH.setText("T???n t???i");
            this.lbCheckKH.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 17px;");
            for(int i =0;i< kq2.size();i++)
            {
                kh = kq2.get(i);
                idKhachHang = kq2.get(i).getIDNguoiDung();
            }
            if(kh.getRole().contains("QuanLy")){
                this.lbKhuyenMai.setText(String.valueOf(kmSV.getKM("QuanLy")));
            }else
            if(kh.getRole().contains("NhanVien")){
                this.lbKhuyenMai.setText(String.valueOf(kmSV.getKM("NhanVien")));
            }
            else{
                this.lbKhuyenMai.setText("Kh??ng c?? khuy???n m??i");
                this.rdSinhNhat.setSelected(false);
            }
            this.rdSinhNhat.setSelected(false);
            tamTinh();
        }
        else
        {
            this.lbCheckKH.setText("Kh??ng t???n t???i");
            this.lbCheckKH.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 17px;");
            this.lbKhuyenMai.setText("Kh??ng c?? khuy???n m??i");
            
        }
    }
    public void setIDNV(String id){
        idNhanVien = id;
    }

    public void printDH(String idHD) throws SQLException, IOException{
        String path = new File("src/main/resources/HoaDon").getAbsolutePath();
         Writer bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + idHD + ".txt"), "UTF8"));
            bw.write("?????a Ch???: "+ getDiaChi(this.idNhanVien));
            bw.write("\t\t\tPhi???u thanh to??n\r\n\r\n");
            bw.write("M?? h??a ????n: " + idHD + "\r\n");
            Date h = new Date();
            bw.write("Th???i gian: " + h + "\r\n");
            bw.write("NH??N VI??N: " + nvSV.findNVByID(this.idNhanVien).getTenNguoiDung() + "\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("ID\tT??n S???n Ph???m\tS??? l?????ng\tKG\tGi?? b??n\r\n");
            bw.write("-----------------------------------------------------------\r\n");
            //Ghi s???n ph???m
            int quantotal = 0;
            for (int i = 0; i < tbvHH.size(); i++) {
                String id = String.valueOf(i);
                String TenSP = tbvHH.get(i).getTenHangHoa();
                String SL = String.valueOf(tbvHH.get(i).getSL());
                String KG = String.valueOf(tbvHH.get(i).getKG());
                String Gia = String.valueOf(tbvHH.get(i).getGia()) + " || " +String.valueOf(tbvHH.get(i).getGiaGiam());
                bw.write(id + "\t" + TenSP + "\t\t" + SL + "\t\t"+ KG + "\t\t" + Gia + "\r\n\r\n");
                
            }
            bw.write("------------------------------------------------------------\r\n");
            
            bw.write("\tChi???t kh???u:\t" + this.khuyenmai + " VN??\r\n");
            bw.write("\t--------------------------------------------\r\n");
            bw.write("\tTh??nh ti???n:\t\t\t" + this.thanhtien + " VN??\r\n");
            bw.write("\t--------------------------------------------\r\n");
            bw.write("\tTi???n kh??ch ????a:\t\t\t" + this.tienkh + " VN??\r\n");
            bw.write("\tTi???n tr??? l???i:\t\t\t" + this.tienthoi + " VN??\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("---------------------C??M ??N QU?? KH??CH!----------------------");
            bw.close();
    }
    public String getDiaChi(String idNguoiDung) throws SQLException{
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT DiaChi FROM chinhanh WHERE idNguoiDung = ?");
            stm.setString(1, idNguoiDung);
            ResultSet rs = stm.executeQuery();
            String diachi = "";
            while(rs.next()){
                diachi = rs.getString("DiaChi");
            }
            return diachi;
        }
    }
    public void checkSN(){
        this.rdSinhNhat.selectedProperty().addListener(cl->{
            if(rdSinhNhat.isSelected()){
                if(tongTienCheckSN >= 1000000){
                    try {
                        this.tfFindKH.setText("");
                        findKH();
                        this.khuyenmai = 0.1;
                        this.lbKhuyenMai.setText("0.1");
                        idKhachHang = null;
                        tamTinh();
                    } catch (SQLException ex) {
                        Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    this.lbKhuyenMai.setText("Kh??ng c?? khuy???n m??i");
                    rdSinhNhat.setSelected(false);
                    utills.showBox("????n h??ng ch??a l???n h??n 1 tri???u ?????ng", Alert.AlertType.WARNING).show();
                    try {
                        findKH();
                    } catch (SQLException ex) {
                        Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else
            {
                khuyenmai = 0.0;
                try {
                    tamTinh();
                } catch (SQLException ex) {
                    Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
