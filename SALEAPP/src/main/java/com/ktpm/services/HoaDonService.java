/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class HoaDonService {
    public boolean saveHoaDon(String IdDonHang,Date NgayTao,String TenNV,double SoLuong,
            double ThanhTien,double KhuyenMai,double TienKH,double TienThoi,String IDNguoiDung,String IDKhachHang) throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO donhang(idDonHang,NgayTao,TenNV,SoLuong,ThanhTien,KhuyenMai,TienKH,TienThoi,idNguoiDung,idKhachHang) values (?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, IdDonHang);
            stm.setDate(2, (java.sql.Date) NgayTao);
            stm.setString(3, TenNV);
            stm.setDouble(4, SoLuong);
            stm.setDouble(5, ThanhTien);
            stm.setDouble(6, KhuyenMai);
            stm.setDouble(7, TienKH);
            stm.setDouble(8, TienThoi);
            stm.setString(9, IDNguoiDung);
            stm.setString(10, IDKhachHang);
            
            if(stm.executeUpdate() >0 ){
                return true;
            }else
                return false;
        }
    }
    
    public void upDateHH_DH(String idDH, String idHH) throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO donhang_hanghoa(idDonHang,idHangHoa) VALUES(N?,N?)");
            stm.setString(1,idDH);
            stm.setString(2,idHH);
            conn.setAutoCommit(false);
            stm.executeUpdate();
            conn.commit();
        }
    }
}
