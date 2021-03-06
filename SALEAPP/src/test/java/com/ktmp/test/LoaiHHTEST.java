/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.pojo.HangHoa;
import com.ktpm.pojo.LoaiHH;
import com.ktpm.services.KhuyenMaiService;
import com.ktpm.services.LoaiHHService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.JDBCutils;
import java.lang.Exception;

/**
 *
 * @author ACER
 */
public class LoaiHHTEST {
    private static LoaiHHService loaihhSV;
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        loaihhSV = new LoaiHHService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //getLoaiHH
    @Test
    public void testExistgetLoaiHH() throws SQLException{
        List<LoaiHH> loaiHH = loaihhSV.getLoaiHH();
        List<String> tenloaihh = new ArrayList<>();
        tenloaihh.add("Nước ngọt");
        tenloaihh.add("Bánh");
        tenloaihh.add("Sữa");
        for(int i =0;i<loaiHH.size();i++){
            Assertions.assertEquals(tenloaihh.get(i), loaiHH.get(i).getTenLoaiHH());
        }
    }
    @Test
    public void testNotExistgetLoaiHH() throws SQLException{
        List<LoaiHH> loaiHH = loaihhSV.getLoaiHH();
        List<String> tenloaihh = new ArrayList<>();
        tenloaihh.add("Dầu ăn");
        tenloaihh.add("Kẹo");
        tenloaihh.add("Nước mắm");
        for(int i =0;i<loaiHH.size();i++){
            Assertions.assertNotEquals(tenloaihh.get(i), loaiHH.get(i).getTenLoaiHH());
        }
    }
    //getLoaiHHByLoai
    @Test
    public void testGetLoaiHHByLoai() throws SQLException{
        List<HangHoa> hangHoa = loaihhSV.getLoaiHHByLoai("Nước ngọt");
        List<String> tenHangHoa = new ArrayList<>();
        tenHangHoa.add("Pepsi");
        tenHangHoa.add("Spire");
        for (int i=0;i<tenHangHoa.size();i++){
            Assertions.assertEquals(hangHoa.get(i).getTenHangHoa(), tenHangHoa.get(i));
        }
        hangHoa = loaihhSV.getLoaiHHByLoai("sữa");
        tenHangHoa.clear();
        tenHangHoa.add("Sữa");
        for (int i=0;i<tenHangHoa.size();i++){
            Assertions.assertEquals(hangHoa.get(i).getTenHangHoa(), tenHangHoa.get(i));
        }
        hangHoa = loaihhSV.getLoaiHHByLoai("Bánh");
        tenHangHoa.clear();
        tenHangHoa.add("Snack Hành");
        tenHangHoa.add("SnackLay");
        for (int i=0;i<tenHangHoa.size();i++){
            Assertions.assertEquals(hangHoa.get(i).getTenHangHoa(), tenHangHoa.get(i));
        }
    }
    public void testNotExistGetLoaiHHByLoai() throws SQLException{
        List<HangHoa> hangHoa = loaihhSV.getLoaiHHByLoai("Nước ngọt");
        List<String> tenHangHoa = new ArrayList<>();
        tenHangHoa.add("Pepsi");
        tenHangHoa.add("Spire");
        for (int i=0;i<tenHangHoa.size();i++){
            Assertions.assertEquals(hangHoa.get(i).getTenHangHoa(), tenHangHoa.get(i));
        }
        hangHoa = loaihhSV.getLoaiHHByLoai("sữa");
        tenHangHoa.clear();
        tenHangHoa.add("Sữa");
        for (int i=0;i<tenHangHoa.size();i++){
            Assertions.assertEquals(hangHoa.get(i).getTenHangHoa(), tenHangHoa.get(i));
        }
        hangHoa = loaihhSV.getLoaiHHByLoai("Bánh");
        tenHangHoa.clear();
        tenHangHoa.add("Snack Hành");
        tenHangHoa.add("SnackLay");
        for (int i=0;i<tenHangHoa.size();i++){
            Assertions.assertEquals(hangHoa.get(i).getTenHangHoa(), tenHangHoa.get(i));
        }
    }
    //GetSL
    @Test
    public void testExistgetSL() throws SQLException{
        double sl = loaihhSV.getSL("Pepsi");
        boolean kq = false;
        if(sl > 0){
            kq = true;
        }
        Assertions.assertTrue(kq);
    }
    @Test
    public void testNotExistgetSL() throws SQLException{
        double sl = loaihhSV.getSL("Sting");
        boolean kq = false;
        if(sl > 0){
            kq = true;
        }
        Assertions.assertFalse(kq);
    }
    //getAllNameImg 
    
