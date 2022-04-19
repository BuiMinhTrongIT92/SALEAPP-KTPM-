/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.NguoiDung;
import com.ktpm.pojo.NhanVien;
import com.ktpm.pojo.QuanLy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class QuanLyService {
    
    
     public QuanLy findQLByUSPS(String username,String password) throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            PreparedStatement getNVQL = conn.prepareStatement("SELECT *  FROM nguoidung WHERE TaiKhoan = ? AND MatKhau = ?");
            getNVQL.setString(1, username.strip());
            getNVQL.setString(2,password.strip());
            
            ResultSet rs = getNVQL.executeQuery();
            NguoiDung nvql = null;
            while(rs.next()){
                nvql = new QuanLy();
                nvql.setIDNguoiDung(rs.getString("idNguoiDung"));
                nvql.setTenNguoiDung(rs.getString("TenNguoiDung"));
                nvql.setTaiKhoan(rs.getString("TaiKhoan"));
                nvql.setMatKhau(rs.getString("MatKhau"));
                nvql.setGioiTinh(rs.getString("GioiTinh"));
                nvql.setActive(rs.getBoolean("Active"));
                nvql.setEmail(rs.getString("Email"));
                nvql.setNgayTao(rs.getDate("NgayTao"));
                nvql.setSDT(rs.getInt("SDT"));
                nvql.setRole(rs.getString("Role"));
                nvql.setNgaySinh(rs.getDate("NgaySinh"));
            }
            return (QuanLy) nvql;
        }
        
    }
}
