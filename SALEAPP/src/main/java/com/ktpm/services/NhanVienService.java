/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.NguoiDung;
import com.ktpm.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class NhanVienService {
    public NhanVien findNVByID(String id) throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            PreparedStatement getNVBH = conn.prepareStatement("SELECT *  FROM nguoidung WHERE idNguoiDung = ? and ROLE = 'NhanVien'");
            getNVBH.setString(1, id);
            
            ResultSet rs = getNVBH.executeQuery();
            NhanVien nvbh = null;
            while(rs.next()){
                nvbh = new NhanVien(rs.getString("idNguoiDung"), rs.getString("TenNguoiDung"), rs.getString("TaiKhoan"),rs.getString("MatKhau"), rs.getString("GioiTinh"), rs.getBoolean("Active"), rs.getString("Email"),
                        rs.getDate("NgayTao"),rs.getInt("SDT"), rs.getString("Role"),rs.getDate("NgaySinh"));
            }
            return nvbh;
        }
    }
    public NhanVien findNVByUSPS(String username,String password) throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            PreparedStatement getNVBH = conn.prepareStatement("SELECT *  FROM nguoidung WHERE TaiKhoan = ? AND MatKhau = ?");
            getNVBH.setString(1, username.strip());
            getNVBH.setString(2,password.strip());
            
            ResultSet rs = getNVBH.executeQuery();
            NguoiDung nvbh = null;
            while(rs.next()){
                nvbh = new NhanVien();
                nvbh.setIDNguoiDung(rs.getString("idNguoiDung"));
                nvbh.setTenNguoiDung(rs.getString("TenNguoiDung"));
                nvbh.setTaiKhoan(rs.getString("TaiKhoan"));
                nvbh.setMatKhau(rs.getString("MatKhau"));
                nvbh.setGioiTinh(rs.getString("GioiTinh"));
                nvbh.setActive(rs.getBoolean("Active"));
                nvbh.setEmail(rs.getString("Email"));
                nvbh.setNgayTao(rs.getDate("NgayTao"));
                nvbh.setSDT(rs.getInt("SDT"));
                nvbh.setRole(rs.getString("Role"));
                nvbh.setNgaySinh(rs.getDate("NgaySinh"));
            }
            return (NhanVien) nvbh;
        }
    }
    public List<NhanVien> getDSNhanVien(String role, Boolean active) throws SQLException {
        List<NhanVien> listNV = new ArrayList<>();
        String sql = "SELECT * FROM nguoidung WHERE Role = ? AND Active = ?";
        
        try (Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, role);
            stm.setBoolean(2, true);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                String idNguoiDung = rs.getString("idNguoiDung");
                String tenNguoiDung = rs.getString("TenNguoiDung");
                String taiKhoan = rs.getString("TaiKhoan");
                String matKhau = rs.getString("MatKhau");
                String gioiTinh = rs.getString("GioiTinh");
                boolean trangThai = rs.getBoolean("Active");
                String email = rs.getString("Email");
                Date ngayTao = rs.getDate("NgayTao");
                int SDT = rs.getInt("SDT");
                Date ngaySinh = rs.getDate("NgaySinh");
                
                NhanVien n = new NhanVien(idNguoiDung, tenNguoiDung, taiKhoan, 
                        matKhau, gioiTinh, trangThai, email, ngayTao, SDT, role, ngaySinh);
                
                listNV.add(n); 
            }    
        }
        return listNV;
    }
    public int xoaNhanVien(String cId) throws SQLException {
       try (Connection conn = JDBCutils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM nguoidung WHERE idNguoiDung = ? AND Active = ?");
           stm.setString(1, cId);
           stm.setBoolean(2, false);
           
           int i = stm.executeUpdate();  
           return i;
       }    
    }
    public List<NhanVien> getNhanVien(String kwNV, Boolean active) throws SQLException {
        List<NhanVien> listNhanVien = new ArrayList<>();
        try (Connection conn = JDBCutils.getConn()) {
            String sql = "SELECT * FROM nguoidung WHERE Active = ?";
            
            if (kwNV != null && !kwNV.isEmpty()) {
                sql = "SELECT * FROM nguoidung WHERE Active = ? AND TenNguoiDung like concat('%', ?, '%') OR Active = ? AND TaiKhoan like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, active);
            
            if (kwNV != null && !kwNV.isEmpty()) {
                    stm.setBoolean(1, active);
                    stm.setString(2, kwNV);
                    stm.setBoolean(3, active);
                    stm.setString(4, kwNV); 
            }
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                String idNguoiDung = rs.getString("idNguoiDung");
                String tenNguoiDung = rs.getString("TenNguoiDung");
                String taiKhoan = rs.getString("TaiKhoan");
                String matKhau = rs.getString("MatKhau");
                String gioiTinh = rs.getString("GioiTinh");
                boolean trangThai = rs.getBoolean("Active");
                String email = rs.getString("Email");
                Date NgayTao = rs.getDate("NgayTao");
                int SDT = rs.getInt("SDT");
                String role = rs.getString("Role");
                Date NgaySinh = rs.getDate("NgaySinh");
                
                NhanVien n = new NhanVien(idNguoiDung, tenNguoiDung, taiKhoan, 
                        matKhau, gioiTinh, trangThai, email, NgayTao, SDT, role, NgaySinh);
                
                listNhanVien.add(n);
            }              
        }
        return listNhanVien;   
    }
    public void themNhanVien(NhanVien n) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            
            String sql = "INSERT INTO nguoidung (idNguoiDung, TenNguoiDung, TaiKhoan, MatKhau, NgaySinh, GioiTinh, Active, Email, NgayTao, SDT, Role)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, n.getIDNguoiDung());
            stm.setString(2, n.getTenNguoiDung());
            stm.setString(3, n.getTaiKhoan());
            stm.setString(4, n.getMatKhau());
            stm.setDate(5, new java.sql.Date(n.getNgaySinh().getTime()));
            stm.setString(6, n.getGioiTinh());
            stm.setBoolean(7, true);
            stm.setString(8, n.getEmail());
            stm.setDate(9, new java.sql.Date(n.getNgayTao().getTime()));
            stm.setInt(10, n.getSDT());
            stm.setString(11, n.getRole());
            
            
            stm.executeUpdate();
            
            conn.commit();
        }
    }
    
    public int capNhatNhanVien(NhanVien nv) throws SQLException {
        try (Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE nguoidung SET TenNguoiDung = ?,"
                    + "NgaySinh = ?, GioiTinh = ?, Email = ?, "
                    + "SDT = ?, Role = ? WHERE idNguoiDung = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getTenNguoiDung());
            stm.setDate(2, new java.sql.Date(nv.getNgaySinh().getTime()));
            stm.setString(3, nv.getGioiTinh());
            stm.setString(4, nv.getEmail());
            stm.setInt(5, nv.getSDT());
            stm.setString(6, nv.getRole());
            stm.setString(7, nv.getIDNguoiDung());          
            
            
            int i = stm.executeUpdate();
            conn.commit();
            return i;
        }
    }
    public int xoaNhanVien_TamThoi(String nId) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE nguoidung SET Active = ? WHERE idNguoiDung = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setString(2, nId);
            
            int i = stm.executeUpdate();
            conn.commit();
            return i;   
        }
    }
}