    @Test
    public void testExistgetAllNameImg() throws SQLException {
        Assertions.assertTrue(loaihhSV.getAllNameImg("pepsi"));
    }
    @Test
    public void testNotExistgetAllNameImg() throws SQLException {
        Assertions.assertFalse(loaihhSV.getAllNameImg("peps"));
    }

    //themLoaiHH
    @Test
    public void testthemLoaiHH() throws SQLException{
        String id = "";
        LoaiHH lhh = new LoaiHH("4", "Yến", "Lon", true);
        loaihhSV.themLoaiHH(lhh);
        try(Connection conn = JDBCutils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idLoaiHH FROM loaihanghoa WHERE Active = true AND idLoaiHH = '4'");

            while (rs.next()) {
                id = rs.getString("idLoaiHH");
            }
            Assertions.assertEquals("4", id);
        }
    }
    @Test
    public void testNotthemLoaiHH() throws SQLException{
        String erro = "";
        LoaiHH lhh = new LoaiHH("4", "Yến", "Lon", true);
        try {
            loaihhSV.themLoaiHH(lhh);
        } catch (SQLException e) {
            erro = e.getMessage();
        }
        Assertions.assertEquals("Duplicate entry '4' for key 'loaihanghoa.PRIMARY'",erro);
    }
    //capNhatLoaiHH
    @Test
    public void testExistcapNhatLoaiHH(){
        try {
            LoaiHH lhh = new LoaiHH("4", "Yến", "Hũ", true);
            loaihhSV.capNhatLoaiHH(lhh);
            try(Connection conn = JDBCutils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT DonVi FROM loaihanghoa WHERE Active = true AND idLoaiHH = '4'");
            String donvi = "";
            while (rs.next()) {
                donvi = rs.getString("DonVi");
            }
            Assertions.assertEquals("Hũ", donvi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHHTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testNotExistcapNhatLoaiHH(){
        try {
            LoaiHH lhh = new LoaiHH("4", "Yến", "Hũ", true);
            loaihhSV.capNhatLoaiHH(lhh);
            try(Connection conn = JDBCutils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT DonVi FROM loaihanghoa WHERE Active = true AND idLoaiHH = '4'");
                String donvi = "";
                while (rs.next()) {
                    donvi = rs.getString("DonVi");
                }
                Assertions.assertNotEquals("Can", donvi);
                }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHHTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //xoaLoaiHH
    @Test
    public void testSuccessxoaLoaiHH() throws SQLException{
        try {
            try(Connection conn = JDBCutils.getConn()) {
            String sql = "UPDATE loaihanghoa SET Active = ? WHERE idLoaiHH = ?";
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setString(2, "4");
            stm.executeUpdate();
            conn.commit();
          
        }
            loaihhSV.xoaLoaiHH("4");
            try(Connection conn = JDBCutils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT idLoaiHH FROM loaihanghoa WHERE Active = true AND idLoaiHH = '4'");
                String donvi = "";
                while (rs.next()) {
                    donvi = rs.getString("idLoaiHH");
                }
                Assertions.assertNotEquals("4", donvi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiHHTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @Test
    public void testFailxoaLoaiHH() throws SQLException{
            loaihhSV.xoaLoaiHH("4");
            try(Connection conn = JDBCutils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT idLoaiHH FROM loaihanghoa WHERE Active = true AND idLoaiHH = '4'");
                String donvi = "";
                while (rs.next()) {
                    donvi = rs.getString("idLoaiHH");
                }
                Assertions.assertEquals("4", donvi);
            }
    }
    //xoaLoaiHH_TamThoi
    @Test
    public void TestSuccessxoaLoaiHH_TamThoi() throws SQLException{
            LoaiHH lhh = new LoaiHH("3", "Sữa", "Lốc", true);
            loaihhSV.capNhatLoaiHH(lhh);
            loaihhSV.xoaLoaiHH_TamThoi("3");
            try(Connection conn = JDBCutils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Active FROM loaihanghoa WHERE idLoaiHH = '3'");
                boolean donvi = true;
                while (rs.next()) {
                    donvi = rs.getBoolean("Active");
                }
                Assertions.assertFalse(donvi);
            }
    }
    @Test
    public void TestFailxoaLoaiHH_TamThoi() throws SQLException{
            LoaiHH lhh = new LoaiHH("3", "Sữa", "Lốc", false);
            loaihhSV.capNhatLoaiHH(lhh);
            loaihhSV.xoaLoaiHH_TamThoi("3");
            try(Connection conn = JDBCutils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Active FROM loaihanghoa WHERE idLoaiHH = '3' AND Active = true");
                boolean donvi = true;
                while (rs.next()) {
                    donvi = rs.getBoolean("Active");
                }
                Assertions.assertTrue(donvi);
            }
    }
}
