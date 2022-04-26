/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.services.HoaDonService;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import utils.JDBCutils;

public class HoaDonTEST {

    private static HoaDonService hd;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        hd = new HoaDonService();
    }

    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSuccessupdateHH_DH() throws SQLException {
        try {
            hd.upDateHH_DH("feda63ff-59d8-4130-ba81-912f10928345", "4");
            try (Connection conn = JDBCutils.getConn()) {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT idHangHoa FROM donhang_hanghoa WHERE idDonHang='feda63ff-59d8-4130-ba81-912f10928345'");
                String hanghoa = "";
                while (rs.next()) {
                    hanghoa = rs.getString("idHangHoa");

                }
                Assertions.assertEquals("4", hanghoa);

            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonTEST.class.toString()).log(Level.SEVERE, null, ex);

        }
    }
    @Test
    public void testSuccesaveHoaDon() throws SQLException {
    try{
        Date n = new Date();
        Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
        hd.saveHoaDon("11",ngaydat, "Lam", 2.0,20000.0 , 0.5, 30000.0,10000.0, "1",null);
     try (Connection conn = JDBCutils.getConn()) {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT idDonHang FROM donhang WHERE idDonHang='11'");
                String tenNV = "";
                while (rs.next()) {
                    tenNV = rs.getString("TenNV");

                }
                Assertions.assertEquals("Lam", tenNV);

            }    
    }catch (SQLException ex) {
            Logger.getLogger(HoaDonTEST.class.toString()).log(Level.SEVERE, null, ex);
        }
    
    }
//    @Test
//    public void testFailsaveHoaDon() throws SQLException {
//    try{
//        Date n = new Date();
//        Date ngaydat = new java.sql.Date(n.getYear(),n.getMonth(),n.getDate());
//        hd.saveHoaDon("11",ngaydat, "Lam", 2.0,20000.0 , 0.5, 30000.0,10000.0, "1",null);
//     try (Connection conn = JDBCutils.getConn()) {
//                Statement stm = conn.createStatement();
//                ResultSet rs = stm.executeQuery("SELECT idDonHang FROM donhang WHERE idDonHang='11'");
//                String tenNV = "";
//                while (rs.next()) {
//                    tenNV = rs.getString("TenNV");
//
//                }
//                Assertions.assertEquals("Lam", tenNV);
//
//            }    
//    }catch (SQLException ex) {
//            Logger.getLogger(HoaDonTEST.class.toString()).log(Level.SEVERE, null, ex);
//        }
//    
//    }
}
