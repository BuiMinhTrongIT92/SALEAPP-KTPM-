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
    
}

