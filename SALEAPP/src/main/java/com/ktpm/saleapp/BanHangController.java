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
import com.ktpm.services.LoaiHHService;
import com.ktpm.services.NhanVienService;
import java.net.URL;
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
import utils.utills;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class BanHangController implements Initializable {
    @FXML private ComboBox<LoaiHH> CbLoaiHH;
    @FXML private GridPane GridItems;
    @FXML private TextField tfFindHH,tfTienKhachTra,tfFindKH;
    @FXML private TableView tbvHangHoa;
    @FXML private Button loadAllHH;
    @FXML private Label lbNgayMua,lbSoluong,lbKhuyenMai,lbThanhTien,lbTienThoi,lbCheckKH;
    public String idNhanVien;
    public String idKhachHang;
    private static final LoaiHHService loaihhSV = new LoaiHHService();
    private static final HangHoaService hanghoaSV = new HangHoaService();
    private static final KhachHangService khachhangSV = new KhachHangService();
    private static final HoaDonService hoadonSV = new HoaDonService();
    private static final NhanVienService nvSV = new NhanVienService();
    private static List<HangHoa> hanghoa = new ArrayList<>();
    int column = 0;
    int row = 0;
    List<HangHoa> tbvHH = new ArrayList<>();
    double sl,thanhtien,khuyenmai,tienkh,tienthoi;
    private static List<Button> removeHH = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoaiHHService loaiHHSV = new LoaiHHService();
        LoadHH();
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
                this.GridItems.getChildren().clear();
                showAllHH(this.tfFindHH.getText());
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
        
    }
    public void showAllHH(String kw) throws SQLException{
        String aa = "";
        GridItems.setPadding(new Insets(10,10,10,20));
        column =0;
        row = 0;
        GridItems.setVgap(20);
        GridItems.setHgap(130);
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
                Button btnSL = new Button("Nhập SL");
                setBtnNhap(btnSL);
                hh.setBtnSL(btnSL);
                Button btnCong = new Button("+");
                setBtnCong(btnCong);
                hh.setBtnCong(btnCong);
                Button btnTru = new Button("-");
                setBtnTru(btnTru);
                hh.setBtnTru(btnTru);
                Button btnKG = new Button("Nhập KG");
                setBtnNhapKG(btnKG);
                hh.setBtnKG(btnKG);
                Button btnXoa = new Button("Xóa");
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
                                utills.showBox("Số lượng trong kho không đủ", Alert.AlertType.WARNING).show();
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
        });
        return item;
    }
    public void LoadHH(){
        TableColumn col1 = new TableColumn("Tên Hàng Hóa");
        col1.setCellValueFactory(new PropertyValueFactory("TenHangHoa"));
        col1.setPrefWidth(200);

        TableColumn col2 = new TableColumn("Giá");
        col2.setCellValueFactory(new PropertyValueFactory("Gia"));
        col2.setPrefWidth(200);
        TableColumn col11 = new TableColumn("Giá giảm");
        col11.setCellValueFactory(new PropertyValueFactory("GiaGiam"));
        col11.setPrefWidth(200);

        TableColumn col3 = new TableColumn("Xuất Xứ");
        col3.setCellValueFactory(new PropertyValueFactory("XuatXu"));
        col3.setPrefWidth(200);
        
        TableColumn col4 = new TableColumn("Số Lượng");
        col4.setCellValueFactory (new PropertyValueFactory("SL"));
        col4.setPrefWidth(100);
        
        TableColumn col5 = new TableColumn("Nhập SL");
        col5.setCellValueFactory(new PropertyValueFactory("btnSL"));
        col5.setPrefWidth(100);
        
        TableColumn col6 = new TableColumn("Thêm SL");
        col6.setCellValueFactory(new PropertyValueFactory("btnCong"));
        col6.setPrefWidth(100);
        
        TableColumn col7 = new TableColumn("Trừ SL");
        col7.setCellValueFactory(new PropertyValueFactory("btnTru"));
        col7.setPrefWidth(100);
        
        TableColumn col8 = new TableColumn();
        col8.setCellValueFactory(new PropertyValueFactory("KG"));
        col8.setPrefWidth(100);
        
        TableColumn col9 = new TableColumn("Nhập SL");
        col9.setCellValueFactory(new PropertyValueFactory("btnKG"));
        col9.setPrefWidth(100);
        
        TableColumn col10 = new TableColumn("Xóa");
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
        if(this.lbKhuyenMai.getText().contains("10%")){
            khuyenmai = 0.1;
            tongtien = tongtien*0.9;
            thanhtien = tongtien;
            lbThanhTien.setText(currencyVN.format(tongtien));
        }
        else
        {
            lbThanhTien.setText(currencyVN.format(tongtien));
            thanhtien = tongtien;
        }
        if(Double.parseDouble(this.tfTienKhachTra.getText()) !=0 && Double.parseDouble(this.tfTienKhachTra.getText()) >= tongtien){
            tienkh = Double.parseDouble(this.tfTienKhachTra.getText());
            this.lbTienThoi.setText(currencyVN.format(Double.parseDouble(this.tfTienKhachTra.getText()) - tongtien));
            tienthoi = Double.parseDouble(this.tfTienKhachTra.getText()) - tongtien;
        }
        else
            utills.showBox("Số tiền không hợp lệ", Alert.AlertType.WARNING).show();
    }
   
    public void thanhToan() throws SQLException{
        if(this.tbvHH.size() > 0 && tienkh >= thanhtien){
            Date n = new Date();
            Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
            Optional<ButtonType> option = utills.showBox("Xác nhận thanh toán", Alert.AlertType.CONFIRMATION).showAndWait();
            if(option.get() == ButtonType.OK){
                if((hoadonSV.saveHoaDon(UUID.randomUUID().toString(), ngaydat,this.nvSV.findNVByID(idNhanVien).getTenNguoiDung() , 
                        sl, thanhtien, khuyenmai, tienkh, tienthoi, String.valueOf(idNhanVien), String.valueOf(idKhachHang))) == true){
                    utills.showBox("Lưu hóa đơn thành công", Alert.AlertType.INFORMATION).show();
                    sl =0;
                    thanhtien = 0;
                    khuyenmai = 0;
                    tienkh = 0;
                    tienthoi = 0;
                    this.lbNgayMua.setText("");
                    this.lbSoluong.setText("");
                    this.lbKhuyenMai.setText("");
                    this.lbThanhTien.setText("");
                    this.tfTienKhachTra.setText("");
                    this.lbTienThoi.setText("");
                }else
                    utills.showBox("Lưu hóa đơn thất bại", Alert.AlertType.WARNING).show();
            }
            for(int i =0;i<this.tbvHH.size();i++){
            hanghoaSV.UpdateHH(tbvHH.get(i));
            }
            this.tbvHH.clear();
            this.tbvHangHoa.refresh();
        }
        else{
            utills.showBox("Lỗi thanh toán", Alert.AlertType.WARNING).show();
        }
       
    }
    public void setBtnNhap(Button btn){
        btn.setOnAction((evt) ->{
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập số lượng: ");
        Optional<String> ans = dialog.showAndWait();
        TableCell tbc = (TableCell)((Button)evt.getSource()).getParent();
        HangHoa qs = (HangHoa)tbc.getTableRow().getItem();
        try {
            if(Double.parseDouble(ans.get()) >0){
                if(Double.parseDouble(ans.get()) <= hanghoaSV.getSLCheck(qs.getTenHangHoa())){ 
                    qs.setSL(Double.parseDouble(ans.get()));
                }
                else
                    utills.showBox("Số lượng trong kho không đủ", Alert.AlertType.WARNING).show();
            }else
                utills.showBox("Số lượng không được bằng 0", Alert.AlertType.WARNING).show();
            
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
                    utills.showBox("Số lượng trong kho không đủ", Alert.AlertType.WARNING).show();
                else
                    qs.setSL(sl);
            } catch (SQLException ex) {
                Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.tbvHangHoa.refresh();
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
                    utills.showBox("Số lượng không được nhỏ hơn 1", Alert.AlertType.WARNING).show();
                else
                    qs.setSL(sl);
                this.tbvHangHoa.refresh();
            });
    }
    public void setBtnNhapKG(Button btn){
        btn.setOnAction((evt1) ->{
                TextInputDialog dialog1 = new TextInputDialog();
                dialog1.setHeaderText("Nhập số lượng: ");
                Optional<String> ans1 = dialog1.showAndWait();
                TableCell tbc1 = (TableCell)((Button)evt1.getSource()).getParent();
                HangHoa qs1 = (HangHoa)tbc1.getTableRow().getItem();
                try {
                    if(Double.parseDouble(ans1.get()) >0){
                        if(Double.parseDouble(ans1.get()) <= hanghoaSV.getKGCheck(qs1.getTenHangHoa())){
                            qs1.setKG(Double.parseDouble(ans1.get()));
                        }
                        else
                            utills.showBox("Số lượng trong kho không đủ", Alert.AlertType.WARNING).show();
                    }
                    else
                        utills.showBox("Số lượng không được bằng 0", Alert.AlertType.WARNING).show();
                } catch (SQLException ex) {
                    Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tbvHangHoa.refresh();
            });
    }
    public void setBtnXoa(Button btn){
        btn.setOnAction((evt1) ->{
                TableCell tbc2 = (TableCell)((Button)evt1.getSource()).getParent();
                HangHoa qs2 = (HangHoa)tbc2.getTableRow().getItem();
                
                Optional<ButtonType> option = utills.showBox("Xác nhận xóa", Alert.AlertType.CONFIRMATION).showAndWait();
                if(option.get() == ButtonType.OK){
                    tbvHH.remove(qs2);
                }
                this.tbvHangHoa.refresh();
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
            this.lbCheckKH.setText("Tồn tại");
            this.lbCheckKH.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 17px;");
            for(int i =0;i< kq1.size();i++)
            {
                kh = kq1.get(i);
                idKhachHang = kq1.get(i).getIDNguoiDung();
            }
            if(checkKMKH(kh, ngaydat) == true){
                this.lbKhuyenMai.setText("10%");
            }
            else{
                this.lbKhuyenMai.setText("Không có khuyến mãi");
            }
        }
        else if(kq2.size() >0){
            this.lbCheckKH.setText("Tồn tại");
            this.lbCheckKH.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 17px;");
            for(int i =0;i< kq2.size();i++)
            {
                kh = kq2.get(i);
                idKhachHang = kq2.get(i).getIDNguoiDung();
            }
            if(checkKMKH(kh, ngaydat) == true){
                this.lbKhuyenMai.setText("10%");
            }
            else{
                this.lbKhuyenMai.setText("Không có khuyến mãi");
            }
        }else
        {
            this.lbCheckKH.setText("Không tồn tại");
            this.lbCheckKH.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 17px;");
            this.lbKhuyenMai.setText("Không có khuyến mãi");
        }
    }
    public boolean checkKMKH(KhachHang kh, Date ngaydat){
        double tongtien = 0;
        boolean kq = false;
        if(kh.getNgaySinh().getDate() == ngaydat.getDate() && (kh.getNgaySinh().getMonth()+1) == (ngaydat.getMonth()+1)){
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
            if(this.lbKhuyenMai.getText().contains("10%")){
                tongtien = tongtien*0.9;
            }
            if(tongtien > 1000000)
                kq = true;
        }
        return kq;
        
    }
    public void setIDNV(String id){
        idNhanVien = id;
        
    }

   
    
}
