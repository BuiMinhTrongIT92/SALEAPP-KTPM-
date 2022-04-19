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
}
