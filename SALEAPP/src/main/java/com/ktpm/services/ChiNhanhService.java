/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.pojo.LoaiHH;
import com.ktpm.pojo.NguoiDung;
import com.ktpm.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCutils;

/**
 *
 * @author NhatTien
 */
public class ChiNhanhService {
    public List<ChiNhanh> getChiNhanh(String kwCN, Boolean active) throws SQLException {
        List<ChiNhanh> listChiNhanh = new ArrayList<>();
        try (Connection conn = JDBCutils.getConn()) {
            String sql = "SELECT * FROM chinhanh WHERE Active = ?";
            
            if (kwCN != null && !kwCN.isEmpty()) {
                sql = "SELECT * FROM chinhanh WHERE Active = ? AND TenChiNhanh like concat('%', ?, '%') OR Active = ? AND DiaChi like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, active);
            
            if (kwCN != null && !kwCN.isEmpty()) {
                    stm.setBoolean(1, active);
                    stm.setString(2, kwCN);
                    stm.setBoolean(3, active);
                    stm.setString(4, kwCN); 
            }
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                ChiNhanh c = new ChiNhanh(rs.getString("idChiNhanh"), 
                        rs.getString("TenChiNhanh"), 
                        rs.getString("DiaChi"),
                        rs.getString("idNguoiDung"), 
                        rs.getBoolean("Active"));
                listChiNhanh.add(c);
            }              
        }
        return listChiNhanh;  
    }

    public void themChiNhanh(ChiNhanh c) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "INSERT INTO chinhanh (idChiNhanh, TenChiNhanh, DiaChi, idNguoiDung, Active) VALUES(?, ?, ?, ?, ?)";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, c.getIDChiNhanh());
            stm.setString(2, c.getTenChiNhanh());
            stm.setString(3, c.getDiaChi());
            stm.setString(4, c.getIDNguoiDung());
            stm.setBoolean(5, true);
            
            stm.executeUpdate();
            
            conn.commit();
        }     
    }   
    
    public int capNhatChiNhanh(ChiNhanh c) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE chinhanh SET TenChiNhanh = ?, DiaChi = ?, idNguoiDung = ? WHERE idChiNhanh = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, c.getTenChiNhanh());
            stm.setString(2, c.getDiaChi());
            stm.setString(3, c.getIDNguoiDung());
            stm.setString(4, c.getIDChiNhanh());

            int i = stm.executeUpdate();
            conn.commit();
            return i;
        }
    }
    
    public int xoaChiNhanh(String cId) throws SQLException {
       try (Connection conn = JDBCutils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM chinhanh WHERE idChiNhanh =? AND Active = ?");
           stm.setString(1, cId);
           stm.setBoolean(2, false);
           
           int i = stm.executeUpdate();  
           return i;
       }    
   }
    
    public int xoaChiNhanh_TamThoi(String cId) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE chinhanh SET Active = ? WHERE idChiNhanh = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setString(2, cId);
            
            int i = stm.executeUpdate();
            conn.commit();
            return i;   
        }
    }

}
