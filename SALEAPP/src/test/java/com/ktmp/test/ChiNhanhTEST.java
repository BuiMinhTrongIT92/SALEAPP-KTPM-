/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.pojo.ChiNhanh;
import com.ktpm.services.ChiNhanhService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JDBCutils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author NhatTien
 */
public class ChiNhanhTEST {
    private static ChiNhanhService chinhanhSV;
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        chinhanhSV = new ChiNhanhService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testExistgetChiNhanh() throws SQLException {
        try(Connection conn = JDBCutils.getConn()) {
            List<ChiNhanh> chiNhanh = chinhanhSV.getChiNhanh(null, true);
            List<String> tenChiNhanh = new ArrayList<>();
            tenChiNhanh.add("Chi Nhánh 1");
            tenChiNhanh.add("Chi Nhánh 2");
//            tenChiNhanh.add("Chi Nhánh 3");
            for (int i = 0; i < tenChiNhanh.size(); i++) {
                Assertions.assertEquals(tenChiNhanh.get(i), chiNhanh.get(i).getTenChiNhanh());
            }
        }
    }
    
    @Test
    public void testSuccessThemChiNhanh() throws SQLException {
        String idChiNhanh;
        try(Connection conn = JDBCutils.getConn()) {
            ChiNhanh cn = new ChiNhanh("3", "Chi Nhánh 3", "24 Trường Chinh", "d85ed478-a4ac-4359-ac24-1c3e88ddd278", true);
            chinhanhSV.themChiNhanh(cn);
            
            String sql = "SELECT idChiNhanh FROM chinhanh WHERE TenChiNhanh = N?";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "Chi Nhánh 3");
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Assertions.assertEquals("3", rs.getString("idChiNhanh"));    
            }
        }
    }
    
    @Test
    public void testFailThemChiNhanh() throws SQLException {
        ChiNhanh cn = new ChiNhanh("5", "Chi Nhánh 6", "24 Lê Lợi", "1", true);

        try (Connection conn = JDBCutils.getConn()) {
            chinhanhSV.themChiNhanh(cn); 
        } catch (SQLException ex) {
            Assertions.assertNotEquals("Duplicate entry '5' for key 'chinhanh.idChiNhanh_UNIQUE'", ex.getMessage().toString());
        }
    }
    
    @Test
    public void testSuccessCapNhat() throws SQLException {
        String err;
        String idChiNhanh = "2";
        ChiNhanh cn = new ChiNhanh("2", "Chi Nhánh 5", "24 Lê Lợi, P.6, Q. Gò Vấp", "1", true);
        
        try (Connection conn = JDBCutils.getConn()){
            chinhanhSV.capNhatChiNhanh(cn);
            
            String sql = "SELECT idChiNhanh FROM chinhanh WHERE DiaChi = N?";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, "24 Lê Lợi, P.6, Q. Gò Vấp");
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Assertions.assertEquals(idChiNhanh, rs.getString("idChiNhanh"));    
            }  
        } catch(SQLException ex) {
           ex.getMessage();
        }
    }
     
    
//    @Test
//    public void testExistgetLoaiHH() throws SQLException{
//        List<LoaiHH> loaiHH = loaihhSV.getLoaiHH();
//        List<String> tenloaihh = new ArrayList<>();
//        tenloaihh.add("Nước ngọt");
//        tenloaihh.add("Bánh");
//        tenloaihh.add("Sữa");
//        for(int i =0;i<loaiHH.size();i++){
//            Assertions.assertEquals(tenloaihh.get(i), loaiHH.get(i).getTenLoaiHH());
//        }
//    }
    
//     @Test
//    public void testExistcapNhatLoaiHH(){
//        try {
//            LoaiHH lhh = new LoaiHH("4", "Yến", "Hũ", true);
//            loaihhSV.capNhatLoaiHH(lhh);
//            try(Connection conn = JDBCutils.getConn()){
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT DonVi FROM loaihanghoa WHERE Active = true AND idLoaiHH = '4'");
//            String donvi = "";
//            while (rs.next()) {
//                donvi = rs.getString("DonVi");
//            }
//            Assertions.assertEquals("Hũ", donvi);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(LoaiHHTEST.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
}