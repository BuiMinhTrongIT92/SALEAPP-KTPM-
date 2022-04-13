/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

import com.ktpm.saleapp.NhanVienController;
import com.ktpm.services.LoaiHHService;
import com.ktpm.services.NhanVienService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class test {
    public static void main(String[] agrs) throws SQLException {
        Connection conn = JDBCutils.getConn();
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa WHERE idLoaiHH in (SELECT idLoaiHH FROM loaihanghoa WHERE TenLoaiHH = N?)");
            stm.setString(1,"Nước ngọt");
            ResultSet rs = stm.executeQuery();
            
            List<HangHoa> hanghoa = new ArrayList<>();
            while(rs.next()){
                String Id = rs.getString("idHangHoa");
                String TenLoaiHH = rs.getString("TenHangHoa");
                Double Gia = rs.getDouble("Gia");
                String XuatXu = rs.getString("XuatXu");
                String IDLoaiHH = rs.getString("idLoaiHH");
                String AnhHH = rs.getString("AnhHH");
                Double SL = rs.getDouble("SoLuong");
                Double KG = rs.getDouble("KG");
                hanghoa.add(new HangHoa(Id,TenLoaiHH,Gia,XuatXu,IDLoaiHH,AnhHH,SL,KG));
            }
           
        
        for(int i =0;i< hanghoa.size();i++){
            System.out.println(hanghoa.get(i).getTenHangHoa());
        }
    }
}
//        NhanVienController sss= new NhanVienController();
//        if(sss.LoginNVBH("Lam", "123") == true){
//            System.out.println("ok");
//        }else{
//            System.out.println("no");
//        }
         
//      SimpleDateFormat kk = new SimpleDateFormat("dd-MM-yyyy");
//       Date k = kk.parse("02-02-2001");
//       String kkk = kk.format(k);
//        System.out.println(kkk);
//        
//        String k = "2001-02-02";
//        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(k);
            
//        String k = "2001-02-02";
//        DateTimeFormatter a = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH);
//        Date aa = (Date) a.parse(k);
//        System.out.println(aa);
//
//        String k = "02-02-2001";
//        SimpleDateFormat n = new SimpleDateFormat("dd-MM-yyyy");
//        Date aa = n.parse(k);
//        System.out.println(aa);
//        NguoiDung nc = new NhanVien(3, "trong","trong" , "123", "nam", true, "trong@gmail.com", aa, 1, "NhanVien");
//        System.out.println(nc.getNgayTao());
//        System.out.println(nc.getIDNguoiDung());

//        NhanVienService na = new NhanVienService();
//        NhanVien k = na.findNVByUSPS("Lam", "123");
//        System.out.println(k.getNgayTao());



//        try(Connection conn = JDBCutils.getConn()){
//            PreparedStatement getNVBH = conn.prepareStatement("SELECT *  FROM nguoidung WHERE TaiKhoan = ? AND MatKhau = ?");
//            getNVBH.setString(1, "Lam");
//            getNVBH.setString(2,"123");
//           
//            
//            ResultSet rs = getNVBH.executeQuery();
//            NguoiDung nvbh = null;
//            while(rs.next()){
//                nvbh = new NhanVien();
//                nvbh.setIDNguoiDung(rs.getInt("idNguoiDung"));
//                nvbh.setTenNguoiDung(rs.getString("TenNguoiDung"));
//                nvbh.setTaiKhoan(rs.getString("TaiKhoan"));
//                nvbh.setMatKhau(rs.getString("MatKhau"));
//                nvbh.setGioiTinh(rs.getString("GioiTinh"));
//                nvbh.setActive(rs.getBoolean("Active"));
//                nvbh.setEmail(rs.getString("Email"));
//                nvbh.setNgayTao(rs.getDate("NgayTao"));
//                nvbh.setSDT(rs.getInt("SDT"));
//                nvbh.setRole(rs.getString("Role"));
//            }
//            System.out.println(nvbh.getNgayTao());
//        }
//        

//         try(Connection conn = JDBCutils.getConn()){
//            PreparedStatement getNVQL = conn.prepareStatement("SELECT *  FROM nguoidung WHERE TaiKhoan = ? AND MatKhau = ?");
//            getNVQL.setString(1, "Huy");
//            getNVQL.setString(2,"456");
//            
//            ResultSet rs = getNVQL.executeQuery();
//            NguoiDung nvbh = null;
//            while(rs.next()){
//                nvbh = new QuanLy();
//                nvbh.setIDNguoiDung(rs.getString("idNguoiDung"));
//                nvbh.setTenNguoiDung(rs.getString("TenNguoiDung"));
//                nvbh.setTaiKhoan(rs.getString("TaiKhoan"));
//                nvbh.setMatKhau(rs.getString("MatKhau"));
//                nvbh.setGioiTinh(rs.getString("GioiTinh"));
//                nvbh.setActive(rs.getBoolean(1));
//                nvbh.setEmail(rs.getString("Email"));
//                nvbh.setNgayTao(rs.getDate("NgayTao"));
//                nvbh.setSDT(rs.getInt("SDT"));
//                nvbh.setRole(rs.getString("Role"));
//            }
//           System.out.println(nvbh.getNgayTao());
//        }
//    }
       
    
