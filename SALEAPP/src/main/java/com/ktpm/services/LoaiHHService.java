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
        try {
            ImageView imgv = new ImageView("/souresImage/" + imgname + ".jpg");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
  
}
           



