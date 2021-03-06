/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.HangHoa;
import com.ktpm.pojo.LoaiHH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class HangHoaService {
    public String getIDHH(String TenHH) throws SQLException{
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT idHangHoa FROM hanghoa where TenHangHoa = N? and Active = true ");
            stm.setString(1, TenHH);
            ResultSet rs = stm.executeQuery();
            String idHH =  "";
            while(rs.next()){
                idHH = rs.getString("idHangHoa");
            }
           return idHH;
        }
    }
    
    public HangHoa getHangHoa(String IdHH) throws SQLException{
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa where idHangHoa = N? and Active = true");
            stm.setString(1, IdHH);
            ResultSet rs = stm.executeQuery();
            HangHoa hh = null;
            while(rs.next()){
                hh = new HangHoa();
                hh.setIdHangHoa(rs.getString("idHangHoa"));
                hh.setTenHangHoa(rs.getString("TenHangHoa"));
                hh.setGia(rs.getDouble("Gia"));
                hh.setXuatXu(rs.getString("XuatXu"));
                hh.setIDLoaiHH(rs.getString("idLoaiHH"));
                hh.setAnhHH(rs.getString("AnhHH"));
                hh.setSL(rs.getDouble("SoLuong"));
                hh.setKG(rs.getDouble("KG"));
                hh.setActive(rs.getBoolean("Active"));
            }
           return hh;
        }
    }
    public boolean isHangHoa(List<HangHoa> listHH, String tenHH){
        boolean kq = false;
        for(HangHoa hh : listHH){
            if(hh.getTenHangHoa().toLowerCase().contains(tenHH.toLowerCase()) && hh.isActive() == true)
                kq = true;
        }
        return kq;
    }
    public List<HangHoa> getHHByKey(String kw) throws SQLException{
       try(Connection conn = JDBCutils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa hh, loaihanghoa lhh where hh.idLoaiHH = lhh.idLoaiHH and "
                    + "lhh.Active = true and (TenHangHoa like concat('%', N?, '%') or idHangHoa like concat('%', N?, '%'))");
            stm.setString(1, kw);
            stm.setString(2,kw);
            if (kw == null)
               kw = "";
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            HangHoa hh = null;
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
                boolean Actv = rs.getBoolean("Active");
                hh = new HangHoa(Id,TenLoaiHH,Gia,XuatXu,IDLoaiHH,AnhHH,SL,KG,Actv);
                if(hh.isActive() == true){
                    hanghoa.add(hh);
                }
            }
            return hanghoa;
        }
    }
    public List<HangHoa> getHHByLoai(String loaiHH, String kw) throws SQLException{
       try(Connection conn = JDBCutils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa hh, loaihanghoa lhh where hh.idLoaiHH = lhh.idLoaiHH and lhh.Active = true and (TenHangHoa like concat('%', N?, '%') "
                    + "or idHangHoa like concat('%', N?, '%')) and TenLoaiHH = N?");
            stm.setString(1, kw);
            stm.setString(2,kw);
            stm.setString(3,loaiHH);
            if (kw == null)
               kw = "";
            stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            HangHoa hh = null;
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
                boolean Actv = rs.getBoolean("Active");
                hh = new HangHoa(Id,TenLoaiHH,Gia,XuatXu,IDLoaiHH,AnhHH,SL,KG,Actv);
                if(hh.isActive() == true){
                    hanghoa.add(hh);
                }
            }
            return hanghoa;
        }
    }
    public double getSLCheck (String getSLloaihh) throws SQLException{
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT SoLuong FROM hanghoa WHERE TenHangHoa = N? and Active = true");
            stm.setString(1, getSLloaihh);
            ResultSet rs = stm.executeQuery();
            double sl = 0;
            while(rs.next()){
                sl = rs.getDouble("Soluong");
            }
            return sl;
        }
    }
    public double getKGCheck (String getSLloaihh) throws SQLException{
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT KG FROM hanghoa WHERE TenHangHoa = N? and Active = true");
            stm.setString(1, getSLloaihh);
            ResultSet rs = stm.executeQuery();
            double kg = 0;
            while(rs.next()){
                kg = rs.getDouble("KG");
            }
            return kg;
        }
    }
    public double checkGiamGia(String idHH,String ngaybd) throws SQLException{
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT GiamGia FROM hanghoa_quidinh hh, quidinh qd where hh.idQuiDinh = qd.idQuiDinh and Active = true and idHangHoa = N? and ? > qd.NgayBD and ? < qd.NgayKT");
            stm.setString(1, idHH);
            stm.setString(2, ngaybd);
            stm.setString(3, ngaybd);
            ResultSet rs = stm.executeQuery();
            double kg = 0;
            while(rs.next()){
                kg = rs.getDouble("GiamGia");
            }
            return kg;
        }
    }
    public void UpdateHH(HangHoa hh) throws SQLException{
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("UPDATE HangHoa SET SoLuong = Soluong - ?, KG = KG - ? WHERE idHangHoa = ?");
            conn.setAutoCommit(false);
            stm.setDouble(1, hh.getSL());
            stm.setDouble(2, hh.getKG());
            stm.setString(3, hh.getIdHangHoa());
            stm.executeUpdate();
            conn.commit();
        }
    }
    public List<HangHoa> getHangHoa(String kwHH, Boolean active) throws SQLException {
        List<HangHoa> listHangHoa = new ArrayList<>();
        try (Connection conn = JDBCutils.getConn()) {
            String sql = "SELECT * FROM hanghoa WHERE Active = ?";
            
            if (kwHH != null && !kwHH.isEmpty()) {
                sql = "SELECT * FROM hanghoa WHERE Active = ? AND TenHangHoa like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, active);
            
            if (kwHH != null && !kwHH.isEmpty()) {
                    stm.setBoolean(1, active);
                    stm.setString(2, kwHH);
            }
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                HangHoa h = new HangHoa(rs.getString("idHangHoa"), 
                        rs.getString("TenHangHoa"), rs.getDouble("Gia"), 
                        rs.getString("XuatXu"), rs.getString("idLoaiHH"), 
                        rs.getString("AnhHH"), rs.getDouble("SoLuong"), 
                        rs.getDouble("KG"), rs.getBoolean("Active"));                
                listHangHoa.add(h);
            }              
        }
        return listHangHoa;  
    }
    public int xoaHangHoa(String hId) throws SQLException {
       try (Connection conn = JDBCutils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM hanghoa WHERE idHangHoa =? AND Active = ?");
           stm.setString(1, hId);
           stm.setBoolean(2, false);
           
           int i = stm.executeUpdate();  
           return i;
       }    
   }
    public void themHangHoa(HangHoa h) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "INSERT INTO hanghoa (idHangHoa, TenHangHoa, Gia, XuatXu, idLoaiHH, AnhHH, SoLuong, KG, Active) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, h.getIdHangHoa());
            stm.setString(2, h.getTenHangHoa());
            stm.setDouble(3, h.getGia());
            stm.setString(4, h.getXuatXu());
            stm.setString(5, h.getIDLoaiHH());
            stm.setString(6, h.getAnhHH());
            stm.setDouble(7, h.getSL());
            stm.setDouble(8, h.getKG());
            stm.setBoolean(9, true);
            
            stm.executeUpdate();
            
            conn.commit();
        }     
    }
    public int capNhatHangHoa(HangHoa hh) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE hanghoa SET idLoaiHH = ?, TenHangHoa = ?, Gia = ?, XuatXu = ?, AnhHH = ?, SoLuong = ?, KG = ? WHERE idHangHoa = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, hh.getIDLoaiHH());
            stm.setString(2, hh.getTenHangHoa());
            stm.setDouble(3, hh.getGia());
            stm.setString(4, hh.getXuatXu());
            stm.setString(5, hh.getAnhHH());
            stm.setDouble(6, hh.getSL());
            stm.setDouble(7, hh.getKG());
            stm.setString(8, hh.getIdHangHoa());
            
            int i = stm.executeUpdate();
            conn.commit();
            return i;
        }
    }
    public int xoaHangHoa_TamThoi(String hId) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE hanghoa SET Active = ? WHERE idHangHoa = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setString(2, hId);
            
            int i = stm.executeUpdate();
            conn.commit();
            return i;   
        }
    }
<<<<<<< HEAD
=======
    
>>>>>>> e8967775d65aafbc1e619615a5047e12d0665059
    public List<HangHoa> getHHTHeoLoai(String idLoaiHH) throws SQLException {
        List<HangHoa> listHangHoa = new ArrayList<>();
        try (Connection conn = JDBCutils.getConn()) {
            String sql = "SELECT * FROM hanghoa WHERE Active = true AND idLoaiHH = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, idLoaiHH);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                HangHoa h = new HangHoa(rs.getString("idHangHoa"), 
                        rs.getString("TenHangHoa"), rs.getDouble("Gia"), 
                        rs.getString("XuatXu"), rs.getString("idLoaiHH"), 
                        rs.getString("AnhHH"), rs.getDouble("SoLuong"), 
                        rs.getDouble("KG"), rs.getBoolean("Active"));                
                listHangHoa.add(h);
            }    
        }
        return listHangHoa;
     }
}

