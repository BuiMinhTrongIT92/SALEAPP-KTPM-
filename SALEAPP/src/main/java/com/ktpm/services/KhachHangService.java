/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.KhachHang;
import com.ktpm.pojo.NguoiDung;
import com.ktpm.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class KhachHangService {
    public List<KhachHang> getKHByTen(String kw) throws SQLException{
         try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * from nguoidung where TenNguoiDung = N? ");
            stm.setString(1, kw);


            ResultSet rs = stm.executeQuery();
            List<KhachHang> listKH= new ArrayList<>();
            KhachHang kh = null;
            while(rs.next()){
                kh = new KhachHang();
                kh.setIDNguoiDung(rs.getString("idNguoiDung"));
                kh.setTenNguoiDung(rs.getString("TenNguoiDung"));
                kh.setTaiKhoan(rs.getString("TaiKhoan"));
                kh.setMatKhau(rs.getString("MatKhau"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setActive(rs.getBoolean("Active"));
                kh.setEmail(rs.getString("Email"));
                kh.setNgayTao(rs.getDate("NgayTao"));
                kh.setSDT(rs.getInt("SDT"));
                kh.setRole(rs.getString("Role"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                listKH.add(kh);
            }
            return listKH;
        }
    }
    public List<KhachHang> getKHBySDT(String kw) throws SQLException{
         try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * from nguoidung where SDT = N? ");
            stm.setString(1, kw);


            ResultSet rs = stm.executeQuery();
            List<KhachHang> listKH= new ArrayList<>();
            KhachHang kh = null;
            while(rs.next()){
                kh = new KhachHang();
                kh.setIDNguoiDung(rs.getString("idNguoiDung"));
                kh.setTenNguoiDung(rs.getString("TenNguoiDung"));
                kh.setTaiKhoan(rs.getString("TaiKhoan"));
                kh.setMatKhau(rs.getString("MatKhau"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setActive(rs.getBoolean("Active"));
                kh.setEmail(rs.getString("Email"));
                kh.setNgayTao(rs.getDate("NgayTao"));
                kh.setSDT(rs.getInt("SDT"));
                kh.setRole(rs.getString("Role"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                listKH.add(kh);
            }
            return listKH;
        }
    }
}
