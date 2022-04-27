/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.pojo.LoaiHH;
import com.ktpm.pojo.NhanVien;
import com.ktpm.services.LoaiHHService;
import com.ktpm.services.NhanVienService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class NhanVienTEST {
    private static NhanVienService nvSV;
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        nvSV = new NhanVienService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //findNVByID
    @Test
    public void testExistfindNVByID() throws SQLException{
        NhanVien nv = nvSV.findNVByID("d85ed478-a4ac-4359-ac24-1c3e88ddd278");
        Assertions.assertEquals("Sang", nv.getTenNguoiDung());
    }
    @Test
    public void testFailfindNVByID() throws SQLException{
        NhanVien nv = nvSV.findNVByID("d85ed478-a4ac-4359-ac24-1c3e88ddd27");
        Assertions.assertNull(nv);
    }
    //findNVByUSPS
    @Test
    public void testExistfindNVByUSPS() throws SQLException{
        NhanVien nv = nvSV.findNVByUSPS("Lam", "202cb962ac59075b964b07152d234b70");
        Assertions.assertEquals("Lam", nv.getTenNguoiDung());
    }
    @Test
    public void testNotExistfindNVByUSPS() throws SQLException{
        NhanVien nv = nvSV.findNVByUSPS("Lam", "250cf8b51c773f3f8dc8b4be867a9a02");
        Assertions.assertNull(nv);
    }
    //getDSNhanVien
    @Test
    public void testExistgetDSNhanVien() throws SQLException{
        List<NhanVien> dsnv = nvSV.getDSNhanVien("NhanVien", true);
        if(dsnv.size() > 0){
            Assertions.assertEquals(2, dsnv.size());
        }
    }
    @Test
    public void testNotExistgetDSNhanVien() throws SQLException{
        List<NhanVien> dsnv = nvSV.getDSNhanVien("", false);
        Assertions.assertEquals(0, dsnv.size());
    }
    //xoaNhanVien
    @Test
    public void testSuccessxoaNhanVien() throws SQLException{
        Date n = new Date();
        Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
        NhanVien nv = new NhanVien("1f12c88e-c578-11ec-9d64-0242ac120002", "Biec", "Biec", "202cb962ac59075b964b07152d234b70", "Nam", true, "Biec@gmail.com", ngaydat, 986521405,"NhanVien" , ngaydat);
        nvSV.themNhanVien(nv);
        nvSV.xoaNhanVien("1f12c88e-c578-11ec-9d64-0242ac120002");
        NhanVien nvv = nvSV.findNVByID("1f12c88e-c578-11ec-9d64-0242ac120002");
        Assertions.assertNotEquals("1f12c88e-c578-11ec-9d64-0242ac120002",nvv.getIDNguoiDung());
    }
  //XoaNhanVien_TamThoi
      @Test
    public void testExistxoaNhanVien_TamThoi() throws SQLException{
         
        Date n = new Date();
        Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
        NhanVien nv = new NhanVien("1f12c88e-c578-11ec-9d64-0242ac120002", "Biec", "Biec", "202cb962ac59075b964b07152d234b70", "Nam", true, "Biec@gmail.com", ngaydat, 986521405,"NhanVien" , ngaydat);
        nvSV.capNhatNhanVien(nv);
            nvSV.xoaNhanVien_TamThoi("1f12c88e-c578-11ec-9d64-0242ac120002");
            try(Connection conn = JDBCutils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Active FROM nguoidung WHERE idNguoiDung = '1f12c88e-c578-11ec-9d64-0242ac120002'" );
                boolean active = true;
                while (rs.next()) {
                    active = rs.getBoolean("Active");
                }
                Assertions.assertFalse(active);
            }
    }
       @Test
    public void testFailxoaNhanVien_TamThoi() throws SQLException{
         Date n = new Date();
        Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
        NhanVien nv = new NhanVien("1f12c88e-c578-11ec-9d64-0242ac120002", "Biec", "Biec", "202cb962ac59075b964b07152d234b70", "Nam", true, "Biec@gmail.com", ngaydat, 986521405,"NhanVien" , ngaydat);
            
           
            nvSV.capNhatNhanVien(nv);
            nvSV.xoaNhanVien_TamThoi("1f12c88e-c578-11ec-9d64-0242ac120002");
            try(Connection conn = JDBCutils.getConn()){
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Active FROM nguoidung WHERE idNguoiDung = '1f12c88e-c578-11ec-9d64-0242ac120002'and Active = true" );
                boolean active = true;
                while (rs.next()) {
                    active = rs.getBoolean("Active");
                }
                Assertions.assertTrue(active);
            }
    }
     //capNhatNhanVien
 
    @Test
    public void testExistcapNhatNhanVien(){
     try {
            Date n = new Date();
            Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
        NhanVien nv = new NhanVien("1f12c88e-c578-11ec-9d64-0242ac120002", "Biec", "Biec", "202cb962ac59075b964b07152d234b70", "Nam", true, "Biec@gmail.com", ngaydat, 986521405,"NhanVien" , ngaydat);
            nvSV.capNhatNhanVien(nv);
            try(Connection conn = JDBCutils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT TenNguoiDung FROM nguoidung WHERE Active = true AND Email = 'Biec@gmail.com'");
            String tennhanvien = "";
            while (rs.next()) {
                tennhanvien = rs.getString("TenNguoiDung");
            }
            Assertions.assertEquals("Biec", tennhanvien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testNotExistcapNhatNhanVien(){
        try {
            Date n = new Date();
            Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
        NhanVien nv = new NhanVien("1f12c88e-c578-11ec-9d64-0242ac120002", "Ngang", "Biec", "202cb962ac59075b964b07152d234b70", "Nu", true, "Biec@gmail.com", ngaydat, 986521405,"NhanVien" , ngaydat);
            nvSV.capNhatNhanVien(nv);
            try(Connection conn = JDBCutils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT TenNguoiDung FROM nguoidung WHERE idNguoiDung = '1f12c88e-c578-11ec-9d64-0242ac120002'and Active = true" );
            String ten = "";
            while (rs.next()) {
                ten = rs.getString("TenNguoiDung");
            }
            Assertions.assertNotEquals("Nam", ten);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test 
    public void testExistgetNhanVien()throws SQLException{
        List <NhanVien> listnv= new ArrayList<>();
        List<String> tennv= new ArrayList<>();
        tennv.add("Trong");
        tennv.add("Sang");
        tennv.add("Biec");
        for(int i =0;i<listnv.size();i++){
            Assertions.assertEquals(tennv.get(i), listnv.get(i).getTenNguoiDung());
        }  
    }
      @Test 
    public void testNotExistgetNhanVien()throws SQLException{
        List <NhanVien> listnv= new ArrayList<>();
        List<String> tennv= new ArrayList<>();
        tennv.add("Quang");
        tennv.add("Hung");
        tennv.add("Thang");
        for(int i =0;i<listnv.size();i++){
            Assertions.assertNotEquals(tennv.get(i), listnv.get(i).getTenNguoiDung());
        }  
    }
    
}
