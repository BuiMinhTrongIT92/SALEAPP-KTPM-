/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.HangHoa;
import com.ktpm.pojo.LoaiHH;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import utils.JDBCutils;



/**
 *
 * @author ACER
 */
public class LoaiHHService {
    public List<LoaiHH> getLoaiHH() throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM loaihanghoa");
            List<LoaiHH> loaiHH = new ArrayList<>();
            LoaiHH loaihh = null;
            while(rs.next()){
                String Id = rs.getString("idLoaiHH");
                String TenLoaiHH = rs.getString("TenLoaiHH");
                String DonVi = rs.getString("DonVi");
                boolean Activ = rs.getBoolean("Active");
                loaihh = new LoaiHH(Id,TenLoaiHH,DonVi,Activ);
                if(loaihh.isActive() == true){
                    loaiHH.add(loaihh);
                }
            }
            return loaiHH;
        }
        
    }
    public int xoaLoaiHH(String lId) throws SQLException {
       try (Connection conn = JDBCutils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM loaihanghoa WHERE idLoaiHH =? AND Active = ?");
           stm.setString(1, lId);
           stm.setBoolean(2, false);
           
           int i = stm.executeUpdate();  
           return i;
       }    
   }
    public List<HangHoa> getLoaiHHByLoai(String loaihh) throws SQLException{
       try(Connection conn = JDBCutils.getConn()){
    
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa WHERE idLoaiHH in (SELECT idLoaiHH FROM loaihanghoa WHERE TenLoaiHH = N? and Active = true) and Active = true");
            stm.setString(1,loaihh);
            ResultSet rs = stm.executeQuery();
            List<HangHoa> hanghoa = new ArrayList<>();
            HangHoa hh = null;
            while(rs.next()){
                String Id = rs.getString("idHangHoa");
                String TenLoaiHH = rs.getString("TenHangHoa");
                Double Gia = rs.getDouble("Gia");
                String XuatXu = rs.getString("XuatXu");
                String IDLoaiHH = rs.getString("idLoaiHH");
                String AnhHH = rs.getString("AnhHH");
                Double SL = rs.getDouble("SoLuong");
                Double KG = rs.getDouble("KG");
                boolean Activ = rs.getBoolean("Active");
                hh = new HangHoa(Id,TenLoaiHH,Gia,XuatXu,IDLoaiHH,AnhHH,SL,KG,Activ);
                if(hh.isActive() == true){
                    hanghoa.add(hh);
                }
            }
            return hanghoa;
        }
    }
    public double getSL (String getSLloaihh) throws SQLException{
        double kq =0;
        try(Connection conn = JDBCutils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT KG,SoLuong FROM hanghoa WHERE TenHangHoa = N? and Active = true");
            stm.setString(1, getSLloaihh);
            ResultSet rs = stm.executeQuery();
            double sl = 0;
            double kg = 0;
            while(rs.next()){
                sl = rs.getDouble("KG");
                kg = rs.getDouble("Soluong");
            }
            if(sl == 0)
                kq = kg;
            if(kg == 0){
                kq = sl;
            }
            return kq;
        }
    }

     public boolean getAllNameImg(String imgname) throws SQLException{
            String path = new File("src/main/resources/souresImage").getAbsolutePath();
            File[] file = new File(path).listFiles();
            List<String> kq = new ArrayList<>();
            boolean fil = false;
            for(int i =0;i< file.length;i++){
                if(file[i].getName().contains(imgname + ".jpg")){
                    fil = true;
                }
            }
            return fil;
    }
   
    public List<LoaiHH> getLoaiHH(String kwLHH, Boolean active) throws SQLException {
        List<LoaiHH> listLoaiHH = new ArrayList<>();
        try (Connection conn = JDBCutils.getConn()) {
            String sql = "SELECT * FROM loaihanghoa WHERE Active = ?";
            
            if (kwLHH != null && !kwLHH.isEmpty()) {
                sql = "SELECT * FROM loaihanghoa WHERE Active = ? AND TenLoaiHH like concat('%', ?, '%') OR Active = ? AND DonVi like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, active);
            
            if (kwLHH != null && !kwLHH.isEmpty()) {
                    stm.setBoolean(1, active);
                    stm.setString(2, kwLHH);
                    stm.setBoolean(3, active);
                    stm.setString(4, kwLHH); 
            }
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                while (rs.next()) {
                    LoaiHH l = new LoaiHH(rs.getString("idLoaiHH"), 
                            rs.getString("TenLoaiHH"), 
                            rs.getString("DonVi"), 
                            rs.getBoolean("Active"));
                    listLoaiHH.add(l);
                }
            }              
        }
        return listLoaiHH;  
    }
    public void themLoaiHH(LoaiHH lhh) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "INSERT INTO loaihanghoa (idLoaiHH, TenLoaiHH, DonVi, Active) VALUES(?, ?, ?, ?)";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, lhh.getIDloaiHH());
            stm.setString(2, lhh.getTenLoaiHH());
            stm.setString(3, lhh.getDonVi());
            stm.setBoolean(4, true);
            
            stm.executeUpdate();
            
            conn.commit();
        }     
    }
    public int capNhatLoaiHH(LoaiHH l) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE loaihanghoa SET TenLoaiHH = ?, DonVi = ? WHERE idLoaiHH = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, l.getTenLoaiHH());
            stm.setString(2, l.getDonVi());
            stm.setString(3, l.getIDloaiHH());
            
            int i = stm.executeUpdate();
            conn.commit();
            return i;
        }
    }
    public int xoaLoaiHH_TamThoi(String lId) throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE loaihanghoa SET Active = ? WHERE idLoaiHH = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setString(2, lId);
            
            int i = stm.executeUpdate();
            conn.commit();
            return i;   
        }
    }
}
           



